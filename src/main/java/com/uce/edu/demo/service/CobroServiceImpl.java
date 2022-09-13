package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.ICobroRepository;
import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.Reserva;

@Service
public class CobroServiceImpl implements ICobroService {

	@Autowired
	private ICobroRepository cobroRepository;

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void realizarPago(String numeroTarjeta, BigDecimal renta, Reserva reserva) {

		BigDecimal rentaDias = renta.multiply(new BigDecimal(reserva.getDiasReserva()));
		BigDecimal precioIva = rentaDias.multiply(new BigDecimal(0.12));
		BigDecimal precioTotal = rentaDias.add(precioIva);

		Cobro cobro = new Cobro();
		cobro.setTarjeta(numeroTarjeta);
		cobro.setFechaPago(LocalDateTime.now());

		cobro.setSubTotal(rentaDias);
		cobro.setPrecioIva(precioIva);
		cobro.setPrecioTotal(precioTotal);
		cobro.setReserva(reserva);

		this.cobroRepository.insertarPago(cobro);

	}

}
