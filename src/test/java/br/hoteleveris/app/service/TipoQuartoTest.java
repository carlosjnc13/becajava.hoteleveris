package br.hoteleveris.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListTipoQuartoResponse;
import br.hoteleveris.app.response.TipoQuartoResponse;

@SpringBootTest
public class TipoQuartoTest {
	
	@Autowired
	private TipoQuartoService service;
	
	//TESTES DE CRIAÇÃO
	@Test
	public void criar() {
		TipoQuartoRequest request = new TipoQuartoRequest();
		request.setDescricao("Teste");
		request.setValor(2000.00);
		
		BaseResponse response = service.criar(request);
	
		Assertions.assertEquals(201, response.getStatusCode());
		Assertions.assertEquals("Tipo de Quarto cadastrado.", response.getMessage());
	}
	@Test
	public void criarSemDescricao() {
		TipoQuartoRequest request = new TipoQuartoRequest();
		//request.setDescricao("Teste");
		request.setValor(2000.00);
		
		BaseResponse response = service.criar(request);
	
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Descrição Invalida ou indefinida", response.getMessage());
	}
	@Test
	public void criarSemValor() {
		TipoQuartoRequest request = new TipoQuartoRequest();
		request.setDescricao("Teste");
		request.setValor(-2000.00);
		
		BaseResponse response = service.criar(request);
	
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Valor Invalido ou não inserido ", response.getMessage());
	}
	//TESTES DE LISTAGEM
	
	@Test
	public void listar() {
		 ListTipoQuartoResponse response = service.listar();
		 
		assertThat(!response.getTipos().isEmpty());
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Lista Obtida", response.getMessage());
	}
	@Test
	public void listaVazia() {
		 ListTipoQuartoResponse response = new ListTipoQuartoResponse();
		 
		assertThat(response.getTipos().isEmpty());
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Lista Vazia", response.getMessage());
	}
	//TESTES DE OBTENÇÃO POR ID
	
	@Test
	public void obter() {
		Long id = 1L;
		
		TipoQuartoResponse response = service.obter(id);
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Tipo de Quarto Obtido.", response.getMessage());
	}
	@Test
	public void obterIdInexistente() {
		Long id = 100000L;
		
		TipoQuartoResponse response = service.obter(id);
		
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Cliente não encontrado.", response.getMessage());
	}
	@Test
	public void obterIdInvalido() {
		Long id = -1L;
		
		TipoQuartoResponse response = service.obter(id);
		
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("ID invalido.", response.getMessage());
	}
	
	

}
