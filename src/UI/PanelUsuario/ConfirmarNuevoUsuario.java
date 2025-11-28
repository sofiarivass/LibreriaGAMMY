package UI.PanelUsuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.TipoEmpleado;
import BLL.Usuario;
import DLL.UsuarioDTO;
import Repository.Encriptador;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class ConfirmarNuevoUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param rol 
	 * @param pass 
	 * @param nombre 
	 * @param usuario 
	 */
	public ConfirmarNuevoUsuario(String usuario, String nombre, String pass, String rol, Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(156, 11, 146, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblConfirmeSiLa = new JLabel("Confirmar si la informacion es correcta");
		lblConfirmeSiLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmeSiLa.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblConfirmeSiLa.setBounds(27, 45, 418, 50);
		contentPane.add(lblConfirmeSiLa);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(79, 129, 87, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(80, 165, 87, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contraseña:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(84, 200, 107, 25);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo Empleado:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(82, 239, 136, 25);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoUsuario volver = new NuevoUsuario(user);
				volver.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(70, 292, 96, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_Usuario = new JLabel("");
		lblNewLabel_Usuario.setBounds(156, 136, 233, 14);
		contentPane.add(lblNewLabel_Usuario);
		lblNewLabel_Usuario.setText(usuario);
		
		JLabel lblNewLabel_Nombre = new JLabel("");
		lblNewLabel_Nombre.setBounds(156, 172, 233, 14);
		contentPane.add(lblNewLabel_Nombre);
		lblNewLabel_Nombre.setText(nombre);
		
		JLabel lblNewLabel_Pass = new JLabel("");
		lblNewLabel_Pass.setBounds(201, 207, 188, 14);
		contentPane.add(lblNewLabel_Pass);
		lblNewLabel_Pass.setText(pass);
		
		JLabel lblNewLabel_Rol = new JLabel("");
		lblNewLabel_Rol.setBounds(228, 246, 161, 14);
		contentPane.add(lblNewLabel_Rol);
		lblNewLabel_Rol.setText(rol);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario nuevo;
				Boolean estadoEmpleado = true;
				LinkedList<TipoEmpleado> listaTipos = UsuarioDTO.seleccionarTipoEmpleado();
				String[] tipos = new String[listaTipos.size()];
				TipoEmpleado tipo_empleado = null;
				
				for (TipoEmpleado lista : listaTipos) {
					if (lista.getTipoEmpleado().equals(rol)) {
						tipo_empleado = lista;
						break;
					}
				}
				
				nuevo = new Usuario(nombre, usuario, Encriptador.encriptar(pass),estadoEmpleado, tipo_empleado);
				UsuarioDTO.agregarUsuario(nuevo);
				PanelUsuarios volver = new PanelUsuarios(user);
				volver.setVisible(true);
				dispose();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.setBounds(313, 292, 96, 34);
		contentPane.add(btnRegistrar);
	}
}
