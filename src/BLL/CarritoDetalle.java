package BLL;

public class CarritoDetalle {
	private int cantidad;
	private Libro fkLibro;
	private Carrito fkCarrito;
	
	public CarritoDetalle(int cantidad, Libro fkLibro, Carrito fkCarrito) {
		this.cantidad = cantidad;
		this.fkLibro = fkLibro;
		this.fkCarrito = fkCarrito;
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
	
	
}
