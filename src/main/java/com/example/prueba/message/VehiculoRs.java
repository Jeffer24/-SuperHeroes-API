package com.example.prueba.message;

import java.io.Serializable;

public class VehiculoRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8639960763272073043L;

	private Integer vehiculoId;

	private String descripcion;

	private String tipoVehiculo;

	public VehiculoRs(Integer vehiculoId, String descripcion, String tipoVehiculo) {
		this.vehiculoId = vehiculoId;
		this.descripcion = descripcion;
		this.tipoVehiculo = tipoVehiculo;
	}

	public Integer getVehiculoId() {
		return vehiculoId;
	}

	public void setVehiculoId(Integer vehiculoId) {
		this.vehiculoId = vehiculoId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}


}
