package com.algaworks.algafood.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("testes")
public class TesteController {

	@Autowired
	CozinhaRepository cozinhaRepository;
	
//	@GetMapping("/cozinhas/por-nome")
//	public List<Cozinha> buscarPorNome(@RequestParam String nome){
//		return cozinhaRepository.buscarPorNome(nome);
//	}
	
}
