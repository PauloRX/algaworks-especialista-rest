package com.algaworks.algafood.di.eventos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.anotacao.TipoNotificador;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.notificacao.enumerador.NivelUrgencia;

@Component
public class NotificacaoListener {

	@TipoNotificador(NivelUrgencia.NORMAL)
	@Autowired
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema esta ativo!");
	}
	
}
