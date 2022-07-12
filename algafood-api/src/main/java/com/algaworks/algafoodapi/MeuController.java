package com.algaworks.algafoodapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.service.AtivacaoClienteService;

@Controller
public class MeuController {

	private AtivacaoClienteService ativacaoClienteService;
	
	public MeuController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
		System.out.println("Construtor MeuController: " + ativacaoClienteService);
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		Cliente joao = new Cliente("Joao", "joao@xyz.com", "11980802132");
		ativacaoClienteService.ativar(joao);
		return "Hello!";
	}
	
}
