package com.example.prueba.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.message.VehiculoRq;
import com.example.prueba.message.VehiculoRs;
import com.example.prueba.model.exceptions.ApiException;
import com.example.prueba.service.VehiculoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @author Jefferson David Rangel <jrangel@grupoasd.com.co>
 */
@RestController
@RequestMapping("/vehiculo")
@Api(tags = "vehiculo", value = "/vehiculo")
public class VehiculoApiController {

	private VehiculoService vehiculoService;

	public VehiculoApiController(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}

	@PostMapping("/obtener-filtros")
	@ApiOperation(value = "Busqueda avanzada de vehiculos", response = VehiculoRs.class, responseContainer = "Vehiculo", httpMethod = "POST")
	public ResponseEntity<List<VehiculoRs>> getByFilters(HttpServletRequest request, @RequestBody VehiculoRq vehiculoRq)
			throws ApiException {
		return ResponseEntity.ok(vehiculoService.findByFilters(vehiculoRq));
	}

}
