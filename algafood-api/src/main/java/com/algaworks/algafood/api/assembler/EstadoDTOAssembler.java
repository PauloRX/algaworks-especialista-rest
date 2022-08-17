package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.EstadoDTO;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoDTOAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public EstadoDTO toModel(Estado estado) {
		return mapper.map(estado, EstadoDTO.class);
	}
	
	public List<EstadoDTO> toCollectionModel(List<Estado> estados) {
		return estados.stream()
				.map(estado -> 
					this.toModel(estado))
						.collect(Collectors.toList());
	}
}
