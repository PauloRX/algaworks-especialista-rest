package com.algaworks.algafoodapi.jpa.formapagamento;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.infrastructure.repository.FormaPagamentoRepositoryImpl;

public class ExclusaoFormaPagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepositoryImpl restaurantes = applicationContext.getBean(FormaPagamentoRepositoryImpl.class);
		
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setId(1L);
		
		restaurantes.remover(formaPagamento);

	}

}
