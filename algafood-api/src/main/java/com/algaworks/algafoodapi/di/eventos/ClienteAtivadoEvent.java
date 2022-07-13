package com.algaworks.algafoodapi.di.eventos;

import com.algaworks.algafoodapi.di.modelo.Cliente;

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
