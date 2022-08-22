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

import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoInputDesassembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
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
	private FormaPagamentoModelAssembler assembler;
	
	@Autowired
	private FormaPagamentoInputDesassembler desassembler;
	
	@GetMapping
	public List<FormaPagamentoModel> listar() {
		return assembler.toCollectionModel(repository.findAll());
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoModel buscarPorId(@PathVariable Long formaPagamentoId) {
		return assembler.toModel(cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = desassembler.toDomainObject(formaPagamentoInput);
		return assembler.toModel(cadastroFormaPagamento.salvar(formaPagamento));
	}
	
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamentoInput formaPagamentoInput) {
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
