package com.uce.edu.demo.repository.modelo;

public class RetiroTo {

	private String numero;
	private String placa;
	private String modelo;
	
	private String estado;
	private String fechaInicio;
	private String fechaFin;
	private String cedula;
	
	public RetiroTo(String placa, String modelo, String estado, String fechaInicio, String fechaFin, String numero, String cedula) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.numero = numero;
		this.cedula=cedula;
	}

	
	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	
	
	
}
