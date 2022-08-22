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

import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.assembler.RestauranteInputDesassembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private RestauranteModelAssembler restauranteAssembler;
	
	@Autowired
	private RestauranteInputDesassembler restauranteDesassembler;
	
	@GetMapping
	public List<RestauranteModel> listar() {
		return restauranteAssembler.toCollectionDTO(restauranteRepository.findAll());
	}

	@GetMapping("/{restauranteId}")
	public RestauranteModel buscarPorId(@PathVariable Long restauranteId) {

		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		RestauranteModel restauranteDTO = restauranteAssembler.toModel(restaurante);
		
		return restauranteDTO;
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			
			Restaurante restaurante = restauranteDesassembler.toDomainObject(restauranteInput);
			
			return restauranteAssembler.toModel(cadastroRestaurante.salvar(restaurante));
			
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			
			throw new NegocioException(e.getMessage());
		
		}
	}

	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@RequestBody @Valid RestauranteInput restauranteInput, @PathVariable Long restauranteId) {
		
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		restauranteDesassembler.copyToDomainObject(restauranteInput, restauranteAtual);
		
		try {
			
			return restauranteAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
		
		} catch (EntidadeNaoEncontradaException e) {
		
			throw new NegocioException(e.getMessage());
		
		}
	}
	
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);
	}
	
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);
	}


}
