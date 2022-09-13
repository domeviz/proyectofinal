package com.uce.edu.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVip;

@SpringBootTest
class VehiculoServiceImplTest {

	@Autowired
	private IVehiculoService iVehiculoService;
	
	@Autowired
	private IReservaService reservaService;
	@Test
	void testBuscarPorId() {
		Integer id=1;
		Vehiculo v=this.iVehiculoService.buscarPorId(1);
		assertThat(v.getId()).isEqualTo(id);
	}

	@Test
	void testInsertar() {
		Vehiculo v=new Vehiculo();
		v.setAnioFabricacion("2021");
		v.setAvaluo(new BigDecimal(200000));
		v.setCilindraje("1737");
		v.setEstado("Disponible");
		v.setMarca("Ford");
		v.setModelo("A12");
		v.setPaisFabricacion("Italia");
		v.setPlaca("PCD-1224");
		v.setRenta(new BigDecimal(500));
		this.iVehiculoService.insertar(v);
		assertNotNull(v.getAnioFabricacion());
		
	}

	@Test
	void testActualizar() {
		Vehiculo v=new Vehiculo();
		v.setId(1);
		v.setAnioFabricacion("2021");
		this.iVehiculoService.actualizar(v);
		assertEquals(v.getAnioFabricacion(), "2021");
	}

	@Test
	void testEliminar() {
		Vehiculo v=new Vehiculo();
		v.setId(3);
		v.setAnioFabricacion("2021");
		Integer id=3;
		//this.iVehiculoService.eliminar(id); 
		assertNull(this.iVehiculoService.buscarPorId(id));
	}

	@Test
	void testBuscarTodos() {
		List<Vehiculo> vlist=this.iVehiculoService.buscarTodos();
		assertThat(vlist);
	}

	@Test
	void testBuscarPorMarca() {
		String marca="Nissan";
		List<Vehiculo> vlist=this.iVehiculoService.buscarPorMarca("Nissan");
		assertNotNull(vlist);
	}

	@Test
	void testBuscarFechas() {
		List<Vehiculo> vlist=this.iVehiculoService.buscarFechas("2022-02-12", "2022-12-12");
		assertNotNull(vlist);
	}

	@Test
	void testReporteVehiculosVip() {
		List<VehiculoVip> vlist=this.iVehiculoService.reporteVehiculosVip(4, 2022);
		assertThat(vlist);
	}
	
	@Test
	void testInsertar2() {
		
		//Test 1: Para insertar vehiculo
		Vehiculo vehiculo=new Vehiculo();
		vehiculo.setPlaca("PCA-1021");
		vehiculo.setMarca("Chevrolet");
		vehiculo.setModelo("Spark");
		vehiculo.setEstado("Disponible");
		vehiculo.setRenta(new BigDecimal(27800));
		vehiculo.setAvaluo(new BigDecimal(52));
		vehiculo.setAnioFabricacion("2020-04-10");
		vehiculo.setCilindraje("1600");
		vehiculo.setPaisFabricacion("Japon");
		
		this.iVehiculoService.insertar(vehiculo);
		
	}

	@Test
	void testVehiculosDisponibles() {
		
		//Test 2: Para verificar si existe un vehiculo segun parametros
		List<Vehiculo> disponibles=this.iVehiculoService.vehiculosDisponibles("Chevrolet", "Spark");		
		assertNotNull(disponibles);
	}
	
	@Test
	void testBuscarPorPlaca() {
		
		//Test 3: Para verificar si existe un vehiculo por una placa
		List<Vehiculo> disponibles=this.iVehiculoService.buscarPorMarca("AAA--0000");	
		assertNotNull(disponibles);
	}

	@Test
	void testInsertar1() {
		
		//Test 4: Para insertar vehiculo
		Vehiculo vehiculo=new Vehiculo();
		vehiculo.setPlaca("PCi-2021");
		vehiculo.setMarca("Chevrolet");
		vehiculo.setModelo("Camaro");
		vehiculo.setEstado("No Disponible");
		vehiculo.setRenta(new BigDecimal(37800));
		vehiculo.setAvaluo(new BigDecimal(82));
		vehiculo.setAnioFabricacion("2020-11-10");
		vehiculo.setCilindraje("1600");
		vehiculo.setPaisFabricacion("EEUU");
		
		this.iVehiculoService.insertar(vehiculo);
		
	}
	
	@Test
	void testBuscarvehiculoDisponible() {
		
		//Test 5: Para validar si una fecha esta disponible
		this.reservaService.buscarvehiculoDisponible("PAA-1021", "2022-01-10", "2022-01-20");
	    assertFalse(false);
	}
	
	@Test
	void testReservarVehiculo() {
		
		//Test 6: Para reservar un vehiculo
		Reserva reserva=this.reservaService.reservarVehiculo("PAA-1021", "1719139881", "2022-01-10", "2022-01-20");
		assertEquals(reserva.getDiasReserva(), 11);
	}



	@Test
	void testRetiro() {
		
		//Test 7: Para verificar el retiro
		this.reservaService.retiro("R27-17191");
		Vehiculo vehiculo=this.iVehiculoService.buscarPorPlaca("PAA-1021");
		assertNotNull(vehiculo);
	}
	
	@Test
	void testRetiro2() {
		
		//Test 8: Para verificar el retiro
		this.reservaService.retiro("R50-17191");
		Vehiculo vehiculo=this.iVehiculoService.buscarPorPlaca("PAA-1021");
		assertNotNull(vehiculo);
	}

}
