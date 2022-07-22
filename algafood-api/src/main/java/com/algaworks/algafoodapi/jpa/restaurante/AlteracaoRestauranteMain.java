package com.algaworks.algafoodapi.jpa.restaurante;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.infrastructure.repository.RestauranteRepositoryImpl;

public class AlteracaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepositoryImpl restaurantes = applicationContext.getBean(RestauranteRepositoryImpl.class);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Bond Bico");
		restaurante1.setTaxaFrete(new BigDecimal("10.0"));
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Rubaiyat Figueira");
		restaurante2.setTaxaFrete(new BigDecimal("15.0"));
		
		restaurantes.adicionar(restaurante1);
		restaurantes.adicionar(restaurante2);
		

	}

}
