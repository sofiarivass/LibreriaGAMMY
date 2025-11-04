package UI;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Cliente;

import BLL.Usuario;
import Repository.Validaciones;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usuariotxt;
	private JButton btnIngresar;
	private JPasswordField contraseniatxt;


	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		usuariotxt = new JTextField();
		usuariotxt.setBounds(108, 93, 217, 30);
		contentPane.add(usuariotxt);
		usuariotxt.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(109, 77, 46, 14);
		contentPane.add(lblUsuario);

		JLabel lblIniciarSesion = new JLabel("Iniciar sesión");
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIniciarSesion.setBounds(50, 21, 333, 31);
		contentPane.add(lblIniciarSesion);

		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(108, 143, 79, 14);
		contentPane.add(lblContrasenia);

		JLabel lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblError.setBounds(79, 209, 275, 32);
		contentPane.add(lblError);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = null;

				boolean usuario = Validaciones.validarVacioJframe(usuariotxt.getText());
				boolean contrasenia = Validaciones.validarVacioJframe(contraseniatxt.getText());

				if (usuario != false || contrasenia != false) {
					lblError.setText("Por favor complete los campos");
				} else {
					user = Usuario.login(usuariotxt.getText(), contraseniatxt.getText());
					if (user != null) {
						if (user.getEstado() == false) {
							lblError.setText("El usuario fue dado de baja.");
						} else {
							Main main = new Main(user);
							main.setVisible(false);
							dispose();
						}
					} else {
						lblError.setText("El usuario no existe.");
					}
				}
			}
		});

		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIngresar.setBounds(162, 252, 109, 27);
		contentPane.add(btnIngresar);
		
		contraseniatxt = new JPasswordField();
		contraseniatxt.setBounds(108, 158, 217, 30);
		contentPane.add(contraseniatxt);
		

	}
}
