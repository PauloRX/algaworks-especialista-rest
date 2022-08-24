package com.algaworks.algafood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioInputDesassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Usuario toDomainModel(@Valid UsuarioInput input) {
		return mapper.map(input, Usuario.class);
	}

	public void copyToObjectModel(@Valid UsuarioInput input, Usuario usuario) {
		mapper.map(input, usuario);
	}

	public void copyInputSenhaToObjectModel(@Valid UsuarioComSenhaInput inputSenha, Usuario usuario) {
		mapper.map(inputSenha, usuario);
		
	}

}
