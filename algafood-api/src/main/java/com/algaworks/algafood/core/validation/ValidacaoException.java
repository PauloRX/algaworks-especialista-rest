package com.algaworks.algafood.core.validation;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = -4603570011553105971L;
	
	private BindingResult bindingResult;

}
