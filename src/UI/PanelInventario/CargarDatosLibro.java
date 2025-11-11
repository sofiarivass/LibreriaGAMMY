package UI.PanelInventario;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import BLL.Libro;
import BLL.Usuario;
import DLL.LibroDTO;
import Enums.*;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

	/**
	 * Create the frame.
	 */
	public CargarDatosLibro(Usuario user, String funcion, Libro libro) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCargarDatos = new JLabel();
		lblCargarDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargarDatos.setBounds(118, 28, 355, 25);
		lblCargarDatos.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblCargarDatos);
		if (funcion.equals("crear")) {
			lblCargarDatos.setText("Cargar Nuevo Libro al Sistema");
		} else {
			lblCargarDatos.setText("Editar Datos de un Libro");
		}

		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setBounds(39, 92, 46, 14);
		contentPane.add(lblTitulo);

		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(39, 151, 46, 14);
		contentPane.add(lblEditorial);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(39, 207, 46, 14);
		contentPane.add(lblAutor);

		JLabel lblFecha = new JLabel("Fecha de publicación (opcional)");
		lblFecha.setBounds(39, 262, 191, 14);
		contentPane.add(lblFecha);

		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(309, 92, 41, 14);
		contentPane.add(lblGenero);

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setBounds(310, 151, 41, 14);
		contentPane.add(lblIdioma);

		JLabel lblPublico = new JLabel("Público objetivo");
		lblPublico.setBounds(310, 207, 144, 14);
		contentPane.add(lblPublico);

		JLabel lblPaginas = new JLabel("Cantidad de páginas");
		lblPaginas.setBounds(39, 319, 172, 14);
		contentPane.add(lblPaginas);

		JLabel lblTapa = new JLabel("Tipo de tapa");
		lblTapa.setBounds(310, 262, 87, 14);
		contentPane.add(lblTapa);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(143, 456, 46, 14);
		contentPane.add(lblPrecio);

		JLabel lblPrecio_1 = new JLabel("$");
		lblPrecio_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecio_1.setBounds(129, 473, 14, 29);
		contentPane.add(lblPrecio_1);

		JLabel lblStock_1 = new JLabel("unidades");
		lblStock_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStock_1.setBounds(385, 473, 82, 29);
		contentPane.add(lblStock_1);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(289, 456, 46, 14);
		contentPane.add(lblStock);

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(new Color(159, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblError.setBounds(34, 545, 499, 43);
		contentPane.add(lblError);

		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(39, 107, 217, 30);
		contentPane.add(txtTitulo);

		txtEditorial = new JTextField();
		txtEditorial.setColumns(10);
		txtEditorial.setBounds(39, 166, 217, 30);
		contentPane.add(txtEditorial);
		
		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		txtAutor.setBounds(39, 222, 217, 30);
		contentPane.add(txtAutor);

		JDateChooser txtFecha = new JDateChooser();
		txtFecha.setDateFormatString("dd/MM/yyyy");
		txtFecha.setBounds(39, 279, 217, 30);

		txtPaginas = new JTextField();
		txtPaginas.setColumns(10);
		txtPaginas.setBounds(39, 335, 217, 30);
		contentPane.add(txtPaginas);

		JComboBox comboBoxGenero = new JComboBox();
		comboBoxGenero.setBounds(308, 107, 217, 30);
		for (Generos genero : Generos.values()) {
			comboBoxGenero.addItem(genero.name());
		}
		contentPane.add(comboBoxGenero);

		JComboBox comboBoxIdioma = new JComboBox();
		comboBoxIdioma.setBounds(308, 166, 217, 30);
		for (Idiomas idioma : Idiomas.values()) {
			comboBoxIdioma.addItem(idioma.name());
		}
		contentPane.add(comboBoxIdioma);

		JComboBox comboBoxPublico = new JComboBox();
		comboBoxPublico.setBounds(308, 222, 217, 30);
		for (Publico publico : Publico.values()) {
			comboBoxPublico.addItem(publico.name());
		}
		contentPane.add(comboBoxPublico);

		JComboBox comboBoxTapa = new JComboBox();
		comboBoxTapa.setBounds(308, 279, 217, 30);
		for (Tapas tapa : Tapas.values()) {
			comboBoxTapa.addItem(tapa.name());
		}
		contentPane.add(comboBoxTapa);

		JCheckBox chckbxFirmado = new JCheckBox("¿Libro firmado?");
		chckbxFirmado.setBounds(311, 334, 162, 20);
		contentPane.add(chckbxFirmado);

		JCheckBox chckbxSaga = new JCheckBox("¿Pertenece a una saga?");
		chckbxSaga.setBounds(311, 360, 200, 20);
		contentPane.add(chckbxSaga);

		JCheckBox chckbxEdicion = new JCheckBox("¿Edición especial?");
		chckbxEdicion.setBounds(311, 385, 162, 20);
		contentPane.add(chckbxEdicion);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(143, 472, 87, 30);
		contentPane.add(txtPrecio);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(289, 472, 87, 30);
		contentPane.add(txtStock);

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
						lblError.setText("Solo se permiten archivos JPG, JPEG o PNG");
						return;
					}

					try {
						portada = Files.readAllBytes(selectedFile.toPath());
						lblError.setText("");
					} catch (IOException ex) {
						ex.printStackTrace();
						lblError.setText("Error al leer la imagen");
					}
				}
			}
		});
		btnCargarPortada.setForeground(new Color(0, 0, 0));
		btnCargarPortada.setBackground(new Color(192, 192, 192));
		btnCargarPortada.setBounds(49, 380, 197, 25);
		contentPane.add(btnCargarPortada);

		Calendar hoy = Calendar.getInstance();
		txtFecha.setMaxSelectableDate(hoy.getTime());
		contentPane.add(txtFecha);

		if (funcion.equals("editar")) {
			txtTitulo.setText(libro.getTitulo());
			txtAutor.setText(libro.getAutor());
			txtEditorial.setText(libro.getEditorial());
			txtFecha.setDate(libro.getFechaPublicacion());
//			txtPaginas.setText(libro.getNumPaginas());
			// portada
			comboBoxGenero.setSelectedItem(libro.getGenero());
			comboBoxIdioma.setSelectedItem(libro.getIdioma());
			comboBoxPublico.setSelectedItem(libro.getPublicoObjetivo());
			comboBoxTapa.setSelectedItem(libro.getTapa());
			chckbxFirmado.setSelected(libro.getFirmado());
			chckbxSaga.setSelected(libro.getSaga());
			chckbxEdicion.setSelected(libro.getEdicionEspecial());
//			txtPrecio.setText(libro.getPrecio());
//			txtStock.setText(libro.getStock());
		}

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
							lblError.setText("La cantidad de páginas debe ser un número entero y positivo");
						} else if (numPaginasV.equals("tamaño")) {
							lblError.setText("La cantidad de páginas debe ser un número más pequeño");
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
									lblError.setText("El stock debe ser un número entero y positivo");
								} else if (stockV.equals("tamaño")) {
									lblError.setText("El stock debe ser un número más pequeño");
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

									Libro libroNuevo = new Libro(titulo, autor, editorial, fechaPublicacion, genero, idioma,
											publicoObjetivo, numPaginas, firmado, edicionEspecial, tapa, saga, precio,
											stock, true, portada);
									
									if (funcion.equals("crear")) {
										boolean coincidencia = LibroDTO.agregarLibro(libroNuevo);
										if (coincidencia == false) {
											lblError.setText("Ese libro ya está cargado en el sistema");
										} else {
											System.out.println("todo ok");
											InfoLibro frame = new InfoLibro(user, libroNuevo, "crear");
											frame.setVisible(true);
											dispose();
										}
									} else {
										libroNuevo.setId_libro(libro.getId_libro());
										boolean coincidencia = LibroDTO.editarLibro(libroNuevo);
										if (coincidencia == false) {
											lblError.setText("Ese libro ya está cargado en el sistema");
										} else {
											System.out.println("todo ok");
											InfoLibro frame = new InfoLibro(user, libroNuevo, "");
											frame.setVisible(true);
											dispose();
										}
									}

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
				GestionarLibros frame = new GestionarLibros(user);
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
