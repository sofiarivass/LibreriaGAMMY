package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BLL.Exportacion;
import BLL.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VendedorInternacional extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// esto ya no sirve porque lo llamariamos desde el main principal
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VendedorInternacional frame = new VendedorInternacional();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public VendedorInternacional(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido " + user.getNombre());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(139, 11, 250, 32);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Gestionar Clientes");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.setBounds(58, 105, 141, 32);
		contentPane.add(btnNewButton);
		
		JButton btnGestionarExportaciones = new JButton("Gestionar Exportaciones");
		btnGestionarExportaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exportacion.modificarVentaExport(user);
			}
		});
		btnGestionarExportaciones.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnGestionarExportaciones.setBounds(58, 162, 141, 32);
		contentPane.add(btnGestionarExportaciones);
		
		JButton btnSeguimientoDeEnvios = new JButton("Seguimiento de Envios");
		btnSeguimientoDeEnvios.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSeguimientoDeEnvios.setBounds(266, 105, 141, 32);
		contentPane.add(btnSeguimientoDeEnvios);
		
		JButton btnGestionarInventario = new JButton("Gestionar Inventario");
		btnGestionarInventario.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnGestionarInventario.setBounds(266, 162, 141, 32);
		contentPane.add(btnGestionarInventario);
		
		JButton btnCerrarSesin = new JButton("Cerrar Sesión");
		btnCerrarSesin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCerrarSesin.setBounds(153, 225, 141, 32);
		contentPane.add(btnCerrarSesin);
	}
}


	
