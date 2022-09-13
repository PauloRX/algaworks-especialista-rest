package com.algaworks.algafood.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoInputDesassembler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.infrastructure.spec.PedidoSpecs;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.repository.filter.PedidoFilter;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoModelAssembler pedidoAssembler;
	
	@Autowired
	private PedidoInputDesassembler pedidoDesassembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoAssembler;
	
	@GetMapping
	public List<PedidoResumoModel> pesquisar(PedidoFilter filtro) {
		return pedidoResumoAssembler.toCollectionModel(
				pedidoRepository.findAll(PedidoSpecs.usandoFiltros(filtro)));
	}

	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		return pedidoAssembler.toModel(emissaoPedido.buscarOuFalhar(codigoPedido));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PedidoModel adicionar(@RequestBody PedidoInput input) {
		Pedido pedido = pedidoDesassembler.toDomainObject(input);
		
		//TODO Autenticacao
		pedido.setCliente(new Usuario());
		pedido.getCliente().setId(1L);
		
		return pedidoAssembler.toModel(emissaoPedido.emissao(pedido));
	}
	
}
