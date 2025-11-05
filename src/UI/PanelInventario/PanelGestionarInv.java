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
import UI.PanelExportacion.NuevaExportacion;
import javax.swing.SwingConstants;

public class PanelGestionarInv extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PanelGestionarInv(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
				NuevaExportacion nuevaVenta = new NuevaExportacion(user);
				nuevaVenta.setVisible(true);
				dispose();
			}
		});
		btnCargarLibros.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCargarLibros.setBounds(24, 79, 165, 32);
		contentPane.add(btnCargarLibros);

		JButton btnModificarLibros = new JButton("Modificar Libros");
		btnModificarLibros.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnModificarLibros.setBounds(247, 79, 165, 32);
		contentPane.add(btnModificarLibros);

		JButton btnMostrarLibros = new JButton("Mostrar Libros");
		btnMostrarLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMostrarLibros.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMostrarLibros.setBounds(247, 136, 165, 32);
		contentPane.add(btnMostrarLibros);

		JButton btnEliminarLibros = new JButton("Eliminar Libros");
		btnEliminarLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEliminarLibros.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEliminarLibros.setBounds(24, 136, 165, 32);
		contentPane.add(btnEliminarLibros);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelVendedorInternacional vendedorInter = new PanelVendedorInternacional(user);
				vendedorInter.setVisible(true);
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVolver.setBounds(148, 205, 141, 32);
		contentPane.add(btnVolver);
	}

}
