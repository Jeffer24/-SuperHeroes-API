package com.example.prueba.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.prueba.message.VehiculoRq;
import com.example.prueba.message.VehiculoRs;
import com.example.prueba.model.exceptions.ApiException;
import com.example.prueba.repository.VehiculoRepository;
import com.example.prueba.service.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService {

	private VehiculoRepository vehiculoRepository;

	public VehiculoServiceImpl(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

	@Override
	public List<VehiculoRs> findByFilters(VehiculoRq vehiculoRq) throws ApiException {
		if (vehiculoRq.getDescripcion() != null && vehiculoRq.getDescripcion().isEmpty()) {
			vehiculoRq.setDescripcion(null);
		}

		if (vehiculoRq.getTipoVehiculo() != null && vehiculoRq.getTipoVehiculo().isEmpty()) {
			vehiculoRq.setTipoVehiculo(null);
		}

		return vehiculoRepository.findByFilters(vehiculoRq.getDescripcion(), vehiculoRq.getTipoVehiculo()).stream()
				.map(v -> {
					return new VehiculoRs(v.getVehiculoId(), v.getDescripcion(), v.getTipoVehiculo());
				}).collect(Collectors.toList());
	}

}
