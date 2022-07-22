package com.algaworks.algafood.di.notificacao;

import com.algaworks.algafood.di.model.Cliente;

public class NotificadorSMS implements Notificador {

	private boolean caixaAlta;
	
	public NotificadorSMS() {
		System.out.println("Construtor NotificadorSMS chamado");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		if(this.caixaAlta) { mensagem = mensagem.toUpperCase(); }
		System.out.printf("Notificando %s por SMS do telefone %s: %s \n", 
				cliente.getNome(), 
				cliente.getTelefone(),
				mensagem);
	}

	public void setCaixaAlta(boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}

}
