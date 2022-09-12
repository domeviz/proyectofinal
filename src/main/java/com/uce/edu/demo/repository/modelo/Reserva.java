package com.uce.edu.demo.repository.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@Column(name = "rese_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rese_id_seq")
	@SequenceGenerator(name = "rese_id_seq", sequenceName = "rese_id_seq", allocationSize = 1)
	private Integer id;

	@Column(name = "rese_numero_reserva")
	private String numeroReserva;

	@Column(name = "rese_numero_cedula")
	private String numero;

	@Column(name = "rese_dias")
	private Integer diasReserva;

	@Column(name = "rese_fecha_inicio")
	private String fechaInicio;

	@Column(name = "rese_fecha_fin")
	private String fechaFin;

	@Column(name = "rese_estado")
	private String estado;

	// Relacion muchos a uno con cliente
	@ManyToOne
	@JoinColumn(name = "rese_clie_id")
	private Cliente cliente;

	// Relacion muchos a uno con vehiculo
	@ManyToOne
	@JoinColumn(name = "rese_vehi_id")
	private Vehiculo vehiculo;

	// Relacion uno a uno con cobro
	@OneToOne(mappedBy = "reserva", fetch = FetchType.EAGER)
	private Cobro cobro;

	// SET y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
	}

	public Integer getDiasReserva() {
		return diasReserva;
	}

	public void setDiasReserva(Integer diasReserva) {
		this.diasReserva = diasReserva;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", numeroReserva=" + numeroReserva + ", numero=" + numero + ", diasReserva="
				+ diasReserva + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + "]";
	}

}
