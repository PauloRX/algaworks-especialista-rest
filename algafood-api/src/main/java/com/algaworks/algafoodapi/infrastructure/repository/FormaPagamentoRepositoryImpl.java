package com.algaworks.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<FormaPagamento> todas() {
		return entityManager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
	}
	
	@Override
	public FormaPagamento buscarPorId(Long id) {
		return entityManager.find(FormaPagamento.class, id);
	}
	
	@Override
	@Transactional
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		return entityManager.merge(formaPagamento);
	}
	
	@Override
	@Transactional
	public void remover(FormaPagamento formaPagamento) {
		formaPagamento = buscarPorId(formaPagamento.getId());
		entityManager.remove(formaPagamento);
	}
}