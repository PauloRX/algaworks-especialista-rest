package com.algaworks.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 2534601478596953912L;

	public PedidoNaoEncontradoException(String codigoPedido) {
		super(String.format("Nao foi encontrado pedido com o codigo %s", codigoPedido));
	}
	
}
