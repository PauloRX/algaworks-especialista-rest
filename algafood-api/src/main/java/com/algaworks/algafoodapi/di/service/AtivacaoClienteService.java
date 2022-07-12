package com.algaworks.algafoodapi.di.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.notificacao.Notificador;

public class AtivacaoClienteService {
	
	@Autowired
	private List<Notificador> notificadores;
	
	public AtivacaoClienteService(List<Notificador> notificadores) {
		this.notificadores = notificadores;
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();
		for (Notificador notificador : notificadores) {
			notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");			
		}
	}

}
