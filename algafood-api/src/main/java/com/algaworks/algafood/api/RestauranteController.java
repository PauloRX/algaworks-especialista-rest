package com.algaworks.algafood.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.RestauranteDTOAssembler;
import com.algaworks.algafood.api.dto.RestauranteDTO;
import com.algaworks.algafood.api.model.input.RestauranteInputDTO;
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
	private RestauranteDTOAssembler restauranteAssembler;
	
	@GetMapping
	public List<RestauranteDTO> listar() {
		return toCollectionDTO(restauranteRepository.findAll());
	}

	@GetMapping("/{restauranteId}")
	public RestauranteDTO buscarPorId(@PathVariable Long restauranteId) {

		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		RestauranteDTO restauranteDTO = restauranteAssembler.toModel(restaurante);
		
		return restauranteDTO;
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInput) {
		try {
			
			Restaurante restaurante = restauranteAssembler.toDomainObject(restauranteInput);
			
			return restauranteAssembler.toModel(cadastroRestaurante.salvar(restaurante));
			
		} catch (EntidadeNaoEncontradaException e) {
			
			throw new NegocioException(e.getMessage());
		
		}
	}

	@PutMapping("/{restauranteId}")
	public RestauranteDTO atualizar(@RequestBody @Valid RestauranteInputDTO restauranteInput, @PathVariable Long restauranteId) {
		
		Restaurante restaurante = restauranteAssembler.toDomainObject(restauranteInput);
		
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		BeanUtils.copyProperties(restaurante, restauranteAtual, 
				"id", 
				"formasPagamento", 
				"endereco", 
				"dataCadastro",
				"produtos");
		try {
			
			return restauranteAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
		
		} catch (EntidadeNaoEncontradaException e) {
		
			throw new NegocioException(e.getMessage());
		
		}
	}

	private List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
		
		return restaurantes.stream()
			.map(restaurante -> restauranteAssembler.toModel(restaurante))
			.collect(Collectors.toList()
		);
		
	}

}
