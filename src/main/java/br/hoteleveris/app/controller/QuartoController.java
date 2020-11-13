package br.hoteleveris.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.hoteleveris.app.request.QuartoPatchRequest;
import br.hoteleveris.app.request.QuartoRequest;
import br.hoteleveris.app.response.BaseResponse;
import br.hoteleveris.app.response.ListQuartoResponse;
import br.hoteleveris.app.service.QuartoService;

@RestController
@RequestMapping("/quartos")
public class QuartoController extends BaseController {

	final QuartoService _service;

	public QuartoController(QuartoService service) {
		_service = service;

	}

	@PostMapping
	public ResponseEntity<BaseResponse> criar(@RequestBody QuartoRequest request) {
		try {
			BaseResponse response = _service.criar(request);
			return ResponseEntity.status(response.statusCode).body(response);

		} catch (Exception e) {
			return ResponseEntity.status(error.statusCode).body(error);
		}

	}

	@GetMapping(path = "/tipos/{id}")
	public ResponseEntity<BaseResponse> listarByTipo(@PathVariable Long id) {
		try {
			ListQuartoResponse response = _service.listarByTipo(id);
			return ResponseEntity.status(response.statusCode).body(response);

		} catch (Exception e) {
			return ResponseEntity.status(error.statusCode).body(error);
		}

	}

	@PatchMapping(path = "/{id}")
	public ResponseEntity<BaseResponse> atualizarSituacao(@PathVariable Long id,
			@Validated @RequestBody QuartoPatchRequest request) {
		try {
			BaseResponse response = _service.atualizarSituacao(id, request);
			return ResponseEntity.status(response.statusCode).body(response);

		} catch (Exception e) {
			return ResponseEntity.status(error.statusCode).body(error);
		}

	}

}
