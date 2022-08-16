package com.algaworks.algafood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteInputDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteDTODesassembler {
	
	@Autowired
	ModelMapper mapper;

	public Restaurante toDomainObject(@Valid RestauranteInputDTO restauranteInput) {

		return mapper.map(restauranteInput, Restaurante.class);

	}
	
	public void copyToDomainObject(RestauranteInputDTO input, Restaurante restaurante) {
		restaurante.setCozinha(new Cozinha());
		mapper.map(input, restaurante);
	}
	
}
