package com.algaworks.algafoodapi.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.notificacao.enumerador.NivelUrgencia;

@Profile("dev")
@TipoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmailMock implements Notificador {

	private final String servidorSMTP = "smtp.algamail.com.br";
	
	public NotificadorEmailMock() {
		System.out.println("Construtor NotificadorEmail chamado");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("MOCK: Notificacao seria enviada para %s atraves do e-mail %s - servidor %s: %s \n", 
				cliente.getNome(), 
				cliente.getEmail(),
				this.servidorSMTP,
				mensagem);
	}

}
