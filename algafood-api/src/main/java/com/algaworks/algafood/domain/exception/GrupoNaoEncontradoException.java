package com.algaworks.algafood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 7879971223614555633L;

	public GrupoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public GrupoNaoEncontradoException(Long grupoId) {
		this(String.format("Nao foi encontrado grupo com o codigo %d", grupoId));
	}

}
