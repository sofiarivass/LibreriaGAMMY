package BLL;
import Enums.*;
import java.time.LocalDate;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import DLL.ClienteDTO;
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
	
	public Exportacion(int idVenta, double totalVenta, LocalDate fechaVenta, String metodoPago, String moneda,
			String estado, String origen, String destino, String estadoEnvio) {
		super(idVenta, totalVenta, fechaVenta, metodoPago, moneda, estado);
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

	// Métodos
	
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

	
	@Override
	public String toString() {
		
		return "[id= " + getIdVenta() + ", Fecha= " + getFechaVenta() + ", metodoPago = " + getMetodoPago() 
				+ ", Moneda= " + getMoneda() + ", origen= " + origen + ", destino= " + destino + "]";
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
		int cantidadTotal = 0;
		double totalVenta = 0;
		LocalDate fechaVenta = LocalDate.now();
		String metodoPago, moneda, estado, origen, destino, estadoEnvio, detalles, continuarVenta = "";
		Cliente cliente = null;
		TipoVenta fkTipoVenta = null;
		Carrito fkCarrito = null;
		Usuario fkUsuario = null;
		
		cliente = Cliente.buscarCliente();
		
		if (cliente != null) {
			carrito = Libro.elegirLibros(cliente);
			
			if (carrito.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No podemos continuar con la Venta, No tenemos Stock disponible!!");
			} else {
				do {
					flag = false;
					detalles = "Datos de Libros:\n\n";
					
					// contador de precio total y cantidad de libros
					for (int i = 0; i < carrito.size(); i++) {
						cantidadTotal = cantidadTotal + carrito.get(i).getCantidad();
						totalVenta = totalVenta + (carrito.get(i).getCantidad() * carrito.get(i).getFkLibro().getPrecio());
						detalles = detalles + (i+1) + " - " + carrito.get(i).getFkLibro().getTitulo() 
								+ "- cantidad: " + carrito.get(i).getCantidad() 
								+ "- Precio x Unidad: $" + carrito.get(i).getFkLibro().getPrecio() + "\n";
					}
					
					// si el vendedor que se logea es el de internacional siempre sera venta Mayorista
					if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Internacional")) {
						fkTipoVenta = new TipoVenta(2,"Mayorista");					
					}
					
					// datos para la venta
					metodoPago = ((MetodoPago) JOptionPane.showInputDialog(null, "¿Método de pago?", "Datos de la Venta", 1, null, 
							MetodoPago.values(), MetodoPago.values()[0])).name();
					moneda = ((TipoMoneda) JOptionPane.showInputDialog(null, "¿tipo de Moneda?", "Datos de la Venta", 1, null, 
							TipoMoneda.values(), TipoMoneda.values()[0])).name();
					origen = ((Sucursales) JOptionPane.showInputDialog(null, "ingrese el lugar de Origen", "Datos de la Venta", 1, null, 
							Sucursales.values(), Sucursales.values()[0])).name();
					do {
						flag = false;
						destino = ((Sucursales) JOptionPane.showInputDialog(null, "ingrese el lugar de Destino", "Datos de la Venta", 1, null, 
								Sucursales.values(), Sucursales.values())).name();
						if (destino.equals(origen)) {
							JOptionPane.showMessageDialog(null, "El destino no puede ser igual al Origen");
							flag = true;
						}
					} while (flag);
					estado = estados[0];
					estadoEnvio = estadoEnvios[0];
					
					detalles = detalles + "- Cantidad Total: " + cantidadTotal + " libros.\n- Precio Total: $" + totalVenta 
							+ "\n\nDatos de Pago:\n\n- Pago: " + metodoPago + "\n- Moneda: " + moneda + "\n- Estado: " + estado 
							+ "\n- Origen: " + origen + "\n- Destino: " + destino + "\n- Estado de Envio: " + estadoEnvio;
					
					continuarVenta = Validaciones.menuContinuar(detalles + "\n\n¿Desea continuar con la Venta?", "Detalles de la Venta!!", null);
					
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
			int cantidadTotal = 0;
			double totalVenta = 0;
			LocalDate fechaVenta = LocalDate.now();
			String metodoPago, moneda, estado, origen, destino, estadoEnvio, detalles;
			TipoVenta fkTipoVenta = null;
			Carrito fkCarrito = null;
			Usuario fkUsuario = null;
			
			// libros elegidos por el cliente
			carrito = Libro.elegirLibros(cliente);
			
			if (carrito.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No podemos continuar con la Venta, No tenemos Stock disponible!!");
			} else {
				do {
					flag = false;
					detalles = "Datos de Libros:\n\n";
					
					// conteo de precio total y cantidad de libros
					for (int i = 0; i < carrito.size(); i++) {
						cantidadTotal = cantidadTotal + carrito.get(i).getCantidad();
						totalVenta = totalVenta + (carrito.get(i).getCantidad() * carrito.get(i).getFkLibro().getPrecio());
						detalles = detalles + (i+1) + " - " + carrito.get(i).getFkLibro().getTitulo() 
								+ "- cantidad: " + carrito.get(i).getCantidad() 
								+ "- Precio x Unidad: $" + carrito.get(i).getFkLibro().getPrecio() + "\n";
					}
					
					// si el vendedor que se registra es el de internacional siempre sera venta Mayorista
					if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Internacional")) {
						fkTipoVenta = new TipoVenta(2,"Mayorista");					
					}
					
					// datos para la venta
					metodoPago = ((MetodoPago) JOptionPane.showInputDialog(null, "¿Método de pago?", "Datos de la Venta", 1, null, 
							MetodoPago.values(), MetodoPago.values())).name();
					moneda = ((TipoMoneda) JOptionPane.showInputDialog(null, "¿tipo de Moneda?", "Datos de la Venta", 1, null, 
							TipoMoneda.values(), TipoMoneda.values())).name();
					origen = ((Sucursales) JOptionPane.showInputDialog(null, "ingrese el lugar de Origen", "Datos de la Venta", 1, null, 
							Sucursales.values(), Sucursales.values())).name();
					do {
						flag = false;
						destino = ((Sucursales) JOptionPane.showInputDialog(null, "ingrese el lugar de Destino", "Datos de la Venta", 1, null, 
								Sucursales.values(), Sucursales.values())).name();
						if (destino.equals(origen)) {
							JOptionPane.showMessageDialog(null, "El destino no puede ser igual al Origen");
							flag = true;						
						}
					} while (flag);
					estado = estados[0];
					estadoEnvio = estadoEnvios[0];
					
					detalles = detalles + "- Cantidad Total: " + cantidadTotal + " libros.\n- Precio Total: $" + totalVenta 
							+ "Datos de Pago:\n\n- Pago: " + metodoPago + "\n- Moneda: " + moneda + "\n- Estado: " + estado 
							+ "\n- Origen: " + origen + "\n - Destino: " + destino + "\n- Estado de Envio: " + estadoEnvio;
					
					continuarVenta = Validaciones.menuContinuar(detalles + "\n\n¿Desea continuar con la Venta?", 
							"Detalles de la Venta!!", null);
					
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
	
	/**
	 * funcion para mostrar los datos a modificar de la Venta Internacional.
	 * @param user
	 */
	public static void modificarVentaExport(Usuario user) {
		String []ventas, eleccion;
		String []clientes;
		String []estados = {"completada", "modificada", "anulada"};
		String []menu = {"Modificar Datos de Venta", "Volver"};
		String []menuVentas = {"Metodo-Pago", "Moneda", "Origen", "Destino", "Guardar-Cambios", "volver"};
		String seleccion, detallesVenta = "", detallesVentaSinCambios = "";
		int opcion, opcionDos;
		boolean cambio, sinCambios = false;
		LinkedList<Exportacion> listaVentas = new LinkedList<Exportacion>();
		Exportacion ventaExport = null;
		Exportacion ventaElegida = null;
		Cliente cliente = null;
		TipoVenta fkTipoVenta = null;
		
		LinkedList<Cliente> listaClientes = ClienteDTO.consultarClientes();
		if (listaClientes.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Aún no Existen Clientes Registrados!!");
		} else {
			clientes = new String[listaClientes.size()];
			for (int i = 0; i < clientes.length; i++) {
				clientes[i] = listaClientes.get(i).getIdCliente() + " - " + listaClientes.get(i).getNombre();
			}
			
			seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un Cliente para ver sus Ventas", "Registro de Clientes!!", 1, null, clientes, clientes[0]);
			eleccion = seleccion.split(" - ");
			
			for (Cliente cliente2 : listaClientes) {
				if (cliente2.getIdCliente() == Integer.parseInt(eleccion[0])) {
					cliente = cliente2;
				}
			}
			
			if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Internacional")) {
				fkTipoVenta = new TipoVenta(2,"Mayorista");					
			}
			
			LinkedList<Carrito> listaCarrito = Carrito.obtenerCarrito(cliente);
			
			if (listaCarrito != null) {
				for (int i = 0; i < listaCarrito.size(); i++) {
					ventaExport = VentasExportDTO.verVentas(listaCarrito.get(i).getIdCarrito(),fkTipoVenta);
					if (ventaExport != null) {
						listaVentas.add(ventaExport);						
					}
				}

				if (listaVentas.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El Cliente " + cliente.getNombre() + ", aún no tiene compras registradas!!");
				} else {
					ventas = new String[listaVentas.size()];
					
					for (int i = 0; i < ventas.length; i++) {
						ventas[i] = listaVentas.get(i).getIdVenta() + " - fecha: " + listaVentas.get(i).getFechaVenta();
					}
					
					
					seleccion = (String) JOptionPane.showInputDialog(null, "El Cliente: " + cliente.getNombre() + ", registra: " 
							+ ventas.length + " ventas" + "\nSeleccione una venta para ver en Detalle:", "Registro de Ventas!!", 1, null, ventas, ventas[0]);
					eleccion = seleccion.split(" - ");
					
					do {
						if (sinCambios != true) {
							for (Exportacion venta : listaVentas) {
								if (venta.getIdVenta() == Integer.parseInt(eleccion[0])) {
									ventaElegida = venta;
									detallesVenta = "Datos de la Venta:" 
											+ "\n- Metodo de Pago: " + ventaElegida.getMetodoPago()
											+ "\n- Tipo de Moneda: " + ventaElegida.getMoneda()
											+ "\n- Estado: " + ventaElegida.getEstado()
											+ "\n- Origen: " + ventaElegida.getOrigen()
											+ "\n- Destino: " + ventaElegida.getDestino();
									break;
								}
							}
							detallesVentaSinCambios = detallesVenta;
						}
						
						if (sinCambios) {
							opcionDos = JOptionPane.showOptionDialog(null, "Seleccione para modificar:\n\n" 
									+ detallesVentaSinCambios, "Modificacion de Ventas", 0, 1, null, menu, menu[0]);
						} else {
							opcionDos = JOptionPane.showOptionDialog(null, "Seleccione para modificar:\n\n" 
									+ detallesVenta, "Modificacion de Ventas", 0, 1, null, menu, menu[0]);							
						}
						
						switch (opcionDos) {
						case 0:
							// modificar datos de la venta
							cambio = false;
							do {
								
								detallesVenta = "Datos de la Venta:" + "\n[ Metodo de Pago: " + ventaElegida.getMetodoPago()
								+ " - Tipo de Moneda: " + ventaElegida.getMoneda()
								+ " - Estado: " + ventaElegida.getEstado()
								+ " - Origen: " + ventaElegida.getOrigen()
								+ " - Destino: " + ventaElegida.getDestino() + " ]";
								
								opcion = JOptionPane.showOptionDialog(null, detallesVenta ,"Modificacion de Ventas", 0, 1, null, menuVentas, menuVentas[0]);
								switch (opcion) {
								case 0:
									// metodo de pago
									String metodo = ((MetodoPago) JOptionPane.showInputDialog(null, "¿Método de pago?", "Modificando Venta", 1, null, 
											MetodoPago.values(), MetodoPago.values())).name();
									cambio = true;
									ventaElegida.setMetodoPago(metodo);
									break;
								case 1:
									// moneda
									String moneda = ((TipoMoneda) JOptionPane.showInputDialog(null, "¿tipo de Moneda?", "Modificando Venta", 1, null, 
											TipoMoneda.values(), TipoMoneda.values())).name();
									cambio = true;
									ventaElegida.setMoneda(moneda);
									break;
								case 2:
									// origen
									boolean flag2;
									String origen;
									do {
										flag2 = false;
										origen = ((Sucursales) JOptionPane.showInputDialog(null, "ingrese el lugar de Origen", "Modificando Venta", 1, null, 
												Sucursales.values(), Sucursales.values())).name();
										if (origen.equals(ventaElegida.getDestino())) {
											JOptionPane.showMessageDialog(null, "El lugar de Origen no puede ser igual al lugar de Destino");
											flag2 = true;
										}
									} while (flag2);
									cambio = true;
									ventaElegida.setOrigen(origen);
									break;
								case 3:
									// destino
									boolean flag;
									String destino;
									do {
										flag = false;
										destino = ((Sucursales) JOptionPane.showInputDialog(null, "ingrese el lugar de Destino", "Modificando Venta", 1, null, 
												Sucursales.values(), Sucursales.values())).name();
										if (destino.equals(ventaElegida.getOrigen())) {
											JOptionPane.showMessageDialog(null, "El lugar de Destino no puede ser igual al lugar de Origen");
											flag = true;						
										}
									} while (flag);
									cambio = true;
									ventaElegida.setDestino(destino);
									break;
								case 4: 
									// guardar los cambios
									String preguntar;
									if (cambio) {
										preguntar = Validaciones.menuSiNo("¿Esta seguro que desea guardar los cambios?", "Guardar Cambios de la Venta", null);
										if (preguntar.equalsIgnoreCase("Sí")) {
											ventaElegida.setEstado(estados[1]);
											detallesVenta = "Datos de la Venta:" 
													+ "\n- Metodo de Pago: " + ventaElegida.getMetodoPago()
													+ "\n- Tipo de Moneda: " + ventaElegida.getMoneda()
													+ "\n- Estado: " + ventaElegida.getEstado()
													+ "\n- Origen: " + ventaElegida.getOrigen()
													+ "\n- Destino: " + ventaElegida.getDestino();
											VentasExportDTO.actualizarVentaExport(ventaElegida, detallesVenta);
											cambio = false;
											sinCambios = false;
										}
									} else {
										JOptionPane.showMessageDialog(null, "No realizo ningun cambio!!");
									}
									break;
								case 5:
									// volver
									if (cambio) {
										String pregunta = Validaciones.menuSiNo("¿Esta seguro que desea Volver sin guardar los cambios?", "Salir", null);
										if (pregunta.equalsIgnoreCase("Sí")) {
											sinCambios = true;
											break;
										} else {
											sinCambios = false;
											opcion = 6;
										}										
									}
									break;
								}
								
							} while (opcion != 5);
							break;
						}
						
					} while (opcionDos != 1);
				}
			} else {
				JOptionPane.showMessageDialog(null, "El Cliente " + cliente.getNombre() + ", aún no tiene compras registradas!!");
			}
			
		}
		
	}
	
	/**
	 * funcion para anular una Venta Internacional.
	 * @param user
	 */
	public static void anularVentaExport(Usuario user) {
		String []ventas, eleccion;
		String []clientes;
		String []estados = {"completada", "modificada", "anulada"};
		String []menu = {"Anular Venta", "Volver"};
		String seleccion, detallesVenta = "";
		int opcion;
		LinkedList<Exportacion> listaVentas = new LinkedList<Exportacion>();
		Exportacion ventaExport = null;
		Exportacion ventaElegida = null;
		Cliente cliente = null;
		TipoVenta fkTipoVenta = null;
		
		LinkedList<Cliente> listaClientes = ClienteDTO.consultarClientes();
		if (listaClientes.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Aún no Existen Clientes Registrados!!");
		} else {
			clientes = new String[listaClientes.size()];
			for (int i = 0; i < clientes.length; i++) {
				clientes[i] = listaClientes.get(i).getIdCliente() + " - " + listaClientes.get(i).getNombre();
			}
			
			seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un Cliente para ver sus Ventas", "Registro de Clientes!!", 1, null, clientes, clientes[0]);
			eleccion = seleccion.split(" - ");
			
			for (Cliente cliente2 : listaClientes) {
				if (cliente2.getIdCliente() == Integer.parseInt(eleccion[0])) {
					cliente = cliente2;
				}
			}
			
			if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Internacional")) {
				fkTipoVenta = new TipoVenta(2,"Mayorista");					
			}
			
			LinkedList<Carrito> listaCarrito = Carrito.obtenerCarrito(cliente);
			
			if (listaCarrito != null) {
				for (int i = 0; i < listaCarrito.size(); i++) {
					ventaExport = VentasExportDTO.verVentas(listaCarrito.get(i).getIdCarrito(),fkTipoVenta);
					if (ventaExport != null && !(ventaExport.getEstado().equalsIgnoreCase("anulada"))) {
						listaVentas.add(ventaExport);						
					}
				}

				if (listaVentas.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El Cliente " + cliente.getNombre() + ", aún no tiene compras registradas!!");
				} else {
					ventas = new String[listaVentas.size()];
					
					for (int i = 0; i < ventas.length; i++) {
						ventas[i] = listaVentas.get(i).getIdVenta() + " - fecha: " + listaVentas.get(i).getFechaVenta();
					}
					
					seleccion = (String) JOptionPane.showInputDialog(null, "El Cliente: " + cliente.getNombre() + ", registra: " 
							+ ventas.length + " ventas" + "\nSeleccione una venta que desee anular", "Registro de Ventas!!", 1, null, ventas, ventas[0]);
					eleccion = seleccion.split(" - ");
					
					do {
						for (Exportacion venta : listaVentas) {
							if (venta.getIdVenta() == Integer.parseInt(eleccion[0])) {
								ventaElegida = venta;
								detallesVenta = "Datos de la Venta:" 
										+ "\n- Metodo de Pago: " + ventaElegida.getMetodoPago()
										+ "\n- Tipo de Moneda: " + ventaElegida.getMoneda()
										+ "\n- Estado: " + ventaElegida.getEstado()
										+ "\n- Origen: " + ventaElegida.getOrigen()
										+ "\n- Destino: " + ventaElegida.getDestino();
								break;
							}
						}
						
						opcion = JOptionPane.showOptionDialog(null, "Detalles de la Venta:\n\n" + detallesVenta, "Anulacion de Venta", 0, 1, null, menu, menu[0]);
						
						switch (opcion) {
						case 0:
							// modificar datos de la venta
							String preguntar = Validaciones.menuSiNo("¿Esta seguro que desea Anular esta Venta?", "Anular Venta!!", null);
							if (preguntar.equalsIgnoreCase("Sí")) {
								if (ventaElegida.getEstado().equals(estados[2])) {
									JOptionPane.showMessageDialog(null, "La venta con fecha: " + ventaElegida.getFechaVenta() + "\nYa se encuentra Anulada!!");															
								} else {
									ventaElegida.setEstado(estados[2]);
									VentasExportDTO.anularVentaExport(ventaElegida, null);
									opcion = 1;
								}
							}
							break;
						}
					} while (opcion != 1);
				}
			} else {
				JOptionPane.showMessageDialog(null, "El Cliente " + cliente.getNombre() + ", aún no tiene compras registradas!!");
			}
			
		}
	}
		
}
