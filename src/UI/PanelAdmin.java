package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BLL.Usuario;
import UI.PanelUsuario.PanelUsuarios;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAdmin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PanelAdmin(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Bienvenido " + user.getNombre());
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(44, 19, 250, 32);
		contentPane.add(lblTitulo);
		
		JButton btnGestionarClientes = new JButton("Gestionar Empleados");
		btnGestionarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUsuarios gestionEmpleados = new PanelUsuarios(user);
				gestionEmpleados.setVisible(true);
				dispose();
			}
		});
		btnGestionarClientes.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGestionarClientes.setBounds(80, 72, 165, 32);
		contentPane.add(btnGestionarClientes);
		
		JButton btnGestionarExportaciones = new JButton("Estadistica de Ventas");
		btnGestionarExportaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionarExportaciones.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGestionarExportaciones.setBounds(80, 115, 165, 32);
		contentPane.add(btnGestionarExportaciones);
		
		JButton btnSeguimientoDeEnvios = new JButton("Gestion de Descuentos");
		btnSeguimientoDeEnvios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSeguimientoDeEnvios.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSeguimientoDeEnvios.setBounds(80, 158, 165, 32);
		contentPane.add(btnSeguimientoDeEnvios);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main principal = new Main(null);
				principal.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCerrarSesion.setBounds(91, 236, 141, 32);
		contentPane.add(btnCerrarSesion);
	}
}


	

