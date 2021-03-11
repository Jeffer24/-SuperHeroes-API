package com.example.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.prueba.entity.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

	@Query("SELECT v FROM Vehiculo v where (:descripcion is null or v.descripcion like %:descripcion%) "
			+ "and (:tipoVehiculo is null or v.tipoVehiculo like %:tipoVehiculo%)")
	List<Vehiculo> findByFilters(String descripcion, String tipoVehiculo);
}
