package BLL;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Venta {
	private LinkedList<Libro> carrito = new LinkedList<Libro>();
	private int numero;
	private Cliente cliente;
	private int cantidad;
	private double precio;
	private LocalDateTime fechaVenta;
	private String tipoVenta;
	private String metodoPago;
	private String moneda;
	private String descuento;
	private String estado;

	public Venta(LinkedList<Libro> carrito, int numero, Cliente cliente, int cantidad, double precio,
			LocalDateTime fechaVenta, String tipoVenta, String metodoPago, String moneda, String descuento,
			String estado) {
		this.carrito = carrito;
		this.numero = numero;
		this.cliente = cliente;
		this.cantidad = cantidad;
		this.precio = precio;
		this.fechaVenta = fechaVenta;
		this.tipoVenta = tipoVenta;
		this.metodoPago = metodoPago;
		this.moneda = moneda;
		this.descuento = descuento;
		this.estado = estado;
	}

	// Getters y Setters

	public LinkedList<Libro> getCarrito() {
		return carrito;
	}

	public void setCarrito(LinkedList<Libro> carrito) {
		this.carrito = carrito;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getTipoVenta() {
		return tipoVenta;
	}

	public void setTipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
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

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	// Métodos
	@Override
	public String toString() {
		return "Venta [carrito=" + carrito + ", numero=" + numero + ", cliente=" + cliente + ", cantidad=" + cantidad
				+ ", precio=" + precio + ", fechaVenta=" + fechaVenta + ", tipoVenta=" + tipoVenta + ", metodoPago="
				+ metodoPago + ", moneda=" + moneda + ", descuento=" + descuento + ", estado=" + estado + "]";
	}

}
