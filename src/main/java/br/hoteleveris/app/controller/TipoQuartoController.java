package br.hoteleveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.hoteleveris.app.request.TipoQuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListTipoQuartoResponse;
import br.hoteleveris.app.response.TipoQuartoResponse;
import br.hoteleveris.app.service.TipoQuartoService;

@RestController
@RequestMapping("/tiposquarto")
public class TipoQuartoController extends BaseController{
	
	@Autowired
	private TipoQuartoService _service;
	
	@PostMapping
	public ResponseEntity<BaseResponse> criar(@RequestBody TipoQuartoRequest request){
		try {
			BaseResponse response = _service.criar(request);
			return ResponseEntity.status(response.statusCode).body(response);
			
		} catch (Exception e) {
			return ResponseEntity.status(error.statusCode).body(error);
		}
	}
	
	@GetMapping
	public ResponseEntity<BaseResponse> listar(){
		try {
			ListTipoQuartoResponse response = _service.listar();
			return ResponseEntity.status(response.statusCode).body(response);
			
		} catch (Exception e) {
			return ResponseEntity.status(error.statusCode).body(error);
		}
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<BaseResponse> obter(@PathVariable Long id){
		try {
			TipoQuartoResponse response = _service.obter(id);
			return ResponseEntity.status(response.statusCode).body(response);
			
		} catch (Exception e) {
			return ResponseEntity.status(error.statusCode).body(error);
			
		}
		
	}

}
