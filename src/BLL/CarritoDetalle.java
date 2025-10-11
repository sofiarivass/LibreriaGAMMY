package BLL;
import java.util.LinkedList;

import DLL.CarritoDetalleDTO;

public class CarritoDetalle {
	private int cantidad;
	private Libro fkLibro;
	private Carrito fkCarrito;
	
	public CarritoDetalle(int cantidad, Libro fkLibro, Carrito fkCarrito) {
		this.cantidad = cantidad;
		this.fkLibro = fkLibro;
		this.fkCarrito = fkCarrito;
	}
	
	public CarritoDetalle(int cantidad, Libro fkLibro) {
		this.cantidad = cantidad;
		this.fkLibro = fkLibro;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Libro getFkLibro() {
		return fkLibro;
	}
	public void setFkLibro(Libro fkLibro) {
		this.fkLibro = fkLibro;
	}
	public Carrito getFkCarrito() {
		return fkCarrito;
	}
	public void setFkCarrito(Carrito fkCarrito) {
		this.fkCarrito = fkCarrito;
	}
	
	// Métodos
	
	/**
	 * funcion para cargar la tabla carrito_detalle en la BD.
	 * @param carrito
	 * @param fkCarrito
	 */
	public static void cargarDetalle(LinkedList<CarritoDetalle>carrito, Carrito fkCarrito) {
		CarritoDetalle carritoDetalle = null;
		
		for (int i = 0; i < carrito.size(); i++) {
			carritoDetalle = new CarritoDetalle(carrito.get(i).getCantidad(),carrito.get(i).getFkLibro(),fkCarrito);
			CarritoDetalleDTO.cargarDetalle(carritoDetalle);
			HistorialCompras.cargarHistorial(carritoDetalle);			
		}
	}
	
}
