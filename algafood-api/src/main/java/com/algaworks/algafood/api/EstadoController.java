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

import com.algaworks.algafood.api.assembler.EstadoDTOAssembler;
import com.algaworks.algafood.api.assembler.EstadoDTODesassembler;
import com.algaworks.algafood.api.dto.EstadoDTO;
import com.algaworks.algafood.api.dto.input.EstadoInputDTO;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@Autowired
	private EstadoDTOAssembler estadoAssembler;
	
	@Autowired
	private EstadoDTODesassembler estadoDesassembler;
	
	@GetMapping
	public List<EstadoDTO> listar() {
		return estadoAssembler.toCollectionModel(estadoRepository.findAll());
	}

	@GetMapping("/{estadoId}")
	public EstadoDTO buscarPorId(@PathVariable Long estadoId) {
		return estadoAssembler.toModel(cadastroEstado.buscarOuFalhar(estadoId));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EstadoDTO adicionar(@RequestBody @Valid EstadoInputDTO estadoInput) {
		
		Estado estado = estadoDesassembler.toDomainObject(estadoInput);
		
		return estadoAssembler.toModel(cadastroEstado.salvar(estado));
	
	}

	@PutMapping("/{estadoId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EstadoDTO atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInputDTO estadoInput) {
		
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
		estadoDesassembler.copyToDomainObject(estadoInput, estado);
		estado = cadastroEstado.salvar(estado); 
		
		return estadoAssembler.toModel(estado);
	
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}

}
