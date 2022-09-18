package com.algaworks.algafood.domain.infrastructure.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.dto.VendaDiaria;

public class VendaDiariaSpecs {

	public static Specification<VendaDiaria> usandoFiltro() {
		return (root, query, builder) -> {
			
			System.out.println(query.getResultType());

			var predicates = new ArrayList<Predicate>();

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
