package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	ENTIDADE_NAO_ENCONTRADA("Entidade Nao Encontrada", "/entidade-nao-encontrada"),
	ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
	NEGOCIO("Violacao de Regra de Negocio", "/excecao-de-negocio");
	
	private String title;
	private String uri;
	
	private ProblemType(String title, String path) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
	
	
}
