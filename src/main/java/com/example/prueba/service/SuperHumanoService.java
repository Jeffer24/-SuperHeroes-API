package com.example.prueba.service;

import java.util.List;

import com.example.prueba.message.SuperHumanoRq;
import com.example.prueba.message.SuperHumanoRs;
import com.example.prueba.model.exceptions.ApiException;

public interface SuperHumanoService {

	/**
	 * Obtener todos los registros.
	 * 
	 * @return lista de super-humanos.
	 */
	List<SuperHumanoRs> getAll();

	/**
	 * Guardar superHumano en bd.
	 * 
	 * @param superHumano SuperHumanoRq
	 * @throws ApiException
	 */
	void save(SuperHumanoRq superHumano) throws ApiException;

	/**
	 * Busqueda por filtros.
	 * 
	 * @param superHumanoRq
	 * @return
	 * @throws ApiException
	 */
	List<SuperHumanoRs> findByFilters(SuperHumanoRq superHumanoRq) throws ApiException;

}
