package com.algaworks.algafood.domain.service;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	void armazenar(NovaFoto novafoto);
	
	@Getter
	@Builder
	public class NovaFoto {
		private InputStream inputStream;
		private String filename;
	}
	
}
