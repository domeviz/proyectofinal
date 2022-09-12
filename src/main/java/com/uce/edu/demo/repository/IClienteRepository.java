package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;

public interface IClienteRepository {

	public List<Cliente> buscarPorApellido(String apellido);

	public List<Cliente> buscarTodos();

	public void eliminar(Integer id);

	public void insertar(Cliente cliente);

	public Cliente buscarCedula(String cedula);

	public Cliente buscarPorId(Integer id);

	public void actualizar(Cliente cliente);
}
