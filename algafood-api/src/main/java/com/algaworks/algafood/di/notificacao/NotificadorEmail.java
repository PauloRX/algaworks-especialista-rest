package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.anotacao.TipoNotificador;
import com.algaworks.algafood.di.model.Cliente;
import com.algaworks.algafood.notificacao.enumerador.NivelUrgencia;

@Profile("prod")
@TipoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

	@Autowired
	private NotificadorProperties properties;
	
	public NotificadorEmail() {
		System.out.println("Construtor NotificadorEmail chamado");
	}

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s atraves do e-mail %s - servidor %s - porta %s: %s \n", 
				cliente.getNome(), 
				cliente.getEmail(),
				properties.getHostServer(),
				properties.getPortServer(),
				mensagem);
	}

}
