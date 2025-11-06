package UI.PanelInventario;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import BLL.Libro;
import BLL.Usuario;
import Enums.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import java.awt.Color;

public class CargarDatosLibro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo, txtAutor, txtEditorial, txtPaginas, txtPrecio, txtStock;
	private byte[] portada;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CargarDatosLibro frame = new CargarDatosLibro();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public CargarDatosLibro(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCargarNuevoLibro = new JLabel("Cargar Nuevo Libro al Sistema");
		lblCargarNuevoLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargarNuevoLibro.setBounds(118, 28, 355, 25);
		lblCargarNuevoLibro.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblCargarNuevoLibro);

		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(39, 108, 217, 30);
		contentPane.add(txtTitulo);

		JLabel lblTtulo = new JLabel("Título");
		lblTtulo.setBounds(39, 92, 46, 14);
		contentPane.add(lblTtulo);

		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(39, 150, 46, 14);
		contentPane.add(lblAutor);

		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		txtAutor.setBounds(39, 166, 217, 30);
		contentPane.add(txtAutor);

		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(39, 206, 46, 14);
		contentPane.add(lblEditorial);

		txtEditorial = new JTextField();
		txtEditorial.setColumns(10);
		txtEditorial.setBounds(39, 222, 217, 30);
		contentPane.add(txtEditorial);

		JLabel lblFecha = new JLabel("Fecha de publicación (opcional)");
		lblFecha.setBounds(39, 262, 191, 14);
		contentPane.add(lblFecha);

		JDateChooser txtFecha = new JDateChooser();
		txtFecha.setDateFormatString("dd/MM/yyyy");
		txtFecha.setBounds(39, 279, 217, 30);

		Calendar hoy = Calendar.getInstance();
		txtFecha.setMaxSelectableDate(hoy.getTime());
//		txtFecha.getCalendarButton().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
		contentPane.add(txtFecha);

		JComboBox comboBoxGenero = new JComboBox();
		comboBoxGenero.setBounds(308, 107, 217, 30);
		for (Generos genero : Generos.values()) {
			comboBoxGenero.addItem(genero.name());
		}
		contentPane.add(comboBoxGenero);

		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(309, 92, 41, 14);
		contentPane.add(lblGenero);

		JComboBox comboBoxIdioma = new JComboBox();
		comboBoxIdioma.setBounds(308, 166, 217, 30);
		for (Idiomas idioma : Idiomas.values()) {
			comboBoxIdioma.addItem(idioma.name());
		}
		contentPane.add(comboBoxIdioma);

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setBounds(310, 151, 41, 14);
		contentPane.add(lblIdioma);

		JLabel lblPublico = new JLabel("Público objetivo");
		lblPublico.setBounds(310, 207, 144, 14);
		contentPane.add(lblPublico);

		JComboBox comboBoxPublico = new JComboBox();
		comboBoxPublico.setBounds(308, 222, 217, 30);
		for (Publico publico : Publico.values()) {
			comboBoxPublico.addItem(publico.name());
		}
		contentPane.add(comboBoxPublico);

		JLabel lblPaginas = new JLabel("Cantidad de páginas");
		lblPaginas.setBounds(39, 319, 172, 14);
		contentPane.add(lblPaginas);

		txtPaginas = new JTextField();
		txtPaginas.setColumns(10);
		txtPaginas.setBounds(39, 335, 217, 30);
		contentPane.add(txtPaginas);

		JCheckBox chckbxFirmado = new JCheckBox("¿Libro firmado?");
		chckbxFirmado.setBounds(311, 334, 162, 20);
		contentPane.add(chckbxFirmado);

		JCheckBox chckbxEdicion = new JCheckBox("¿Edición especial?");
		chckbxEdicion.setBounds(311, 385, 162, 20);
		contentPane.add(chckbxEdicion);

		JLabel lblTapa = new JLabel("Tipo de tapa");
		lblTapa.setBounds(310, 264, 87, 14);
		contentPane.add(lblTapa);

		JComboBox comboBoxTapa = new JComboBox();
		comboBoxTapa.setBounds(308, 279, 217, 30);
		for (Tapas tapa : Tapas.values()) {
			comboBoxTapa.addItem(tapa.name());
		}
		contentPane.add(comboBoxTapa);

		JCheckBox chckbxSaga = new JCheckBox("¿Pertenece a una saga?");
		chckbxSaga.setBounds(311, 360, 200, 20);
		contentPane.add(chckbxSaga);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(143, 456, 46, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(143, 472, 87, 30);
		contentPane.add(txtPrecio);

		JLabel lblPrecio_1 = new JLabel("$");
		lblPrecio_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecio_1.setBounds(129, 473, 14, 29);
		contentPane.add(lblPrecio_1);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(289, 472, 87, 30);
		contentPane.add(txtStock);

		JLabel lblStock_1 = new JLabel("unidades");
		lblStock_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStock_1.setBounds(385, 473, 82, 29);
		contentPane.add(lblStock_1);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(289, 456, 46, 14);
		contentPane.add(lblStock);

		JButton btnCargarPortada = new JButton("Cargar portada (opcional)");
		btnCargarPortada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();

				// Filtro: solo imágenes
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (JPG, PNG, JPEG)", "jpg", "jpeg",
						"png");
				chooser.setFileFilter(filter);

				int option = chooser.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();

					// Validación manual extra (por seguridad)
					String nombreArchivo = selectedFile.getName().toLowerCase();
					if (!(nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".jpeg")
							|| nombreArchivo.endsWith(".png"))) {
						JOptionPane.showMessageDialog(null, "Solo se permiten archivos JPG, JPEG o PNG");
						return;
					}

					try {
						portada = Files.readAllBytes(selectedFile.toPath());
						JOptionPane.showMessageDialog(null, "Imagen cargada correctamente");
					} catch (IOException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al leer la imagen");
					}
				}
			}
		});
		btnCargarPortada.setForeground(new Color(0, 0, 0));
		btnCargarPortada.setBackground(new Color(192, 192, 192));
		btnCargarPortada.setBounds(58, 380, 178, 25);
		contentPane.add(btnCargarPortada);

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(new Color(159, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblError.setBounds(34, 545, 499, 32);
		contentPane.add(lblError);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblError.setText("");

				boolean tituloV, editorialV;
				String autorV, numPaginasV, stockV, precioV;
				String titulo, editorial, autor, genero, idioma, publicoObjetivo, tapa;
				int numPaginas, stock;
				double precio;
				boolean firmado, edicionEspecial, saga, estado;

				Libro libro;

				tituloV = Repository.Validaciones.validarVacioJframe(txtTitulo.getText());
				editorialV = Repository.Validaciones.validarVacioJframe(txtEditorial.getText());
				if (tituloV == true || editorialV == true) {
					lblError.setText("Por favor complete los campos obligatorios");
				} else {
					titulo = txtTitulo.getText();
					editorial = txtEditorial.getText();
					autorV = Repository.Validaciones.validarStringJframe(txtAutor.getText());
					if (autorV.equals("vacio")) {
						lblError.setText("Por favor complete los campos obligatorios");
					} else if (autorV.equals("numero")) {
						lblError.setText("El nombre del autor no puede contener números");
					} else {
						autor = txtAutor.getText();
						lblError.setText("");
						numPaginasV = Repository.Validaciones.validarIntJframe(txtPaginas.getText());
						if (numPaginasV.equals("vacio")) {
							lblError.setText("Por favor complete los campos obligatorios");
						} else if (numPaginasV.equals("letra")) {
							lblError.setText("La cantidad de páginas debe ser un número entero");
						} else if (numPaginasV.equals("tamaño")) {
							lblError.setText("La cantidad de páginas debe ser un número más pequeño");
						} else if (numPaginasV.equals("negativo")) {
							lblError.setText("La cantidad de páginas debe ser un número positivo");
						} else {
							numPaginas = Integer.parseInt(txtPaginas.getText());
							lblError.setText("");
							precioV = Repository.Validaciones.validarDoubleJframe(txtPrecio.getText());
							if (precioV.equals("vacio")) {
								lblError.setText("Por favor complete los campos obligatorios");
							} else if (precioV.equals("letra")) {
								lblError.setText("El precio debe ser un número");
							} else if (precioV.equals("tamaño")) {
								lblError.setText("El precio debe ser un número más pequeño");
							} else if (precioV.equals("negativo")) {
								lblError.setText("El precio debe ser un número positivo");
							} else {
								precio = Double.parseDouble(txtPrecio.getText());
								lblError.setText("");
								stockV = Repository.Validaciones.validarIntJframe(txtStock.getText());
								if (stockV.equals("vacio")) {
									lblError.setText("Por favor complete los campos obligatorios");
								} else if (stockV.equals("letra")) {
									lblError.setText("El stock debe ser un número entero");
								} else if (stockV.equals("tamaño")) {
									lblError.setText("El stock debe ser un número más pequeño");
								} else if (stockV.equals("negativo")) {
									lblError.setText("El stock debe ser un número positivo");
								} else {
									stock = Integer.parseInt(txtStock.getText());
									lblError.setText("");
									genero = comboBoxGenero.getSelectedItem().toString();
									idioma = comboBoxIdioma.getSelectedItem().toString();
									publicoObjetivo = comboBoxPublico.getSelectedItem().toString();
									tapa = comboBoxTapa.getSelectedItem().toString();
									firmado = chckbxFirmado.isSelected();
									edicionEspecial = chckbxEdicion.isSelected();
									saga = chckbxSaga.isSelected();

									java.util.Date selectedDate = txtFecha.getDate();
									Date fechaPublicacion = null;

									if (selectedDate != null) {
										fechaPublicacion = new Date(selectedDate.getTime());
									}

									libro = new Libro(titulo, autor, editorial, fechaPublicacion, genero, idioma,
											publicoObjetivo, numPaginas, firmado, edicionEspecial, tapa, saga, precio,
											stock, true, portada);

									Libro.nuevoLibro(libro);

									JOptionPane.showMessageDialog(null, "todo bien");
									PanelGestionarInv frame = new PanelGestionarInv(user);
									frame.setVisible(true);
									dispose();
								}
							}
						}
					}
				}
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(308, 617, 109, 27);
		contentPane.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarInv frame = new PanelGestionarInv(user);
				frame.setVisible(true);
				dispose();
			}
		});

		btnCancelar.setForeground(new Color(153, 17, 20));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(444, 617, 109, 27);
		contentPane.add(btnCancelar);

	}
}
