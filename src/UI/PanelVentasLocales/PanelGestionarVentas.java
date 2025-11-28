package UI.PanelVentasLocales;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BLL.Usuario;
import Repository.Validaciones;
import UI.PanelVendedor;
import UI.PanelVendedorInternacional;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PanelGestionarVentas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PanelGestionarVentas(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Gestion de Ventas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(153, 23, 201, 27);
		contentPane.add(lblTitulo);
		
		JButton btnNuevaVenta = new JButton("Nueva Venta");
		btnNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaVenta nuevaVentaLocal = new NuevaVenta(user);
				nuevaVentaLocal.setVisible(true);
				dispose();
			}
		});
		btnNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNuevaVenta.setBounds(58, 99, 165, 32);
		contentPane.add(btnNuevaVenta);
		
		JButton btnModificarVenta = new JButton("Modificar Venta");
		btnModificarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarVentaLocal modificar = new ModificarVentaLocal(user);
				modificar.setVisible(true);
				dispose();
			}
		});
		btnModificarVenta.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnModificarVenta.setBounds(281, 99, 165, 32);
		contentPane.add(btnModificarVenta);
		
		JButton btnAnularVenta = new JButton("Anular Venta");
		btnAnularVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnularVentaLocal anularVenta = new AnularVentaLocal(user);
				anularVenta.setVisible(true);
				dispose();
			}
		});
		btnAnularVenta.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAnularVenta.setBounds(170, 156, 165, 32);
		contentPane.add(btnAnularVenta);
		
		JButton btnRegresar = new JButton("");
		btnRegresar.setIcon(Validaciones.getScaledImageIcon("/img/volver.png", 22, 22));
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelVendedor vendedor = new PanelVendedor(user);
				vendedor.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(460, 200, 35, 29);
		contentPane.add(btnRegresar);
	}
}
