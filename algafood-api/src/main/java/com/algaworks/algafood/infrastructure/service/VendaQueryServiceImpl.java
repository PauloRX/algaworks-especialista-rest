package com.algaworks.algafood.infrastructure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendasQueryService;

@Repository
public class VendaQueryServiceImpl implements VendasQueryService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter, String timeOffset) {
		var builder = entityManager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);
		
		var functionConvertTzDataCriacao = builder.function(
				"convert_tz", 
				Date.class, 
				root.get("dataCriacao"), 
				builder.literal("+00:00"), 
				builder.literal(timeOffset));
		
		var functionDateDataCriacao = builder.function(
				"date", 
				Date.class, 
				functionConvertTzDataCriacao);
		
		
		var selection = builder.construct(VendaDiaria.class, functionDateDataCriacao, builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		var predicates = new ArrayList<Predicate>();

		if (filter.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
		}
		if (filter.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoInicio()));
		}
		if (filter.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoFim()));
		}

		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

		query.select(selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(functionDateDataCriacao);

		return entityManager.createQuery(query).getResultList();
	}

}
