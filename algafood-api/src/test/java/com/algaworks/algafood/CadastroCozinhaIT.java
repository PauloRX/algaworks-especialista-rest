package com.algaworks.algafood;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

	private static final int COZINHA_ID_INEXISTENTE = 200;

	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	@Autowired
	private CozinhaRepository repository;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	private Cozinha cozinhaIndiana = preparaCozinhaIndiana();

	private int qtdCozinhas;
	
	
	@BeforeEach
	public void setUp() {
		
		enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		databaseCleaner.clearTables();
		preparaDados();
		qtdCozinhas = repository.findAll().size();
		
	}

	@Test
	public void deveRetornoStatus200_QuandoConsultarCozinhas() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	
	}
	
	
	@Test
	public void deveRetornarStatus201_QuandoCriarCozinha() {
		
		String cozinhaChinesa = ResourceUtils.getContentFromResource("/json/cozinha.json");
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(cozinhaChinesa)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhaPorIdExistente() {
		given()
			.accept(ContentType.JSON)
			.pathParam("cozinhaId", 2)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaIndiana.getNome()));
	}
	
	@Test
	public void deveRetornarStatus400_QuandoConsultarCozinhaPorIdInexistente() {
		given()
			.accept(ContentType.JSON)
			.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void preparaDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaService.salvar(cozinha1);
		
		cozinhaService.salvar(cozinhaIndiana);
		
		Cozinha cozinha3 = new Cozinha();
		cozinha3.setNome("Mexicana");
		cozinhaService.salvar(cozinha3);
		
		Cozinha cozinha4 = new Cozinha();
		cozinha4.setNome("Argentina");
		cozinhaService.salvar(cozinha4);
		
	}
		
	private Cozinha preparaCozinhaIndiana() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Indiana");
		return cozinha;
	}
	
}
