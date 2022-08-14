package com.algaworks.algafood;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroCozinhaService cozinhaService;

	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@BeforeEach
	public void setUp() {
		
		enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		databaseCleaner.clearTables();
		preparaDados();
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
	public void deveRetornar4Cozinhas_QuandoConsultarCozinhas() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(4))
			.body("nome", hasItems("Indiana", "Argentina"));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCriarCozinha() {
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body("{\"nome\" : \"Chinesa\"}")
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
	}
	
	private void preparaDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaService.salvar(cozinha1);
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Indiana");
		cozinhaService.salvar(cozinha2);
		
		Cozinha cozinha3 = new Cozinha();
		cozinha3.setNome("Mexicana");
		cozinhaService.salvar(cozinha3);
		
		Cozinha cozinha4 = new Cozinha();
		cozinha4.setNome("Argentina");
		cozinhaService.salvar(cozinha4);
		
	}
		
}
