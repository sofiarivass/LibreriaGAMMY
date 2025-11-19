package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BLL.Usuario;
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
	private JTextField txtUsuario;
	private JButton btnIngresar;
	private JPasswordField txtContrasenia;

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(109, 93, 217, 30);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

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
		
		txtContrasenia = new JPasswordField();
		txtContrasenia.setBounds(109, 158, 217, 30);
		contentPane.add(txtContrasenia);

		JLabel lblError = new JLabel("");
		lblError.setForeground(new Color(159, 0, 0));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblError.setBounds(80, 205, 275, 32);
		contentPane.add(lblError);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				Usuario user = null;

				boolean usuario = Repository.Validaciones.validarVacioJframe(txtUsuario.getText());
				boolean contrasenia = Repository.Validaciones.validarVacioJframe(txtContrasenia.getText());
				
				if (usuario == true || contrasenia == true) {
					lblError.setText("Por favor complete los campos");
				} else {
					user = Usuario.login(txtUsuario.getText(), txtContrasenia.getText());

					if (user == null) {
						lblError.setText("Usuario o contraseña incorrectos");
					} else if (user.getEstado() == false) {
						lblError.setText("El usuario fue dado de baja");
					} else {
						Main frame = new Main(user);
						frame.setVisible(false);
						dispose();
					}
				}
			}
		});

		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIngresar.setBounds(163, 252, 109, 27);
		contentPane.add(btnIngresar);
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main frame = new Main(null);
				frame.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(337, 267, 89, 23);
		contentPane.add(btnRegresar);

	}
}
