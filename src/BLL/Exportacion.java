package BLL;
import java.time.LocalDate;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import DLL.VentasExportDTO;
import Repository.Validaciones;

public class Exportacion extends Venta {
	private String origen;
	private String destino;
	private String estadoEnvio;

	public Exportacion(int idVenta, double totalVenta, LocalDate fechaVenta, String metodoPago, String moneda,
			String estado, TipoVenta fkTipoVenta, Descuento fkDescuento, Carrito fkCarrito, Usuario fkUsuario,
			String origen, String destino, String estadoEnvio) {
		super(idVenta, totalVenta, fechaVenta, metodoPago, moneda, estado, fkTipoVenta, fkDescuento, fkCarrito,
				fkUsuario);
		this.origen = origen;
		this.destino = destino;
		this.estadoEnvio = estadoEnvio;
	}
	
	public Exportacion(double totalVenta, LocalDate fechaVenta, String metodoPago, String moneda,
			String estado, TipoVenta fkTipoVenta, Carrito fkCarrito, Usuario fkUsuario,
			String origen, String destino, String estadoEnvio) {
		super(totalVenta, fechaVenta, metodoPago, moneda, estado, fkTipoVenta, fkCarrito,
				fkUsuario);
		this.origen = origen;
		this.destino = destino;
		this.estadoEnvio = estadoEnvio;
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

	// Datos para realizar la Venta Internacional
	public static void nuevaVentaExport(Usuario user) {
		LinkedList<Libro> listaCarrito = null;
		Cliente cliente = null;
		String opcion, opcionDos = "";
		
		opcion = Validaciones.menuSiNo("¿Es un cliente?", null, null);
		
		if (opcion.equalsIgnoreCase("Si")) {
			cliente = Cliente.buscarCliente();
			listaCarrito = Libro.elegirLibros();
			
		} else {
			boolean flag = false;
			JOptionPane.showMessageDialog(null, "Registrar nuevo Cliente");
			do {
				cliente = Cliente.registrarCliente();
				if (cliente == null ) {
					opcionDos = Validaciones.menuSiNo("No se pudo registrar al Cliente!!\n¿Desea repetir la operación?", null, null);
					flag = opcionDos.equalsIgnoreCase("Si")? true:false;
				} else {
					JOptionPane.showMessageDialog(null, "Se registro al Cliente correctamente!!");					
				}
			} while (flag);
			
			if (!opcionDos.equalsIgnoreCase("No")) {
				// variables para el objeto Exportacion(venta)
				String []metodos = {"Transferencia", "tarjeta(debito)", "tarjeta(crédito)"};
				String []monedas = {"USD", "ARS"};
				String []estados = {"completado", "pendiente"};
				double totalVenta = 0;
				LocalDate fechaVenta = LocalDate.now();
				String metodoPago, moneda, estado, origen, destino, estadoEnvio;
				TipoVenta fkTipoVenta = null;
				Carrito fkCarrito = null;
				Usuario fkUsuario = null;
				
				// libros elegidos por el cliente
				listaCarrito = Libro.elegirLibros();
				
				// guardamos la info en tabla carrito de la BD
				Carrito.cargarCarrito(fechaVenta, cliente);
				
				// conteo de precio total y cantidad de libros
				for (int i = 0; i < listaCarrito.size(); i++) {
					totalVenta = totalVenta + (listaCarrito.get(i).getStock() * listaCarrito.get(i).getPrecio());
					
				}
				
				if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Internacional")) {
					fkTipoVenta = new TipoVenta(2,"Mayorista");					
				}
				
				// obtenemos el objeto carrito con id_carrito de BD
				fkCarrito = Carrito.obtenerCarrito(cliente);
				
				// llenamos la tabla carrito_detalle de la BD
				CarritoDetalle.cargarDetalle(listaCarrito,fkCarrito);
				
				fkUsuario = user;
				
				metodoPago = (String) JOptionPane.showInputDialog(null, "¿Método de pago?", null, 0, null, metodos, metodos[0]);
				moneda = (String) JOptionPane.showInputDialog(null, "¿tipo de Moneda?", null, 0, null, monedas, monedas[0]);
				estado = (String) JOptionPane.showInputDialog(null, "¿estado de la compra?", null, 0, null, estados, estados[0]);
				origen = Validaciones.validarString("ingrese el lugar de origen", null, null);
				destino = Validaciones.validarString("ingrese el lugar de destino", null, null);
				estadoEnvio = (String) JOptionPane.showInputDialog(null, "¿Método de pago?", null, 0, null, estados, estados[0]);
				
				Exportacion venta = new Exportacion(totalVenta,fechaVenta,metodoPago,moneda,estado,fkTipoVenta,fkCarrito,fkUsuario,origen,destino,estadoEnvio);
				
				VentasExportDTO.nuevaVentaExport(venta);
				
			} else {
				JOptionPane.showMessageDialog(null, "Operacion cancelada!!\nNo se pudo cargar la venta");
			}
		}
	}
	
}
