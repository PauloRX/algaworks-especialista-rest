package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	private String cep;
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	private CidadeResumoModel cidade;
	
}
