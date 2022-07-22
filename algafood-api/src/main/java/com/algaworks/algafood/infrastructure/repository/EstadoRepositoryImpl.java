package com.algaworks.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Estado> todos() {
		return entityManager.createQuery("from Estado", Estado.class).getResultList();
	}
	
	@Override
	public Estado buscarPorId(Long id) {
		return entityManager.find(Estado.class, id);
	}
	
	@Override
	@Transactional
	public Estado adicionar(Estado estado) {
		return entityManager.merge(estado);
	}
	
	@Override
	@Transactional
	public void remover(Estado estado) {
		estado = buscarPorId(estado.getId());
		entityManager.remove(estado);
	}
}