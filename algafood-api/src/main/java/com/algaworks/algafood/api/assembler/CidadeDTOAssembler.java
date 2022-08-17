package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.CidadeDTO;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeDTOAssembler {
	
	@Autowired
	ModelMapper mapper;

	public CidadeDTO toModel(Cidade cidade) {

		return mapper.map(cidade, CidadeDTO.class);

	}

	public List<CidadeDTO> toCollectionDTO(List<Cidade> cidades) {

		return cidades.stream().map(cidade -> 
				this.toModel(cidade))
				.collect(Collectors.toList());

	}
	
}
