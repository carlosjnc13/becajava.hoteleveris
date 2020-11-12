package br.hoteleveris.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.repository.TipoQuartoRepository;
import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListTipoQuartoResponse;

@Service
public class TipoQuartoService {
	
	final TipoQuartoRepository _repository;
	
	public TipoQuartoService(TipoQuartoRepository repository) {
		_repository = repository;
	}
	
	//CRIAR TIPO DE QUARTO
	public BaseResponse criar(TipoQuartoRequest request) {
		BaseResponse response = new BaseResponse();
		response.statusCode = 400;
		
		if(request.getDescricao() == null || request.getDescricao() == "") {
			response.message = "Descrição Invalida ou indefinida";
			return response;	
		}
		if(request.getValor() <= 0 || request.getValor() == null) {
			response.message = "Valor Invalido ou não inserido ";
			return response;
		}
		
		TipoQuarto tipoQuarto = new TipoQuarto();
		tipoQuarto.setDescricao(request.getDescricao());
		tipoQuarto.setValor(request.getValor());
		
		_repository.save(tipoQuarto);
		
		response.message = "Tipo de Quarto cadastrado.";
		response.statusCode = 200;
		
		return response;
		
	}
	
	//LISTAR TIPOS DE QUARTO
	
	public ListTipoQuartoResponse listar() {
		
		List<TipoQuarto> lista = _repository.findAll();
		ListTipoQuartoResponse response = new ListTipoQuartoResponse();
		
		if(lista.isEmpty()) {
			response.message = "Lista Vazia";
			response.statusCode = 400;
			return response;
		}
		
		response.setTipos(lista);
		response.message = "Lista Obtida";
		response.statusCode = 200;
		
		return response;

	}

}
