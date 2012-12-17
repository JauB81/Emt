package com.researchmobile.emt.entity;

import java.io.Serializable;

public class Historial implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String historial;
	private String tipo;
	private String fecha;
	public String getHistorial() {
		return historial;
	}
	public void setHistorial(String historial) {
		this.historial = historial;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
