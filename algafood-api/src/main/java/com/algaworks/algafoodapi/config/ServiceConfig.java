package com.algaworks.algafoodapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafoodapi.di.notificacao.Notificador;
import com.algaworks.algafoodapi.di.service.AtivacaoClienteService;

@Configuration
public class ServiceConfig {

	@Bean
    AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
        return new AtivacaoClienteService(notificador);
    }
	
}
