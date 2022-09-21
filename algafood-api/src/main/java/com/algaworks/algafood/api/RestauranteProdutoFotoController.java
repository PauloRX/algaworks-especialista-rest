package com.algaworks.algafood.api;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}")
public class RestauranteProdutoFotoController {
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestParam MultipartFile arquivo) {
		var nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
		var arquivoFoto = Path.of("/Users/pauloxavier/Pictures/catalogo", nomeArquivo);
		
		
		System.out.println(nomeArquivo);
		System.out.println(arquivo.getContentType());
		
		try {
			arquivo.transferTo(arquivoFoto);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
