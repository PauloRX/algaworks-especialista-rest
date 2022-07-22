package com.algaworks.algafood.di.eventos;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalListener {

	@EventListener
	public void emissaoNotaFiscal(ClienteAtivadoEvent event) {
		System.out.println("Emitindo nota fiscar para o cliente " + event.getCliente().getNome());
	}
	
}
