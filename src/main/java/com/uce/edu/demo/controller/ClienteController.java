package com.uce.edu.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.DatoReserva;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.ICobroService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private ICobroService cobroService;

	@GetMapping("/inicio")
	public String paginaPrincipal(Cliente cliente, Vehiculo vehiculo) {
		return "vistaPrincipalClientes";
	}

	@PostMapping("/registro")
	public String registrarCliente(Cliente cliente) {

		this.iClienteService.registro(cliente);
		// cambiar a redireccionar a pagina principal
		return "redirect:/clientes/inicio";
	}

	@GetMapping("/buscarCliente")
	public String buscarClienteCedula(Model modelo, Cliente cliente) {

		Cliente clie = this.iClienteService.buscarPorCedula(cliente.getNumeroCedula());

		modelo.addAttribute("cliente", clie);
		return "vistaClienteActualizar";

	}

	@PutMapping("/actualizarCliente/{idCliente}")
	public String actualizarCliente(@PathVariable("idCliente") Integer id, Cliente cliente) {

		cliente.setId(id);
		this.iClienteService.actualizar(cliente);
		return "redirect:/empleados/buscarClientes";
	}

	@GetMapping("/buscarAutos")
	public String buscarAutosDisponibles(Model modelo, Vehiculo vehiculo) {

		List<Vehiculo> listaVehiculos = this.vehiculoService.vehiculosDisponibles(vehiculo.getMarca(),
				vehiculo.getModelo());
		modelo.addAttribute("vehiculos", listaVehiculos);
		return "vistaAutosDisponibles";
	}

	@GetMapping("/reservas")
	public String buscarAutosDisponibles(DatoReserva datoReserva, RedirectAttributes a) {

		boolean disponible = this.reservaService.buscarvehiculoDisponible(datoReserva.getPlaca(),
				datoReserva.getFechaInicio(), datoReserva.getFechaFin());

		if (disponible == false) {
			a.addFlashAttribute("error", "ATENCION: Este vehiculo No esta disponible");
			return "redirect:/clientes/mostrarDisponibles";
		}

		return "vistaReservar";
	}

	@PostMapping("/generarReserva")
	public String reservarAuto(DatoReserva datoReserva, Model modelo) {
		Reserva nuevaReserva = this.reservaService.reservarVehiculo(datoReserva.getPlaca(), datoReserva.getCedula(),
				datoReserva.getFechaInicio(), datoReserva.getFechaFin());
		BigDecimal precio = this.vehiculoService.buscarPorPlaca(datoReserva.getPlaca()).getRenta();
		// Reserva
		// datos=this.reservaService.buscarAutoReserva(datosReserva.getCedula());
		System.out.println(precio);
		this.cobroService.realizarPago(datoReserva.getTarjeta(), precio, nuevaReserva);
		modelo.addAttribute("datosReserva", datoReserva);
		return "vistaListaReservas";
	}

	@GetMapping("/mostrarDisponibles")
	public String autosDisponibles(DatoReserva datoReserva) {
		return "vistaListaAutosDisponibles";
	}
	
	@GetMapping("/vehiculosClientes")
	public String vehiculosClientes(Vehiculo vehiculo) {
		return "vistaListaVehiculosCliente";
	}
	
	@GetMapping("/registroCliente")
	public String registro(Cliente cliente, Vehiculo vehiculo) {
		return "vistaRegistroCliente";
	}
	
	@GetMapping("/vistaActualizar")
	public String vistaACtualizar(Cliente cliente) {
		return "vistaActualizarCliente";
	}

}