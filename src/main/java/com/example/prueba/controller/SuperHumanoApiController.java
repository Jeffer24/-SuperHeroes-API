package com.example.prueba.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.message.SuperHumanoRq;
import com.example.prueba.message.SuperHumanoRs;
import com.example.prueba.model.exceptions.ApiException;
import com.example.prueba.service.SuperHumanoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @author Jefferson David Rangel <jrangel@grupoasd.com.co>
 */
@RestController
@RequestMapping("/super-humano")
@Api(tags = "super-humano", value = "/super-humano")
public class SuperHumanoApiController {

	private SuperHumanoService superHumanoService;

	public SuperHumanoApiController(SuperHumanoService superHumanoService) {
		this.superHumanoService = superHumanoService;
	}

	@GetMapping("/listar")
	@ApiOperation(value = "Obtener todos los super-humanos", response = SuperHumanoRs.class, responseContainer = "List", httpMethod = "GET")
	public ResponseEntity<List<SuperHumanoRs>> getAll(HttpServletRequest request) {
		return ResponseEntity.ok(superHumanoService.getAll());
	}

	@PutMapping("/guardar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Crear super humano", httpMethod = "PUT")
	public void create(ServletRequest request, @RequestBody @Validated SuperHumanoRq superHumamo) throws ApiException {
		superHumanoService.save(superHumamo);
	}

	@PostMapping("/obtener-filtros")
	@ApiOperation(value = "Busqueda avanzada de super-humanos", response = SuperHumanoRs.class, responseContainer = "SuperHumano", httpMethod = "POST")
	public ResponseEntity<List<SuperHumanoRs>> getByFilters(HttpServletRequest request, @RequestBody SuperHumanoRq superHumanoRq)
			throws ApiException {
		return ResponseEntity.ok(superHumanoService.findByFilters(superHumanoRq));
	}

}
