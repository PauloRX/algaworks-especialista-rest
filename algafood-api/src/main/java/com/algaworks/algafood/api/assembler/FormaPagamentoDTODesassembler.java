package com.algaworks.algafood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.input.FormaPagamentoInputDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDTODesassembler {

	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamento toDomainObject(@Valid FormaPagamentoInputDTO formaPagamentoInput) {
		return mapper.map(formaPagamentoInput, FormaPagamento.class);
	}

	public void copyToDomainObject(FormaPagamentoInputDTO formaPagamentoInput, FormaPagamento formaPagamento) {
		mapper.map(formaPagamentoInput, formaPagamento);
	}

}
