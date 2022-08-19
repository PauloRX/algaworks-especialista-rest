package com.algaworks.algafood.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.CozinhaDTOAssembler;
import com.algaworks.algafood.api.assembler.CozinhaDTODesassembler;
import com.algaworks.algafood.api.dto.CozinhaDTO;
import com.algaworks.algafood.api.dto.input.CozinhaInputDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;;

	@Autowired
	private CozinhaDTOAssembler cozinhaAssembler;
	
	@Autowired
	private CozinhaDTODesassembler cozinhaDesassembler;
	
	@GetMapping
	public List<CozinhaDTO> listar() {
		return cozinhaAssembler.toCollectionModel(cozinhaRepository.findAll());
	}

	@GetMapping("/{cozinhaId}")
	public CozinhaDTO buscar(@PathVariable Long cozinhaId) {
		return cozinhaAssembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId)); 
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CozinhaDTO adicionar(@RequestBody @Valid CozinhaInputDTO cozinhaInput) {
		Cozinha cozinha = cozinhaDesassembler.toDomainObject(cozinhaInput);
		return cozinhaAssembler.toModel(cadastroCozinha.salvar(cozinha));
	}

	@PutMapping("/{cozinhaId}")
	public CozinhaDTO atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputDTO cozinhaInput) {
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
		cozinhaDesassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		return cozinhaAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));
	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir(cozinhaId);
	}

}