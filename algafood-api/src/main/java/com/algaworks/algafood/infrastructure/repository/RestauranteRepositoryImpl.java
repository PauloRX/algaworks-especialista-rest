package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl {

	@Autowired
	private EntityManager manager;

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if (StringUtils.hasLength(nome)) { predicates.add(builder.like(root.get("nome"), "%"+nome+"%")); }
		if (taxaFreteInicial != null) { predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial)); }
		if (taxaFreteFinal != null) { predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal)); }
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		return manager.createQuery(criteria).getResultList();
	
	}

}
