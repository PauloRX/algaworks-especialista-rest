package com.algaworks.algafood;

import org.springframework.boot.test.context.SpringBootTest;

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
