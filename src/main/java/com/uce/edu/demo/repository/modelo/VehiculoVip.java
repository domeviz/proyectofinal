package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

public class VehiculoVip {

	private String placa;
	private String modelo;
	private String marca;
	private BigDecimal valorSubtotal;
	private BigDecimal valorTotal;

	public VehiculoVip(String placa, String modelo, String marca, BigDecimal valorSubtotal, BigDecimal valorTotal) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.valorSubtotal = valorSubtotal;
		this.valorTotal = valorTotal;
	}

	public VehiculoVip() {

	}

	@Override
	public String toString() {
		return "VehiculoVip [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", valorSubtotal="
				+ valorSubtotal + ", valorTotal=" + valorTotal + "]";
	}

	// GET Y SET
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

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}