package BLL;
import java.time.LocalDate;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import DLL.ClienteDTO;
import DLL.VentasLocalDTO;
import Enums.MetodoPago;
import Enums.TipoMoneda;
import Repository.Validaciones;

public class Venta {
	private int idVenta;
	private double totalVenta;
	private LocalDate fechaVenta;
	private String metodoPago;
	private String moneda;
	private String estado;
	private TipoVenta fkTipoVenta;
	private Descuento fkDescuento;
	private Carrito fkCarrito;
	private Usuario fkUsuario;
	
	
	public Venta(int idVenta, double totalVenta, LocalDate fechaVenta, String metodoPago, String moneda, String estado,
			TipoVenta fkTipoVenta, Descuento fkDescuento, Carrito fkCarrito, Usuario fkUsuario) {
		this.idVenta = idVenta;
		this.totalVenta = totalVenta;
		this.fechaVenta = fechaVenta;
		this.metodoPago = metodoPago;
		this.moneda = moneda;
		this.estado = estado;
		this.fkTipoVenta = fkTipoVenta;
		this.fkDescuento = fkDescuento;
		this.fkCarrito = fkCarrito;
		this.fkUsuario = fkUsuario;
	}
	
	public Venta(int idVenta, double totalVenta, LocalDate fechaVenta, String metodoPago, String moneda, String estado) {
		this.idVenta = idVenta;
		this.totalVenta = totalVenta;
		this.fechaVenta = fechaVenta;
		this.metodoPago = metodoPago;
		this.moneda = moneda;
		this.estado = estado;
	}

	public Venta(double totalVenta, LocalDate fechaVenta, String metodoPago, String moneda, String estado, TipoVenta fkTipoVenta,
			Carrito fkCarrito, Usuario fkUsuario) {
		this.totalVenta = totalVenta;
		this.fechaVenta = fechaVenta;
		this.metodoPago = metodoPago;
		this.moneda = moneda;
		this.estado = estado;
		this.fkTipoVenta = fkTipoVenta;
//		this.fkDescuento = fkDescuento; solo por ahora porque no es parte del MVP
		this.fkCarrito = fkCarrito;
		this.fkUsuario = fkUsuario;
	}


	// Getters y Setters
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public double getTotalVenta() {
		return totalVenta;
	}
	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoVenta getFkTipoVenta() {
		return fkTipoVenta;
	}
	public void setFkTipoVenta(TipoVenta fkTipoVenta) {
		this.fkTipoVenta = fkTipoVenta;
	}

	public Descuento getFkDescuento() {
		return fkDescuento;
	}
	public void setFkDescuento(Descuento fkDescuento) {
		this.fkDescuento = fkDescuento;
	}

	public Carrito getFkCarrito() {
		return fkCarrito;
	}
	public void setFkCarrito(Carrito fkCarrito) {
		this.fkCarrito = fkCarrito;
	}

	public Usuario getFkUsuario() {
		return fkUsuario;
	}
	public void setFkUsuario(Usuario fkUsuario) {
		this.fkUsuario = fkUsuario;
	}

	// Métodos
	
	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", totalVenta=" + totalVenta + ", fechaVenta=" + fechaVenta
				+ ", metodoPago=" + metodoPago + ", moneda=" + moneda + ", estado=" + estado + ", fkTipoVenta="
				+ fkTipoVenta + ", fkDescuento=" + fkDescuento + ", fkCarrito=" + fkCarrito + ", fkUsuario=" + fkUsuario
				+ "]";
	}
	
	/**
	 * funcion para pedir los datos para realizar la Venta Internacional.
	 * @param user
	 */
	public static void nuevaVenta(Usuario user) {
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
		int cantidadTotal = 0;
		double totalVenta = 0;
		LocalDate fechaVenta = LocalDate.now();
		String metodoPago, moneda, estado, detalles, continuarVenta = "";
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
					if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Local")) {
						fkTipoVenta = new TipoVenta(1,"Minorista");					
					}
					
					// datos para la venta
					metodoPago = ((MetodoPago) JOptionPane.showInputDialog(null, "¿Método de pago?", "Datos de la Venta", 1, null, 
							MetodoPago.values(), MetodoPago.values()[0])).name();
					moneda = ((TipoMoneda) JOptionPane.showInputDialog(null, "¿tipo de Moneda?", "Datos de la Venta", 1, null, 
							TipoMoneda.values(), TipoMoneda.values()[0])).name();
					estado = estados[0];
					detalles = detalles + "- Cantidad Total: " + cantidadTotal + " libros.\n- Precio Total: $" + totalVenta 
							+ "\n\nDatos de Pago:\n\n- Pago: " + metodoPago + "\n- Moneda: " + moneda + "\n- Estado: " + estado;
					
					continuarVenta = Validaciones.menuContinuar(detalles + "\n\n¿Desea continuar con la Venta?", "Detalles de la Venta!!", null);
					
					if (continuarVenta.equalsIgnoreCase("Modificar")) {
						flag = true;
					}
				} while (flag);
				
				fkCarrito = Carrito.cargarCarrito(fechaVenta, cliente);
				CarritoDetalle.cargarDetalle(carrito,fkCarrito);
				fkUsuario = user;
				
				Venta venta = new Venta(totalVenta,fechaVenta,metodoPago,moneda,estado,fkTipoVenta,fkCarrito,fkUsuario);
				
				Libro.actualizarStock(carrito);
				VentasLocalDTO.nuevaVentaLocal(venta, detalles);
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Cliente No Encontrado!!");
			nuevaVenta(user);
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
			int cantidadTotal = 0;
			double totalVenta = 0;
			LocalDate fechaVenta = LocalDate.now();
			String metodoPago, moneda, estado, detalles;
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
					if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Local")) {
						fkTipoVenta = new TipoVenta(1,"Minorista");					
					}
					
					// datos para la venta
					metodoPago = ((MetodoPago) JOptionPane.showInputDialog(null, "¿Método de pago?", "Datos de la Venta", 1, null, 
							MetodoPago.values(), MetodoPago.values())).name();
					moneda = ((TipoMoneda) JOptionPane.showInputDialog(null, "¿tipo de Moneda?", "Datos de la Venta", 1, null, 
							TipoMoneda.values(), TipoMoneda.values())).name();
					estado = estados[0];
					detalles = detalles + "- Cantidad Total: " + cantidadTotal + " libros.\n- Precio Total: $" + totalVenta 
							+ "Datos de Pago:\n\n- Pago: " + metodoPago + "\n- Moneda: " + moneda + "\n- Estado: " + estado;
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
				
				Venta venta = new Venta(totalVenta,fechaVenta,metodoPago,moneda,estado,fkTipoVenta,fkCarrito,fkUsuario);
				// descontamos el stock en la BD
				Libro.actualizarStock(carrito);
				// realizamos la cárga de la Venta en la BD
				VentasLocalDTO.nuevaVentaLocal(venta, detalles);
			}
			
		} else {
			nuevaVenta(user);
		}
	}
	
	/**
	 * funcion para mostrar los datos a modificar de la Venta Internacional.
	 * @param user
	 */
	public static void modificarVentaLocal(Usuario user) {
		String []ventas, eleccion;
		String []clientes;
		String []estados = {"completada", "modificada", "anulada"};
		String []menu = {"Modificar Datos de Venta", "Volver"};
		String []menuVentas = {"Metodo-Pago", "Moneda","Guardar-Cambios", "volver"};
		String seleccion, detallesVenta = "", detallesVentaSinCambios = "";
		int opcion, opcionDos;
		boolean cambio, sinCambios = false;
		LinkedList<Venta> listaVentas = new LinkedList<Venta>();
		Venta ventaLocal = null;
		Venta ventaElegida = null;
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
			
			if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Local")) {
				fkTipoVenta = new TipoVenta(1,"Minorista");					
			}
			
			LinkedList<Carrito> listaCarrito = Carrito.obtenerCarrito(cliente);
			
			if (listaCarrito != null) {
				for (int i = 0; i < listaCarrito.size(); i++) {
					ventaLocal = VentasLocalDTO.verVentasLocales(listaCarrito.get(i).getIdCarrito(),fkTipoVenta);
					if (ventaLocal != null) {
						listaVentas.add(ventaLocal);						
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
							for (Venta venta : listaVentas) {
								if (venta.getIdVenta() == Integer.parseInt(eleccion[0])) {
									ventaElegida = venta;
									detallesVenta = "Datos de la Venta:" 
											+ "\n- Metodo de Pago: " + ventaElegida.getMetodoPago()
											+ "\n- Tipo de Moneda: " + ventaElegida.getMoneda()
											+ "\n- Estado: " + ventaElegida.getEstado();
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
								+ " - Estado: " + ventaElegida.getEstado() + " ]";
								
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
									// guardar los cambios
									String preguntar = Validaciones.menuSiNo("¿Esta seguro que desea guardar los cambios?", "Guardar Cambios de la Venta", null);
									if (preguntar.equalsIgnoreCase("Sí")) {
										ventaElegida.setEstado(estados[1]);
										detallesVenta = "Datos de la Venta:" 
												+ "\n- Metodo de Pago: " + ventaElegida.getMetodoPago()
												+ "\n- Tipo de Moneda: " + ventaElegida.getMoneda()
												+ "\n- Estado: " + ventaElegida.getEstado();
										VentasLocalDTO.actualizarVentaLocal(ventaElegida, detallesVenta);
										cambio = false;
										sinCambios = false;
									}
									break;
								case 3:
									// volver
									if (cambio) {
										String pregunta = Validaciones.menuSiNo("¿Esta seguro que desea Volver sin guardar los cambios?", "Salir", null);
										if (pregunta.equalsIgnoreCase("Sí")) {
											sinCambios = true;
											break;
										} else {
											sinCambios = false;
											opcion = 3;
										}										
									}
									break;
								}
								
							} while (opcion != 3);
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
	public static void anularVentaLocal(Usuario user) {
		String []ventas, eleccion;
		String []clientes;
		String []estados = {"completada", "modificada", "anulada"};
		String []menu = {"Anular Venta", "Volver"};
		String seleccion, detallesVenta = "";
		int opcion;
		LinkedList<Venta> listaVentas = new LinkedList<Venta>();
		Venta ventaLocal = null;
		Venta ventaElegida = null;
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
			
			if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Local")) {
				fkTipoVenta = new TipoVenta(1,"Minorista");					
			}
			
			LinkedList<Carrito> listaCarrito = Carrito.obtenerCarrito(cliente);
			
			if (listaCarrito != null) {
				for (int i = 0; i < listaCarrito.size(); i++) {
					ventaLocal = VentasLocalDTO.verVentasLocales(listaCarrito.get(i).getIdCarrito(),fkTipoVenta);
					if (ventaLocal != null && !(ventaLocal.getEstado().equalsIgnoreCase("anulada"))) {
						listaVentas.add(ventaLocal);						
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
						for (Venta venta : listaVentas) {
							if (venta.getIdVenta() == Integer.parseInt(eleccion[0])) {
								ventaElegida = venta;
								detallesVenta = "Datos de la Venta:" 
										+ "\n- Metodo de Pago: " + ventaElegida.getMetodoPago()
										+ "\n- Tipo de Moneda: " + ventaElegida.getMoneda()
										+ "\n- Estado: " + ventaElegida.getEstado();
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
									VentasLocalDTO.anularVentaLocal(ventaElegida, null);	
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
