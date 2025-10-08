package BLL;

import java.time.LocalDateTime;

public class Venta {
	private int idVenta;
	private double totalVenta;
	private LocalDateTime fechaVenta;
	private String metodoPago;
	private String moneda;
	private String estado;
	private String origen;
	private String destino;
	private String estadoEnvio;
	private Descuento fkDescuento;
	private Carrito fkCarrito;
	private Usuario fkUsuario;
	
	public Venta(int idVenta, double totalVenta, LocalDateTime fechaVenta, String metodoPago, String moneda,
			String estado, String origen, String destino, String estadoEnvio, Descuento fkDescuento, Carrito fkCarrito,
			Usuario fkUsuario) {
		this.idVenta = idVenta;
		this.totalVenta = totalVenta;
		this.fechaVenta = fechaVenta;
		this.metodoPago = metodoPago;
		this.moneda = moneda;
		this.estado = estado;
		this.origen = origen;
		this.destino = destino;
		this.estadoEnvio = estadoEnvio;
		this.fkDescuento = fkDescuento;
		this.fkCarrito = fkCarrito;
		this.fkUsuario = fkUsuario;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getEstadoEnvio() {
		return estadoEnvio;
	}

	public void setEstadoEnvio(String estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}

	public Descuento getFkDescuento() {
		return fkDescuento;
	}

	public void setFkDescuento(Descuento fkDescuento) {
		this.fkDescuento = fkDescuento;
	}

	public Carrito getFkCarrito() {
		return fkCarrito;
	}

	public void setFkCarrito(Carrito fkCarrito) {
		this.fkCarrito = fkCarrito;
	}

	public Usuario getFkUsuario() {
		return fkUsuario;
	}

	public void setFkUsuario(Usuario fkUsuario) {
		this.fkUsuario = fkUsuario;
	}
	
	// Getters y Setters

	
	

	// Métodos
	

}
