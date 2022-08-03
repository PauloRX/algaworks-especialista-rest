package com.algaworks.algafood.domain.exception;

public class EntidadeEmUsoException extends NegocioException {

	private static final long serialVersionUID = 6164727756406016422L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
	
	public EntidadeEmUsoException(Long id) {
		this(String.format("A entidade codigo %d nao pode ser removida, pois esta em uso", id));
	}

}
