package com.example.prueba.service;

import java.util.List;

import com.example.prueba.message.VehiculoRq;
import com.example.prueba.message.VehiculoRs;
import com.example.prueba.model.exceptions.ApiException;

public interface VehiculoService {
	
	/**
	 * Busqueda por filtros.
	 * 
	 * @param vehiculoRq
	 * @return
	 * @throws ApiException
	 */
	List<VehiculoRs> findByFilters(VehiculoRq vehiculoRq) throws ApiException;

}
