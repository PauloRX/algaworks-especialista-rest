package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import com.algaworks.algafoodapi.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> todos();
	
	Restaurante buscarPorId(Long id);
	
	Restaurante adicionar(Restaurante restaurante);
	
	void remover(Restaurante restaurante);
	
}
