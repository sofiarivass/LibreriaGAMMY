package UI.PanelExportacion;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import BLL.Carrito;
import BLL.Cliente;
import BLL.Exportacion;
import BLL.TipoEmpleado;
import BLL.TipoVenta;
import BLL.Usuario;
import DLL.ClienteDTO;
import DLL.VentasExportDTO;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class AnularExportacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Cliente cliente;
	private Exportacion venta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnularExportacion frame = new AnularExportacion(new Usuario("","","",true,(new TipoEmpleado(2,"Vendedor Internacional"))));
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
	public AnularExportacion(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Anular Ventas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(314, 10, 158, 48);
		contentPane.add(lblTitulo);
		
		model = new DefaultTableModel(new String[]{"ID-VENTA", "FECHA-VENTA", "METODO-PAGO", "TIPO-MONEDA", "ORIGEN", "DESTINO", "ESTADO"}, 0);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 63, 766, 382);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 746, 137);
		panel.add(scrollPane);
		
		// mensajes de Errores
		JLabel lblCliente = new JLabel("");
		lblCliente.setBounds(10, 157, 746, 21);
		panel.add(lblCliente);
		
		JLabel lblVenta = new JLabel("");
		lblVenta.setBounds(10, 183, 746, 21);
		panel.add(lblVenta);
		
		JLabel lblErrorSeleccion = new JLabel("");
		lblErrorSeleccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorSeleccion.setForeground(new Color(255, 0, 0));
		lblErrorSeleccion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorSeleccion.setBounds(559, 214, 197, 24);
		panel.add(lblErrorSeleccion);
		
		JLabel lblExito = new JLabel("");
		lblExito.setHorizontalAlignment(SwingConstants.CENTER);
		lblExito.setForeground(new Color(33, 139, 60));
		lblExito.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblExito.setBounds(187, 311, 392, 33);
		panel.add(lblExito);
		
		JLabel lblSelecCliente = new JLabel("Seleccionar Cliente:");
		lblSelecCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelecCliente.setBounds(10, 209, 161, 33);
		panel.add(lblSelecCliente);
		
		JComboBox selectorCliente = new JComboBox();
		selectorCliente.setBounds(10, 244, 141, 24);
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
				lblExito.setText("");
				lblErrorSeleccion.setText("");
				lblVenta.setText("");
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
							lblCliente.setText("");
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
					lblCliente.setForeground(Color.red);
					lblCliente.setText("elija un Cliente!!");
				}
			}
		});
		btnSelecCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelecCliente.setBounds(164, 243, 85, 23);
		panel.add(btnSelecCliente);
		
		JButton btnAnular = new JButton("Anular / Activar Venta");
		btnAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinkedList<Exportacion> listaVentas = new LinkedList<Exportacion>();
				TipoVenta fkTipoVenta = null;
				Exportacion ventaExport = null;
				
				if (venta != null) {
					if (venta.getEstado().equalsIgnoreCase("anulada")) {
						venta.setEstado("activa");	
					} else {
						venta.setEstado("anulada");
					}
					
					if (VentasExportDTO.anularVentaExportJframe(venta)) {
						lblExito.setText("La Venta se actualizo con Exito!!");	
						venta = null;
					} else {
						lblExito.setText("Error al Ejecutar la consulta!!");
						lblVenta.setText("");
						venta = null;
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
					}
					
					librosElegidos(listaVentas);
				} else {
					lblExito.setText("");
					lblErrorSeleccion.setText("Seleccione una Venta!!");
				}
			}
		});
		btnAnular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAnular.setBounds(584, 238, 172, 33);
		panel.add(btnAnular);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarExport gestionExport = new PanelGestionarExport(user);
				gestionExport.setVisible(true);
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(10, 348, 98, 24);
		panel.add(btnVolver);
		
		// evento al hacer click en algun libro cargado en la tabla
		table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                	
                	lblCliente.setForeground(Color.black);
                	lblCliente.setText(cliente.toString());
                	lblExito.setText("");
                	lblErrorSeleccion.setText("");
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
							lblVenta.setText(venta.toString());
							break;
						}
					}
                }
            }
        });
		
	}
	
	// funciones
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
					L.getDestino(), //destino
					L.getEstado() //estado
			});
		}
	}
}
