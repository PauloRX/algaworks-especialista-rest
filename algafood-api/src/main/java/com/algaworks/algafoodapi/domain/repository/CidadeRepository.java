package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import com.algaworks.algafoodapi.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> todos();
	
	Cidade buscarPorId(Long id);
	
	Cidade adicionar(Cidade cidade);
	
	void remover(Cidade cidade);
	
}
