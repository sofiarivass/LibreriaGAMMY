package UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
//import UI.PanelVentas.GestionarCliente;

import javax.swing.SwingConstants;

public class PanelVendedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PanelVendedor(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Bienvenido " + user.getNombre());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(82, 25, 340, 32);
		contentPane.add(lblTitulo);

		JButton btnGestionarClientes = new JButton("Gestionar Clientes");
		btnGestionarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				GestionarCliente gestionarCliente = new GestionarCliente(user);
//				gestionarCliente.setVisible(true);
				dispose();
			}
		});
		btnGestionarClientes.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGestionarClientes.setBounds(59, 109, 165, 32);
		contentPane.add(btnGestionarClientes);

		JButton btnGestionarExportaciones = new JButton("Gestionar Ventas");
		btnGestionarExportaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				PanelGestionarVentas gestionVentas = new PanelGestionarVentas(user);
//				gestionVentas.setVisible(true);
//				dispose();
			}
		});
		btnGestionarExportaciones.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGestionarExportaciones.setBounds(272, 109, 165, 32);
		contentPane.add(btnGestionarExportaciones);

		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main principal = new Main(null);
				principal.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCerrarSesion.setBounds(182, 178, 141, 32);
		contentPane.add(btnCerrarSesion);
	}

}
