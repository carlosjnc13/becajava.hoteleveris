package br.hoteleveris.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.hoteleveris.app.model.Ocupacao;
import br.hoteleveris.app.repository.ClienteRepository;
import br.hoteleveris.app.repository.OcupacaoRepository;
import br.hoteleveris.app.repository.QuartoRepository;
import br.hoteleveris.app.repository.TipoQuartoRepository;
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
		//TransferenciaRequest transferencia = new TransferenciaRequest();
		
		for (Ocupacao ocupacao: lista) {
		double valor = ocupacao.getQuarto().getTipoQuarto().getValor() * ocupacao.getQtdDiarias();

		
		TransferenciaRequest transferencia = new TransferenciaRequest();
		transferencia.setHashDestino(hashContaHotel);
		transferencia.setHashOrigem(ocupacao.getCliente().getHash());
		transferencia.setValor(valor);
		
		BaseResponse response = restTemplate.postForObject(url, transferencia, BaseResponse.class);
		
		ocupacao.setSituacao("P");
		ocupacaoRepository.save(ocupacao);
		
		}
		
		//BaseResponse response = restTemplate.postForObject(url, transferencia, BaseResponse.class);
		//response.statusCode = 200;
		//response.message = "Transferencia completa.";
		//return response;
	}
	
	

}
