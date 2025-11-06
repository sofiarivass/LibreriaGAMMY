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
	private Cliente cliente;

	/**
	 * Create the frame.
	 */
	public NuevaExportacion(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Buscar Cliente");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(167, 10, 152, 31);
		contentPane.add(lblTitulo);
		
		JLabel lblDNICliente = new JLabel("DNI:");
		lblDNICliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDNICliente.setBounds(138, 63, 36, 13);
		contentPane.add(lblDNICliente);
		
		dniCliente = new JTextField();
		dniCliente.setBounds(184, 62, 96, 19);
		contentPane.add(dniCliente);
		dniCliente.setColumns(10);
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(184, 91, 194, 13);
		contentPane.add(lblError);
		
		JLabel lblClienteEncontrado = new JLabel("");
		lblClienteEncontrado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClienteEncontrado.setBounds(21, 112, 444, 13);
		contentPane.add(lblClienteEncontrado);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarExport gestionExport = new PanelGestionarExport(user);
				gestionExport.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(20, 135, 85, 21);
		contentPane.add(btnVolver);
		
		JButton btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCliente(lblError,lblClienteEncontrado);
			}
		});
		btnBuscarCliente.setBounds(290, 61, 85, 21);
		contentPane.add(btnBuscarCliente);
		
		JButton btnContinuarVenta = new JButton("continuar Venta");
		btnContinuarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cliente != null) {
					ElegirLibros libros = new ElegirLibros(user,cliente);
					libros.setVisible(true);
					dispose();					
				} else {
					lblError.setText("Primero busque un Cliente!!");
				}
			}
		});
		btnContinuarVenta.setBounds(336, 135, 128, 21);
		contentPane.add(btnContinuarVenta);
		
		JButton btnRegistrarCliente = new JButton("registrar cliente");
		btnRegistrarCliente.setBounds(152, 135, 137, 21);
		contentPane.add(btnRegistrarCliente);
		
		
	}
	
	// funciones
	public void buscarCliente(JLabel lblError, JLabel lblClienteEncontrado) {
		lblError.setText("");
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
				
				if (cliente != null) {
					lblClienteEncontrado.setText(
							"Cliente: [id: " + cliente.getIdCliente()
							+ ", dni: " + cliente.getDni()
							+ ", nombre: " + cliente.getNombre() 
							+ ", tel: " + cliente.getTelefono() + "]");
				} else {
					lblError.setText("Cliente No Registrado!!");
				}						
			}
		}
	}
}
