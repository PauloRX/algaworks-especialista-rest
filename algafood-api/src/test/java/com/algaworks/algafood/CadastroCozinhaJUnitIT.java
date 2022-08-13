package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@SpringBootTest
public class CadastroCozinhaJUnitIT {

//	@Autowired
//	CadastroCozinhaService cozinhaService;
//	
//	@Test
//	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
//		// Scenario
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome("Mexicana");
//		
//		//action
//		 novaCozinha = cozinhaService.salvar(novaCozinha);
//		
//		//validation
//		 assertThat(novaCozinha).isNotNull();
//		 assertThat(novaCozinha.getId()).isNotNull();
//	}
//	
//	@Test
//	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
//		assertThrows(ConstraintViolationException.class, () -> {
//			Cozinha novaCozinha = new Cozinha();
//			novaCozinha.setNome(null);
//			cozinhaService.salvar(novaCozinha);
//		});
//	}
//	
//	@Test
//	public void deveFalhar_QuandoExcluirUmaCozinhaEmUso() {
//		assertThrows(EntidadeEmUsoException.class, () ->{
//			cozinhaService.excluir(3l);
//		});
//	}
//	
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
//		assertThrows(CozinhaNaoEncontradaException.class, () ->{
//			cozinhaService.excluir(100l);
//		});
//	}
		
}
