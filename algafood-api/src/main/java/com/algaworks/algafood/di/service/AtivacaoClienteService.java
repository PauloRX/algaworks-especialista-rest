package com.algaworks.algafood.di.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.eventos.ClienteAtivadoEvent;
import com.algaworks.algafood.di.model.Cliente;

@Component
public class AtivacaoClienteService {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public void ativar(Cliente cliente) {
		cliente.ativar();
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
	}

	@PostConstruct
	public void init() { System.out.println("INIT"); }
	
	@PreDestroy
	public void destroy() { System.out.println("DESTROY"); }
	
}
