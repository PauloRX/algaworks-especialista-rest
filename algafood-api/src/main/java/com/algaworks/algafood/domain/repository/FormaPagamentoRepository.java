package com.algaworks.algafoodapi.domain.repository;

import java.util.List;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> todas();
	
	FormaPagamento buscarPorId(Long id);
	
	FormaPagamento adicionar(FormaPagamento formaPagamento);
	
	void remover(FormaPagamento formaPagamento);
	
}
