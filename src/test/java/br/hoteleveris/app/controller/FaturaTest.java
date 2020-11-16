package br.hoteleveris.app.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.service.FaturaService;

@SpringBootTest
public class FaturaTest {
	
	@Autowired
	private FaturaService service;
	
	@Test
	public void Transferencia() {
		BaseResponse response = service.transferencia();
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Transferencia completa", response.getMessage());
		
	}
	@Test
	public void TransferenciaSemDebitos() {
		BaseResponse response = service.transferencia();
		
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Não Há nenhuma Ocupação em debito.", response.getMessage());
		
	}
	
}
