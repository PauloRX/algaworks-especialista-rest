package com.algaworks.algafood.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.service.VendasQueryService;
import com.algaworks.algafood.domain.service.VendasReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfVendasReportService implements VendasReportService {

	@Value("/relatorios/vendas-diarias.jasper")
	private String reportPath;

	@Autowired
	private VendasQueryService vendasQueryService;

	@Override
	public byte[] emitirRelatorioPdf(VendaDiariaFilter filter, String timeOffset) {

		try {
			var inputStream = this.getClass().getResourceAsStream(reportPath);
			var parameters = new HashMap<String, Object>();
			parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

			var vendasDiarias = vendasQueryService.consultarVendasDiarias(filter, timeOffset);
			var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

			var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Nao foi possivel emitir o relatorio", e);
		}
	}

}
