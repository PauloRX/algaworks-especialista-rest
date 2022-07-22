package com.algaworks.algafood.anotacao;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import org.springframework.beans.factory.annotation.Qualifier;

import com.algaworks.algafood.notificacao.enumerador.NivelUrgencia;

@Retention(RUNTIME)
@Qualifier
public @interface TipoNotificador {
	
	NivelUrgencia value();

}
