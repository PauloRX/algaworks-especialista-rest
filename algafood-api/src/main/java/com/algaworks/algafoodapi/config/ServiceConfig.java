package com.algaworks.algafoodapi.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafoodapi.di.notificacao.Notificador;
import com.algaworks.algafoodapi.di.service.AtivacaoClienteService;

@Configuration
public class ServiceConfig {

    @Bean
    AtivacaoClienteService ativacaoClienteService(List<Notificador> notificadores) {
        return new AtivacaoClienteService(notificadores);
    }
	
}
