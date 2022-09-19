package com.algaworks.algafood.infrastructure.service.report;

import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.service.VendasReportService;

@Service
public class PdfVendasReportService implements VendasReportService {

	@Override
	public byte[] emitirRelatorioPdf(VendaDiariaFilter filter, String timeOffset) {
		// TODO Auto-generated method stub
		return null;
	}

}
