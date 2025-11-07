package UI.PanelExportacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.CarritoDetalle;
import BLL.Cliente;
import BLL.Libro;
import BLL.Usuario;
import DLL.LibroDTO;
import Enums.MetodoPago;
import Enums.Sucursales;
import Enums.TipoMoneda;
import Repository.Validaciones;
import UI.Main;

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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.EventQueue;

public class ElegirLibros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel model;
    private JTextField textCantidad;

    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				ElegirLibros frame = new ElegirLibros(null,new Cliente(1,1,"","",""));
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
    
	public ElegirLibros(Usuario user,Cliente cliente) {
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
		
		model = new DefaultTableModel(new String[]{"ID", "TITULO", "PRECIO", "CANTIDAD"}, 0);
		
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
		lblCantidad.setBounds(213, 200, 62, 13);
		panel.add(lblCantidad);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(213, 223, 54, 19);
		panel.add(textCantidad);
		textCantidad.setColumns(10);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBackground(new Color(255, 255, 255));
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblError.setBounds(10, 249, 360, 13);
		panel.add(lblError);
		
		JLabel lblErrorDestino = new JLabel("");
		lblErrorDestino.setBackground(Color.RED);
		lblErrorDestino.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblErrorDestino.setBounds(499, 361, 146, 14);
		panel.add(lblErrorDestino);
		
		JButton btnAceptar = new JButton("Agregar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ejecutamos la funcion para cargar el libro seleccionado (tiene validaciones)
				cargarLibro(selector,lblError,librosCarrito,listaLibros);
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptar.setBounds(285, 222, 85, 21);
		panel.add(btnAceptar);
		
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 37, 635, 141);
		panel.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Libros en el Carrito:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 14, 129, 13);
		panel.add(lblNewLabel);
		
		JButton btnEliminar = new JButton("Eliminar Libro");
		btnEliminar.setBounds(516, 222, 129, 21);
		panel.add(btnEliminar);
		
		JButton btnCancelarVenta = new JButton("Cancelar Venta");
		btnCancelarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarExport gestionExport = new PanelGestionarExport(user);
				gestionExport.setVisible(true);
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
		lblMetodoPago.setBounds(10, 312, 118, 21);
		panel.add(lblMetodoPago);
		
		JComboBox selectorPago = new JComboBox();
		selectorPago.setBounds(10, 335, 103, 22);
		selectorPago.addItem("Seleccionar");
		for (MetodoPago pago : MetodoPago.values()) {
			selectorPago.addItem(pago.name());
		}
		panel.add(selectorPago);
		
		JLabel lblTipoMoneda = new JLabel("Tipo de Moneda:");
		lblTipoMoneda.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipoMoneda.setBounds(170, 312, 118, 21);
		panel.add(lblTipoMoneda);
		
		JComboBox selectorMoneda = new JComboBox();
		selectorMoneda.setBounds(170, 335, 103, 22);
		selectorMoneda.addItem("Seleccionar");
		for (TipoMoneda tipo : TipoMoneda.values()) {
			selectorMoneda.addItem(tipo.name());
		}
		panel.add(selectorMoneda);
		
		JComboBox selectorOrigen = new JComboBox();
		selectorOrigen.setBounds(334, 335, 103, 22);
		selectorOrigen.addItem("Seleccionar");
		for (Sucursales origen : Sucursales.values()) {
			selectorOrigen.addItem(origen.name());
		}
		panel.add(selectorOrigen);
		
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOrigen.setBounds(334, 312, 62, 21);
		panel.add(lblOrigen);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDestino.setBounds(499, 312, 62, 21);
		panel.add(lblDestino);
		
		JComboBox selectorDestino = new JComboBox();
		selectorDestino.setBounds(499, 335, 103, 22);
		selectorDestino.addItem("Seleccionar");
		for (Sucursales destino : Sucursales.values()) {
			selectorDestino.addItem(destino.name());
		}
		panel.add(selectorDestino);
		
		JButton btnRealizarVenta = new JButton("Realizar Venta");
		btnRealizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRealizarVenta.setBounds(527, 515, 118, 23);
		panel.add(btnRealizarVenta);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
	}
	// funciones
	public void librosElegidos(LinkedList<CarritoDetalle> lista) {
		LinkedList<CarritoDetalle> libros = lista.stream()
				.sorted(Comparator.comparingInt(CarritoDetalle::getCantidad).reversed())
				.collect(Collectors.toCollection(LinkedList::new));
		
		model.setRowCount(0);
		for (CarritoDetalle L : libros) {
			model.addRow(new Object[] {
					L.getFkLibro().getId_libro(),
					L.getFkLibro().getTitulo(),
					L.getFkLibro().getPrecio(),
					L.getCantidad()
			});
		}
	}
	
	public void cargarLibro(JComboBox selector, JLabel lblError, LinkedList<CarritoDetalle> librosCarrito, LinkedList<Libro> listaLibros) {
		String nombreLibro = (String)selector.getSelectedItem();
		boolean texto = Validaciones.validarVacioJframe(nombreLibro);
		boolean texto2 = Validaciones.validarVacioJframe(textCantidad.getText());
		boolean cantidad;
		
		if (texto != true && texto2 != true) {
			cantidad = Validaciones.validarIntJframe(textCantidad.getText());
			if (cantidad != true) {
				if (nombreLibro.equalsIgnoreCase("selección")) {
					lblError.setText("Elijá un Libro!!");
				} else {
					boolean encontrado = true, sinStock = false;
					
					lblError.setText("");
					
					if (librosCarrito.isEmpty()) {
						for (Libro libro : listaLibros) {
							if (nombreLibro.equalsIgnoreCase(libro.getTitulo()) && libro.getStock() >= Integer.parseInt(textCantidad.getText())) {
								librosCarrito.add(new CarritoDetalle(Integer.parseInt(textCantidad.getText()),libro));
								libro.setStock(libro.getStock()-Integer.parseInt(textCantidad.getText()));
								sinStock = false;									
								break;
							} else {
								sinStock = true;
							}
						}
						
						if (sinStock) {
							lblError.setText("No tenemos Stock del libro: \"" + nombreLibro + "\"");
						}
						textCantidad.setText("");
						selector.setSelectedItem("Selección");
					} else {
						// descontamos de la lista principal de libros la cantidad de libros que estan comprando
						for (Libro libro : listaLibros) {
							if (nombreLibro.equalsIgnoreCase(libro.getTitulo()) && libro.getStock() >= Integer.parseInt(textCantidad.getText())) {
								libro.setStock(libro.getStock()-Integer.parseInt(textCantidad.getText()));
								sinStock = false;
								break;									
							} else {
								sinStock = true;
							}
						}
						
						// buscamos en el carrito que ya tiene los otro libros que selecciono y le agregamos el stock
						if (sinStock != true) {
							for (CarritoDetalle carrito : librosCarrito) {
								if (carrito.getFkLibro().getTitulo().equalsIgnoreCase(nombreLibro)) {
									carrito.setCantidad(carrito.getCantidad() + Integer.parseInt(textCantidad.getText()));
									encontrado = false;
									break;
								} 
							}
							
							// si no encuentra un libro previamente seleccionado es un libro nuevo y lo carga al carrito
							if (encontrado) {
								for (Libro libro : listaLibros) {
									if (nombreLibro.equalsIgnoreCase(libro.getTitulo())) {
										librosCarrito.add(new CarritoDetalle(Integer.parseInt(textCantidad.getText()),libro));
										libro.setStock(libro.getStock()-Integer.parseInt(textCantidad.getText()));
										break;
									}
								}
							}		
							textCantidad.setText("");
							selector.setSelectedItem("Selección");
						} else {
							lblError.setText("No tenemos Stock del libro: \"" + nombreLibro + "\"");
//							lblError.setText("No tenemos Stock sufiente de " + nombreLibro);
						}
					}
					librosElegidos(librosCarrito);
				}
			} else {
				lblError.setText("Ingrese una Cantidad Valida!!");
			}
		} else {
			lblError.setText("No puede dejar los campos Vacio!!");			
		}
	}
}
