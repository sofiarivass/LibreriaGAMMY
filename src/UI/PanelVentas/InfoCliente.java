package UI.PanelVentas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BLL.Libro;
import BLL.Usuario;
import UI.PanelVendedorInternacional;

import java.awt.Font;
import java.awt.Color;

public class InfoCliente extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public InfoCliente(Usuario user, Libro libro, String funcion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		
		if(funcion.equals("crear")) {			
			JLabel lblExito = new JLabel("Libro creado exitosamente!");
			lblExito.setHorizontalAlignment(SwingConstants.CENTER);
			lblExito.setForeground(new Color(0, 128, 0));
			lblExito.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			lblExito.setBounds(147, 13, 352, 32);
			contentPane.add(lblExito);
		}

		JLabel lblID = new JLabel();
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblID.setBounds(79, 277, 101, 32);
		lblID.setText("ID: " + libro.getId_libro());
		contentPane.add(lblID);

		JLabel lblTitulo = new JLabel();
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(-24, 158, 306, 32);
		lblTitulo.setText("Nombre: <dynamic>");
		contentPane.add(lblTitulo);

		JLabel lblAutor = new JLabel();
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutor.setBounds(-26, 201, 310, 32);
		lblAutor.setText("DNI: <dynamic>");
		contentPane.add(lblAutor);

		JLabel lblEditorial = new JLabel();
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditorial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEditorial.setBounds(235, 158, 353, 32);
		lblEditorial.setText("Mail: <dynamic>");
		contentPane.add(lblEditorial);

		JLabel lblFecha = new JLabel();
		lblFecha.setForeground(new Color(0, 0, 128));
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setBounds(-7, 115, 272, 32);
		lblFecha.setText("Datos Personales");
		contentPane.add(lblFecha);

		JLabel lblTapa = new JLabel();
		lblTapa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTapa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTapa.setBounds(280, 201, 262, 32);
		lblTapa.setText("Teléfono: <dynamic>");
		contentPane.add(lblTapa);

		JLabel lblEstado = new JLabel();
		lblEstado.setForeground(new Color(0, 0, 128));
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstado.setBounds(293, 277, 237, 32);
		lblEstado.setText("Estado: " + (libro.getEstado() == true ? "Activo" : "Inactivo"));
		contentPane.add(lblEstado);

		JButton btnCancelar = new JButton("Volver");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelVendedorInternacional frame = new PanelVendedorInternacional(user);
				frame.setVisible(true);
				dispose();
			}
		});

		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(220, 337, 109, 27);
		contentPane.add(btnCancelar);
		
		JLabel lblDatosDeContacto = new JLabel();
		lblDatosDeContacto.setText("Datos de Contacto");
		lblDatosDeContacto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeContacto.setForeground(new Color(0, 0, 128));
		lblDatosDeContacto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDatosDeContacto.setBounds(275, 115, 272, 32);
		contentPane.add(lblDatosDeContacto);
		
		JLabel lblNewLabel = new JLabel("INFORMACIÓN DEL CLIENTE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(92, 23, 365, 43);
		contentPane.add(lblNewLabel);

	}

}
