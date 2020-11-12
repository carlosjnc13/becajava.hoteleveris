package br.hoteleveris.app.service;

import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.repository.OcupacaoRepository;
import br.hoteleveris.app.request.OcupacaoRequest;
import br.hoteleveris.app.response.BaseResponse;

@Service
public class OcupacaoService {
	
	final OcupacaoRepository _repository;
	
	public OcupacaoService(OcupacaoRepository repository) {
		_repository = repository;
	}
	
	//CRIAR
	
	public BaseResponse criar(OcupacaoRequest request) {
		BaseResponse response = new BaseResponse();
		response.statusCode = 400;
		
	
		if(request.getQuarto().getId() == null || request.getQuarto().getId() <= 0) {
			response.message = " ID de Quarto Invalido ou não inserido";
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
		if(request.getCliente().getId() == null || request.getCliente().getId() <= 0) {
			response.message = "ID do Cliente Invalido ou não inserido";
			return response;
		}
		
		Ocupacao ocupacao = new Ocupacao();
		ocupacao.setCliente(request.getCliente());
		ocupacao.setData(request.getData());
		ocupacao.setQtdDiarias(request.getQtdDiarias());
		ocupacao.setQuarto(request.getQuarto());
		ocupacao.setSituacao(request.getSituacao());
		
		_repository.save(ocupacao);
		
		response.message = "Ocupacao Inserida";
		response.statusCode = 200;
		
		return response;
		
		
		
	}
	
	

}
