package com.algaworks.algafood.jpa.restaurante;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.infrastructure.repository.RestauranteRepositoryImpl;

public class BuscaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepositoryImpl restaurantes = applicationContext.getBean(RestauranteRepositoryImpl.class);
		
		List<Restaurante> todosRestaurantes = restaurantes.todos();
		
		todosRestaurantes.forEach(r -> System.out.printf("%s - %2f - %s\n", r.getNome(), r.getTaxaFrete(), r.getCozinha().getNome()));

	}

}
