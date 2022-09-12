package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVip;

public interface IClienteService {

	public void registro(Cliente cliente);

	public void registroComoEmpleado(Cliente cliente);

	public List<Cliente> buscarPorApellido(String apellido);

	public void eliminar(Integer id);

	public Cliente buscarPorId(Integer id);

	public void actualizar(Cliente cliente);

	public Cliente buscarPorCedula(String cedula);

	public List<Cliente> buscarTodos();

	public List<ClienteVip> reporteClientesVip();
}
