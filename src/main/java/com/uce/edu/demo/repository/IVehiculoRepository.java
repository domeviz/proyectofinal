
package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;

public interface IVehiculoRepository {

	public void ingresarVehiculo(Vehiculo v);

	public void actualizarEstado(Vehiculo v);

	public List<Vehiculo> vehiculosDisponibles(String marca, String modelo);

	public Vehiculo buscarPorPlaca(String placa);

	public Vehiculo buscarPorId(Integer id);

	public void eliminar(Integer id);

	public List<Vehiculo> buscarTodos();

	public List<Vehiculo> buscarPorMarca(String marca);

	public List<Vehiculo> buscarPorFechas(String fechaInicio, String fechaFin);

}
