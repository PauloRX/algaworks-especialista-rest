package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String USR_FINAL_MESSAGE = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema";

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	private ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {
		Problem problem = createProblemBuilder(HttpStatus.CONFLICT, ProblemType.ENTIDADE_EM_USO, ex.getMessage())
				.userMessage("Esse recurso nao pode sofrer alteracao porque esta em uso")
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	private ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex,
			WebRequest request) {
		Problem problem = createProblemBuilder(HttpStatus.NOT_FOUND, ProblemType.RECURSO_NAO_ENCONTRADO,
				ex.getMessage())
				.userMessage(ex.getMessage())
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(NegocioException.class)
	private ResponseEntity<?> handleNegocio(NegocioException ex, WebRequest request) {
		Problem problem = createProblemBuilder(HttpStatus.BAD_REQUEST, ProblemType.NEGOCIO, ex.getMessage())
				.userMessage(ex.getMessage())
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<Object> handleUncaughtException(Exception ex, WebRequest request) {
		String detail = USR_FINAL_MESSAGE;
		Problem problem = createProblemBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ProblemType.ERRO_DE_SISTEMA, detail)
				.userMessage(USR_FINAL_MESSAGE)
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Problem.builder().status(status.value()).detail(status.getReasonPhrase())
					.userMessage(USR_FINAL_MESSAGE)
					.timestamp(LocalDateTime.now())
					.build();
		} else if (body instanceof String) {
			body = Problem.builder().status(status.value()).detail((String) body)
					.userMessage((String) body)
					.timestamp(LocalDateTime.now())
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);

		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
		}

		String detail = "O corpo da requisacao esta invalido. Verifique a sintaxe.";
		Problem problem = createProblemBuilder(status, ProblemType.MENSAGEM_INCOMPREENSIVEL, detail)
				.userMessage(USR_FINAL_MESSAGE)
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String detail = "Um ou mais campos estao invalidos. faca o preenchimento correto e tente novamente.";
		
		BindingResult bindingResult = ex.getBindingResult();
		
		List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
				.map(objectError -> {
					
					String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
					String name = objectError.getObjectName();
					if (objectError instanceof FieldError) {
						name = ((FieldError) objectError).getField();
					}
					return Problem.Object.builder()
						.name(name)
						.userMessage(message)
						.build(); 
				})
				.collect(Collectors.toList());
				
		Problem problem = createProblemBuilder(status, ProblemType.DADOS_INVALIDOS, detail)
				.userMessage(detail)
				.objects(problemObjects)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String detail = String.format("O recurso '%s' que voce tentou acessar e inexistente.", ex.getRequestURL());
		Problem problem = createProblemBuilder(status, ProblemType.RECURSO_NAO_ENCONTRADO, detail)
				.userMessage(detail)
				.build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

	    String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
	            ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
	    
	    Problem problem = createProblemBuilder(status, ProblemType.PARAMETRO_INVALIDO, detail)
	    		.userMessage(USR_FINAL_MESSAGE)
	    		.build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String detail = StringUtils.EMPTY;
		String path = joinPath(ex.getPath());

		detail = String.format(
				"A propriedade '%s' e invalida. Corrija e informe apenas as chaves do json especificadas no contrato",
				path);

		Problem problem = createProblemBuilder(status, ProblemType.PROPRIEDADE_INVALIDA, detail)
				.userMessage(USR_FINAL_MESSAGE)
				.build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath());
		String detail = String.format(
				"A propriedade '%s' recebeu valor '%s' que e um tipo invalido. Corrija e informe um valor compativel com o tipo %s",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		Problem problem = createProblemBuilder(status, ProblemType.MENSAGEM_INCOMPREENSIVEL, detail)
				.userMessage(USR_FINAL_MESSAGE)
				.build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder().status(status.value()).title(problemType.getTitle()).type(problemType.getUri())
				.detail(detail).timestamp(LocalDateTime.now());
	}

	private String joinPath(List<Reference> references) {
		return references.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
	}

}
