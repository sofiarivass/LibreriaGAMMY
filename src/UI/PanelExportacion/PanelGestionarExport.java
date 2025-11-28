package UI.PanelExportacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BLL.Usuario;
import Repository.Validaciones;
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
		setBounds(100, 100, 521, 279);
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
				ModificarVenta modificar = new ModificarVenta(user);
				modificar.setVisible(true);
				dispose();
			}
		});
		btnModificarExportacion.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnModificarExportacion.setBounds(281, 99, 165, 32);
		contentPane.add(btnModificarExportacion);
		
		JButton btnAnularExportacion = new JButton("Anular Exportación");
		btnAnularExportacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnularExportacion anularVenta = new AnularExportacion(user);
				anularVenta.setVisible(true);
				dispose();
			}
		});
		btnAnularExportacion.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAnularExportacion.setBounds(170, 156, 165, 32);
		contentPane.add(btnAnularExportacion);
		
		JButton btnRegresar = new JButton("");
		btnRegresar.setIcon(Validaciones.getScaledImageIcon("/img/volver.png", 22, 22));
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelVendedorInternacional vendedorInter = new PanelVendedorInternacional(user);
				vendedorInter.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(460, 200, 35, 29);
		contentPane.add(btnRegresar);
	}
}
