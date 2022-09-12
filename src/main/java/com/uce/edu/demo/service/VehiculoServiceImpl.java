package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IVehiculoRepository;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVip;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository iVehiculoRepository;

	@Override
	public List<Vehiculo> vehiculosDisponibles(String marca, String modelo) {
		return this.iVehiculoRepository.vehiculosDisponibles(marca, modelo);
	}

	@Override
	public Vehiculo buscarPorId(Integer id) {
		return this.iVehiculoRepository.buscarPorId(id);
	}

	@Override
	public void insertar(Vehiculo vehiculo) {
		vehiculo.setEstado("Disponible");
		this.iVehiculoRepository.ingresarVehiculo(vehiculo);
	}

	@Override
	public void actualizar(Vehiculo vehiculo) {
		this.iVehiculoRepository.actualizarEstado(vehiculo);
	}

	@Override
	public void eliminar(Integer id) {
		this.iVehiculoRepository.eliminar(id);
	}

	@Override
	public List<Vehiculo> buscarTodos() {
		return this.iVehiculoRepository.buscarTodos();
	}

	@Override
	public List<Vehiculo> buscarPorMarca(String marca) {
		return this.iVehiculoRepository.buscarPorMarca(marca);
	}

	@Override
	public Vehiculo buscarPorPlaca(String placa) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarPorPlaca(placa);
	}

	@Override
	public List<Vehiculo> buscarFechas(String fechaInicio, String fechaFin) {
		// TODO Auto-generated method stub
		return this.iVehiculoRepository.buscarPorFechas(fechaInicio, fechaFin);
	}
	
	@Override
	public List<VehiculoVip> reporteVehiculosVip(Integer mes, Integer anio) {
		LocalDate fechaInicio = LocalDate.of(anio, mes, 1);
		LocalDate fechaFin;

		if (mes == 2) {
			fechaFin = LocalDate.of(anio, mes, 28);
		} else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			fechaFin = LocalDate.of(anio, mes, 31);
		} else {
			fechaFin = LocalDate.of(anio, mes, 30);
		}

		String fInicio = fechaInicio.toString();
		String fFin = fechaFin.toString();

		List<Vehiculo> vehiculosFechasValidas = this.buscarFechas(fInicio, fFin);
		List<VehiculoVip> vehiculosVip = new ArrayList<VehiculoVip>();

		BigDecimal valorSubtotal = new BigDecimal(0);
		BigDecimal valorTotal = new BigDecimal(0);

		for (Vehiculo v : vehiculosFechasValidas) {

			List<Reserva> reservasVehiculo = v.getReservas();

			for (Reserva r : reservasVehiculo) {

				valorSubtotal = valorSubtotal.add(r.getCobro().getSubTotal());
				valorTotal = valorTotal.add(r.getCobro().getPrecioTotal());

			}
			VehiculoVip vehiculoVip = new VehiculoVip(v.getPlaca(), v.getModelo(), v.getMarca(), valorSubtotal,
					valorTotal);
			vehiculosVip.add(vehiculoVip);
		}

		List<VehiculoVip> reporteVehiculosVip = vehiculosVip.parallelStream()
				.sorted(Collections.reverseOrder(Comparator.comparing(VehiculoVip::getValorTotal)))
				.collect(Collectors.toList());

		return reporteVehiculosVip;

	}
}
