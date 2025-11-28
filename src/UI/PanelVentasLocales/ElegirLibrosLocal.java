package UI.PanelVentasLocales;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import BLL.Carrito;
import BLL.CarritoDetalle;
import BLL.Cliente;
import BLL.Exportacion;
import BLL.Libro;
import BLL.TipoVenta;
import BLL.Usuario;
import BLL.Venta;
import DLL.LibroDTO;
import DLL.VentasExportDTO;
import DLL.VentasLocalDTO;
import Enums.MetodoPago;
import Enums.Sucursales;
import Enums.TipoMoneda;
import Repository.Validaciones;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.SwingConstants;

public class ElegirLibrosLocal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textCantidad;
	private CarritoDetalle carritoSeleccionado;

	/**
	 * Create the frame.
	 */
    
	public ElegirLibrosLocal(Usuario user, Cliente cliente) {
		LinkedList<CarritoDetalle> librosCarrito = new LinkedList<CarritoDetalle>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Datos de la Venta");
		lblTitulo.setBounds(250, 10, 185, 32);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblTitulo);

		JLabel lblCliente = new JLabel("Cliente: " + cliente.toString());
		lblCliente.setBounds(19, 52, 647, 13);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblCliente);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(13, 79, 660, 574);
		contentPane.add(tabbedPane);

		model = new DefaultTableModel(new String[] { "ID", "TITULO", "PRECIO", "CANTIDAD" }, 0);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Elegir Libros", null, panel, null);
		panel.setLayout(null);

		JComboBox selector = new JComboBox();
		selector.setBounds(10, 223, 185, 21);
		selector.addItem("Selección");
		panel.add(selector);

		LinkedList<Libro> listaLibros = LibroDTO.elegirLibros();
		for (Libro libro : listaLibros) {
			System.out.println(libro.getStock());
		}

		if (listaLibros != null) {
			for (Libro libro : listaLibros) {
				selector.addItem(libro.getTitulo());
			}
		} else {
			selector.addItem("Libros No disponibles");
		}

		JLabel lblselector = new JLabel("Seleccionar Libro:");
		lblselector.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblselector.setBounds(10, 200, 129, 13);
		panel.add(lblselector);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCantidad.setBounds(213, 200, 75, 13);
		panel.add(lblCantidad);

		textCantidad = new JTextField();
		textCantidad.setBounds(213, 223, 54, 19);
		panel.add(textCantidad);
		textCantidad.setColumns(10);

		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblError.setBounds(10, 249, 507, 13);
		panel.add(lblError);

		JLabel lblMensaje = new JLabel("");
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblMensaje.setForeground(Color.RED);
		lblMensaje.setBounds(527, 184, 118, 13);
		panel.add(lblMensaje);
		
		JLabel lblStock = new JLabel("");
		lblStock.setForeground(new Color(14, 162, 2));
		lblStock.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblStock.setBounds(285, 201, 85, 13);
		panel.add(lblStock);
		
		JLabel lblErrorPago = new JLabel("");
		lblErrorPago.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorPago.setForeground(new Color(255, 0, 0));
		lblErrorPago.setBounds(114, 391, 161, 13);
		panel.add(lblErrorPago);
		
		JLabel lblErrorMoneda = new JLabel("");
		lblErrorMoneda.setForeground(Color.RED);
		lblErrorMoneda.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorMoneda.setBounds(399, 391, 166, 13);
		panel.add(lblErrorMoneda);
		
		JLabel lblErrorContinuar = new JLabel("");
		lblErrorContinuar.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorContinuar.setForeground(new Color(255, 0, 0));
		lblErrorContinuar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblErrorContinuar.setBounds(121, 447, 413, 13);
		panel.add(lblErrorContinuar);
		
		selector.addActionListener(e-> {
			lblMensaje.setText("");
			if (selector.getSelectedItem() != "Selección") {
				lblError.setText("");
				for (Libro libro : listaLibros) {
					if (libro.getTitulo().equals(selector.getSelectedItem())) {
						lblStock.setText("disp: " + libro.getStock() + " libros.");
						break;
					}
				}
			} else {
				lblStock.setText("");
			}
		});
		
		JButton btnAceptar = new JButton("Agregar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarLibro(selector,lblError,lblErrorContinuar,librosCarrito,listaLibros);
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptar.setBounds(285, 222, 85, 21);
		panel.add(btnAceptar);

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 37, 635, 141);
		panel.add(scrollPane);

		// evento al hacer click en algun libro cargado en la tabla
		table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
            	lblMensaje.setText("");
                int row = table.getSelectedRow();
                if (row != -1) {
                	// buscar dentro del carrito el libro que tenga el mismo id que el de la tabla
                	for (CarritoDetalle libro : librosCarrito) {    // esto es la fila del id
						if (libro.getFkLibro().getId_libro() == (int)model.getValueAt(row, 0)) {
							//cargamos el objeto carritoSeleccionado
							carritoSeleccionado = libro; //carrito
							break;
						}
					}

				}
			}
		});

		JLabel lblSubtituloLibrosCarrito = new JLabel("Libros en el Carrito:");
		lblSubtituloLibrosCarrito.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSubtituloLibrosCarrito.setBounds(10, 14, 129, 13);
		panel.add(lblSubtituloLibrosCarrito);

		JButton btnEliminar = new JButton("Eliminar Libro");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (carritoSeleccionado != null) {
					for (Libro libro : listaLibros) {
						if (libro.equals(carritoSeleccionado.getFkLibro())) {
							libro.setStock(libro.getStock()+ carritoSeleccionado.getCantidad());
							selector.setSelectedItem("Selección");
							librosCarrito.remove(carritoSeleccionado);
							break;
						}
					}
					// actualizamos la lista
					librosElegidos(librosCarrito);
					lblMensaje.setText("Libro eliminado!!");
					lblMensaje.setForeground(new Color(20,122,5));
					carritoSeleccionado = null;
				} else {
					lblMensaje.setText("Seleccione un Libro!!");
					lblMensaje.setForeground(Color.RED);
				}

			}
		});
		btnEliminar.setBounds(516, 222, 129, 21);
		panel.add(btnEliminar);

		JButton btnCancelarVenta = new JButton("Cancelar Venta");
		btnCancelarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarVentas gestionVenta = new PanelGestionarVentas(user);
				gestionVenta.setVisible(true);
				dispose();
			}
		});
		btnCancelarVenta.setBounds(10, 516, 129, 21);
		panel.add(btnCancelarVenta);

		JLabel lblSubtitulo = new JLabel("Cargar Datos:");
		lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSubtitulo.setBounds(255, 264, 144, 25);
		panel.add(lblSubtitulo);

		JLabel lblMetodoPago = new JLabel("Metodo de Pago:");
		lblMetodoPago.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMetodoPago.setBounds(114, 337, 118, 21);
		panel.add(lblMetodoPago);

		JComboBox selectorPago = new JComboBox();
		selectorPago.setBounds(112, 359, 103, 22);
		selectorPago.addItem("Seleccionar");
		for (MetodoPago pago : MetodoPago.values()) {
			selectorPago.addItem(pago.name());
		}
		panel.add(selectorPago);

		JLabel lblTipoMoneda = new JLabel("Tipo de Moneda:");
		lblTipoMoneda.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipoMoneda.setBounds(399, 337, 118, 21);
		panel.add(lblTipoMoneda);

		JComboBox selectorMoneda = new JComboBox();
		selectorMoneda.setBounds(399, 359, 103, 22);
		selectorMoneda.addItem("Seleccionar");
		for (TipoMoneda tipo : TipoMoneda.values()) {
			selectorMoneda.addItem(tipo.name());
		}
		panel.add(selectorMoneda);
		
		selectorPago.addActionListener(e -> {
			if (selectorPago.getSelectedItem() != "Seleccionar") {
				lblErrorPago.setText("");
			}
		});
		
		selectorMoneda.addActionListener(e -> {
			if (selectorMoneda.getSelectedItem() != "Seleccionar") {
				lblErrorMoneda.setText("");
			}
		});

		JButton btnRealizarVenta = new JButton("Realizar Venta");
		btnRealizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (librosCarrito.isEmpty()) {
					lblErrorContinuar.setText("para continuar con la Venta, debe agregar libros a su Carrito!!");
					lblError.setText("Elija un Libro!!");
				} else {
					if (selectorPago.getSelectedItem() != "Seleccionar") {
						if (selectorMoneda.getSelectedItem() != "Seleccionar") {
							tabbedPane.setEnabledAt(1, true);
							tabbedPane.setSelectedIndex(1);
							tabbedPane.setEnabledAt(0, false);
						} else {
							lblErrorMoneda.setText("elija el tipo de Moneda!!");
						}
					} else {
						lblErrorPago.setText("elija el Metodo de Pago!!");
					}
				}
			}
		});
		btnRealizarVenta.setBounds(527, 515, 118, 23);
		panel.add(btnRealizarVenta);
		
		
		// SEGUNDO PANEL PARA VER LOS DETALLES DE LOS LIBROS ELEGIDOS
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Detalles", null, panel_2, null);
		tabbedPane.setEnabledAt(1, false);
		panel_2.setLayout(null);

		JLabel lblTituloDetalles = new JLabel("Detalles de la Venta");
		lblTituloDetalles.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDetalles.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTituloDetalles.setBounds(248, 25, 159, 25);
		panel_2.add(lblTituloDetalles);

		JLabel lblLibros = new JLabel();
		lblLibros.setFont(new Font("Lucida Fax", Font.PLAIN, 13));
		lblLibros.setVerticalAlignment(SwingConstants.TOP);
		lblLibros.setBounds(10, 57, 635, 208);
		panel_2.add(lblLibros);
		
		JLabel lblPago = new JLabel();
		lblPago.setFont(new Font("Lucida Fax", Font.PLAIN, 13));
		lblPago.setVerticalAlignment(SwingConstants.TOP);
		lblPago.setText((String) null);
		lblPago.setBounds(10, 318, 635, 175);
		panel_2.add(lblPago);
		
		btnRealizarVenta.addActionListener(e ->{
			lblLibros.setText(mostrarDetallesLibro(librosCarrito));
			lblPago.setText(mostrarDetallesPago(selectorPago,selectorMoneda));
		});
		
		JButton btnCancelar = new JButton("Cancelar Venta");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarVentas gestionVenta = new PanelGestionarVentas(user);
				gestionVenta.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(10, 516, 142, 21);
		panel_2.add(btnCancelar);
		
		JLabel lblVentaMensaje = new JLabel("");
		lblVentaMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentaMensaje.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblVentaMensaje.setForeground(Color.RED);
		lblVentaMensaje.setBounds(77, 476, 500, 25);
		panel_2.add(lblVentaMensaje);
		
		JButton btnConfirmarVenta = new JButton("Confirmar Venta");
		btnConfirmarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = cargarVenta(librosCarrito, user, cliente, selectorPago, selectorMoneda);
				
				if (flag) {
					tabbedPane.setEnabledAt(2, true);
					tabbedPane.setSelectedIndex(2);
					tabbedPane.setEnabledAt(1, false);
				} else {
					lblVentaMensaje.setText("Ocurrio un Error inesperado!!");
				}
			}
		});
		btnConfirmarVenta.setBounds(503, 516, 142, 21);
		panel_2.add(btnConfirmarVenta);
		
		JLabel lblTituloDetallesPago = new JLabel("Detalles del Pago");
		lblTituloDetallesPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDetallesPago.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTituloDetallesPago.setBounds(248, 283, 159, 25);
		panel_2.add(lblTituloDetallesPago);
		
		
		// TERCER PANEL PARA MOSTRAR EL MENSAJE DE EXITO DE LA VENTA
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("fin venta", null, panel_3, null);
		tabbedPane.setEnabledAt(2, false);
		panel_3.setLayout(null);
		
		JLabel lblMensajeExito = new JLabel("");
		lblMensajeExito.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajeExito.setForeground(new Color(28, 157, 21));
		lblMensajeExito.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblMensajeExito.setBounds(143, 31, 369, 28);
		panel_3.add(lblMensajeExito);
		
		JLabel lblTitulo_1 = new JLabel("Detalles de la Venta");
		lblTitulo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo_1.setBounds(234, 78, 187, 28);
		panel_3.add(lblTitulo_1);
		
		JLabel lblDatosVenta = new JLabel();
		lblDatosVenta.setVerticalAlignment(SwingConstants.TOP);
		lblDatosVenta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDatosVenta.setBounds(63, 117, 529, 162);
		panel_3.add(lblDatosVenta);
		
		JLabel lblDatosPago = new JLabel();
		lblDatosPago.setVerticalAlignment(SwingConstants.TOP);
		lblDatosPago.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDatosPago.setBounds(63, 321, 529, 153);
		panel_3.add(lblDatosPago);
		
		btnConfirmarVenta.addActionListener(e-> {
			lblMensajeExito.setText("Venta Realizada con Exito!!");
			lblDatosVenta.setText(mostrarDetallesLibro(librosCarrito));
			lblDatosPago.setText(mostrarDetallesPago(selectorPago,selectorMoneda));
		});
		
		JButton btnBotonAceptar = new JButton("Aceptar");
		btnBotonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarVentas gestionVenta = new PanelGestionarVentas(user);
				gestionVenta.setVisible(true);
				dispose();
			}
		});
		btnBotonAceptar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBotonAceptar.setBounds(279, 484, 96, 35);
		panel_3.add(btnBotonAceptar);
	}

	// FUNCIONES
	public void librosElegidos(LinkedList<CarritoDetalle> lista) {
		LinkedList<CarritoDetalle> libros = lista.stream()
				.sorted(Comparator.comparingInt(CarritoDetalle::getCantidad).reversed())
				.collect(Collectors.toCollection(LinkedList::new));

		model.setRowCount(0);
		for (CarritoDetalle L : libros) {
			model.addRow(new Object[] { L.getFkLibro().getId_libro(), // id
					L.getFkLibro().getTitulo(), // titulo
					L.getFkLibro().getPrecio(), // precio
					L.getCantidad() // cantidad
			});
		}
	}
	
	public void cargarLibro(JComboBox selector, JLabel lblError, JLabel lblErrorContinuar, LinkedList<CarritoDetalle> librosCarrito, LinkedList<Libro> listaLibros) {
		String nombreLibro = (String)selector.getSelectedItem();
		boolean texto = Validaciones.validarVacioJframe(nombreLibro);
		String cantidad = Validaciones.validarIntJframe(textCantidad.getText());
		
		if (texto != false || nombreLibro.equalsIgnoreCase("selección")) {
			lblError.setText("debe seleccionar un Libro!!");
		} else {
			switch (cantidad) {
			case "vacio":
				lblError.setText("Debe ingresar una Cantidad!!");	
				break;
			case "letra":
				lblError.setText("No puede ingresar Letras!!");	
				break;
			case "tamaño":
				lblError.setText("No puede ingresar esa Cantidad!!");	
				break;
			case "ok":
				int cant = Integer.parseInt(textCantidad.getText());
				int stockLibro = 0;
				boolean encontrado = true, sinStock = false;
				
				lblError.setText("");
				lblErrorContinuar.setText("");
				
				if (librosCarrito.isEmpty()) {
					for (Libro libro : listaLibros) {
						if (nombreLibro.equalsIgnoreCase(libro.getTitulo()) && libro.getStock() >= cant) {
							librosCarrito.add(new CarritoDetalle(cant,libro));
							libro.setStock(libro.getStock()-cant);
							sinStock = false;
							break;
						} else if (nombreLibro.equalsIgnoreCase(libro.getTitulo())) {
							sinStock = true;
							stockLibro = libro.getStock();
						}
					}
					
					if (sinStock) {
						lblError.setText("No tenemos Stock del libro: \"" + nombreLibro + "\", Stock disp: " + stockLibro + " libros.");
					}
					
					textCantidad.setText("");
					selector.setSelectedItem("Selección");
				} else {
					// verificamos que en la lista principal de libros tengamos stock del libro seleccionado.
					for (Libro libro : listaLibros) {
						if (nombreLibro.equalsIgnoreCase(libro.getTitulo()) && libro.getStock() >= cant) {
							libro.setStock(libro.getStock()-cant);
							sinStock = false;
							break;									
						} else if (nombreLibro.equalsIgnoreCase(libro.getTitulo())) {
							sinStock = true;
							stockLibro = libro.getStock();
						}
					}
					
					// buscamos en el carrito si el ya tenemos el libro seleccionado y le agregamos la cantidad extra.
					if (sinStock != true) {
						for (CarritoDetalle carrito : librosCarrito) {
							if (carrito.getFkLibro().getTitulo().equalsIgnoreCase(nombreLibro)) {
								carrito.setCantidad(carrito.getCantidad() + cant);
								
								encontrado = false;
								break;
							} 
						}
						
						// si no encuentra un libro previamente seleccionado es un libro nuevo y lo carga al carrito.
						if (encontrado) {
							for (Libro libro : listaLibros) {
								if (nombreLibro.equalsIgnoreCase(libro.getTitulo())) {
									librosCarrito.add(new CarritoDetalle(cant,libro));
									break;
								}
							}
						}		
						textCantidad.setText("");
						selector.setSelectedItem("Selección");
					} else {
						lblError.setText("No tenemos Stock del libro: \"" + nombreLibro + "\", Stock disp: " + stockLibro + " libros.");
						textCantidad.setText("");
						selector.setSelectedItem("Selección");
					}
				}
				librosElegidos(librosCarrito);
				break;
			}			
		}
	}
	
	public String mostrarDetallesLibro(LinkedList<CarritoDetalle> lista) {
		String detalles = "<html><strong>Libros:</strong><br><br>";
		int totalPago = 0;
		int cant = 0;

		for (CarritoDetalle carrito : lista) {
			totalPago = (int) (totalPago + (carrito.getCantidad() * carrito.getFkLibro().getPrecio()));
			cant = cant + carrito.getCantidad();
			detalles = detalles + "- " + carrito.getFkLibro().getTitulo() + ", cantidad: " + carrito.getCantidad()
					+ " unidades, precio: $" + carrito.getFkLibro().getPrecio() + "<br>";
		}
		
		detalles = detalles + "<br><strong>Total de Libros: </strong>" + cant + " libros.";
		return detalles = detalles + "<br><strong>Total a Pagar: </strong>$" + totalPago + "<br></html>";
	}

	public double totalVentaLibros(LinkedList<CarritoDetalle> lista) {
		double totalPago = 0;
		for (CarritoDetalle carrito : lista) {
			totalPago = totalPago + (carrito.getCantidad() * carrito.getFkLibro().getPrecio());
		}
		return totalPago;
	}
	
	public String mostrarDetallesPago(JComboBox pago, JComboBox moneda) {
		String datos =
			    "<html>"
			        + "<table cellspacing='20'>" // separa un poco las columnas
			        + "<tr>"
			            + "<td style='padding-right: 40px;'><b>Método de Pago:</b> " + pago.getSelectedItem() + ".</td>"
			            + "<td><b>Tipo de Moneda:</b> " + moneda.getSelectedItem() + ".</td>"
			        + "</tr>"
			        + "</table>"
			    + "</html>";
		return datos;
	}
	
	public boolean cargarVenta(LinkedList<CarritoDetalle> librosCarrito, Usuario user, Cliente cliente, JComboBox pago, JComboBox moneda) {
		LocalDate fechaVenta = LocalDate.now();
		TipoVenta fkTipoVenta = null;
		Carrito fkCarrito = null;
		Usuario fkUsuario = null;
		double totalVenta = totalVentaLibros(librosCarrito);
		String []estados = {"completada", "modificada", "anulada"};
		
		if (user.getFkTipoEmpleado().getTipoEmpleado().equalsIgnoreCase("Vendedor Local")) {
			fkTipoVenta = new TipoVenta(1,"Minorista");					
		}
		String estado = estados[0];
		
		fkCarrito = Carrito.cargarCarrito(fechaVenta, cliente);
		CarritoDetalle.cargarDetalle(librosCarrito,fkCarrito);
		fkUsuario = user;
		
		Venta venta = new Venta(totalVenta,fechaVenta,(String)pago.getSelectedItem(),(String)moneda.getSelectedItem(),estado,fkTipoVenta,fkCarrito,fkUsuario);
		
		Libro.actualizarStock(librosCarrito);
		
		return VentasLocalDTO.nuevaVentaLocalJframe(venta);
	}
}
