package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import com.algaworks.algafoodapi.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> todos();
	
	Estado buscarPorId(Long id);
	
	Estado adicionar(Estado estado);
	
	void remover(Estado estado);
}
