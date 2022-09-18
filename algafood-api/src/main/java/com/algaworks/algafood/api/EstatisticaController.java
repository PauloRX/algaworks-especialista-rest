package com.algaworks.algafood.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendasQueryService;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticaController {
	
	@Autowired
	private VendasQueryService queryService;
	
	@GetMapping("/vendas-diarias")
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter){
		
		return queryService.consultarVendasDiarias(filter);
	}

}
