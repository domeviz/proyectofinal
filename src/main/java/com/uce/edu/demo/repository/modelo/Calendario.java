package com.uce.edu.demo.repository.modelo;

public class Calendario {

	private String mes;

	private String anio;

	public Calendario() {

	}

	public Calendario(String mes, String anio) {
		super();
		this.mes = mes;
		this.anio = anio;
	}

	@Override
	public String toString() {
		return "Calendario [mes=" + mes + ", anio=" + anio + "]";
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

}