package com.algaworks.algafoodapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafoodapi.di.notificacao.NotificadorEmail;
import com.algaworks.algafoodapi.di.notificacao.NotificadorSMS;

@Configuration
public class NotificadorConfig {

	@Bean
    NotificadorEmail notificadorEmail() {
        NotificadorEmail notificador = new NotificadorEmail("smtp.algamail.com.br");
        notificador.setCaixaAlta(true);
        return notificador;
    }
	
	@Bean
    NotificadorSMS notificadorSMS() {
        NotificadorSMS notificador = new NotificadorSMS();
        notificador.setCaixaAlta(false);
        return notificador;
    }
	
}
