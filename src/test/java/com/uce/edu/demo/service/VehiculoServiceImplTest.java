package com.uce.edu.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVip;

@SpringBootTest
class VehiculoServiceImplTest {

	@Autowired
	private IVehiculoService iVehiculoService;
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

}
