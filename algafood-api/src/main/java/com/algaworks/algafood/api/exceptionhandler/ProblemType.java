package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	ENTIDADE_NAO_ENCONTRADA("Entidade Nao Encontrada", "/entidade-nao-encontrada");
	
	private String title;
	private String uri;
	
	private ProblemType(String title, String path) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
	
	
}
