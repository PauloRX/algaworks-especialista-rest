package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	
	InputStream recuperar(String nomeArquivo);

	void armazenar(NovaFoto novafoto);
	
	void remover(String nomeArquivo	);
	
	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}
	
	default void substituir(NovaFoto novaFoto, String nomeAntigo) {
		this.armazenar(novaFoto);
		if (nomeAntigo != null) {
			this.remover(nomeAntigo);
		}
	}

	@Getter
	@Builder
	public class NovaFoto {
		private InputStream inputStream;
		private String filename;
	}
	
}
