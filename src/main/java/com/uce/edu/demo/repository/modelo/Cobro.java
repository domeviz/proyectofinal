package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="cobro")
public class Cobro {
	
	@Id
	@Column(name="cobr_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="cobr_id_seq" )
	@SequenceGenerator(name="cobr_id_seq", sequenceName = "cobr_id_seq", allocationSize =1 )
	private Integer id;
	
	@Column(name="cobr_tarjeta")
	private String tarjeta;
	
	@Column(name="cobr_fecha_pago")
	private LocalDateTime fechaPago;
	
	@Column(name="cobr_subtotal")
	private BigDecimal subTotal;
	
	@Column(name="cobr_precio_iva")
	private BigDecimal precioIva;
	
	@Column(name="cobr_precio_total")
	private BigDecimal precioTotal;
	
	//Relacion uno a uno con cobro
	@OneToOne
	@JoinColumn(name="cobr_rese_id")
	private Reserva reserva;

	//SET y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public LocalDateTime getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDateTime fechaPago) {
		this.fechaPago = fechaPago;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getPrecioIva() {
		return precioIva;
	}

	public void setPrecioIva(BigDecimal precioIva) {
		this.precioIva = precioIva;
	}

	public BigDecimal getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}	
	
}
