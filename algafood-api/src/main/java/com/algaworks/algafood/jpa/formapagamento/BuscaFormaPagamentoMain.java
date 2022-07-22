package com.algaworks.algafood.jpa.formapagamento;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.infrastructure.repository.FormaPagamentoRepositoryImpl;

public class BuscaFormaPagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepositoryImpl restaurantes = applicationContext.getBean(FormaPagamentoRepositoryImpl.class);
		
		List<FormaPagamento> formasPagamentos = restaurantes.todas();
		
		formasPagamentos.forEach(r -> System.out.printf("%d - %s\n", r.getId(), r.getDescricao()));

	}

}
