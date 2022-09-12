package com.uce.edu.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uce.edu.demo.repository.modelo.Cliente;


@SpringBootTest
class ClienteServiceImplTest {

	private static Logger LOG = Logger.getLogger(ClienteServiceImplTest.class);

	@Autowired
	private IClienteService iClienteService;

	@BeforeAll
	public static void beforeClass() throws Exception {

		LOG.info("Inicia la prueba");

	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {

		LOG.info("Termina la prueba");

	}

	@BeforeEach
	public void setUp() throws Exception {

		LOG.info("Inicia la prueba del metodo");

	}

	@AfterEach
	public void tearDown() throws Exception {

		LOG.info("Termina la prueba del metodo");
	}

	@Test
	public void testBuscarPorApellido() {

		LOG.info("Test buscar Cliente por Apellido");
		String apellido = "Buitron";
		List<Cliente> clientes = this.iClienteService.buscarPorApellido(apellido);

		clientes.forEach(c -> LOG.info(clientes));

		assertThat(clientes).size().isGreaterThan(0);

	}

	@Test

	public void testEliminar() {

		LOG.info("Test Borrar Cliente con id 3");
		Integer id = 3;

		this.iClienteService.eliminar(id);

		assertNull(this.iClienteService.buscarPorId(id));

	}

	@Test
	public void testBuscarPorId() {

		LOG.info("Test Buscar Cliente con id 1");

		Integer id = 1;
		Cliente cliente = this.iClienteService.buscarPorId(id);

		assertThat(cliente.getId()).isEqualTo(id);
	}

	@Test
	public void testActualizar() {

		LOG.info("Test Actualzar Actualizar con id 2");
		Integer id = 2;
		String nombre = "ZEUS";

		Cliente cliente = new Cliente();

		cliente.setNombre("ZEUS");
		cliente.setApellido("Xaxom");

		cliente.setFechaNacimiento("1900-20-10");
		cliente.setGenero("M");
		cliente.setTelefono("0987485963");
		cliente.setId(id);

		this.iClienteService.actualizar(cliente);

		Cliente clieneActualizado = this.iClienteService.buscarPorCedula("00000008");

		assertThat(clieneActualizado.getNombre()).isEqualTo(nombre);

	}

	@Test
	public void testRegistro() {

		LOG.info("Test Registro de Cliente");

		Cliente cliente = new Cliente();
		cliente.setNombre("Andreina");
		cliente.setApellido("Reasco");
		cliente.setNumeroCedula("456");
		cliente.setFechaNacimiento("1900-20-10");
		cliente.setGenero("M");
		cliente.setTelefono("0987485963");

		this.iClienteService.registro(cliente);

		assertNotNull(this.iClienteService.buscarPorId(cliente.getId()));

	}

	@Test
	public void testRegistroComoEmpleado() {

		LOG.info("Test Registro de Cliente por parte de Empleado");

		String empleado = "E";

		Cliente cliente = new Cliente();
		cliente.setNombre("Jazmin");
		cliente.setApellido("Torres");
		cliente.setNumeroCedula("000000001");
		cliente.setFechaNacimiento("1900-20-10");
		cliente.setGenero("F");
		cliente.setTelefono("0987485963");

		this.iClienteService.registroComoEmpleado(cliente);

		Cliente clienteIngresoEmpleado = this.iClienteService.buscarPorId(cliente.getId());

		assertThat(clienteIngresoEmpleado.getRegistro()).isEqualTo(empleado);

	}

	@Test
	public void testBuscarPorCedula() {

		LOG.info("Test Buscar Cliente por Cedula");

		LOG.info("Test Busqueda Cedula con CLiente");
		String cedula = "1917114480";
		Cliente cliente = this.iClienteService.buscarPorCedula(cedula);

		assertThat(cliente.getNumeroCedula()).isEqualTo(cedula);

	}

	@Test
	public void testBuscarTodos() {

		LOG.info("Test Buscar Todos los Cliente");

		List<Cliente> clientesTodos = this.iClienteService.buscarTodos();

		clientesTodos.forEach(c -> LOG.info(clientesTodos));

		assertThat(clientesTodos).size().isGreaterThan(0);
	}

}
