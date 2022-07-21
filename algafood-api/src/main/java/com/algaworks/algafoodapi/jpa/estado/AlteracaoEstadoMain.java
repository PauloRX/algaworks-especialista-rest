package com.algaworks.algafoodapi.jpa.estado;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.infrastructure.repository.EstadoRepositoryImpl;

public class AlteracaoEstadoMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepositoryImpl estados = applicationContext.getBean(EstadoRepositoryImpl.class);
		
		List<Estado> todosEstados = estados.todos();
		
		todosEstados.forEach(e -> System.out.println(e.getNome()));
		
	}

}