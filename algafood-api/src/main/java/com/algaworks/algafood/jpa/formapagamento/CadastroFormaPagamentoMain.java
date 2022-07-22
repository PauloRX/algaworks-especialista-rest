package com.algaworks.algafoodapi.jpa.formapagamento;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.infrastructure.repository.FormaPagamentoRepositoryImpl;

public class CadastroFormaPagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepositoryImpl formasPagamentos = applicationContext.getBean(FormaPagamentoRepositoryImpl.class);
		
		List<FormaPagamento> todasFormaPagamentos = formasPagamentos.todas();
		todasFormaPagamentos.forEach(f -> System.out.println(f.getDescricao()));

	}

}
