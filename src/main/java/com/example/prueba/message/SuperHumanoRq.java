package com.example.prueba.message;

import java.io.Serializable;

import lombok.Data;

@Data
public class SuperHumanoRq implements Serializable{
	
	/**
	 * Serial UUID.
	 */
	private static final long serialVersionUID = -5522170710629408796L;
	
	private Integer superHumanoId;

	private String nombre;

	private String ciudad;
	
	private Boolean condicion;

	public SuperHumanoRq(Integer superHumanoId, String nombre, String ciudad, Boolean condicion) {
		this.ciudad = ciudad;
		this.superHumanoId = superHumanoId;
		this.nombre = nombre;
		this.condicion = condicion;
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
