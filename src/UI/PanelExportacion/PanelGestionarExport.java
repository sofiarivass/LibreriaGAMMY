package UI.PanelExportacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BLL.Usuario;
import UI.PanelVendedorInternacional;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelGestionarExport extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PanelGestionarExport(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Gestion de Exportación");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(142, 23, 243, 27);
		contentPane.add(lblTitulo);
		
		JButton btnNuevaExportacion = new JButton("Nueva Exportación");
		btnNuevaExportacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaExportacion nuevaVenta = new NuevaExportacion(user);
				nuevaVenta.setVisible(true);
				dispose();
			}
		});
		btnNuevaExportacion.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNuevaExportacion.setBounds(58, 99, 165, 32);
		contentPane.add(btnNuevaExportacion);
		
		JButton btnModificarExportacion = new JButton("Modificar Exportación");
		btnModificarExportacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarVenta modificar = new ModificarVenta(user,null,null);
				modificar.setVisible(true);
				dispose();
			}
		});
		btnModificarExportacion.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnModificarExportacion.setBounds(281, 99, 165, 32);
		contentPane.add(btnModificarExportacion);
		
		JButton btnMostrarExportaciones = new JButton("Mostrar Exportaciones");
		btnMostrarExportaciones.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMostrarExportaciones.setBounds(281, 156, 165, 32);
		contentPane.add(btnMostrarExportaciones);
		
		JButton btnAnularExportacion = new JButton("Anular Exportación");
		btnAnularExportacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// anular exportacion
			}
		});
		btnAnularExportacion.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAnularExportacion.setBounds(58, 156, 165, 32);
		contentPane.add(btnAnularExportacion);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelVendedorInternacional vendedorInter = new PanelVendedorInternacional(user);
				vendedorInter.setVisible(true);
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVolver.setBounds(182, 225, 141, 32);
		contentPane.add(btnVolver);
	}
}
