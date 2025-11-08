package UI.PanelInventario;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import UI.PanelVendedorInternacional;
import javax.swing.SwingConstants;
import java.awt.Color;

public class PanelGestionarInv extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PanelGestionarInv(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Gestion de Inventario");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(96, 23, 243, 27);
		contentPane.add(lblTitulo);

		JButton btnCargarLibros = new JButton("Cargar Libros");
		btnCargarLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarDatosLibro frame = new CargarDatosLibro(user, "crear", null);
				frame.setVisible(true);
				dispose();
			}
		});
		btnCargarLibros.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCargarLibros.setBounds(20, 79, 165, 32);
		contentPane.add(btnCargarLibros);

		JButton btnGestionarLibros = new JButton("Gestionar Libros");
		btnCargarLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionarLibros frame = new GestionarLibros(user);
				frame.setVisible(true);
				dispose();
			}
		});
		btnGestionarLibros.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnGestionarLibros.setBounds(243, 79, 165, 32);
		contentPane.add(btnGestionarLibros);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelVendedorInternacional vendedorInt = new PanelVendedorInternacional(user);
				vendedorInt.setVisible(true);
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVolver.setBounds(146, 144, 141, 32);
		contentPane.add(btnVolver);
	}
}
