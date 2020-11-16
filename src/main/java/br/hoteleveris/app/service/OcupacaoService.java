package br.hoteleveris.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.repository.OcupacaoRepository;
import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListOcupacaoResponse;

@Service
public class OcupacaoService {
	
	
	@Autowired
	private OcupacaoRepository _repository;
	
	//CRIAR OCUPAÇÃO
	
	public BaseResponse criar(OcupacaoRequest request) {
		BaseResponse response = new BaseResponse();
		response.statusCode = 400;
		
	
		if(request.getQuartoId() == null || request.getQuartoId() <= 0) {
			response.message = "ID de Quarto Invalido ou não inserido";
			return response;
		}
		if(request.getQtdDiarias() == null || request.getQtdDiarias() <= 0) {
			response.message = "Quantidade de diarias invalida ou não inserida";
			return response;
		}
		if(request.getData() == null || request.getData() == "") {
			response.message = "Data não Inserida ou Invalida";
			return response;
		}
		if(request.getClienteId() == null || request.getClienteId() <= 0) {
			response.message = "ID do Cliente Invalido ou não inserido";
			return response;
		}
		
		Cliente cliente = new Cliente();
		cliente.setId(request.getClienteId());
		
		Quarto quarto = new Quarto();
		quarto.setId(request.getQuartoId());
		
		Ocupacao ocupacao = new Ocupacao();
		ocupacao.setCliente(cliente);
		ocupacao.setData(request.getData());
		ocupacao.setQtdDiarias(request.getQtdDiarias());
		ocupacao.setQuarto(quarto);
		ocupacao.setSituacao("N");
		
		_repository.save(ocupacao);
		
		response.message = "Ocupacao Inserida.";
		response.statusCode = 201;
		
		return response;

	}
	// LISTAR OCUPAÇÕES
	
	public ListOcupacaoResponse listar() {
		
		List<Ocupacao> lista = _repository.findAll();
		ListOcupacaoResponse response = new ListOcupacaoResponse();
		
		if(lista.isEmpty()) {
			response.message = "Lista Vazia";
			response.statusCode = 400;
			return response;
		}
		
		response.setOcupacoes(lista);
		response.message = "Lista Obtida";
		response.statusCode = 200;
		return response;
		
		
		
	}
	
	

}
