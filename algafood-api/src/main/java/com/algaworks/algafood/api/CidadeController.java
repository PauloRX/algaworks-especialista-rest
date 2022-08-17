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

import com.algaworks.algafood.api.assembler.CidadeDTOAssembler;
import com.algaworks.algafood.api.assembler.CidadeDTODesassembler;
import com.algaworks.algafood.api.dto.CidadeDTO;
import com.algaworks.algafood.api.model.input.CidadeInputDTO;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeDTOAssembler cidadeAssembler;
	
	@Autowired
	private CidadeDTODesassembler cidadeDesassembler;
	
	@GetMapping
	public List<CidadeDTO> listar() {
		return cidadeAssembler.toCollectionDTO(cidadeRepository.findAll());
	}

	@GetMapping(value =  "/{cidadeId}", consumes = {"application/json"}, produces = {"application/json"})
	public CidadeDTO buscarPorId(@PathVariable Long cidadeId) {
		return cidadeAssembler.toModel(cadastroCidade.buscarOuFalhar(cidadeId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeDTO adicionar(@RequestBody @Valid CidadeInputDTO cidadeInput) {
		try {
			Cidade cidade = cidadeDesassembler.toDomainObject(cidadeInput);
			return cidadeAssembler.toModel(cadastroCidade.salvar(cidade));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInputDTO cidadeInput) {
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
		cidadeDesassembler.copyToDomainObject(cidadeInput, cidadeAtual);
		
		cidadeAtual = cadastroCidade.salvar(cidadeAtual);
		try {
			return cidadeAssembler.toModel(cidadeAtual);
		} catch (CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}

}