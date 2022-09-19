package com.algaworks.algafood.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendasQueryService;
import com.algaworks.algafood.domain.service.VendasReportService;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticaController {
	
	@Autowired
	private VendasQueryService vendasQueryService;
	
	@Autowired
	private VendasReportService vendasReportService;
	
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter, 
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
		
		return vendasQueryService.consultarVendasDiarias(filter, timeOffset);
	}
	
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filter, 
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
		
		byte[] bytesPdf = vendasReportService.emitirRelatorioPdf(filter, timeOffset);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
		
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
	}

}
