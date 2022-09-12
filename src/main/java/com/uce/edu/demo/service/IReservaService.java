package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.ReservaTo;
import com.uce.edu.demo.repository.modelo.RetiroTo;

public interface IReservaService {

	public Reserva reservarVehiculo(String placa, String cedula, String inicio, String fin);

	public boolean buscarvehiculoDisponible(String placa, String inicio, String fin);

	public void retiro(String numero);

	public Reserva buscarAutoReserva(String numero);

	public List<ReservaTo> buscarPorFechas(String fechaInicio, String fechaFin);

	public RetiroTo buscarReservas(String numero);
}
