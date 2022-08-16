package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.RestauranteDTO;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteDTOAssembler {
	
	@Autowired
	ModelMapper mapper;

	public RestauranteDTO toModel(Restaurante restaurante) {

		return mapper.map(restaurante, RestauranteDTO.class);

	}

	public List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {

		return restaurantes.stream().map(restaurante -> 
				this.toModel(restaurante))
				.collect(Collectors.toList());

	}
}
