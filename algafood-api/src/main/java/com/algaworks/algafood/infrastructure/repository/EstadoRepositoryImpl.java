package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

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
	public Estado salvar(Estado estado) {
		return entityManager.merge(estado);
	}
	
	@Override
	@Transactional
	public void remover(Long estadoId) {
		Estado estado = buscarPorId(estadoId);
		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		entityManager.remove(estadoId);
	}
}