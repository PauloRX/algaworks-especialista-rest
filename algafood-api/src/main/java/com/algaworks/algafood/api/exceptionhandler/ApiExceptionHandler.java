package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	private ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
				
	}
	
	@ExceptionHandler(NegocioException.class)
	private ResponseEntity<?> tratarNegocioException(NegocioException e) {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	private ResponseEntity<?> tratarHttpMediaTypeNotSupportedException() {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem("Tipo de midia (Content-Type) nao suportada")
				.build();
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problema);
	}
	
}
