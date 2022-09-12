package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.ClienteTo;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.RetiroTo;
import com.uce.edu.demo.repository.modelo.VehiculoTo;

@Repository
@Transactional
public class ReservaRepositoryImpl implements IReservaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void guardar(Reserva r) {
		this.entityManager.persist(r);
	}

	@Override
	public void actualizarReserva(Reserva r) {
		this.entityManager.merge(r);
	}

	@Override
	public List<Reserva> buscarReserva(String placa) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r INNER JOIN r.vehiculo v WHERE v.placa=:datoVehiculo", Reserva.class);
		myQuery.setParameter("datoVehiculo", placa);
		return myQuery.getResultList();
	}

	@Override
	public Reserva buscarAutoReserva(String numero) {
		TypedQuery<Reserva> myQuery = this.entityManager
				.createQuery("SELECT r FROM Reserva r WHERE r.numero=:datoNumero", Reserva.class);
		myQuery.setParameter("datoNumero", numero);
		return myQuery.getSingleResult();
	}

	@Override
	public Reserva buscarPlaca(Integer id, String placa) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r INNER JOIN r.vehiculo v WHERE r.id=:datoId AND v.placa=:datoVehiculo",
				Reserva.class);
		myQuery.setParameter("datoId", id);
		myQuery.setParameter("datoVehiculo", placa);
		return myQuery.getSingleResult();
	}

	@Override
	public Reserva buscarReservaPlaca(String placa) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r INNER JOIN r.vehiculo v WHERE v.placa=:datoVehiculo", Reserva.class);
		myQuery.setParameter("datoVehiculo", placa);
		return myQuery.getSingleResult();
	}

	@Override
	public List<Reserva> buscarPorFechas(String fechaInicio, String fechaFin) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r WHERE r.fechaInicio >= :datoFechaInicio AND r.fechaFin <= :datoFechaFin",
				Reserva.class);
		myQuery.setParameter("datoFechaInicio", fechaInicio);
		myQuery.setParameter("datoFechaFin", fechaFin);

		return myQuery.getResultList();
	}

	@Override
	public ClienteTo reporteCliente(Integer id, String cedula) {
		TypedQuery<ClienteTo> myQuery = this.entityManager.createQuery(
				"SELECT NEW com.uce.edu.demo.modelo.ClienteTo(c.nombre, c.apellido)  FROM Reserva r INNER JOIN r.cliente c WHERE r.id=:datoId AND c.numeroCedula=:datoCedula",
				ClienteTo.class);
		myQuery.setParameter("datoId", id);
		myQuery.setParameter("datoCedula", cedula);
		return myQuery.getSingleResult();
	}

	@Override
	public VehiculoTo reporteVehiculo(Integer id, String placa) {
		TypedQuery<VehiculoTo> myQuery = this.entityManager.createQuery(
				"SELECT NEW com.uce.edu.demo.modelo.VehiculoTo(v.placa, v.modelo, v.marca) FROM Reserva r INNER JOIN r.vehiculo v WHERE r.id=:datoId AND v.placa=:datoPlaca",
				VehiculoTo.class);
		myQuery.setParameter("datoId", id);
		myQuery.setParameter("datoPlaca", placa);
		return myQuery.getSingleResult();
	}

	@Override
	public RetiroTo buscarReservas(String numero) {
		TypedQuery<RetiroTo> myQuery = this.entityManager.createQuery(
				"SELECT  NEW  com.uce.edu.demo.modelo.RetiroTo(v.placa, v.modelo, r.estado, r.fechaInicio, r.fechaFin, r.numero, c.numeroCedula) FROM Reserva r "
						+ "INNER JOIN r.vehiculo v INNER JOIN r.cliente c WHERE r.numero=:datoNumero",
				RetiroTo.class);
		myQuery.setParameter("datoNumero", numero);
		return myQuery.getSingleResult();
	}

}
