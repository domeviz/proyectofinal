package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Cliente;

@Repository
@Transactional
public class ClienteRepositoryImpl implements IClienteRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Cliente buscarCedula(String cedula) {
		TypedQuery<Cliente> myQuery=this.entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.numeroCedula=:datoCedula"
				,Cliente.class);
		myQuery.setParameter("datoCedula", cedula);
		return myQuery.getSingleResult();
	}
	
	
	
	
	@Override
	public void insertar(Cliente cliente) {
		this.entityManager.persist(cliente);
	}
	
	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Cliente> buscarPorApellido(String apellido) {
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c WHERE c.apellido = :datoApellido",Cliente.class);
		myQuery.setParameter("datoApellido", apellido);
		return myQuery.getResultList();
	}
	
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente buscarPorId(Integer id) {
		return this.entityManager.find(Cliente.class, id);
	}
	@Override
	//@Transactional(value = TxType.MANDATORY)
	public void eliminar(Integer id) {
		this.entityManager.remove(this.buscarPorId(id));
	}

	@Override
	//transactional
	public void actualizar(Cliente cliente) {
		this.entityManager.merge(cliente);
	}

	@Override
	public List<Cliente> buscarTodos() {
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
		return myQuery.getResultList();
	}
}
