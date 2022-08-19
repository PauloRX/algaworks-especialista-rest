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

import com.algaworks.algafood.api.assembler.FormaPagamentoDTOAssembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoDTODesassembler;
import com.algaworks.algafood.api.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.dto.input.FormaPagamentoInputDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formaspagamento")
public class FormaPagamentoController {
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@Autowired
	private FormaPagamentoRepository repository;
	
	@Autowired
	private FormaPagamentoDTOAssembler assembler;
	
	@Autowired
	private FormaPagamentoDTODesassembler desassembler;
	
	@GetMapping
	public List<FormaPagamentoDTO> listar() {
		return assembler.toCollectionModel(repository.findAll());
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoDTO buscarPorId(@PathVariable Long formaPagamentoId) {
		return assembler.toModel(cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInput) {
		FormaPagamento formaPagamento = desassembler.toDomainObject(formaPagamentoInput);
		return assembler.toModel(cadastroFormaPagamento.salvar(formaPagamento));
	}
	
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamentoInputDTO formaPagamentoInput) {
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		desassembler.copyToDomainObject(formaPagamentoInput, formaPagamento);
		return assembler.toModel(cadastroFormaPagamento.salvar(formaPagamento));
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		cadastroFormaPagamento.excluir(formaPagamentoId);
	}

}
