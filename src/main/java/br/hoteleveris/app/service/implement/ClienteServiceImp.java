package br.hoteleveris.app.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.hoteleveris.app.model.Cliente;
import br.hoteleveris.app.repository.ClienteRepository;
import br.hoteleveris.app.request.ClienteRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ClienteResponse;
import br.hoteleveris.app.service.ClienteService;

@Service
public class ClienteServiceImp implements ClienteService{
	
	@Autowired
	private ClienteRepository _repository;
	
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
	
	//OBTER CLIENTE POR ID
	
	public ClienteResponse obter(Long id) {
		ClienteResponse response = new ClienteResponse();
		Optional<Cliente> cliente = _repository.findById(id);
		response.statusCode = 400;
		
		if(id <= 0 || id == null) {
			response.message= "ID invalido.";
			return response;
		}
		if(!cliente.isPresent()) {
			response.message = "Cliente não encontrado.";
			return response;
		}
		
		//HASH NÃO PODE SER RETORNADO
		response.setNome(cliente.get().getNome());
		response.setCpf(cliente.get().getCpf());
		response.setId(cliente.get().getId());
		
		response.message = "Cliente Obtido";
		response.statusCode = 200;
		
		return response;
		
	}

}
