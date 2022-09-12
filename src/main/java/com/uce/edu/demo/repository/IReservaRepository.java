package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.ClienteTo;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.RetiroTo;
import com.uce.edu.demo.repository.modelo.VehiculoTo;

public interface IReservaRepository {

	public void guardar(Reserva r);

	public void actualizarReserva(Reserva r);

	public List<Reserva> buscarReserva(String placa);

	public Reserva buscarAutoReserva(String numero);

	public Reserva buscarPlaca(Integer id, String placa);

	public Reserva buscarReservaPlaca(String placa);

	public List<Reserva> buscarPorFechas(String fechaInicio, String fechaFin);

	public ClienteTo reporteCliente(Integer id, String cedula);

	public VehiculoTo reporteVehiculo(Integer id, String placa);

	public RetiroTo buscarReservas(String numero);

}
