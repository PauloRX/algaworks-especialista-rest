package com.algaworks.algafood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.CidadeInputDTO;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class CidadeDTODesassembler {
	
	@Autowired
	ModelMapper mapper;

	public Cidade toDomainObject(@Valid CidadeInputDTO cidadeInput) {

		return mapper.map(cidadeInput, Cidade.class);

	}
	
	public void copyToDomainObject(CidadeInputDTO cidadeInput, Cidade cidade) {
		cidade.setEstado(new Estado());
		mapper.map(cidadeInput, cidade);
	}
	
}
