package Logica;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Exportacion extends Venta {
	private String origen;
	private String destino;
	private String estadoEnvio;
	
	public Exportacion(LinkedList<Libro> carrito, int numero, Cliente cliente, int cantidad, double precio,
			LocalDateTime fechaVenta, String tipoVenta, String metodoPago, String moneda, String descuento,
			String estado, String origen, String destino, String estadoEnvio) {
		super(carrito, numero, cliente, cantidad, precio, fechaVenta, tipoVenta, metodoPago, moneda, descuento, estado);
		this.origen = origen;
		this.destino = destino;
		this.estadoEnvio = estadoEnvio;
	}

	// Getters y Setters
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

	// Métodos
	@Override
	public String toString() {
		return "Exportacion [origen=" + origen + ", destino=" + destino + ", estadoEnvio=" + estadoEnvio + "]";
	}
	
}
