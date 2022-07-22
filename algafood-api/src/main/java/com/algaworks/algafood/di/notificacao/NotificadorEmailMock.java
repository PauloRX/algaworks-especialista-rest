package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.anotacao.TipoNotificador;
import com.algaworks.algafood.di.model.Cliente;
import com.algaworks.algafood.notificacao.enumerador.NivelUrgencia;

@Profile("dev")
@TipoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmailMock implements Notificador {

	@Autowired
	private NotificadorProperties properties;
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("MOCK: Notificacao seria enviada para %s atraves do e-mail %s - servidor %s - porta %d: %s \n", 
				cliente.getNome(), 
				cliente.getEmail(),
				properties.getHostServer(),
				properties.getPortServer(),
				mensagem);
	}

}
