package com.algaworks.algafood.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

	private String cep;
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	private CidadeResumoDTO cidade;
	
}
