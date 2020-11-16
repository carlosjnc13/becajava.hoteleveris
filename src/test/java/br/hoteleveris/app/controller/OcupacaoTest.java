package br.hoteleveris.app.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListOcupacaoResponse;
import br.hoteleveris.app.service.OcupacaoService;

@SpringBootTest
public class OcupacaoTest {
	
	@Autowired
	private OcupacaoService service;
	
	// TESTES PARA CRIAR OCUPAÇÃO
	@Test
	public void criar() {
		
		OcupacaoRequest request = new OcupacaoRequest();
		
		request.setClienteId(2L);
		request.setQuartoId(17L);
		request.setData("11/11/2011");
		request.setQtdDiarias(6L);
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(201, response.getStatusCode());
		Assertions.assertEquals("Ocupacao Inserida.", response.getMessage());
	}
	@Test
	public void criarSemCliente() {
		
		OcupacaoRequest request = new OcupacaoRequest();
		
		//request.setClienteId(2L);
		request.setQuartoId(17L);
		request.setData("11/11/2011");
		request.setQtdDiarias(6L);
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("ID do Cliente Invalido ou não inserido", response.getMessage());
	}
	@Test
	public void criarSemQUarto() {
		
		OcupacaoRequest request = new OcupacaoRequest();
		
		request.setClienteId(2L);
		//request.setQuartoId(17L);
		request.setData("11/11/2011");
		request.setQtdDiarias(6L);
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("ID de Quarto Invalido ou não inserido", response.getMessage());
	}
	@Test
	public void criarSemData() {
		
		OcupacaoRequest request = new OcupacaoRequest();
		
		request.setClienteId(2L);
		request.setQuartoId(17L);
		//request.setData("11/11/2011");
		request.setQtdDiarias(6L);
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Data não Inserida ou Invalida", response.getMessage());
	}
	@Test
	public void criarSemQtdDiarias() {
		
		OcupacaoRequest request = new OcupacaoRequest();
		
		request.setClienteId(2L);
		request.setQuartoId(17L);
		request.setData("11/11/2011");
		//request.setQtdDiarias(6L);
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Quantidade de diarias invalida ou não inserida", response.getMessage());
	}
	//TESTES PARA LISTAR
	
	@Test
	public void listar() {
		
		ListOcupacaoResponse response = service.listar();
		
		assertThat(!response.getOcupacoes().isEmpty());
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Lista Obtida", response.getMessage());
		
	}

}
