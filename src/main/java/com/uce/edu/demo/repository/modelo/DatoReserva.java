package com.uce.edu.demo.repository.modelo;

public class DatoReserva {

	private String placa;
	private String cedula;
	private String tarjeta;
	private String fechaInicio;
	private String fechaFin;

	public DatoReserva() {

	}

	public DatoReserva(String placa, String cedula, String tarjeta, String fechaInicio, String fechaFin) {
		super();
		this.placa = placa;
		this.cedula = cedula;
		this.tarjeta = tarjeta;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
}