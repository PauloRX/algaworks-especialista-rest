package com.algaworks.algafood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDesassembler {
	
	@Autowired
	ModelMapper mapper;

	public Restaurante toDomainObject(@Valid RestauranteInput restauranteInput) {

		return mapper.map(restauranteInput, Restaurante.class);

	}
	
	public void copyToDomainObject(RestauranteInput input, Restaurante restaurante) {
		restaurante.setCozinha(new Cozinha());
		
		if (restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());;
		}
		mapper.map(input, restaurante);
	}
	
}
