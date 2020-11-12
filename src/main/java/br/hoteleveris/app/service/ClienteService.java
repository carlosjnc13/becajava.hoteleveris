package br.hoteleveris.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.repository.ClienteRepository;
import br.hoteleveris.app.request.ClienteRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ClienteResponse;

@Service
public class ClienteService {
	
	final ClienteRepository _repository;
	
	public ClienteService(ClienteRepository repository) {
		_repository = repository;
		
	}
	//CRIAR CLIENTE
	
	public BaseResponse criar(ClienteRequest request) {
		BaseResponse response = new BaseResponse();
		response.statusCode = 400;
		
		if(request.getNome() == null || request.getNome() == "") {
			response.message = "Nome Não inserido.";
			return response;
		}
		if(request.getCpf() == null || request.getCpf() == "") {
			response.message = "Cpf não Inserido.";
			return response;
		}
		if(request.getHash() == null || request.getHash() == "") {
			response.message = "Hash não inserido..";
			return response;
		}
		
		Cliente cliente = new Cliente();
		cliente.setNome(request.getNome());
		cliente.setCpf(request.getCpf());
		cliente.setHash(request.getHash());
		
		_repository.save(cliente);
		
		response.message = "Cliente Cadastrado.";
		response.statusCode = 200;
		
		return response;

	}
	
	//OBTER POR ID
	
	public ClienteResponse obter(Long id) {
		ClienteResponse response = new ClienteResponse();
		Optional<Cliente> cliente = _repository.findById(id);
		response.statusCode = 400;
		
		if(id <= 0 || id == null) {
			response.message= "ID invalido.";
			return response;
		}
		if(cliente.isEmpty()) {
			response.message = "Cliente não encontrado.";
			return response;
		}
		
		response.setNome(cliente.get().getNome());
		response.setCpf(cliente.get().getCpf());
		response.setId(cliente.get().getId());
		
		response.message = "Cliente Obtido";
		response.statusCode = 200;
		
		return response;
		
	}

}
