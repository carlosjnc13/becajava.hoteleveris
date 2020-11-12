package br.hoteleveris.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.service.QuartoService;

@RestController
@RequestMapping("/quartos")
public class QuartoController extends BaseController{
	
	final QuartoService _service;
	
	public QuartoController(QuartoService service) {
		_service = service;
		
	}
	
	@PostMapping
	public ResponseEntity<BaseResponse> criar (@RequestBody QuartoRequest request){
		try {
			BaseResponse response = _service.criar(request);
			return ResponseEntity.status(response.statusCode).body(response);
			
		} catch (Exception e) {
			return ResponseEntity.status(error.statusCode).body(error);
		}
		
	}

}
