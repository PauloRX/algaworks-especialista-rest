package com.algaworks.algafoodapi.di.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.notificacao.Notificador;

public class AtivacaoClienteService {

	@Autowired
	private Notificador notificador;

	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
	}

}
