package com.algaworks.algafood.api;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}")
public class RestauranteProdutoFotoController {
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@Autowired
	private CadastroProdutoService cadastroProduto;
	
	@Autowired
	private FotoProdutoModelAssembler produtoModelAssembler;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInput input) throws IOException {
		
		MultipartFile arquivo = input.getArquivo();
		
		Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
		
		FotoProduto fotoProduto = FotoProduto.builder()
				.produto(produto)
				.descricao(input.getDescricao())
				.nomeArquivo(arquivo.getName())
				.contentType(arquivo.getContentType())
				.tamanho(arquivo.getSize())
				.nomeArquivo(arquivo.getOriginalFilename())
				.build();

		FotoProduto fotoSalva = catalogoFotoProduto.salvar(fotoProduto, arquivo.getInputStream());
		
		return produtoModelAssembler.toModel(fotoSalva);

	}

}
