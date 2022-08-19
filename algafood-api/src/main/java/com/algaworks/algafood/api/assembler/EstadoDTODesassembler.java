package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.input.EstadoInputDTO;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoDTODesassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Estado toDomainObject(EstadoInputDTO estadoInput) {
		return mapper.map(estadoInput, Estado.class);
	}
	
	public void copyToDomainObject(EstadoInputDTO estadoInput, Estado estado) {
		mapper.map(estadoInput, estado);
	}
	
}
