package UI.PanelCliente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Cliente;
import BLL.Usuario;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;

public class CargarDatosCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre, txtMail, txtTelefono;
	private JTextField txtDNI;

	/**
	 * Create the frame.
	 */
	public CargarDatosCliente(Usuario user, Cliente cliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCargarDatos = new JLabel("Editar Datos de un Cliente");
		lblCargarDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargarDatos.setBounds(34, 28, 263, 25);
		lblCargarDatos.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblCargarDatos);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(57, 92, 46, 14);
		contentPane.add(lblNombre);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(57, 151, 46, 14);
		contentPane.add(lblTelefono);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(57, 207, 46, 14);
		contentPane.add(lblMail);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(57, 262, 46, 14);
		contentPane.add(lblDNI);

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(new Color(159, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblError.setBounds(10, 332, 275, 43);
		contentPane.add(lblError);

		txtNombre = new JTextField(cliente.getNombre());
		txtNombre.setColumns(10);
		txtNombre.setBounds(57, 107, 217, 30);
		contentPane.add(txtNombre);

		txtTelefono = new JTextField(cliente.getTelefono());
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(57, 166, 217, 30);
		contentPane.add(txtTelefono);

		txtMail = new JTextField(cliente.getMail());
		txtMail.setColumns(10);
		txtMail.setBounds(57, 222, 217, 30);
		contentPane.add(txtMail);
		
		txtDNI = new JTextField("" + cliente.getDni());
		txtDNI.setColumns(10);
		txtDNI.setBounds(57, 277, 217, 30);
		contentPane.add(txtDNI);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				lblError.setText("");
//
//				boolean tituloV, editorialV;
//				String autorV, numPaginasV, stockV, precioV;
//				String titulo, editorial, autor, genero, idioma, publicoObjetivo, tapa;
//				int numPaginas, stock;
//				double precio;
//				boolean firmado, edicionEspecial, saga, estado;
//
//				tituloV = Repository.Validaciones.validarVacioJframe(txtNombre.getText());
//				editorialV = Repository.Validaciones.validarVacioJframe(txtTelefono.getText());
//				if (tituloV == true || editorialV == true) {
//					lblError.setText("Por favor complete los campos obligatorios");
//				} else {
//					titulo = txtNombre.getText();
//					editorial = txtTelefono.getText();
//					autorV = Repository.Validaciones.validarStringJframe(txtMail.getText());
//					if (autorV.equals("vacio")) {
//						lblError.setText("Por favor complete los campos obligatorios");
//					} else if (autorV.equals("numero")) {
//						lblError.setText("El nombre del autor no puede contener números");
//					} else {
//						autor = txtMail.getText();
//						lblError.setText("");
//						numPaginasV = Repository.Validaciones.validarIntJframe(txtPaginas.getText());
//						if (numPaginasV.equals("vacio")) {
//							lblError.setText("Por favor complete los campos obligatorios");
//						} else if (numPaginasV.equals("letra")) {
//							lblError.setText("La cantidad de páginas debe ser un número entero y positivo");
//						} else if (numPaginasV.equals("tamaño")) {
//							lblError.setText("La cantidad de páginas debe ser un número más pequeño");
//						} else {
//							numPaginas = Integer.parseInt(txtPaginas.getText());
//							lblError.setText("");
//							precioV = Repository.Validaciones.validarDoubleJframe(txtPrecio.getText());
//							if (precioV.equals("vacio")) {
//								lblError.setText("Por favor complete los campos obligatorios");
//							} else if (precioV.equals("letra")) {
//								lblError.setText("El precio debe ser un número");
//							} else if (precioV.equals("tamaño")) {
//								lblError.setText("El precio debe ser un número más pequeño");
//							} else if (precioV.equals("negativo")) {
//								lblError.setText("El precio debe ser un número positivo");
//							} else {
//								precio = Double.parseDouble(txtPrecio.getText());
//								lblError.setText("");
//								stockV = Repository.Validaciones.validarIntJframe(txtStock.getText());
//								if (stockV.equals("vacio")) {
//									lblError.setText("Por favor complete los campos obligatorios");
//								} else if (stockV.equals("letra")) {
//									lblError.setText("El stock debe ser un número entero y positivo");
//								} else if (stockV.equals("tamaño")) {
//									lblError.setText("El stock debe ser un número más pequeño");
//								} else {
//									stock = Integer.parseInt(txtStock.getText());
//									lblError.setText("");
//									genero = comboBoxGenero.getSelectedItem().toString();
//									idioma = comboBoxIdioma.getSelectedItem().toString();
//									publicoObjetivo = comboBoxPublico.getSelectedItem().toString();
//									tapa = comboBoxTapa.getSelectedItem().toString();
//									firmado = chckbxFirmado.isSelected();
//									edicionEspecial = chckbxEdicion.isSelected();
//									saga = chckbxSaga.isSelected();
//
//									java.util.Date selectedDate = txtFecha.getDate();
//									Date fechaPublicacion = null;
//
//									if (selectedDate != null) {
//										fechaPublicacion = new Date(selectedDate.getTime());
//									}
//
//									Cliente clienteNuevo = new Cliente(titulo, autor, editorial, fechaPublicacion,
//											genero, idioma, publicoObjetivo, numPaginas, firmado, edicionEspecial, tapa,
//											saga, precio, stock, true, portada);
//
//									if (funcion.equals("crear")) {
//										boolean coincidencia = ClienteDTO.agregarCliente(clienteNuevo);
//										if (coincidencia == false) {
//											lblError.setText("Ese cliente ya está cargado en el sistema");
//										} else {
//											System.out.println("todo ok");
//											InfoCliente frame = new InfoCliente(user, clienteNuevo, "crear");
//											frame.setVisible(true);
//											dispose();
//										}
//									} else {
//										clienteNuevo.setId_cliente(cliente.getId_cliente());
//										boolean coincidencia = ClienteDTO.editarCliente(clienteNuevo);
//										if (coincidencia == false) {
//											lblError.setText("Ese cliente ya está cargado en el sistema");
//										} else {
//											System.out.println("todo ok");
//											InfoCliente frame = new InfoCliente(user, clienteNuevo, "");
//											frame.setVisible(true);
//											dispose();
//										}
//									}
//
//								}
//							}
//						}
//					}
//				}
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(111, 393, 109, 27);
		contentPane.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionarCliente frame = new GestionarCliente(user);
				frame.setVisible(true);
				dispose();
			}
		});

		btnCancelar.setForeground(new Color(153, 17, 20));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(111, 430, 109, 27);
		contentPane.add(btnCancelar);


	}
}
