package com.example.prueba.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.prueba.entity.SuperHumano;
import com.example.prueba.message.SuperHumanoRq;
import com.example.prueba.message.SuperHumanoRs;
import com.example.prueba.model.exceptions.ApiException;
import com.example.prueba.repository.SuperHumanoRepository;
import com.example.prueba.service.SuperHumanoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SuperHumanoServiceImpl implements SuperHumanoService {

	/*
	 * SuperHumanoRepository.
	 */
	private SuperHumanoRepository superHumanoRepository;

	public SuperHumanoServiceImpl(SuperHumanoRepository superHumanoRepository) {
		this.superHumanoRepository = superHumanoRepository;
	}

	@Override
	public List<SuperHumanoRs> getAll() {

		return superHumanoRepository.findAll().stream().map(sh -> {
			return new SuperHumanoRs(sh.getSuperHumanoId(), sh.getNombre(), sh.getCiudad(), sh.getCondicion());
		}).collect(Collectors.toList());

	}

	@Override
	public void save(SuperHumanoRq superHumanoRq) throws ApiException {
		SuperHumano superHumano;
		if (superHumanoRq.getSuperHumanoId() != null) {
			superHumano = new SuperHumano(superHumanoRq.getSuperHumanoId(), superHumanoRq.getNombre(),
					superHumanoRq.getCiudad(), superHumanoRq.getCondicion());
		} else {
			superHumano = new SuperHumano(superHumanoRq.getNombre(), superHumanoRq.getCiudad(),
					superHumanoRq.getCondicion());
		}
		superHumanoRepository.save(superHumano);

	}

	@Override
	public List<SuperHumanoRs> findByFilters(SuperHumanoRq superHumanoRq) throws ApiException {
		if (superHumanoRq.getNombre() != null && superHumanoRq.getNombre().isEmpty()) {
			superHumanoRq.setNombre(null);
		}

		if (superHumanoRq.getCiudad() != null && superHumanoRq.getCiudad().isEmpty()) {
			superHumanoRq.setCiudad(null);
		}

		return superHumanoRepository
				.findByFilters(superHumanoRq.getNombre(), superHumanoRq.getCiudad(), superHumanoRq.getCondicion())
				.stream().map(sh -> {
					return new SuperHumanoRs(sh.getSuperHumanoId(), sh.getNombre(), sh.getCiudad(), sh.getCondicion());
				}).collect(Collectors.toList());
	}

}
