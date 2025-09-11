package Logica;
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
	
	// Métodos
	
}
