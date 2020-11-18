package br.hoteleveris.app.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Comodidade;
import br.hoteleveris.app.model.Quarto;
import br.hoteleveris.app.model.QuartoComodidade;
import br.hoteleveris.app.model.TipoQuarto;
import br.hoteleveris.app.repository.QuartoComodidadeRepository;
import br.hoteleveris.app.repository.QuartoRepository;
import br.hoteleveris.app.request.ComodidadeRequest;
import br.hoteleveris.app.request.QuartoPatchRequest;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListQuartoResponse;
import br.hoteleveris.app.response.QuartoResponse;
import br.hoteleveris.app.service.QuartoService;

@Service
public class QuartoServiceImp implements QuartoService {
	
	@Autowired
	private QuartoRepository  _repository;
	
	@Autowired
	private QuartoComodidadeRepository _qcRepository;
	
	//CRIAR QUARTO
	public BaseResponse criar(QuartoRequest request) {
		BaseResponse response = new BaseResponse();
		Quarto quarto = new Quarto();
		response.statusCode = 400;
		
		if(request.getAndar() <= 0) {
            response.message = "Andar não inserido ou Invalido.";
            return response;
        }

        if(request.getSituacao() == null || request.getSituacao() == "") {
            response.message = "Situação não inserida ou Invalida.";
            return response;
        }

        if(request.getNoQuarto() <= 0) {
            response.message = "Número do quarto não inserido ou Invalido";
            return response;
        }
        if(request.getIdtipoQuarto() <= 0 || request.getIdtipoQuarto() == null) {
        	response.message = "Id do Tipo de Quarto Invalido Ou não Inserido" ;
        	return response;
        }
		
		quarto.setAndar(request.getAndar());
		quarto.setNoQuarto(request.getNoQuarto());
		quarto.setSituacao(request.getSituacao());
		
		
		TipoQuarto tipoQuarto = new TipoQuarto();
		tipoQuarto.setId(request.getIdtipoQuarto());
		quarto.setTipoQuarto(tipoQuarto);
	
		_repository.save(quarto);
		
		
		//INSERÇÃO DAS COMODIDADES REFERENTES NO BANCO DE DADOS 
		Optional<Quarto> getNumeroQuarto = _repository.findBynoQuarto(request.getNoQuarto());
		Long quartoId = getNumeroQuarto.get().getId();
		
		for(ComodidadeRequest C : request.getComodidades()) {
			
			Quarto q = new Quarto();
			q.setId(quartoId);
			
			Comodidade c = new Comodidade();
			c.setId(C.getId());
			
			QuartoComodidade quartoComodidade = new QuartoComodidade();
			quartoComodidade.setComodidade(c);
			quartoComodidade.setQuarto(q);
			
			
			_qcRepository.save(quartoComodidade);
			
			
		}
		response.message = "Quarto Cadastrado!";
		response.statusCode = 201;
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
	
	public ListQuartoResponse listarByTipo (Long id) {
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
		
		if(request.getSituacao() == "" || request.getSituacao() == null ) {
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
