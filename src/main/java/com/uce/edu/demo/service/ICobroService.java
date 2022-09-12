package com.uce.edu.demo.service;

import java.math.BigDecimal;

import com.uce.edu.demo.repository.modelo.Reserva;

public interface ICobroService {

	public void realizarPago(String numeroTarjeta, BigDecimal costo, Reserva reserva);
}
