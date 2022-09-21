package com.algaworks.algafood.api;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.input.FotoProdutoInput;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}")
public class RestauranteProdutoFotoController {
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInput input) {
		var nomeArquivo = UUID.randomUUID() + "_" + input.getArquivo().getOriginalFilename();
		var arquivoFoto = Path.of("/Users/pauloxavier/Pictures/catalogo", nomeArquivo);
		
		
		System.out.println(input.getDescricao());
		System.out.println(nomeArquivo);
		System.out.println(input.getArquivo().getContentType());
		
		try {
			input.getArquivo().transferTo(arquivoFoto);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
