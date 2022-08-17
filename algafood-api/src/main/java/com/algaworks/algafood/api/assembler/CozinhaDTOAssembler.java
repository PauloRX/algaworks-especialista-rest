package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.CozinhaDTO;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDTOAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public CozinhaDTO toModel(Cozinha cozinha) {

		return mapper.map(cozinha, CozinhaDTO.class);

	}
	
	public List<CozinhaDTO> toCollectionModel(List<Cozinha> cozinhas) {
		return cozinhas.stream()
				.map(cozinha -> 
					this.toModel(cozinha))
						.collect(Collectors.toList());
	}
	
}
