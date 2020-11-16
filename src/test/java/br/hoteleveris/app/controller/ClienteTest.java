package br.hoteleveris.app.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.request.ClienteRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ClienteResponse;
import br.hoteleveris.app.service.ClienteService;

@SpringBootTest
public class ClienteTest {
	
	@Autowired
	private ClienteService service;
	
	//TESTES PARA INSERÇÃO DE CLIENTES
	@Test
	public void criar() {
		ClienteRequest request = new ClienteRequest();
		request.setCpf("12345678");
		request.setHash("345fd5");
		request.setNome("jefferson");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(200, response.getStatusCode());
	}
	
	@Test
	public void criarSemCpf() {
		ClienteRequest request = new ClienteRequest();
		//request.getCpf();
		request.setHash("345fd5");
		request.setNome("jefferson");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		
	}
	
	@Test
	public void criarSemNome() {
		ClienteRequest request = new ClienteRequest();
		request.setCpf("12345678");
		request.setHash("345fd5");
		//request.setNome(nome);
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		
	}
	@Test
	public void criarSemHash() {
		ClienteRequest request = new ClienteRequest();
		request.setCpf("12345678");
		//request.setHash("345fd5");
		request.setNome("jefferson");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		
	}
	
	//TESTES PARA OBTENÇÃO DE CLIENTE POR ID
	@Test
	public void obter() {
		Long id = 2L;
		ClienteResponse response = service.obter(id);
		Assertions.assertEquals(200, response.getStatusCode());
		
	}
	@Test
	public void obterIdInvalido() {
		Long id = 34L;
		ClienteResponse response = service.obter(id);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Cliente não encontrado.", response.getMessage());
		
	}
	@Test
	public void obterIdMenorQueZero() {
		Long id = -1L;
		ClienteResponse response = service.obter(id);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("ID invalido.", response.getMessage());
		
	}
	
}
