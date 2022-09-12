package com.uce.edu.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.IVehiculoRepository;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteTo;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.ReservaTo;
import com.uce.edu.demo.repository.modelo.RetiroTo;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoTo;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Autowired
	private IReservaRepository reservaRepository;

	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public Reserva reservarVehiculo(String placa, String cedula, String inicio, String fin) {
		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(placa);
		Cliente cliente = this.clienteRepository.buscarCedula(cedula);

		LocalDate fInicio = LocalDate.parse(inicio);
		LocalDate fFin = LocalDate.parse(fin);

		int dias = Period.between(fInicio, fFin).getDays() + 1;

		Reserva reserva = new Reserva();
		reserva.setNumero(cedula);
		reserva.setDiasReserva(dias);
		reserva.setFechaInicio(inicio);
		reserva.setFechaFin(fin);
		reserva.setEstado("Reservado");
		reserva.setCliente(cliente);
		reserva.setVehiculo(vehiculo);

		this.reservaRepository.guardar(reserva);
		System.out.println("El vehiculo ha sido reservado");
		return reserva;
	}

	@Override
	public boolean buscarvehiculoDisponible(String placa, String inicio, String fin) {
		List<Reserva> reservas = this.reservaRepository.buscarReserva(placa);
		LocalDate fInicio = LocalDate.parse(inicio);
		LocalDate fFin = LocalDate.parse(fin);

		if (reservas.size() == 0) {
			return true;
		} else {

			for (Reserva dato : reservas) {
				LocalDate fechaInicio = LocalDate.parse(dato.getFechaInicio());
				LocalDate fechaFin = LocalDate.parse(dato.getFechaFin());

				if (fechaInicio.compareTo(fInicio) > 0 && fechaInicio.compareTo(fFin) > 0
						|| fechaFin.compareTo(fInicio) < 0 && fechaFin.compareTo(fFin) < 0) {
					// System.out.println("Disponible, excepto para estas fechas
					// :"+dato.getFechaInicio()+" - "+dato.getFechaFin());
				} else {
					System.out.println(
							"El auto ya esta reservado :" + dato.getFechaInicio() + " - " + dato.getFechaFin());
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public void retiro(String numero) {
		Reserva reserva = this.reservaRepository.buscarAutoReserva(numero);
		reserva.setEstado("Retirado");
		this.reservaRepository.actualizarReserva(reserva);

		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(reserva.getVehiculo().getPlaca());
		vehiculo.setEstado("No Disponible");
		this.vehiculoRepository.actualizarEstado(vehiculo);
	}

	@Override
	public Reserva buscarAutoReserva(String numero) {
		return this.reservaRepository.buscarAutoReserva(numero);
	}

	@Override
	public List<ReservaTo> buscarPorFechas(String fechaInicio, String fechaFin) {

		List<ReservaTo> listaReporte = new ArrayList<>();
		List<Reserva> listaReservas = this.reservaRepository.buscarPorFechas(fechaInicio, fechaFin);

		for (Reserva lista : listaReservas) {
			ClienteTo cliente = this.reservaRepository.reporteCliente(lista.getId(),
					lista.getCliente().getNumeroCedula());
			VehiculoTo vehiculo = this.reservaRepository.reporteVehiculo(lista.getId(), lista.getVehiculo().getPlaca());

			ReservaTo reserva = new ReservaTo();

			reserva.setNombre(cliente.getNombre());
			reserva.setApellido(cliente.getApellido());
			reserva.setPlaca(vehiculo.getPlaca());
			reserva.setModelo(vehiculo.getModelo());
			reserva.setMarca(vehiculo.getMarca());

			reserva.setNumero(lista.getNumero());
			reserva.setDiasReserva(lista.getDiasReserva());
			reserva.setFechaInicio(lista.getFechaInicio());
			reserva.setFechaFin(lista.getFechaFin());
			reserva.setEstado(lista.getEstado());

			listaReporte.add(reserva);
		}

		return listaReporte;
	}

	@Override
	public RetiroTo buscarReservas(String numero) {
		// TODO Auto-generated method stub
		return this.reservaRepository.buscarReservas(numero);
	}

}