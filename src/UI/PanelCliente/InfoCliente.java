package UI.PanelCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import BLL.Cliente;
import BLL.Usuario;
import java.awt.Font;
import java.awt.Color;

public class InfoCliente extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public InfoCliente(Usuario user, Cliente cliente, String funcion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);

		JLabel lblID = new JLabel();
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblID.setBounds(98, 277, 101, 32);
		lblID.setText("ID: " + cliente.getIdCliente());
		contentPane.add(lblID);

		JLabel lblTitulo = new JLabel();
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(-5, 158, 306, 32);
		lblTitulo.setText("Nombre: " + cliente.getNombre());
		contentPane.add(lblTitulo);

		JLabel lblAutor = new JLabel();
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutor.setBounds(-7, 201, 310, 32);
		lblAutor.setText("DNI: " + cliente.getDni());
		contentPane.add(lblAutor);

		JLabel lblEditorial = new JLabel();
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditorial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEditorial.setBounds(285, 158, 353, 32);
		lblEditorial.setText("Mail: " + cliente.getMail());
		contentPane.add(lblEditorial);

		JLabel lblFecha = new JLabel();
		lblFecha.setForeground(new Color(0, 0, 128));
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setBounds(12, 115, 272, 32);
		lblFecha.setText("Datos Personales");
		contentPane.add(lblFecha);

		JLabel lblTapa = new JLabel();
		lblTapa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTapa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTapa.setBounds(330, 201, 262, 32);
		lblTapa.setText("Teléfono: " + cliente.getTelefono());
		contentPane.add(lblTapa);

		JLabel lblEstado = new JLabel();
		lblEstado.setForeground(new Color(0, 0, 128));
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstado.setBounds(343, 277, 237, 32);
		lblEstado.setText("Estado: " + (cliente.getEstado() == true ? "Activo" : "Inactivo"));
		contentPane.add(lblEstado);

		JButton btnCancelar = new JButton("Volver");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionarCliente frame = new GestionarCliente(user);
				frame.setVisible(true);
				dispose();
			}
		});

		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(269, 337, 109, 27);
		contentPane.add(btnCancelar);

		JLabel lblDatosDeContacto = new JLabel();
		lblDatosDeContacto.setText("Datos de Contacto");
		lblDatosDeContacto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeContacto.setForeground(new Color(0, 0, 128));
		lblDatosDeContacto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDatosDeContacto.setBounds(325, 115, 272, 32);
		contentPane.add(lblDatosDeContacto);

		JLabel lblNewLabel = new JLabel("INFORMACIÓN DEL CLIENTE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(139, 23, 365, 43);
		contentPane.add(lblNewLabel);

	}
}
