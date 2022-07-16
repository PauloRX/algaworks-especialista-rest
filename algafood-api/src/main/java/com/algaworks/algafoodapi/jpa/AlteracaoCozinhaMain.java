package com.algaworks.algafoodapi.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.infrastructure.repository.CozinhaRepositoryImpl;

public class AlteracaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepositoryImpl cozinhas = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		Cozinha brasa = new Cozinha();
		brasa.setNome("Brasileira");
		
		Cozinha japa = new Cozinha();
		japa.setNome("Japonesa");
		
		cozinhas.adicionar(brasa);
		cozinhas.adicionar(japa);
		

	}

}
