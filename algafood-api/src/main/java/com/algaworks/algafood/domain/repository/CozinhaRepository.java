package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import com.algaworks.algafoodapi.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> todas();
	
	Cozinha buscarPorId(Long id);
	
	Cozinha adicionar(Cozinha cozinha);
	
	void remover(Cozinha cozinha);
}
