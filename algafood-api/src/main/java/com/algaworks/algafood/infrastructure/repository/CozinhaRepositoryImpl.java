package com.algaworks.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Cozinha> todas() {
		return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	
	@Override
	public Cozinha buscarPorId(Long id) {
		return entityManager.find(Cozinha.class, id);
	}
	
	@Override
	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return entityManager.merge(cozinha);
	}
	
	@Override
	@Transactional
	public void remover(Cozinha cozinha) {
		cozinha = buscarPorId(cozinha.getId());
		entityManager.remove(cozinha);
	}
}