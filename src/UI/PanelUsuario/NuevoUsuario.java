package UI.PanelUsuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.TipoEmpleado;
import BLL.Usuario;
import DLL.UsuarioDTO;
import Repository.Encriptador;
import UI.Main;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class NuevoUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Usuario;
	private JTextField textField_Nombre;
	private JPasswordField passwordField_Pass;
	private JPasswordField passwordField_PassRep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoUsuario frame = new NuevoUsuario(null);
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
	public NuevoUsuario(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(150, 11, 146, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 89, 87, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 140, 87, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contraseña:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(10, 197, 107, 25);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo Empleado:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(10, 341, 136, 25);
		contentPane.add(lblNewLabel_4);
		
		
		JLabel lblNewLabel_5 = new JLabel("Repita la Contraseña:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(10, 279, 184, 25);
		contentPane.add(lblNewLabel_5);
		
		passwordField_Pass = new JPasswordField();
		passwordField_Pass.setBounds(191, 196, 243, 30);
		contentPane.add(passwordField_Pass);
		
		
		passwordField_PassRep = new JPasswordField();
		passwordField_PassRep.setBounds(191, 278, 243, 30);
		contentPane.add(passwordField_PassRep);
		
		
		JLabel lblError_Usuario = new JLabel("");
		lblError_Usuario.setForeground(new Color(255, 0, 0));
		lblError_Usuario.setBounds(148, 114, 286, 14);
		contentPane.add(lblError_Usuario);
		
		
		JLabel lblError_PassRep = new JLabel("");
		lblError_PassRep.setForeground(new Color(255, 0, 0));
		lblError_PassRep.setBounds(150, 315, 286, 14);
		contentPane.add(lblError_PassRep);
		
		
		JLabel lblError_Rol = new JLabel("");
		lblError_Rol.setForeground(new Color(255, 0, 0));
		lblError_Rol.setBounds(10, 412, 286, 14);
		contentPane.add(lblError_Rol);
		
		JLabel lblError_Nombre = new JLabel("");
		lblError_Nombre.setForeground(new Color(255, 0, 0));
		lblError_Nombre.setBounds(150, 171, 286, 14);
		contentPane.add(lblError_Nombre);
		
		JLabel lblError_Pass = new JLabel("");
		lblError_Pass.setForeground(new Color(255, 0, 0));
		lblError_Pass.setBounds(150, 230, 286, 14);
		contentPane.add(lblError_Pass);
		
		textField_Usuario = new JTextField();
		textField_Usuario.setBounds(150, 84, 284, 25);
		contentPane.add(textField_Usuario);
		textField_Usuario.setColumns(10);
		
		textField_Nombre = new JTextField();
		textField_Nombre.setColumns(10);
		textField_Nombre.setBounds(150, 139, 284, 30);
		contentPane.add(textField_Nombre);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 371, 259, 30);
		comboBox.addItem("");
		for(TipoEmpleado rol : UsuarioDTO.seleccionarTipoEmpleado()) {
			comboBox.addItem(rol.getTipoEmpleado());
		}
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUsuarios volver = new PanelUsuarios(user);
				volver.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(10, 456, 118, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				LinkedList<Usuario> usuariosSerch = UsuarioDTO.mostrarUsuarios();
				boolean passV, passRepV, flag;
				String usuarioV, nombreV;
				String usuario, nombre, pass = "", passRep = "", rol;
				
				usuarioV = Repository.Validaciones.validarStringJframe(textField_Usuario.getText());
				if (usuarioV.equals("vacio")) {
					lblError_Usuario.setText("Por favor complete los campos obligatorios");
				}else {
					usuario = textField_Usuario.getText();
					flag = false;
					for (Usuario userCheck: usuariosSerch) {
						if (userCheck.getUsuario().equalsIgnoreCase(usuario)) {
							lblError_Usuario.setText("Usuario Existente");
							System.out.println("Este Usuario existe");
							flag = true;
						}else {
							lblError_Usuario.setText("");
							
						}
					}while (flag);
					
					
				}
				
				nombreV = Repository.Validaciones.validarStringJframe(textField_Nombre.getText());
				if(nombreV.equals("vacio")) {
					lblError_Nombre.setText("Por favor complete los campos obligatorios");
				}else if(nombreV.equals("numero")) {
					lblError_Nombre.setText("El nombre no puede contener números");
				}else {
					nombre = textField_Nombre.getText();
					lblError_Nombre.setText("");
				}
				
				passV = Repository.Validaciones.validarVacioJframe(passwordField_Pass.getText());
				if(passV == true) {
					lblError_Pass.setText("Por favor complete los campos obligatorios");
				}else {
					pass = passwordField_Pass.getText();
					System.out.println("soy pass: " + pass);
					lblError_Pass.setText("");
				}
				
				passRepV = Repository.Validaciones.validarVacioJframe(passwordField_PassRep.getText());
				if(passRepV == true) {
					lblError_PassRep.setText("Por favor complete los campos obligatorios");				
					}else {
						passRep = passwordField_PassRep.getText();
						System.out.println("soy passRep: " + passRep);
						lblError_PassRep.setText("");
					}
				
				if (pass.equals(passRep)) {
					lblError_PassRep.setText("");
				}else {
					lblError_PassRep.setText("Las contraseñas no coinciden");
				}
				
				
				rol = comboBox.getSelectedItem().toString();
				if(rol == "") {
					lblError_Rol.setText("Por favor complete los campos obligatorios");
				}else {
					lblError_Rol.setText("");
				}
				
				
			}		
				
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(333, 456, 118, 50);
		contentPane.add(btnNewButton_1);
		
		
		
	}
}
