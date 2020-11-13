package br.hoteleveris.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.repository.QuartoRepository;
import br.hoteleveris.app.request.QuartoPatchRequest;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListQuartoResponse;
import br.hoteleveris.app.response.QuartoResponse;

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
		
//		for(Comodidade c: request.getComodidade()) {
//			
//		}
		
		quarto.setComodidade(request.getComodidade());
		_repository.save(quarto);
		
		response.statusCode = 200;
		response.message = "Quarto Cadastrado.";
		
		return response;
		
	}
	//BUSCAR POR ID
	
	public QuartoResponse obter(Long id) {
		QuartoResponse response = new QuartoResponse();
		Optional<Quarto> quarto = _repository.findById(id);
		response.statusCode = 400;
		
		if(id <= 0) {
			response.message = "ID Invalido";
			return response;
		}
		if(quarto.isEmpty()) {
			response.message = "Cliente não encontrado.";
			return response;
		}
		
		response.message = "Quarto Obtido.";
		response.statusCode = 200;
		
		response.setSituacao(quarto.get().getSituacao());
		response.setAndar(quarto.get().getAndar());
		response.setId(quarto.get().getId());
		response.setTipoQuarto(quarto.get().getTipoQuarto());
		
		return response;
	}
	//LISTAR QUARTOS POR TIPOS DE QUARTOS
	
	public ListQuartoResponse listarByTipo (long id) {
		ListQuartoResponse response = new ListQuartoResponse();
		List<Quarto> lista = _repository.findBuscarQuartos(id);
		response.statusCode = 400;
		
		if(id <= 0) {
			response.message = "ID invalido";
			return response;
		}
		
		response.setQuartos(lista);
		response.statusCode = 200;
		response.message = "Lista de quartos Obtida";
		
		return response;
		
	}
	
	//PATCH - ATUALIZAÇÃO PARCIAL - SITUAÇÃO
	
	public BaseResponse atualizarSituacao(Long id,QuartoPatchRequest request) {
		BaseResponse response = new BaseResponse();
		Optional<Quarto> quarto = _repository.findById(id);
		response.statusCode = 400;
		
		if(id <= 0 || quarto.isEmpty()) {
			response.message = "ID invalido ou não inserido.";
			return response;
		}
		
		if(request.getSituacao() == null || request.getSituacao() == "") {
			response.message = "Situação Invalida ou não inserida.";
			return response;
		}
		
		//SUBSTITUIÇÃO DA PROPRIEDADE SITUAÇÃO
		quarto.get().setSituacao(request.getSituacao());
		
		_repository.save(quarto.get());
		
		response.statusCode = 200;
		response.message = "Atualização realizada.";
		return response;
	
	}
}
