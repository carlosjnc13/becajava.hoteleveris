package br.hoteleveris.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.request.QuartoPatchRequest;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListQuartoResponse;
import br.hoteleveris.app.response.QuartoResponse;

@SpringBootTest
public class QuartoTest {
	
	@Autowired
	private QuartoService service;
	
	//TESTES DE CRIAÇÃO DE QUARTO
	@Test
	public void criar() {
		QuartoRequest request =  new QuartoRequest();
		request.setAndar(32);
		
		List<ComodidadeRequest> teste = new ArrayList<ComodidadeRequest>();
		ComodidadeRequest comodidade = new ComodidadeRequest();
		comodidade.setId(1L);
		teste.add(comodidade);
		
		request.setComodidades(teste);
		request.setIdtipoQuarto(1L);
		int noQuarto = this.getRandomNumberUsingInts(1,1000);
		request.setNoQuarto(noQuarto);
		request.setSituacao("Ativo");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(201, response.getStatusCode());
		Assertions.assertEquals("Quarto Cadastrado!", response.getMessage());
		
	}
	
	@Test
	public void criarSemIdTipoQuarto() {
		QuartoRequest request =  new QuartoRequest();
		request.setAndar(32);
		
		List<ComodidadeRequest> teste = new ArrayList<ComodidadeRequest>();
		ComodidadeRequest comodidade = new ComodidadeRequest();
		comodidade.setId(1L);
		teste.add(comodidade);
		
		request.setComodidades(teste);
		request.setIdtipoQuarto(-17878L);
		int noQuarto = this.getRandomNumberUsingInts(1,1000);
		request.setNoQuarto(noQuarto);
		request.setSituacao("Ativo");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Id do Tipo de Quarto Invalido Ou não Inserido", response.getMessage());
		
	}
	
	@Test
	public void criarSemAndar() {
		QuartoRequest request =  new QuartoRequest();
		//request.setAndar(32);
		
		List<ComodidadeRequest> teste = new ArrayList<ComodidadeRequest>();
		ComodidadeRequest comodidade = new ComodidadeRequest();
		comodidade.setId(1L);
		teste.add(comodidade);
		
		request.setComodidades(teste);
		request.setIdtipoQuarto(1L);
		request.setNoQuarto(777);
		request.setSituacao("A");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Andar não inserido ou Invalido.", response.getMessage());
		
	}
	@Test
	public void criarSemSituacao() {
		QuartoRequest request =  new QuartoRequest();
		request.setAndar(32);
		
		List<ComodidadeRequest> teste = new ArrayList<ComodidadeRequest>();
		ComodidadeRequest comodidade = new ComodidadeRequest();
		comodidade.setId(1L);
		teste.add(comodidade);
		
		request.setComodidades(teste);
		request.setIdtipoQuarto(1L);
		request.setNoQuarto(777);
		//request.setSituacao("A");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Situação não inserida ou Invalida.", response.getMessage());
		
	}
	@Test
	public void criarSemNoQuarto() {
		QuartoRequest request =  new QuartoRequest();
		request.setAndar(32);
		
		List<ComodidadeRequest> teste = new ArrayList<ComodidadeRequest>();
		ComodidadeRequest comodidade = new ComodidadeRequest();
		comodidade.setId(1L);
		teste.add(comodidade);
		
		request.setComodidades(teste);
		request.setIdtipoQuarto(1L);
		//int noQuarto = this.getRandomNumberUsingInts(1,1000);
		//request.setNoQuarto(noQuarto);
		request.setSituacao("A");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Número do quarto não inserido ou Invalido", response.getMessage());
		
	}
	
	public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
          .findFirst()
          .getAsInt();
    }
	@Test
	public void criarSemTipoQuarto() {
		QuartoRequest request =  new QuartoRequest();
		request.setAndar(32);
		
		List<ComodidadeRequest> teste = new ArrayList<ComodidadeRequest>();
		ComodidadeRequest comodidade = new ComodidadeRequest();
		comodidade.setId(1L);
		teste.add(comodidade);
		
		request.setComodidades(teste);
		request.setIdtipoQuarto(-1L);
		request.setNoQuarto(-777);
		request.setSituacao("A");
		
		BaseResponse response = service.criar(request);
		Assertions.assertEquals(400, response.getStatusCode());
		
		
	}
	//TESTES DE OBTENÇÃO POR ID
	
	@Test
	public void obter() {
		Long id = 18L;
		QuartoResponse response = service.obter(id);
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Quarto Obtido.", response.getMessage());
		
	}
	@Test
	public void obterIdInexistente() {
		Long id = 1889L;
		QuartoResponse response = service.obter(id);
		
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("Cliente não encontrado.", response.getMessage());
	}
	@Test
	public void obterIdInvalido() {
		Long id = -1L;
		QuartoResponse response = service.obter(id);
		
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("ID Invalido", response.getMessage());
	}
	
	//TESTES DE LISTAGEM PO ID DO TIPO DE QUARTO
	
	@Test
	public void listarByTipoQuarto() {
		Long id = 1L;
		ListQuartoResponse response = service.listarByTipo(id);
		
		assertThat(!response.getQuartos().isEmpty());
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Lista de quartos Obtida", response.getMessage());
		
	}
	@Test
	public void listarByTipoQuartoIdInvalido() {
		Long id = -1L;
		ListQuartoResponse response = service.listarByTipo(id);
		
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("ID invalido", response.getMessage());
		
	}
	//TESTES DE ATUALIZAÇÃO
	
	@Test
	public void atualizarSituacao() {
		QuartoPatchRequest request = new QuartoPatchRequest();
		request.setSituacao("I");
		
		Long id = 52L;	
		BaseResponse response = service.atualizarSituacao(id, request);
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertEquals("Atualização realizada.", response.getMessage());

	}
	@Test
	public void atualizarSituacaoIdInexistente() {
		QuartoPatchRequest request = new QuartoPatchRequest();
		request.setSituacao("I");
		
		Long id = 1888L;	
		BaseResponse response = service.atualizarSituacao(id, request);
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertEquals("ID invalido ou não inserido.", response.getMessage());
		
	}
	@Test
	public void atualizarSituacaoInvalida() {
		QuartoPatchRequest request = new QuartoPatchRequest();
		request.setSituacao("");
		
		Long id = 1888L;	
		BaseResponse response = service.atualizarSituacao(id, request);
		Assertions.assertEquals(400, response.getStatusCode());
		
	}
	

}
