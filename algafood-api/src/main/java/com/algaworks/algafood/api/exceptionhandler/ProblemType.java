package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	RECURSO_NAO_ENCONTRADO("Recurso Nao Encontrado", "/recurso-nao-encontrado"),
	ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
	NEGOCIO("Violacao de Regra de Negocio", "/excecao-de-negocio"),
	MENSAGEM_INCOMPREENSIVEL("Mensagem Incompreensivel", "/mensagem-incompreensivel"), 
	PROPRIEDADE_INVALIDA("Propriedade Invalida", "/propriedade-invalida"),
	PARAMETRO_INVALIDO("Parametro Invalido", "/parametro-invalido");
	
	private String title;
	private String uri;
	
	private ProblemType(String title, String path) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
	
	
}
