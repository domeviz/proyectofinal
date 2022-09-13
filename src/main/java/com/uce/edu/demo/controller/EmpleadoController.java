package com.uce.edu.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.RetiroTo;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
	private IClienteService iClienteService;
	@Autowired
	private IVehiculoService iVehiculoService;
	@Autowired
	private IReservaService reservaService;

	static List<String> genero = null;

	static {
		genero = new ArrayList<>();
		genero.add("F");
		genero.add("M");
	}

	@GetMapping("/inicio")
	public String paginaPrincipal(Cliente cliente, Vehiculo vehiculo) {
		return "vistaPrincipalEmpleado";
	}

	// CLIENTES
	@PostMapping("/registroCliente")
	public String registrarCliente(Cliente cliente) {
		this.iClienteService.registroComoEmpleado(cliente);
		return "redirect:/empleados/buscarClientes";
	}

	@GetMapping("/nuevoCliente")
	public String paginaNuevoCliente(Cliente cliente, Model modelo) {
		modelo.addAttribute("genero", genero);
		return "vistaNuevoCliente";
	}

	@GetMapping("/buscarClientes")
	public String buscarListaClientes(Model modelo) {
		List<Cliente> listaClientes = this.iClienteService.buscarTodos();
		modelo.addAttribute("clientes", listaClientes);
		return "vistaListaClientes";
	}

	@GetMapping("/buscarApellido")
	public String buscarClienteApellido(Model modelo, Cliente cliente) {

		List<Cliente> lista = this.iClienteService.buscarPorApellido(cliente.getApellido());

		modelo.addAttribute("clientes", lista);
		return "vistaListaClientes";

	}

	@GetMapping("/buscarClientesApellido")
	public String buscarClientesApellido(Cliente cliente) {
		return "vistaBuscarClientesApellido";
	}

	@GetMapping("/verCliente/{idCliente}")
	public String visualizarCliente(@PathVariable("idCliente") Integer id, Model modelo) {
		Cliente cliente = this.iClienteService.buscarPorId(id);
		modelo.addAttribute("cliente", cliente);
		return "vistaCliente";
	}

	@GetMapping("/buscarCliente/{idCliente}")
	public String buscarCliente(@PathVariable("idCliente") Integer id, Model modelo) {

		Cliente cliente = this.iClienteService.buscarPorId(id);
		modelo.addAttribute("cliente", cliente);
		return "vistaEmpleadoActualizarCliente";
	}

	@PutMapping("/actualizarCliente/{idCliente}")
	public String actualizarClienteE(@PathVariable("idCliente") Integer id, Cliente cliente) {
		cliente.setId(id);
		this.iClienteService.actualizar(cliente);
		return "redirect:/empleados/buscarClientes";
	}

	@DeleteMapping("/borrarCliente/{idCliente}")
	public String borrarCliente(@PathVariable("idCliente") Integer id) {
		this.iClienteService.eliminar(id);
		return "redirect:/empleados/buscarClientes";
	}

	// VEHICULOS

	@GetMapping("/buscar")
	public String buscarTodos(Model modelo) {
		List<Vehiculo> listaVehiculos = this.iVehiculoService.buscarTodos();
		modelo.addAttribute("listaVehiculos", listaVehiculos);
		return "vistaListaVehiculos";
	}

	@GetMapping("/buscarMarca")
	public String buscarMarca(Model modelo, Vehiculo vehiculo) {
		List<Vehiculo> listaVehiculos = this.iVehiculoService.buscarPorMarca(vehiculo.getMarca());
		modelo.addAttribute("listaVehiculos", listaVehiculos);
		return "vistaListaVehiculos";
	}

	@GetMapping("/buscarVehiculosMarca")
	public String buscarVehiculosMarca(Vehiculo vehiculo) {
		return "vistaBuscarVehiculosMarca";
	}

	@GetMapping("/verVehiculo/{idVehiculo}")
	public String buscarVehiculo(@PathVariable("idVehiculo") Integer id, Model modelo) {
		Vehiculo vehiculo = this.iVehiculoService.buscarPorId(id);
		modelo.addAttribute("vehiculo", vehiculo);
		return "vistaVehiculo";
	}

	@GetMapping("/buscarVehiculo/{idVehiculo}")
	public String buscarVehiculoActualizar(@PathVariable("idVehiculo") Integer id, Model modelo) {
		Vehiculo vehiculo = this.iVehiculoService.buscarPorId(id);
		modelo.addAttribute("vehiculo", vehiculo);
		return "vistaVehiculoActualizar";
	}

	@PutMapping("/actualizar/{idVehiculo}")
	public String actualizarVehiculo(@PathVariable("idVehiculo") Integer id, Vehiculo vehiculo, Model modelo) {
		vehiculo.setId(id);
		this.iVehiculoService.actualizar(vehiculo);
		List<String> estadoL = Arrays.asList("Disponible", "No Disponible");
		modelo.addAttribute("estadoL", estadoL);
		return "redirect:/empleados/buscar";
	}

	@DeleteMapping("/borrar/{idVehiculo}")
	public String borrarVehiculo(@PathVariable("idVehiculo") Integer id, RedirectAttributes a) {
		Vehiculo v = this.iVehiculoService.buscarPorId(id);
		if (v.getEstado().equals("No Disponible")) {
			System.out.println("No es posible eliminar");
			a.addFlashAttribute("error", "ATENCION: Este vehiculo No esta disponible");
			return "redirect:/empleados/buscar";
		}
		this.iVehiculoService.eliminar(id);
		return "redirect:/empleados/buscar";
	}

	@PostMapping("/insertar")
	public String insertarVehiculo(Vehiculo vehiculo) {
		this.iVehiculoService.insertar(vehiculo);
		return "redirect:/empleados/buscar";
	}

	@GetMapping("/nuevoVehiculo")
	public String paginaNuevoVehiculo(Vehiculo vehiculo) {
		return "vistaNuevoVehiculo";
	}

	@GetMapping("/retirarVehiculo")
	public String retirarVehiculo(RetiroTo retiroTo, Model modelo) {

		// this.reservaService.retiro(retiroTo.getNumero());
		RetiroTo retiro = this.reservaService.buscarReservas(retiroTo.getNumero());
		modelo.addAttribute("retiro", retiro);
		return "vistaRetiroVehiculo";
	}

	@PostMapping("/insertarRetiro")
	public String insertarRetiro(RetiroTo retiroTo) {
		this.reservaService.retiro(retiroTo.getNumero());
		// modelo.addAttribute("retiro", retiroTo);
		return "redirect:/empleados/inicio";
	}

	@GetMapping("/retirarSinReserva")
	public String reservavacia(Vehiculo vehiculo, RetiroTo retiroTo, Model modelo) {
		modelo.addAttribute("vehiculo", vehiculo);
		modelo.addAttribute("retiroTo", retiroTo);
		return "vistaSinReserva";
	}
	
	@GetMapping("/retiroVehiculo")
	public String retiroVehiculo(RetiroTo retiroTo) {
		return "vistaRetirarVehiculo";
	}
}