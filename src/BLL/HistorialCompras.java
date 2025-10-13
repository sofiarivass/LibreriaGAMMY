package BLL;
import java.time.LocalDate;
import DLL.HistorialComprasDTO;

public class HistorialCompras {
	private Libro fkLibro;
	private Cliente fkCliente;
	private LocalDate fecha;
	
	public HistorialCompras(Libro fkLibro, Cliente fkCliente, LocalDate fecha) {
		this.fkLibro = fkLibro;
		this.fkCliente = fkCliente;
		this.fecha = fecha;
	}
	
	public Libro getFkLibro() {
		return fkLibro;
	}
	public void setFkLibro(Libro fkLibro) {
		this.fkLibro = fkLibro;
	}
	public Cliente getFkCliente() {
		return fkCliente;
	}
	public void setFkCliente(Cliente fkCliente) {
		this.fkCliente = fkCliente;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	// Métodos
	
	public static void cargarHistorial(CarritoDetalle carrito) {
		HistorialComprasDTO.cargarHistorial(carrito);
	}
	
}
