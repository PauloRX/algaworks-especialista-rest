package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl {

	@Autowired
	private EntityManager manager;

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

		var jpql = new StringBuilder();
		jpql.append("FROM Restaurante WHERE 0 = 0 " );
		var parametros = new HashMap<String, Object>();
		
		if (StringUtils.hasLength(nome)) {
			jpql.append("AND nome LIKE :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		if (taxaFreteInicial != null) {
			jpql.append("AND taxaFrete >= :taxaFreteInicial ");
			parametros.put("taxaFreteInicial", taxaFreteInicial);
		}
		if (taxaFreteFinal != null) {
			jpql.append("AND taxaFrete <= :taxaFreteFinal ");
			parametros.put("taxaFreteFinal", taxaFreteFinal);
		}

		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		
		return query.getResultList();
	
	}

}
