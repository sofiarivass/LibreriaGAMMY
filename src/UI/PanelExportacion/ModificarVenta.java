package UI.PanelExportacion;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import BLL.Carrito;
import BLL.Cliente;
import BLL.Exportacion;
import BLL.TipoVenta;
import BLL.Usuario;
import DLL.ClienteDTO;
import DLL.VentasExportDTO;
import Enums.MetodoPago;
import Enums.Sucursales;
import Enums.TipoMoneda;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarVenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Cliente cliente;
	private Exportacion venta;
	private String dato;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarVenta frame = new ModificarVenta(null,null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModificarVenta(Usuario user, Exportacion v, String datos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 40, 621, 353);
		contentPane.add(tabbedPane);
		
		model = new DefaultTableModel(new String[]{"ID-VENTA", "FECHA-VENTA", "METODO-PAGO", "TIPO-MONEDA", "ORIGEN", "DESTINO"}, 0);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("seleccionar venta", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modificar Ventas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(224, 10, 192, 34);
		contentPane.add(lblTitulo);
		
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 34, 596, 125);
		panel.add(scrollPane);
		
		JLabel lblCliente = new JLabel("");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCliente.setBounds(10, 11, 596, 16);
		panel.add(lblCliente);
		
		JLabel lblSelecVenta = new JLabel("");
		lblSelecVenta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelecVenta.setBounds(10, 168, 596, 16);
		panel.add(lblSelecVenta);
		
		JLabel lblSelecCliente = new JLabel("Seleccionar Cliente:");
		lblSelecCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelecCliente.setBounds(10, 193, 114, 24);
		panel.add(lblSelecCliente);
		
		JLabel lblMensajeError = new JLabel("");
		lblMensajeError.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajeError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblMensajeError.setForeground(new Color(255, 0, 0));
		lblMensajeError.setBounds(448, 171, 158, 13);
		panel.add(lblMensajeError);
		
		JLabel lblMensajeErrorCliente = new JLabel("");
		lblMensajeErrorCliente.setForeground(Color.RED);
		lblMensajeErrorCliente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblMensajeErrorCliente.setBounds(133, 199, 114, 13);
		panel.add(lblMensajeErrorCliente);
		
		JComboBox selectorCliente = new JComboBox();
		selectorCliente.setBounds(10, 216, 123, 24);
		selectorCliente.addItem("Selección");
		panel.add(selectorCliente);
		
		LinkedList<Cliente>listaClientes = ClienteDTO.consultarClientes();
		if (listaClientes.isEmpty()) {
			lblCliente.setText("No hay Clientes Disponibles!!");
			lblCliente.setForeground(Color.black);
		} else {
			for (Cliente c : listaClientes) {
				selectorCliente.addItem(c.getIdCliente() + " - " + c.getNombre());
			}
		}
		
		JButton btnSelecCliente = new JButton("Aceptar");
		btnSelecCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensajeError.setText("");
				lblSelecVenta.setText("");
				venta = null;
				LinkedList<Exportacion> listaVentas = new LinkedList<Exportacion>();
				String []seleccionado;
				String seleccion;
				TipoVenta fkTipoVenta = null;
				Exportacion ventaExport = null;
				
				if (selectorCliente.getSelectedItem() != "Selección") {
					seleccion = (String) selectorCliente.getSelectedItem();
					seleccionado = seleccion.split(" - ");
					for (Cliente c : listaClientes) {
						if (c.getIdCliente() == Integer.parseInt(seleccionado[0])) {
							cliente = c;
							selectorCliente.setSelectedItem("Selección");
							lblMensajeErrorCliente.setText("");
							break;
						}
					}
					lblCliente.setText(cliente.toString());
					lblCliente.setForeground(Color.black);
					
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
					}
					librosElegidos(listaVentas);
				} else {
					lblMensajeErrorCliente.setText("elija un Cliente!!");
				}
			}
		});
		btnSelecCliente.setBounds(143, 218, 85, 21);
		panel.add(btnSelecCliente);
		
		JButton btnModificar = new JButton("Modificar Venta");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (venta != null) {
					tabbedPane.setEnabledAt(1, true);
					tabbedPane.setSelectedIndex(1);
					tabbedPane.setEnabledAt(0, false);
				} else {
					lblMensajeError.setText("Seleccione una Venta!!");
				}
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnModificar.setBounds(481, 196, 125, 21);
		panel.add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 295, 85, 21);
		panel.add(btnVolver);
		
		// evento al hacer click en algun libro cargado en la tabla
		table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                	
                	lblMensajeError.setText("");
                	LinkedList<Exportacion> listaVentas = new LinkedList<Exportacion>();
                	TipoVenta fkTipoVenta = null;
    				Exportacion ventaExport = null;
                	
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
					}
                	
                	// buscar dentro de las ventas del cliente quien tenga el mismo id que el de la tabla
                	for (Exportacion libro : listaVentas) {    // esto es la fila del id
						if (libro.getIdVenta() == (int)model.getValueAt(row, 0)) {
							//cargamos la venta
							venta = libro; //Venta
							lblSelecVenta.setText(venta.toString());
							
							JLabel lblExito = new JLabel("");
							lblExito.setHorizontalAlignment(SwingConstants.CENTER);
							lblExito.setForeground(new Color(61, 166, 15));
							lblExito.setFont(new Font("Tahoma", Font.BOLD, 15));
							lblExito.setBounds(246, 283, 335, 30);
							panel.add(lblExito);
							break;
						}
					}
                }
            }
        });
		
		
		// segundo panel
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("modificar venta", null, panel_2, null);
		tabbedPane.setEnabledAt(1, false);
		panel_2.setLayout(null);
		
		JLabel lblSubtitulo = new JLabel("Datos Actuales");
		lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setBounds(234, 10, 148, 21);
		panel_2.add(lblSubtitulo);
		
		JLabel lblDatosActuales = new JLabel("");
		lblDatosActuales.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosActuales.setVerticalAlignment(SwingConstants.TOP);
		lblDatosActuales.setBounds(30, 47, 555, 90);
		panel_2.add(lblDatosActuales);
		
		JLabel lblSubtitulo2 = new JLabel("Modificar Datos de la Venta");
		lblSubtitulo2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSubtitulo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo2.setBounds(196, 147, 224, 32);
		panel_2.add(lblSubtitulo2);
		
		// mensajes de error
		JLabel lblErrorPago = new JLabel("");
		lblErrorPago.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorPago.setForeground(new Color(255, 0, 0));
		lblErrorPago.setBounds(166, 216, 156, 13);
		panel_2.add(lblErrorPago);
		
		JLabel lblErrorOrigen = new JLabel("");
		lblErrorOrigen.setForeground(new Color(255, 0, 0));
		lblErrorOrigen.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorOrigen.setBounds(450, 216, 156, 13);
		panel_2.add(lblErrorOrigen);
		
		JLabel lblErrorMoneda = new JLabel("");
		lblErrorMoneda.setForeground(new Color(255, 0, 0));
		lblErrorMoneda.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorMoneda.setBounds(165, 259, 156, 13);
		panel_2.add(lblErrorMoneda);
		
		JLabel lblErrorDestino = new JLabel("");
		lblErrorDestino.setForeground(new Color(255, 0, 0));
		lblErrorDestino.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorDestino.setBounds(449, 259, 157, 13);
		panel_2.add(lblErrorDestino);
		
		JLabel lblMensaje = new JLabel("");
		lblMensaje.setForeground(new Color(255, 0, 0));
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setBounds(187, 299, 242, 13);
		panel_2.add(lblMensaje);
		
		JLabel lblMetodoPago = new JLabel("Metodo de Pago:");
		lblMetodoPago.setBounds(50, 194, 103, 21);
		panel_2.add(lblMetodoPago);
		
		JComboBox selectorPago = new JComboBox();
		selectorPago.setBounds(163, 194, 95, 21);
		selectorPago.addItem("Seleccionar");
		for (MetodoPago pago : MetodoPago.values()) {
			selectorPago.addItem(pago.name());
		}
		panel_2.add(selectorPago);
		
		JLabel lblTipoMoneda = new JLabel("Tipo de Moneda:");
		lblTipoMoneda.setBounds(50, 237, 103, 21);
		panel_2.add(lblTipoMoneda);
		
		JComboBox selectorMoneda = new JComboBox();
		selectorMoneda.setBounds(163, 237, 95, 21);
		selectorMoneda.addItem("Seleccionar");
		for (TipoMoneda tipo : TipoMoneda.values()) {
			selectorMoneda.addItem(tipo.name());
		}
		panel_2.add(selectorMoneda);
		
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setBounds(377, 194, 63, 21);
		panel_2.add(lblOrigen);
		
		JComboBox selectorOrigen = new JComboBox();
		selectorOrigen.setBounds(447, 194, 95, 21);
		selectorOrigen.addItem("Seleccionar");
		for (Sucursales origen : Sucursales.values()) {
			selectorOrigen.addItem(origen.name());
		}
		panel_2.add(selectorOrigen);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setBounds(376, 237, 53, 21);
		panel_2.add(lblDestino);
		
		JComboBox selectorDestino = new JComboBox();
		selectorDestino.setBounds(448, 237, 95, 21);
		selectorDestino.addItem("Seleccionar");
		for (Sucursales destino : Sucursales.values()) {
			selectorDestino.addItem(destino.name());
		}
		panel_2.add(selectorDestino);
		
		// activadores
		btnModificar.addActionListener(e -> {
			// logica que se carga el momento de cambiar de panel
			if (venta != null) {
				dato = datosVenta(venta);
				lblDatosActuales.setText(dato);				
			}
		});
		
		selectorPago.addActionListener(e -> {
			lblMensaje.setText("");
			String pago = (String) selectorPago.getSelectedItem();
			
			if (!pago.equals("Seleccionar")) {
				lblErrorPago.setText("");
			}
		});
		
		selectorMoneda.addActionListener(e -> {
			lblMensaje.setText("");
			String moneda = (String) selectorMoneda.getSelectedItem();
			
			if (!moneda.equals("Seleccionar")) {
				lblErrorMoneda.setText("");
			}
		});
		
		selectorOrigen.addActionListener(e -> {
			lblMensaje.setText("");
			String origen = (String) selectorOrigen.getSelectedItem();
			String destino = (String) selectorDestino.getSelectedItem();			
			
			if (!origen.equals("Seleccionar") || !destino.equals("Seleccionar")) {
				if (origen.equals(destino)) {
					lblErrorOrigen.setText("Error elija otra Sucursal!!");
				} else if (origen.equals(venta.getDestino())) {
					lblErrorOrigen.setText("Error elija otra Sucursal!!");					
				} else if (!origen.equals(destino) && !origen.equals(venta.getDestino()) && !destino.equals(venta.getOrigen())) {
					lblErrorOrigen.setText("");
					lblErrorDestino.setText("");					
				} else if (origen.equals(venta.getDestino()) && !origen.equals(destino)) {
					lblErrorOrigen.setText("");					
				}
			}
		});
		
		selectorDestino.addActionListener(e -> {
			lblMensaje.setText("");
			String destino = (String) selectorDestino.getSelectedItem();
			String origen = (String) selectorOrigen.getSelectedItem();
			
			
			if (!destino.equals("Seleccionar") || !origen.equals("Seleccionar")) {
				if (destino.equals(origen)) {
					lblErrorDestino.setText("Error elija otra Sucursal!!");
					lblErrorOrigen.setText("Error elija otra Sucursal!!");
				} else if (destino.equals(venta.getOrigen())) {
					lblErrorDestino.setText("Error elija otra Sucursal!!");					
				} else if (!destino.equals(origen) && !destino.equals(venta.getOrigen()) && !origen.equals(venta.getDestino())) {
					lblErrorDestino.setText("");
					lblErrorOrigen.setText("");
				} else if (destino.equals(venta.getOrigen()) && !destino.equals(origen)) {
					lblErrorOrigen.setText("");					
					lblErrorDestino.setText("Error elija otra Sucursal!!");					
				}
			} 
		});
		
		JButton btnRegresar = new JButton("Volver");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinkedList<Exportacion> listaVentas = new LinkedList<Exportacion>();
				tabbedPane.setEnabledAt(0, true);
				cliente = null;
				venta = null;
				librosElegidos(listaVentas);
				lblSelecVenta.setText("");
				lblCliente.setText("");
				tabbedPane.setSelectedIndex(0);
				tabbedPane.setEnabledAt(1, false);
			}
		});
		btnRegresar.setBounds(10, 295, 85, 21);
		panel_2.add(btnRegresar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pago = (String) selectorPago.getSelectedItem();
				String moneda = (String) selectorMoneda.getSelectedItem();
				String origen = (String) selectorOrigen.getSelectedItem();
				String destino = (String) selectorDestino.getSelectedItem();
				
				if (!pago.equals("Seleccionar") || !moneda.equals("Seleccionar") || !origen.equals("Seleccionar") || !destino.equals("Seleccionar")) {
					
					if (!pago.equals("Seleccionar")) {
						venta.setMetodoPago(pago);						
					}
					if (!moneda.equals("Seleccionar")) {
						venta.setMoneda(moneda);						
					}
					
					if (origen.equals(destino) && !origen.equals("Seleccionar") && !destino.equals("Seleccionar")) {
						lblErrorOrigen.setText("Error elija otra Sucursal!!");
						lblErrorDestino.setText("Error elija otra Sucursal!!");
					} else if (origen.equals(venta.getDestino())) {
						lblErrorOrigen.setText("Error elija otra Sucursal!!");									
					} else if (destino.equals(venta.getOrigen())) {
						lblErrorDestino.setText("Error elija otra Sucursal!!");								
					} else {
						if (!origen.equals("Seleccionar")) {
							venta.setOrigen(origen);							
						}
						if (!destino.equals("Seleccionar")) {
							venta.setDestino(destino);							
						}
						
						DetallesModificacion detalles = new DetallesModificacion(user,venta,dato);
						detalles.setVisible(true);
						dispose();
					}
				} else {
					lblMensaje.setText("No realizó ningun cambio!!");
				}
				
			}
		});
		btnActualizar.setBounds(511, 295, 95, 21);
		panel_2.add(btnActualizar);
		
		if (v != null) {
			tabbedPane.setSelectedIndex(1);
			tabbedPane.setEnabledAt(1, true);
			tabbedPane.setEnabledAt(0, false);
			venta = v;
			dato = datos;
			lblDatosActuales.setText(dato);
			selectorPago.setSelectedItem(v.getMetodoPago());
			selectorMoneda.setSelectedItem(v.getMoneda());
			selectorOrigen.setSelectedItem(v.getOrigen());
			selectorDestino.setSelectedItem(v.getDestino());
		}
	}
	
	// cargar las ventas del cliente
	public void librosElegidos(LinkedList<Exportacion> listaVentas) {
		
		model.setRowCount(0);
		for (Exportacion L : listaVentas) {
			model.addRow(new Object[] {
					L.getIdVenta(), //id_venta
					L.getFechaVenta(), //fecha_venta
					L.getMetodoPago(), //metodo_pago
					L.getMoneda(),	  //tipo_modeda
					L.getOrigen(), //origen
					L.getDestino() //destino
			});
		}
	}
	
	// cargar datos de la venta a modificar
	public String datosVenta(Exportacion venta) {
		String datos =
			    "<html>"
			        + "<table cellspacing='20'>" // separa un poco las columnas
			        + "<tr>"
			            + "<td style='padding-right: 40px;'><b>Método de Pago:</b> " + venta.getMetodoPago() + ".</td>"
			            + "<td><b>Origen:</b> " + venta.getOrigen() + ".</td>"
			        + "</tr>"
			        + "<tr>"
			            + "<td style='padding-right: 40px;'><b>Tipo de Moneda:</b> " + venta.getMoneda() + ".</td>"
			            + "<td><b>Destino:</b> " + venta.getDestino() + ".</td>"
			        + "</tr>"
			        + "</table>"
			    + "</html>";
		return datos;
	}

}