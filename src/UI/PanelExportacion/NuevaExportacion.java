package UI.PanelExportacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Cliente;
import BLL.Usuario;
import Repository.Validaciones;
import UI.Main;

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
	private JTextField dniCliente;

	/**
	 * Create the frame.
	 */
	public NuevaExportacion(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Buscar Cliente");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(75, 10, 152, 31);
		contentPane.add(lblTitulo);
		
		JLabel lblDNICliente = new JLabel("DNI:");
		lblDNICliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDNICliente.setBounds(32, 64, 36, 13);
		contentPane.add(lblDNICliente);
		
		dniCliente = new JTextField();
		dniCliente.setBounds(78, 63, 96, 19);
		contentPane.add(dniCliente);
		dniCliente.setColumns(10);
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(75, 92, 194, 13);
		contentPane.add(lblError);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarExport gestionExport = new PanelGestionarExport(user);
				gestionExport.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(32, 124, 85, 21);
		contentPane.add(btnVolver);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main principal = new Main(null);
				principal.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(184, 124, 85, 21);
		contentPane.add(btnSalir);
		
		JButton btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				Cliente cliente = null;
				boolean flag = Validaciones.validarVacioJframe(dniCliente.getText());
				
				if (flag != false) {
					lblError.setText("No puede dejar el campo Vacio!!");
				} else {
					flag = Validaciones.validarDniJframe(dniCliente.getText());
					if (flag != false) {
						lblError.setText("número de dni invalido!!");
						dniCliente.setText("");
					} else {
						cliente = Cliente.buscarClienteJframe(dniCliente.getText());
						ElegirLibros libros = new ElegirLibros(user,cliente);
						libros.setVisible(true);
						dispose();
						
						if (cliente != null) {
							// funcion para registrar al cliente
						} else {
							lblError.setText("Cliente No Registrado!!");
						}						
					}
				}
			}
		});
		btnBuscarCliente.setBounds(184, 62, 85, 21);
		contentPane.add(btnBuscarCliente);
		
		
	}
}
