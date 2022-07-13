package com.algaworks.algafoodapi.di.notificacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.anotacao.TipoNotificador;
import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.notificacao.enumerador.NivelUrgencia;

@Profile("dev")
@TipoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmailMock implements Notificador {

	@Value("${notificador.email.host.server}")
	private String servidorSMTP;
	
	@Value("${notificador.email.port.server}")
	private Integer servidorPort;
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("MOCK: Notificacao seria enviada para %s atraves do e-mail %s - servidor %s - porta %d: %s \n", 
				cliente.getNome(), 
				cliente.getEmail(),
				this.servidorSMTP,
				this.servidorPort,
				mensagem);
	}

}
