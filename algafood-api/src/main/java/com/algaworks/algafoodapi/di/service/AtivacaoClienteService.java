package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.notificacao.NotificadorEmail;

public class AtivacaoClienteService {
	
	private NotificadorEmail notificador;
	
	public void ativat(Cliente cliente) {
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
	}

}
