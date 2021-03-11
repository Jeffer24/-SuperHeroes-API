package com.example.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.prueba.entity.SuperHumano;

@Repository
public interface SuperHumanoRepository extends JpaRepository<SuperHumano, Integer> {

	@Query("SELECT sh FROM SuperHumano sh where (:nombre is null or sh.nombre like %:nombre%) "
			+ "and (:ciudad is null or sh.ciudad like %:ciudad%) and (:condicion is null or sh.condicion = :condicion)")
	List<SuperHumano> findByFilters(String nombre, String ciudad, Boolean condicion);

}
