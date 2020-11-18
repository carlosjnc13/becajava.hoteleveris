package br.hoteleveris.app.service;

import br.hoteleveris.app.request.QuartoPatchRequest;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListQuartoResponse;
import br.hoteleveris.app.response.QuartoResponse;

public interface QuartoService {
	
	BaseResponse criar(QuartoRequest request);
	
	QuartoResponse obter(Long id);
	
	ListQuartoResponse listarByTipo(Long id);
	
	BaseResponse atualizarSituacao(Long id,QuartoPatchRequest request);

}
