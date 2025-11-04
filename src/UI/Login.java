package UI;

import java.awt.EventQueue;
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

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnIngresar;
	private JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(108, 93, 217, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
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
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(108, 158, 217, 30);
		contentPane.add(textField_1);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = Usuario.login();
				
				if (user == null) {					
					lblError = new JLabel("");
					lblError.setHorizontalAlignment(SwingConstants.CENTER);
					lblError.setForeground(new Color(170, 0, 0));
					lblError.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblError.setBounds(44, 207, 346, 31);
					contentPane.add(lblError);
				} else {
					Main principal = new Main(user);
					principal.setVisible(false);
					dispose();
				}
			}
		});
		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIngresar.setBounds(162, 252, 109, 27);
		contentPane.add(btnIngresar);
	
		
	}
}
