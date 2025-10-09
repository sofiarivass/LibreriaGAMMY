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
		String opcion, opcionDos = "", continuarVenta = "";
		
		opcion = Validaciones.menuSiNo("¿Es un cliente?", "Selección", null);
		
		if (opcion.equalsIgnoreCase("Sí")) {
			String []metodos = {"Transferencia", "tarjeta(debito)", "tarjeta(crédito)"};
			String []monedas = {"USD", "ARS"};
			String []estados = {"completado", "pendiente"};
			String []estadoEnvios = {"en preparación", "en camino", "Entregado"};
			double totalVenta = 0;
			boolean flag;
			LocalDate fechaVenta = LocalDate.now();
			String metodoPago, moneda, estado, origen, destino, estadoEnvio, detalles;
			TipoVenta fkTipoVenta = null;
			Carrito fkCarrito = null;
			Usuario fkUsuario = null;
			
			// buscamos al cliente en la BD
			cliente = Cliente.buscarCliente();
			
			// libros elegidos por el cliente
			listaCarrito = Libro.elegirLibros();
			
			do {
				flag = false;
				detalles = "";
				
				// conteo de precio total y cantidad de libros
				for (int i = 0; i < listaCarrito.size(); i++) {
					totalVenta = totalVenta + (listaCarrito.get(i).getStock() * listaCarrito.get(i).getPrecio());
					detalles = detalles + "Libro: " + listaCarrito.get(i).getTitulo() + " -cantidad: " + listaCarrito.get(i).getStock() 
							+ " -Precio x Unidad: $" + listaCarrito.get(i).getPrecio() + "\n";
				}
				
				// si el vendedor que se registra es el de internacional siempre sera venta Mayorista
				if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Internacional")) {
					fkTipoVenta = new TipoVenta(2,"Mayorista");					
				}
				
				// datos para la venta
				metodoPago = (String) JOptionPane.showInputDialog(null, "¿Método de pago?", null, 0, null, metodos, metodos[0]);
				moneda = (String) JOptionPane.showInputDialog(null, "¿tipo de Moneda?", null, 0, null, monedas, monedas[0]);
				estado = (String) JOptionPane.showInputDialog(null, "¿estado de la compra?", null, 0, null, estados, estados[0]);
				origen = Validaciones.validarString("ingrese el lugar de origen", null, null);
				destino = Validaciones.validarString("ingrese el lugar de destino", null, null);
				estadoEnvio = (String) JOptionPane.showInputDialog(null, "¿Estado de Envio?", null, 0, null, estadoEnvios, estadoEnvios[0]);
				
				detalles = detalles + "Precio Total: $" + totalVenta + "\nPago: " + metodoPago + "\nMoneda: " + moneda 
						+ "\nEstado: " + estado + "\nOrigen: " + origen + "\nDestino: " + destino + "\nEstado de Envio: " + estadoEnvio;
					
				continuarVenta = Validaciones.menuContinuar(detalles + "¿Desea continuar con la Venta?", "Detalles de la Venta!!", null);
				
				if (continuarVenta.equalsIgnoreCase("Modificar")) {
					flag = true;
				}
			} while (flag);
			
			// cargamos la info en tabla carrito de la BD
			Carrito.cargarCarrito(fechaVenta, cliente);
			
			// obtenemos el objeto carrito con id_carrito de BD
			fkCarrito = Carrito.obtenerCarrito(cliente);
			
			// cargamos la tabla carrito_detalle de la BD
			CarritoDetalle.cargarDetalle(listaCarrito,fkCarrito);
			
			// enviamos el fk del vendedor que realizo la venta
			fkUsuario = user;
			
			Exportacion venta = new Exportacion(totalVenta,fechaVenta,metodoPago,moneda,estado,fkTipoVenta,fkCarrito,fkUsuario,origen,destino,estadoEnvio);
			VentasExportDTO.nuevaVentaExport(venta);
			
		} else {
			boolean flag;
			JOptionPane.showMessageDialog(null, "Registrar nuevo Cliente");
			do {
				flag = false;
				cliente = Cliente.registrarCliente();
				if (cliente == null ) {
					opcionDos = Validaciones.menuSiNo("No se pudo registrar al Cliente!!\n¿Desea repetir la operación?", null, null);
					flag = opcionDos.equalsIgnoreCase("Sí")? true:false;
				} else {
					JOptionPane.showMessageDialog(null, "Se registro al Cliente correctamente!!");					
				}
			} while (flag);
			
			if (!flag) {
				String []metodos = {"Transferencia", "tarjeta(debito)", "tarjeta(crédito)"};
				String []monedas = {"USD", "ARS"};
				String []estados = {"completado", "pendiente"};
				String []estadoEnvios = {"en preparación", "en camino", "Entregado"};
				double totalVenta = 0;
				boolean flagDos;
				LocalDate fechaVenta = LocalDate.now();
				String metodoPago, moneda, estado, origen, destino, estadoEnvio, detalles;
				TipoVenta fkTipoVenta = null;
				Carrito fkCarrito = null;
				Usuario fkUsuario = null;
				
				// libros elegidos por el cliente
				listaCarrito = Libro.elegirLibros();
				
				do {
					flagDos = false;
					detalles = "";
					
					// conteo de precio total y cantidad de libros
					for (int i = 0; i < listaCarrito.size(); i++) {
						totalVenta = totalVenta + (listaCarrito.get(i).getStock() * listaCarrito.get(i).getPrecio());
						detalles = detalles + "Libro: " + listaCarrito.get(i).getTitulo() + " -cantidad: " + listaCarrito.get(i).getStock() 
								+ " -Precio x Unidad: $" + listaCarrito.get(i).getPrecio() + "\n";
					}
					
					// si el vendedor que se registra es el de internacional siempre sera venta Mayorista
					if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Internacional")) {
						fkTipoVenta = new TipoVenta(2,"Mayorista");					
					}
					
					// datos para la venta
					metodoPago = (String) JOptionPane.showInputDialog(null, "¿Método de pago?", null, 0, null, metodos, metodos[0]);
					moneda = (String) JOptionPane.showInputDialog(null, "¿tipo de Moneda?", null, 0, null, monedas, monedas[0]);
					estado = (String) JOptionPane.showInputDialog(null, "¿estado de la compra?", null, 0, null, estados, estados[0]);
					origen = Validaciones.validarString("ingrese el lugar de origen", null, null);
					destino = Validaciones.validarString("ingrese el lugar de destino", null, null);
					estadoEnvio = (String) JOptionPane.showInputDialog(null, "¿Estado de Envio?", null, 0, null, estadoEnvios, estadoEnvios[0]);
					
					detalles = detalles + "Precio Total: $" + totalVenta + "\nPago: " + metodoPago + "\nMoneda: " + moneda 
							+ "\nEstado: " + estado + "\nOrigen: " + origen + "\nDestino: " + destino + "\nEstado de Envio: " + estadoEnvio;
						
					continuarVenta = Validaciones.menuContinuar(detalles + "¿Desea continuar con la Venta?", "Detalles de la Venta!!", null);
					
					if (continuarVenta.equalsIgnoreCase("Modificar")) {
						flagDos = true;
					}
				} while (flagDos);
				
				// cargamos la info en tabla carrito de la BD
				Carrito.cargarCarrito(fechaVenta, cliente);
				
				// obtenemos el objeto carrito con id_carrito de BD
				fkCarrito = Carrito.obtenerCarrito(cliente);
				
				// cargamos la tabla carrito_detalle de la BD
				CarritoDetalle.cargarDetalle(listaCarrito,fkCarrito);
				
				// enviamos el fk del vendedor que realizo la venta
				fkUsuario = user;
				
				Exportacion venta = new Exportacion(totalVenta,fechaVenta,metodoPago,moneda,estado,fkTipoVenta,fkCarrito,fkUsuario,origen,destino,estadoEnvio);
				VentasExportDTO.nuevaVentaExport(venta);
				
			} else {
				JOptionPane.showMessageDialog(null, "Operacion cancelada!!\nNo se pudo cargar la venta");
			}
		}
	}
	
}
