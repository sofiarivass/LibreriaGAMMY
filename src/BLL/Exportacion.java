package BLL;
import Enums.*;
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

	
	/**
	 * funcion para pedir los datos para realizar la Venta Internacional.
	 * @param user
	 */
	public static void nuevaVentaExport(Usuario user) {
		String opcion = Validaciones.menuSiNo("¿Es un cliente?", "Selección", null);
		
		if (opcion.equalsIgnoreCase("Sí")) {
			clienteRegistrado(user);
		} else {
			clienteNoRegistrado(user);
		}
	}
	
	/**
	 * funcion para realizar una venta con Cliente Registrado.
	 * @param user
	 */
	public static void clienteRegistrado(Usuario user) {
		LinkedList<CarritoDetalle> carrito = null;
		boolean flag;
		String []estados = {"completada", "modificada", "anulada"};
		String []estadoEnvios = {"en preparación", "en camino", "Entregado"};
		double totalVenta = 0;
		LocalDate fechaVenta = LocalDate.now();
		String metodoPago, moneda, estado, origen, destino, estadoEnvio, detalles, continuarVenta = "";;
		Cliente cliente = null;
		TipoVenta fkTipoVenta = null;
		Carrito fkCarrito = null;
		Usuario fkUsuario = null;
		
		cliente = Cliente.buscarCliente();
		
		if (cliente != null) {
			carrito = Libro.elegirLibros(cliente);
			
			if (carrito.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se podemos continuar con la Venta, No tenemos Stock disponible!!");
			} else {
				do {
					flag = false;
					detalles = "";
					
					// contador de precio total y cantidad de libros
					for (int i = 0; i < carrito.size(); i++) {
						totalVenta = totalVenta + (carrito.get(i).getCantidad() * carrito.get(i).getFkLibro().getPrecio());
						detalles = detalles + "Libro: " + carrito.get(i).getFkLibro().getTitulo() 
								+ " -cantidad: " + carrito.get(i).getCantidad() 
								+ " -Precio x Unidad: $" + carrito.get(i).getFkLibro().getPrecio() + "\n";
					}
					
					// si el vendedor que se logea es el de internacional siempre sera venta Mayorista
					if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Internacional")) {
						fkTipoVenta = new TipoVenta(2,"Mayorista");					
					}
					
					// datos para la venta
					metodoPago = ((MetodoPago) JOptionPane.showInputDialog(null, "¿Método de pago?", null, 0, null, 
							MetodoPago.values(), MetodoPago.values()[0])).name();
					moneda = ((TipoMoneda) JOptionPane.showInputDialog(null, "¿tipo de Moneda?", null, 0, null, 
							TipoMoneda.values(), TipoMoneda.values()[0])).name();
					origen = ((Sucursales) JOptionPane.showInputDialog(null, "ingrese el lugar de Origen", null, 0, null, 
							Sucursales.values(), Sucursales.values()[0])).name();
					do {
						flag = false;
						destino = ((Sucursales) JOptionPane.showInputDialog(null, "ingrese el lugar de Destino", null, 0, null, 
								Sucursales.values(), Sucursales.values())).name();
						if (destino.equals(origen)) {
							JOptionPane.showMessageDialog(null, "El destino no puede ser igual al Origen");
							flag = true;
						}
					} while (flag);
					estado = estados[0];
					estadoEnvio = estadoEnvios[0];
					
					detalles = detalles + "Precio Total: $" + totalVenta + "\nPago: " + metodoPago + "\nMoneda: " + moneda 
							+ "\nEstado: " + estado + "\nOrigen: " + origen + "\nDestino: " + destino + "\nEstado de Envio: " + estadoEnvio;
					
					continuarVenta = Validaciones.menuContinuar(detalles + "¿Desea continuar con la Venta?", "Detalles de la Venta!!", null);
					
					if (continuarVenta.equalsIgnoreCase("Modificar")) {
						flag = true;
					}
				} while (flag);
				
				fkCarrito = Carrito.cargarCarrito(fechaVenta, cliente);
				CarritoDetalle.cargarDetalle(carrito,fkCarrito);
				fkUsuario = user;
				
				Exportacion venta = new Exportacion(totalVenta,fechaVenta,metodoPago,moneda,estado,fkTipoVenta,fkCarrito,fkUsuario,origen,destino,estadoEnvio);
				
				Libro.actualizarStock(carrito);
				VentasExportDTO.nuevaVentaExport(venta, detalles);
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Cliente No Encontrado!!");
			nuevaVentaExport(user);
		}
	}
	
	/**
	 * funcion para realizar una venta con Cliente No Registrado.
	 * @param user
	 */
	public static void clienteNoRegistrado(Usuario user) {
		LinkedList<CarritoDetalle> carrito = null;
		Cliente cliente = null;
		String continuarVenta = "";
		boolean flag;
		
		// registramos al cliente validando que no este registrado en la BD.
		cliente = Cliente.registrarCliente();
		
		if (cliente != null ) {
			String []estados = {"completado", "pendiente"};
			String []estadoEnvios = {"en preparación", "en camino", "Entregado"};
			double totalVenta = 0;
			LocalDate fechaVenta = LocalDate.now();
			String metodoPago, moneda, estado, origen, destino, estadoEnvio, detalles;
			TipoVenta fkTipoVenta = null;
			Carrito fkCarrito = null;
			Usuario fkUsuario = null;
			
			// libros elegidos por el cliente
			carrito = Libro.elegirLibros(cliente);
			
			if (carrito.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se podemos continuar con la Venta, No tenemos Stock disponible!!");
			} else {
				do {
					flag = false;
					detalles = "";
					
					// conteo de precio total y cantidad de libros
					for (int i = 0; i < carrito.size(); i++) {
						totalVenta = totalVenta + (carrito.get(i).getCantidad() * carrito.get(i).getFkLibro().getPrecio());
						detalles = detalles + "Libro: " + carrito.get(i).getFkLibro().getTitulo() 
								+ " -cantidad: " + carrito.get(i).getCantidad() 
								+ " -Precio x Unidad: $" + carrito.get(i).getFkLibro().getPrecio() + "\n";
					}
					
					// si el vendedor que se registra es el de internacional siempre sera venta Mayorista
					if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Internacional")) {
						fkTipoVenta = new TipoVenta(2,"Mayorista");					
					}
					
					// datos para la venta
					metodoPago = ((MetodoPago) JOptionPane.showInputDialog(null, "¿Método de pago?", null, 0, null, 
							MetodoPago.values(), MetodoPago.values())).name();
					moneda = ((TipoMoneda) JOptionPane.showInputDialog(null, "¿tipo de Moneda?", null, 0, null, 
							TipoMoneda.values(), TipoMoneda.values())).name();
					origen = ((Sucursales) JOptionPane.showInputDialog(null, "ingrese el lugar de Origen", null, 0, null, 
							Sucursales.values(), Sucursales.values())).name();
					do {
						flag = false;
						destino = ((Sucursales) JOptionPane.showInputDialog(null, "ingrese el lugar de Destino", null, 0, null, 
								Sucursales.values(), Sucursales.values())).name();
						if (destino.equals(origen)) {
							JOptionPane.showMessageDialog(null, "El destino no puede ser igual al Origen");
							flag = true;						
						}
					} while (flag);
					estado = estados[0];
					estadoEnvio = estadoEnvios[0];
					
					detalles = detalles + "Precio Total: $" + totalVenta + "\nPago: " + metodoPago + "\nMoneda: " + moneda 
							+ "\nEstado: " + estado + "\nOrigen: " + origen + "\nDestino: " + destino + "\nEstado de Envio: " + estadoEnvio;
					
					continuarVenta = Validaciones.menuContinuar(detalles + "¿Desea continuar con la Venta?", "Detalles de la Venta!!", null);
					
					if (continuarVenta.equalsIgnoreCase("Modificar")) {
						flag = true;
					}
				} while (flag);
				
				// cargamos la info en tabla carrito de la BD y traemos el id generado automaticamente
				fkCarrito = Carrito.cargarCarrito(fechaVenta, cliente);
				
				// cargamos la tabla carrito_detalle de la BD
				CarritoDetalle.cargarDetalle(carrito,fkCarrito);
				
				// enviamos el fk del vendedor que realizo la venta
				fkUsuario = user;
				
				Exportacion venta = new Exportacion(totalVenta,fechaVenta,metodoPago,moneda,estado,fkTipoVenta,fkCarrito,fkUsuario,origen,destino,estadoEnvio);
				// descontamos el stock en la BD
				Libro.actualizarStock(carrito);
				// realizamos la cárga de la Venta en la BD
				VentasExportDTO.nuevaVentaExport(venta, detalles);
			}
			
		} else {
			nuevaVentaExport(user);
		}
	}
	
}
