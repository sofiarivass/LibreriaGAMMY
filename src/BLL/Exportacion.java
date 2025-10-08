package BLL;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import Repository.Validaciones;

public class Exportacion extends Venta {
	private String origen;
	private String destino;
	private String estadoEnvio;
	
	public Exportacion(int idVenta, double totalVenta, LocalDateTime fechaVenta, String metodoPago, String moneda,
			String estado, String origen, String destino, String estadoEnvio, Descuento fkDescuento, Carrito fkCarrito,
			Usuario fkUsuario, String origen2, String destino2, String estadoEnvio2) {
		super(idVenta, totalVenta, fechaVenta, metodoPago, moneda, estado, origen, destino, estadoEnvio, fkDescuento,
				fkCarrito, fkUsuario);
		origen = origen2;
		destino = destino2;
		estadoEnvio = estadoEnvio2;
	}

	// Métodos
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

	@Override
	public String toString() {
		return "Exportacion [origen=" + origen + ", destino=" + destino + ", estadoEnvio=" + estadoEnvio
				+ ", getIdVenta()=" + getIdVenta() + ", getTotalVenta()=" + getTotalVenta() + ", getFechaVenta()="
				+ getFechaVenta() + ", getMetodoPago()=" + getMetodoPago() + ", getMoneda()=" + getMoneda()
				+ ", getEstado()=" + getEstado() + ", getFkDescuento()=" + getFkDescuento() + ", getFkCarrito()="
				+ getFkCarrito() + ", getFkUsuario()=" + getFkUsuario() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	// Datos para realizar la Venta Internacional
	public static void nuevaVentaExport(Usuario user) {
		LinkedList<Libro> carrito = null;
		Cliente cliente = null;
		String opcion, opcionDos;
		
		opcion = Validaciones.menuSiNo("¿Es un cliente?", null, null);
		
		if (opcion.equalsIgnoreCase("Si")) {
			cliente = Cliente.buscarCliente();
			carrito = Libro.elegirLibros();
			
		} else {
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
	}
	
}
