package br.hoteleveris.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.repository.OcupacaoRepository;
import br.hoteleveris.app.request.TransferenciaRequest;
import br.hoteleveris.app.response.BaseResponse;


@Service
public class FaturaService {
	
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
		
	public BaseResponse transferencia(){
		
		BaseResponse response = new BaseResponse();
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/operacoes/transferencia";
		String hashContaHotel = "XNY4cYbj8Y";
		
		List<Ocupacao> lista = ocupacaoRepository.findBysituacao("N");
		
		if(lista.isEmpty()) {
			response.message ="Não Há nenhuma Ocupação em debito.";
			response.statusCode =  400;
			return response;
			
		}
		
		//VARREDURA DA LISTA DE OCUPAÇÕES COM VALOR DE SITUAÇÃO "N"
		for (Ocupacao ocupacao: lista) {
		double valor = ocupacao.getQuarto().getTipoQuarto().getValor() * ocupacao.getQtdDiarias();
		
		TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
		transferenciaRequest.setHashDestino(hashContaHotel);
		transferenciaRequest.setHashOrigem(ocupacao.getCliente().getHash());
		transferenciaRequest.setValor(valor);
		
		//REQUISIÇÃO
		restTemplate.postForObject(url, transferenciaRequest, BaseResponse.class);
		
		ocupacao.setSituacao("P");
		ocupacaoRepository.save(ocupacao);
		
		}
		response.statusCode = 200;
		response.message = "Transferencia completa";
		
		return response;
		
	}
	
	

}
