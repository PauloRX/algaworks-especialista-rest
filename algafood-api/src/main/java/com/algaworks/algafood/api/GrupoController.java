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

import com.algaworks.algafood.api.assembler.GrupoInputDesassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoRepository repository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private GrupoModelAssembler assembler;

	@Autowired
	private GrupoInputDesassembler desassembler;
	
	@GetMapping
	public List<GrupoModel> listar(){
		return assembler.toCollectionModel(repository.findAll());
	}
	
	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable Long grupoId) {
		return assembler.toModel(cadastroGrupo.buscarOuFalhar(grupoId));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput input) {
		Grupo grupo = desassembler.toDomainObject(input);
		return assembler.toModel(cadastroGrupo.salvar(grupo));			
	}
	
	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput input) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		desassembler.copyToDomainObject(input, grupo);
		return assembler.toModel(cadastroGrupo.salvar(grupo));
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupo.excluir(grupoId);
	}

}
