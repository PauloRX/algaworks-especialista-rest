package com.algaworks.algafood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;

@Component
public class ProdutonputDesassembler {
	
	@Autowired
	ModelMapper mapper;

	public Produto toDomainObject(@Valid ProdutoInput produtoInput) {

		return mapper.map(produtoInput, Produto.class);

	}
	
	public void copyToDomainObject(ProdutoInput input, Produto produto) {
		mapper.map(input, produto);
	}
	
}
