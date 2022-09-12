package com.uce.edu.demo.repository.modelo;


public class ReservaTo {

	//Reserva
    private String numero;
    private Integer diasReserva;
    private String fechaInicio;
    private String fechaFin;
    private String estado;

    //Cliente
    private String nombre;
    private String apellido;

    //Vehiculo
    private String placa;
    private String modelo;
    private String marca;

    public ReservaTo() {

    }

    public ReservaTo(String numero, Integer diasReserva, String fechaInicio, String fechaFin, String estado,
            String nombre, String apellido, String placa, String modelo, String marca) {
        super();
        this.numero = numero;
        this.diasReserva = diasReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
    }

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getDiasReserva() {
		return diasReserva;
	}

	public void setDiasReserva(Integer diasReserva) {
		this.diasReserva = diasReserva;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "ReservaTo [numero=" + numero + ", diasReserva=" + diasReserva + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", estado=" + estado + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + "]";
	}
    
    


}