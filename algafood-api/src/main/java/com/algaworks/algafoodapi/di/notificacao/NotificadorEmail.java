package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.modelo.Cliente;

public class NotificadorEmail implements Notificador {

	private boolean caixaAlta;
	private final String servidorSMTP;
	
	public NotificadorEmail(String servidorSMTP) {
		this.servidorSMTP = servidorSMTP;
		System.out.println("Construtor NotificadorEmail chamado");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		if(this.caixaAlta) { mensagem = mensagem.toUpperCase(); }
		System.out.printf("Notificando %s atraves do e-mail %s: %s \n", 
				cliente.getNome(), 
				cliente.getEmail(),
				mensagem);
	}

	public void setCaixaAlta(boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}

}
