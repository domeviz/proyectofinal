package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

public class ClienteVip {

	private String nombre;
	private String apellido;
	private String cedula;
	private BigDecimal valorIVA;
	private BigDecimal valorTotal;

	public ClienteVip() {

	}

	public ClienteVip(String nombre, String apellido, String cedula, BigDecimal valorIVA, BigDecimal valorTotal) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.valorIVA = valorIVA;
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "ClienteVip [nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula + ", valorIVA="
				+ valorIVA + ", valorTotal=" + valorTotal + "]";
	}

	// GET Y SET
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public BigDecimal getValorIVA() {
		return valorIVA;
	}

	public void setValorIVA(BigDecimal valorIVA) {
		this.valorIVA = valorIVA;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}