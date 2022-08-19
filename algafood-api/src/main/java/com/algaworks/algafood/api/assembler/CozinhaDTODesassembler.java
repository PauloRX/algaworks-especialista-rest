package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.input.CozinhaInputDTO;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDTODesassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Cozinha toDomainObject(CozinhaInputDTO cozinhaInput) {
		return mapper.map(cozinhaInput, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaInputDTO cozinhaInput, Cozinha cozinha) {
		mapper.map(cozinhaInput, cozinha);
	}
	
}
