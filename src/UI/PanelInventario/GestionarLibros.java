package UI.PanelInventario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Libro;
import BLL.Usuario;
import DLL.LibroDTO;
import Repository.Validaciones;
import UI.PanelVendedorInternacional;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class GestionarLibros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Libro libroSeleccionado;
	private JTextField txtBusqueda;

	/**
	 * Create the frame.
	 */

	public GestionarLibros(Usuario user) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 841, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Gestionar Inventario");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(286, 20, 254, 32);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblTitulo);

		JLabel lblSeleccionado = new JLabel("Seleccionado: ");
		lblSeleccionado.setForeground(new Color(0, 0, 128));
		lblSeleccionado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblSeleccionado.setBounds(26, 348, 774, 20);
		contentPane.add(lblSeleccionado);

		model = new DefaultTableModel(
				new String[] { "ID", "TITULO", "AUTOR", "EDITORIAL", "IDIOMA", "PRECIO", "STOCK", "ESTADO" }, 0);

		contentPane.setLayout(null);

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 122, 774, 216);
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Libros cargados en el sistema:");
		lblNewLabel.setBounds(25, 96, 233, 21);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel);

		JLabel lblError = new JLabel();
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(new Color(159, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblError.setBounds(113, 452, 601, 43);
		contentPane.add(lblError);

		JLabel lblExito = new JLabel();
		lblExito.setHorizontalAlignment(SwingConstants.CENTER);
		lblExito.setForeground(new Color(0, 128, 0));
		lblExito.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblExito.setBounds(113, 452, 601, 43);
		contentPane.add(lblExito);

		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(26, 515, 189, 21);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);

//		JButton btnBuscar = new JButton("Buscar");
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(Validaciones.getScaledImageIcon("/img/lupa.png", 15, 15));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = Validaciones.validarVacioJframe(txtBusqueda.getText());
				if (flag) {
					lblExito.setText("");
					lblError.setText("Debe escribir algo para realizar la búsqueda");
				} else {
					cargarTablaBusqueda(txtBusqueda.getText());
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnBuscar.setBounds(219, 512, 34, 27);
		contentPane.add(btnBuscar);

		JLabel lblBuscar = new JLabel("Buscar título, autor o editorial");
		lblBuscar.setForeground(new Color(159, 159, 159));
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBuscar.setBounds(26, 494, 189, 20);
		contentPane.add(lblBuscar);

		JLabel lblFiltrar = new JLabel("Filtrar por estado");
		lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFiltrar.setBounds(631, 58, 169, 21);
		contentPane.add(lblFiltrar);

		JButton btnActivo = new JButton("Activos");
		btnActivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaFiltrada(true);
			}
		});
		btnActivo.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnActivo.setBounds(631, 85, 83, 27);
		contentPane.add(btnActivo);

		JButton btnInactivo = new JButton("Inactivos");
		btnInactivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaFiltrada(false);
			}
		});
		btnInactivo.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnInactivo.setBounds(717, 85, 83, 27);
		contentPane.add(btnInactivo);

		JButton btnCargarLibro = new JButton("Cargar libro");
		btnCargarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarDatosLibro frame = new CargarDatosLibro(user, "crear", null);
				frame.setVisible(true);
				dispose();
			}
		});
		btnCargarLibro.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCargarLibro.setBounds(24, 63, 123, 27);
		contentPane.add(btnCargarLibro);

		JButton btnVerDetalle = new JButton("Ver detalle");
		btnVerDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (libroSeleccionado != null) {
					InfoLibro frame = new InfoLibro(user, libroSeleccionado, "");
					frame.setVisible(true);
					dispose();
				} else {
					lblExito.setText("");
					lblError.setText("Debe seleccionar un libro para ver su detalle");
				}
			}
		});
		btnVerDetalle.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVerDetalle.setBounds(77, 392, 172, 35);
		contentPane.add(btnVerDetalle);

		JButton btnEditarDatos = new JButton("Modificar datos");
		btnEditarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (libroSeleccionado != null) {
					System.out.println("ID LIBRO SEL: " + libroSeleccionado.getId_libro());
					CargarDatosLibro frame = new CargarDatosLibro(user, "editar", libroSeleccionado);
					frame.setVisible(true);
					dispose();
				} else {
					lblExito.setText("");
					lblError.setText("Debe seleccionar un libro para modificar sus datos");
				}

			}
		});
		btnEditarDatos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditarDatos.setBounds(326, 392, 172, 35);
		contentPane.add(btnEditarDatos);

		JButton btnDeshabilitar = new JButton("Dar de baja/alta");
		btnDeshabilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblExito.setText("");
				if (libroSeleccionado != null) {
					lblError.setText("");
					lblExito.setText(LibroDTO.eliminarLibroJFrame(libroSeleccionado));
					cargarTabla();
					libroSeleccionado = null;
				} else {
					lblError.setText("");
					lblError.setText("Debe seleccionar un libro para darlo de baja/alta");
				}

			}
		});
		btnDeshabilitar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDeshabilitar.setBounds(575, 392, 172, 35);
		contentPane.add(btnDeshabilitar);

		// Acción al seleccionar fila
		table.getSelectionModel().addListSelectionListener(e -> {
			lblError.setText("");
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {

					libroSeleccionado = LibroDTO.libroPorID((int) model.getValueAt(row, 0));

					lblSeleccionado.setText("ID: " + libroSeleccionado.getId_libro() + ", TITULO: "
							+ libroSeleccionado.getTitulo() + ", AUTOR: " + libroSeleccionado.getAutor()
							+ ", EDITORIAL: " + libroSeleccionado.getEditorial() + ", IDIOMA: "
							+ libroSeleccionado.getIdioma() + ", PRECIO: " + libroSeleccionado.getPrecio() + ", STOCK: "
							+ libroSeleccionado.getStock() + ", ESTADO: "
							+ (libroSeleccionado.getEstado() == true ? "Activo" : "Inactivo"));
				}
			}
		});

		// Cargar datos
		cargarTabla();

		JButton btnVolver = new JButton("");
		btnVolver.setIcon(Validaciones.getScaledImageIcon("/img/volver.png", 22, 22));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelVendedorInternacional frame = new PanelVendedorInternacional(user);
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(777, 512, 36, 27);
		contentPane.add(btnVolver);

		JButton btnRecargar = new JButton("");
		btnRecargar.setIcon(Validaciones.getScaledImageIcon("/img/recargar.png", 20, 20));
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla();
			}
		});
		btnRecargar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRecargar.setBounds(595, 85, 27, 27);
		contentPane.add(btnRecargar);

	}

	private void cargarTabla() {
		model.setRowCount(0);
		LinkedList<Libro> listaLibros = LibroDTO.mostrarLibros();
		for (Libro libro : listaLibros) {
			model.addRow(new Object[] { libro.getId_libro(), libro.getTitulo(), libro.getAutor(), libro.getEditorial(),
					libro.getIdioma(), "$" + libro.getPrecio(), libro.getStock(),
					(libro.getEstado() == true ? "Activo" : "Inactivo") });
		}
	}

	private void cargarTablaFiltrada(boolean filtro) {
		model.setRowCount(0);
		LinkedList<Libro> listaLibros = LibroDTO.mostrarLibros();
		for (Libro libro : listaLibros) {
			if (libro.getEstado() == filtro) {
				model.addRow(new Object[] { libro.getId_libro(), libro.getTitulo(), libro.getAutor(),
						libro.getEditorial(), libro.getIdioma(), "$" + libro.getPrecio(), libro.getStock(),
						(libro.getEstado() == true ? "Activo" : "Inactivo") });
			}
		}
	}

	private void cargarTablaBusqueda(String buscar) {
		model.setRowCount(0);
		String busqueda = buscar.toLowerCase();
		LinkedList<Libro> listaLibros = LibroDTO.mostrarLibros();
		for (Libro libro : listaLibros) {
			if (libro.getTitulo().toLowerCase().contains(busqueda) || libro.getAutor().toLowerCase().contains(busqueda)
					|| libro.getEditorial().toLowerCase().contains(busqueda)) {
				model.addRow(new Object[] { libro.getId_libro(), libro.getTitulo(), libro.getAutor(),
						libro.getEditorial(), libro.getIdioma(), "$" + libro.getPrecio(), libro.getStock(),
						(libro.getEstado() == true ? "Activo" : "Inactivo") });
			}
		}
	}

}
