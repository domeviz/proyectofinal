package com.uce.edu.demo.repository.modelo;

public class VehiculoTo {

	// Vehiculo
	private String placa;
	private String modelo;
	private String marca;

	public VehiculoTo() {

	}

	public VehiculoTo(String placa, String modelo, String marca) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}
