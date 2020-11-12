package br.hoteleveris.app.service;

import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.repository.QuartoRepository;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;

@Service
public class QuartoService {
	
	final QuartoRepository _repository;
	
	public QuartoService(QuartoRepository repository) {
		_repository =  repository;
	}
	
	//CRIAR QUARTO
	public BaseResponse criar(QuartoRequest request) {
		BaseResponse response = new BaseResponse();
		response.statusCode = 400;
		
		if(request.getAndar() <= 0 ) {
			response.message = "Andar invalido";
			return response;
		}
		if(request.getNoQuarto() <= 0) {
			response.message = "Numero de Quarto Invallido";
			return response;
		}
		if(request.getSituacao() == "" || request.getSituacao() == null) {
			response.message = "Status de Situação Invalido ou não inserido";
			return response;
		}
		if(request.getTipoQuarto().getId() == null || request.getTipoQuarto().getId() <= 0) {
			response.message = "ID Do Tipo de Quarto Invalido Ou não inserido";
			return response;
		}
		if(request.getComodidade().isEmpty()) {
			response.message = "A lista De Comodidades está Vazia";
			return response;
		}
		
		Quarto quarto = new Quarto();
		quarto.setAndar(request.getAndar());
		quarto.setNoQuarto(request.getNoQuarto());
		quarto.setSituacao(request.getSituacao());
		quarto.setTipoQuarto(request.getTipoQuarto());
		
		
		quarto.setComodidade(request.getComodidade());
		_repository.save(quarto);
		
		response.statusCode = 200;
		response.message = "Quarto Cadastrdo.";
		
		return response;
		
	}
	
	
	

}
