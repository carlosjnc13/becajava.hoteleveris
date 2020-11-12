package br.hoteleveris.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Comodidade;
import br.hoteleveris.app.repository.ComodidadeRepository;
import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ComodidadeResponse;

@Service
public class ComodidadeService {
	
	final ComodidadeRepository _repository;
	
	public ComodidadeService(ComodidadeRepository repository) {
		_repository = repository;
		
	}
	
	//CRIAR COMODIDADE
	
	public BaseResponse criar(ComodidadeRequest request) {
		BaseResponse response = new BaseResponse();
		
		if(request.getNome() == null || request.getNome() == "") {
			response.statusCode = 400;
			response.message = "Nome invalido ou não inserido";
			return response;
		}
		
		Comodidade comodidade = new Comodidade();
		comodidade.setNome(request.getNome());
		
		_repository.save(comodidade);
		
		response.statusCode = 200;
		response.message = "Comodidade Cadastrada";
		return response;
	}
	
	//OBTER POR ID
	
	public ComodidadeResponse obter(Long id) {
		ComodidadeResponse response = new ComodidadeResponse();
		Optional<Comodidade> comodidade = _repository.findById(id);
		response.statusCode = 400;
		
		if(id <= 0 || id == null) {
			response.message= "ID invalido.";
			return response;
		}
		if(comodidade.isEmpty()) {
			response.message = "Comodidade não encontrada.";
			return response;
		}
		
		response.setId(comodidade.get().getId());
		response.setNome(comodidade.get().getNome());
		response.message = "Comodidade Obtida.";
		response.statusCode =  200;
		
		return response;
		
	}

}
