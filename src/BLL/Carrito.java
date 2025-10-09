package BLL;
import java.time.LocalDate;
import DLL.CarritoDTO;

public class Carrito {
	private int idCarrito;
	private LocalDate fecha;
	private Cliente fkCliente;
	
	public Carrito(int idCarrito, LocalDate fecha, Cliente fkCliente) {
		this.idCarrito = idCarrito;
		this.fecha = fecha;
		this.fkCliente = fkCliente;
	}
	
	public Carrito(LocalDate fecha, Cliente fkCliente) {
		this.fecha = fecha;
		this.fkCliente = fkCliente;
	}

	public int getIdCarrito() {
		return idCarrito;
	}
	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Cliente getFkCliente() {
		return fkCliente;
	}
	public void setFkCliente(Cliente fkCliente) {
		this.fkCliente = fkCliente;
	}
	
	// Métodos
	
	public static void cargarCarrito(LocalDate fecha, Cliente cliente) {
		Carrito carrito = new Carrito(fecha, cliente);
		CarritoDTO.cargarCarrito(carrito);
	}
	
	public static Carrito obtenerCarrito(Cliente cliente) {
		Carrito carrito = CarritoDTO.obtenerCarrito(cliente);
		return carrito;
	}
	
}
