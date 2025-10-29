package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Cliente;
import BLL.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class NuevaExportacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dniCiente;

	/**
	 * Create the frame.
	 */
	public NuevaExportacion(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Cargar los Datos");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(119, 10, 177, 31);
		contentPane.add(lblTitulo);
		
		JLabel lblDNICliente = new JLabel("DNI:");
		lblDNICliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDNICliente.setBounds(67, 60, 36, 13);
		contentPane.add(lblDNICliente);
		
		dniCiente = new JTextField();
		dniCiente.setBounds(119, 59, 96, 19);
		contentPane.add(dniCiente);
		dniCiente.setColumns(10);
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(119, 88, 191, 13);
		contentPane.add(lblError);
		
		JLabel lblClienteEncontrado = new JLabel("");
		lblClienteEncontrado.setBounds(10, 111, 416, 19);
		contentPane.add(lblClienteEncontrado);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarExport gestionExport = new PanelGestionarExport(user);
				gestionExport.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(10, 232, 85, 21);
		contentPane.add(btnVolver);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main principal = new Main(null);
				principal.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(341, 232, 85, 21);
		contentPane.add(btnSalir);
		
		JButton btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = Cliente.buscarClienteJframe(dniCiente.getText());
				if (cliente != null) {
					lblClienteEncontrado.setText(cliente.toString());;
				} else {
					lblError.setText("Error al ingresar los Datos");
				}
			}
		});
		btnBuscarCliente.setBounds(225, 58, 85, 21);
		contentPane.add(btnBuscarCliente);
		
		
	}
}
