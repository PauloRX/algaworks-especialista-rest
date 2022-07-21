package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import com.algaworks.algafoodapi.domain.model.Permissao;

public interface PermissaoRepository {

	List<Permissao> todas();
	
	Permissao buscarPorId(Long id);
	
	Permissao adicionar(Permissao permissao);
	
	void remover(Permissao permissao);
}
