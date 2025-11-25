package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BLL.Usuario;
import UI.PanelExportacion.PanelGestionarExport;
import UI.PanelInventario.GestionarLibros;
//import UI.PanelVentas.GestionarCliente;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelVendedorInternacional extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PanelVendedorInternacional(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Bienvenido " + user.getNombre());
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(139, 11, 250, 32);
		contentPane.add(lblTitulo);

		JButton btnGestionarClientes = new JButton("Gestionar Clientes");
		btnGestionarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				GestionarCliente gestionarCliente = new GestionarCliente(user);
//				gestionarCliente.setVisible(true);
//				dispose();
			}
		});
		btnGestionarClientes.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGestionarClientes.setBounds(58, 105, 165, 32);
		contentPane.add(btnGestionarClientes);

		JButton btnGestionarExportaciones = new JButton("Gestionar Exportaciones");
		btnGestionarExportaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarExport gestionExport = new PanelGestionarExport(user);
				gestionExport.setVisible(true);
				dispose();
			}
		});
		btnGestionarExportaciones.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGestionarExportaciones.setBounds(58, 162, 165, 32);
		contentPane.add(btnGestionarExportaciones);

		JButton btnSeguimientoDeEnvios = new JButton("Seguimiento de Envios");
		btnSeguimientoDeEnvios.setEnabled(false);
		btnSeguimientoDeEnvios.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSeguimientoDeEnvios.setBounds(281, 162, 165, 32);
		contentPane.add(btnSeguimientoDeEnvios);

		JButton btnGestionarInventario = new JButton("Gestionar Inventario");
		btnGestionarInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionarLibros frame = new GestionarLibros(user);
				frame.setVisible(true);
				dispose();
			}
		});
		btnGestionarInventario.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGestionarInventario.setBounds(281, 105, 165, 32);
		contentPane.add(btnGestionarInventario);

		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main principal = new Main(null);
				principal.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCerrarSesion.setBounds(182, 228, 141, 32);
		contentPane.add(btnCerrarSesion);
	}
}
