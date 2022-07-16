package com.algaworks.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Restaurante> todos() {
		return entityManager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscarPorId(Long id) {
		return entityManager.find(Restaurante.class, id);
	}

	@Override
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		return entityManager.merge(restaurante);
	}

	@Override
	@Transactional
	public void remover(Restaurante restaurante) {
		restaurante = buscarPorId(restaurante.getId());
		entityManager.remove(restaurante);

	}

}
