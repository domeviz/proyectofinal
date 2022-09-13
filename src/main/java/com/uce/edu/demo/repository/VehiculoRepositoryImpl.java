package com.uce.edu.demo.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Vehiculo;

@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void ingresarVehiculo(Vehiculo vehiculo) {
		this.entityManager.persist(vehiculo);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizarEstado(Vehiculo v) {
		this.entityManager.merge(v);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> vehiculosDisponibles(String marca, String modelo) {
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(
				"SELECT v FROM Vehiculo v WHERE v.marca=:datoMarca AND v.modelo=:datoModelo", Vehiculo.class);
		myQuery.setParameter("datoMarca", marca);
		myQuery.setParameter("datoModelo", modelo);
		return myQuery.getResultList();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPorPlaca(String placa) {
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.placa=:datoPlaca ", Vehiculo.class);
		myQuery.setParameter("datoPlaca", placa);
		return myQuery.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPorId(Integer id) {
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void eliminar(Integer id) {
		Vehiculo v = this.buscarPorId(id);
		this.entityManager.remove(v);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarTodos() {
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class);
		return myQuery.getResultList();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarPorMarca(String marca) {
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.marca =:datoMarca", Vehiculo.class);
		myQuery.setParameter("datoMarca", marca);
		return myQuery.getResultList();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarPorFechas(String fechaInicio, String fechaFin) {
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(
				"Select v from Vehiculo v JOIN v.reservas r WHERE r.fechaInicio >= :datoFechaInicio AND r.fechaFin <= :datoFechaFin ",
				Vehiculo.class);
		myQuery.setParameter("datoFechaInicio", fechaInicio);
		myQuery.setParameter("datoFechaFin", fechaFin);

		List<Vehiculo> listaVehiculos = myQuery.getResultList();

		Set<Vehiculo> vehiculosUnicos = new HashSet<Vehiculo>(listaVehiculos);
		listaVehiculos.clear();
		listaVehiculos.addAll(vehiculosUnicos);

		return listaVehiculos;

	}

}
