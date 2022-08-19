package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.FormaPagamentoDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDTOAssembler {

	@Autowired
	private ModelMapper mapper;

	public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {
		return mapper.map(formaPagamento, FormaPagamentoDTO.class);
	}
	
	public List<FormaPagamentoDTO> toCollectionModel(List<FormaPagamento> formasPagamento) {
		return formasPagamento.stream()
				.map(formaPagamento -> 
					this.toModel(formaPagamento))
						.collect(Collectors.toList());
	}

	
}
