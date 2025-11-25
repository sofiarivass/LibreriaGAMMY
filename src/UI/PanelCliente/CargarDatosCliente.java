package UI.PanelCliente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Cliente;
import BLL.Usuario;
import DLL.ClienteDTO;

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
		setBounds(100, 100, 582, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCargarDatos = new JLabel("Editar Datos de un Cliente");
		lblCargarDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargarDatos.setBounds(151, 28, 263, 25);
		lblCargarDatos.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblCargarDatos);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(173, 92, 46, 14);
		contentPane.add(lblNombre);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(173, 151, 46, 14);
		contentPane.add(lblTelefono);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(173, 207, 46, 14);
		contentPane.add(lblMail);

		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(173, 262, 46, 14);
		contentPane.add(lblDNI);

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(new Color(159, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblError.setBounds(10, 332, 546, 43);
		contentPane.add(lblError);

		txtNombre = new JTextField(cliente.getNombre());
		txtNombre.setColumns(10);
		txtNombre.setBounds(174, 107, 217, 30);
		contentPane.add(txtNombre);

		txtTelefono = new JTextField(cliente.getTelefono());
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(174, 166, 217, 30);
		contentPane.add(txtTelefono);

		txtMail = new JTextField(cliente.getMail());
		txtMail.setColumns(10);
		txtMail.setBounds(174, 222, 217, 30);
		contentPane.add(txtMail);

		txtDNI = new JTextField("" + cliente.getDni());
		txtDNI.setColumns(10);
		txtDNI.setBounds(174, 277, 217, 30);
		contentPane.add(txtDNI);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblError.setText("");

				boolean nombreV, dniV, mailV;
				String nombre = "", telefono = "", mail = "";
				int dni = 0;
				boolean estado;

				nombreV = Repository.Validaciones.validarVacioJframe(txtNombre.getText());
				dniV = Repository.Validaciones.validarDniJframe(txtDNI.getText());
				mailV = Repository.Validaciones.validarMail(txtMail.getText());
				telefono = Repository.Validaciones.validarIntJframe(txtTelefono.getText());
				if (telefono.equals("vacio")) {
					lblError.setText("Por favor complete los campos obligatorios");
				} else if (telefono.equals("letra")) {
					lblError.setText("El telefono debe ser un número entero y positivo");
				} else if (telefono.equals("tamaño")) {
					lblError.setText("El telefono debe ser un número más pequeño");
				} else if (nombreV == true) {
					lblError.setText("Por favor complete los campos obligatorios");
				} else if (mailV == false) {
					lblError.setText("Ingrese un Mail Válido");
				} else if (dniV == true) {
					lblError.setText("Ingrese un DNI Válido");
				} else {
					nombre = txtNombre.getText();
					telefono = txtTelefono.getText();
					mail = txtMail.getText();
					dni = Integer.parseInt(txtDNI.getText());

					Cliente clienteNuevo = new Cliente(dni, nombre, telefono, mail, cliente.getEstado());

					clienteNuevo.setIdCliente(cliente.getIdCliente());
					boolean coincidencia = ClienteDTO.modificarCliente(clienteNuevo);
					if (coincidencia == false) {
						lblError.setText("Ese cliente ya está cargado en el sistema");
					} else {
						System.out.println("todo ok");
						InfoCliente frame = new InfoCliente(user, clienteNuevo, "");
						frame.setVisible(true);
						dispose();
					}
				}
			}

		}

		);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(228, 393, 109, 27);
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
		btnCancelar.setBounds(228, 430, 109, 27);
		contentPane.add(btnCancelar);

	}
}
