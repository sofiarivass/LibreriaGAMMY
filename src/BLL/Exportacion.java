package BLL;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import Repository.Validaciones;

public class Exportacion extends Venta {
	private String origen;
	private String destino;
	private String estadoEnvio;
	
	public Exportacion(LinkedList<Libro> carrito, int numero, Cliente cliente, int cantidad, double precio,
			LocalDateTime fechaVenta, int tipoVenta, String metodoPago, String moneda, String descuento,
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
	
	// Datos para realizar la Venta Internacional
	public static void nuevaVentaExport(Usuario user) {
		LinkedList<Libro> carrito = null;
		Cliente cliente = null;
		String opcion, opcionDos;
		
		opcion = Validaciones.menuSiNo("¿Es un cliente?", null, null);
		
		if (opcion.equalsIgnoreCase("Si")) {
			cliente = Cliente.buscarCliente();			
			if (cliente != null) {
				// continua con la venta
			} else {
				// pide datos para crear un cliente nuevo.
				boolean flag = false;
				JOptionPane.showMessageDialog(null, "Cliente No encontrado!!\nRegistre al Cliente para continuar con la Venta");
				do {
					cliente = Cliente.registrarCliente();
					if (cliente == null ) {
						opcionDos = Validaciones.menuSiNo("No se pudo registrar al Cliente!!\n¿Desea repetir la operación?", null, null);
						flag = opcionDos.equalsIgnoreCase("Si")? true:false;
					} else {
						JOptionPane.showMessageDialog(null, "Se registro al Cliente correctamente!!");					
					}
				} while (flag);
				
				carrito = Libro.elegirLibros();
				
				
				
				
				
			}
		} else {
			
		}
	}
	
}
