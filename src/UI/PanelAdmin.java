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
import javax.swing.SwingConstants;
import java.awt.Color;

public class PanelAdmin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PanelAdmin(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//user.getNombre()
		
		JLabel lblTitulo = new JLabel("Bienvenido:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(69, 21, 250, 32);
		contentPane.add(lblTitulo);
		
		JButton btnGestionarClientes = new JButton("Gestionar Empleados");
		btnGestionarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelUsuarios gestionEmpleados = new PanelUsuarios(user);
				gestionEmpleados.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblUsuario = new JLabel(user.getNombre());
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuario.setBounds(69, 50, 250, 32);
		contentPane.add(lblUsuario);
		btnGestionarClientes.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGestionarClientes.setBounds(112, 115, 165, 32);
		contentPane.add(btnGestionarClientes);
		
		JButton btnGestionarExportaciones = new JButton("Estadistica de Ventas");
		btnGestionarExportaciones.setEnabled(false);
		btnGestionarExportaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionarExportaciones.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGestionarExportaciones.setBounds(112, 173, 165, 32);
		contentPane.add(btnGestionarExportaciones);
		
		JButton btnSeguimientoDeEnvios = new JButton("Gestion de Descuentos");
		btnSeguimientoDeEnvios.setEnabled(false);
		btnSeguimientoDeEnvios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSeguimientoDeEnvios.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSeguimientoDeEnvios.setBounds(112, 233, 165, 32);
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
		btnCerrarSesion.setBounds(124, 302, 141, 32);
		contentPane.add(btnCerrarSesion);
		
		JLabel lblPrximamente = new JLabel("Próximamente...");
		lblPrximamente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrximamente.setForeground(new Color(0, 0, 128));
		lblPrximamente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblPrximamente.setBounds(111, 206, 166, 13);
		contentPane.add(lblPrximamente);
		
		JLabel lblPrximamente_1 = new JLabel("Próximamente...");
		lblPrximamente_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrximamente_1.setForeground(new Color(0, 0, 128));
		lblPrximamente_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblPrximamente_1.setBounds(111, 266, 166, 13);
		contentPane.add(lblPrximamente_1);
	}
}


	

