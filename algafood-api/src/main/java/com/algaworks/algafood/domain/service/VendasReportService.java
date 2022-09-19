package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;

public interface VendasReportService {

	byte[] emitirRelatorioPdf(VendaDiariaFilter filter, String timeOffset);

}
