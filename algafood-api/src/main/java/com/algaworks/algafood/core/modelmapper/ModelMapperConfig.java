package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.dto.EnderecoDTO;
import com.algaworks.algafood.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

    	var mapper = new ModelMapper();
    	var enderecoToEnderecoDTOTypeMap = mapper.createTypeMap(Endereco.class, EnderecoDTO.class);
    	
    	enderecoToEnderecoDTOTypeMap.<String>addMapping(
    			src -> src.getCidade().getEstado().getNome(), 
    			(dest, value) -> dest.getCidade().setEstado(value));
    	
    	return mapper;
    
    }

}
