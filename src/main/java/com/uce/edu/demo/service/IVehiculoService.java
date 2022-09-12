package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVip;

public interface IVehiculoService {

	public List<Vehiculo> vehiculosDisponibles(String marca, String modelo);

	public Vehiculo buscarPorId(Integer id);

	public void insertar(Vehiculo vehiculo);

	public void actualizar(Vehiculo vehiculo);

	public void eliminar(Integer id);

	public List<Vehiculo> buscarTodos();

	public List<Vehiculo> buscarPorMarca(String marca);

	public Vehiculo buscarPorPlaca(String placa);

	public List<Vehiculo> buscarFechas(String fechaInicio, String fechaFin);

	public List<VehiculoVip> reporteVehiculosVip(Integer mes, Integer anio);
}
