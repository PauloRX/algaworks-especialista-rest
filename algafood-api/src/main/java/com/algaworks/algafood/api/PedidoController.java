package com.algaworks.algafood.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 5) Pageable pageable) {
		
		Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltros(filtro), pageable);
		List<PedidoResumoModel> listaPedidosResumoModel = pedidoResumoAssembler.toCollectionModel(pedidosPage.getContent());
		Page<PedidoResumoModel> pedidosResumoModelPage = new PageImpl<>(listaPedidosResumoModel, pageable, pedidosPage.getTotalElements());
	
		return pedidosResumoModelPage;
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
