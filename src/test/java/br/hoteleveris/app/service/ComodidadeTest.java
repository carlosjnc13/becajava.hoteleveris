package br.hoteleveris.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ComodidadeResponse;

@SpringBootTest
public class ComodidadeTest {
	
	@Autowired
	private ComodidadeService service;
	
	//CRIAR COMODIDADE
	@Test
	public void criar() {
		ComodidadeRequest request = new ComodidadeRequest();
		request.setNome("Teste");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Comodidade Cadastrada", response.message);
	}
	@Test
	public void criaComNomeVazio() {
		ComodidadeRequest request = new ComodidadeRequest();
		request.setNome("");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Nome invalido ou não inserido", response.message);
	}
	@Test
	public void criaComNomeNulo() {
		ComodidadeRequest request = new ComodidadeRequest();
		request.setNome(null);
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Nome invalido ou não inserido", response.message);
	}
	
	//OBTER COMODIDADE POR ID
	
	@Test
	public void obter() {
		Long id = 1L;
		ComodidadeResponse response = service.obter(id);
		Assertions.assertEquals(200, response.getStatusCode());
	}
	@Test
	public void obterIdInexistente() {
		Long id = 100L;
		ComodidadeResponse response = service.obter(id);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Comodidade não encontrada.", response.message);
	}
	@Test
	public void obterIdInvalido() {
		Long id = -1L;
		ComodidadeResponse response = service.obter(id);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("ID invalido.", response.message);
	}
	@Test
	public void obterRetornoVazio() {
		Long id = 1L;
		ComodidadeResponse response = service.obter(id);
		assertThat(!response.getNome().isEmpty());
		assertThat(!response.getNome().isBlank());
	}

}
