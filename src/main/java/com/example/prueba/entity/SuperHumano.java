package com.example.prueba.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class SuperHumano implements Serializable {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 2167047005624346649L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "super_humano_id")
	private Integer superHumanoId;
	
	private String nombre;

	private String ciudad;

	private Boolean condicion;
	
	public SuperHumano(Integer superHumanoId, String nombre, String ciudad, Boolean condicion) {
		this.superHumanoId = superHumanoId;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.condicion = condicion;
	}

	public SuperHumano(String nombre, String ciudad, Boolean condicion) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.condicion = condicion;
	}
	
	public SuperHumano() {
		
	}

	public Integer getSuperHumanoId() {
		return superHumanoId;
	}

	public void setSuperHumanoId(Integer superHumanoId) {
		this.superHumanoId = superHumanoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Boolean getCondicion() {
		return condicion;
	}

	public void setCondicion(Boolean condicion) {
		this.condicion = condicion;
	}

}
