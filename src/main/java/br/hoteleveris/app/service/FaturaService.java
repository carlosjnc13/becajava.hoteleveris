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
	
	private String hashContaHotel = "XNY4cYbj8Y";
	
	public void transferencia(){
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/operacoes/transferencia";
		
		List<Ocupacao> lista = ocupacaoRepository.findBysituacao("N");
		
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
		
	}
	
	

}
