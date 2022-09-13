package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVip;
import com.uce.edu.demo.repository.modelo.Reserva;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository iClienteRepository;

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public List<Cliente> buscarPorApellido(String apellido) {
		return this.iClienteRepository.buscarPorApellido(apellido);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void eliminar(Integer id) {
		this.iClienteRepository.eliminar(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public Cliente buscarPorId(Integer id) {
		return this.iClienteRepository.buscarPorId(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void actualizar(Cliente cliente) {
		Cliente c = this.buscarPorId(cliente.getId());
		String cedu = c.getNumeroCedula();
		String reg = c.getRegistro();
		cliente.setNumeroCedula(cedu);
		cliente.setRegistro(reg);
		// en el momento de actualizar se obtiene la cedula
		this.iClienteRepository.actualizar(cliente);

	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void registro(Cliente cliente) {
		cliente.setRegistro("C");
		this.iClienteRepository.insertar(cliente);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void registroComoEmpleado(Cliente cliente) {
		cliente.setRegistro("E");
		this.iClienteRepository.insertar(cliente);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public Cliente buscarPorCedula(String cedula) {
		return this.iClienteRepository.buscarCedula(cedula);
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public List<Cliente> buscarTodos() {
		return this.iClienteRepository.buscarTodos();
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public List<ClienteVip> reporteClientesVip() {
		List<Cliente> listaClientes = this.buscarTodos();
		List<ClienteVip> clientesVip = new ArrayList<ClienteVip>();

		for (Cliente c : listaClientes) {
			BigDecimal valorIva = new BigDecimal(0);
			BigDecimal valorTotal = new BigDecimal(0);
			List<Reserva> reservasCliente = c.getReservas();
			for (Reserva r : reservasCliente) {
				valorIva = valorIva.add(r.getCobro().getPrecioIva());
				valorTotal = valorTotal.add(r.getCobro().getPrecioTotal());

			}
			ClienteVip clienteVip = new ClienteVip(c.getApellido(), c.getNombre(), c.getNumeroCedula(), valorIva,
					valorTotal);
			clientesVip.add(clienteVip);
		}

		List<ClienteVip> listaClientesVip = clientesVip.parallelStream()
				.sorted(Collections.reverseOrder(Comparator.comparing(ClienteVip::getValorTotal)))
				.collect(Collectors.toList());

		return listaClientesVip;
	}
}
