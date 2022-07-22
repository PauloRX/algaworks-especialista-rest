package com.algaworks.algafood.di.eventos;

import com.algaworks.algafood.di.model.Cliente;

public class ClienteAtivadoEvent {

	private Cliente cliente;
	
	public ClienteAtivadoEvent(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

}
