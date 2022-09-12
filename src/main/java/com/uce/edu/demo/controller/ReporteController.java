package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Calendario;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVip;
import com.uce.edu.demo.repository.modelo.ReservaTo;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVip;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private IVehiculoService iVehiculoService;

	@Autowired
	private IReservaService reservaService;

	@GetMapping("/inicio")
	public String paginaPrincipal(Cliente cliente, Vehiculo vehiculo) {
		return "vistaInicio";
	}

	@GetMapping("/reporteClientesVIP")
	public String reporteClientesVIP(ClienteVip clienteVip, Model modelo) {
		List<ClienteVip> listaClientesVip = this.iClienteService.reporteClientesVip();
		modelo.addAttribute("listaClientesVip", listaClientesVip);
		return "vistaReporteClientesVip";
	}

	@GetMapping("/reporteVehiculosVIP")
	public String reporteVehiculosVIP(VehiculoVip vehiculoVip, Calendario calendario, Model modelo) {
		List<VehiculoVip> listaVehiculosVip = this.iVehiculoService
				.reporteVehiculosVip(Integer.valueOf(calendario.getMes()), Integer.valueOf(calendario.getAnio()));
		modelo.addAttribute("listaVehiculosVip", listaVehiculosVip);
		return "vistaListaVehiculosVip";
	}

	@GetMapping("/busquedaVIP")
	public String vista(Calendario calendario, Model modelo) {
		modelo.addAttribute("calendario", calendario);
		return "vistaReporteVehiculosVIP";
	}

	@GetMapping("/reporteReservas")
	public String reporteReserva(ReservaTo reserva, Model modelo) {

		List<ReservaTo> listaReservas = this.reservaService.buscarPorFechas(reserva.getFechaInicio(),
				reserva.getFechaFin());
		modelo.addAttribute("listaReservas", listaReservas);
		return "vistaReporteReservas";
	}

	@GetMapping("/buscarReservas")
	public String buscarReservas(ReservaTo reservaTo) {
		return "vistaBuscarReportes";
	}
}
