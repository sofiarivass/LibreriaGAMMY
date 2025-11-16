package UI.PanelInventario;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BLL.Libro;
import BLL.Usuario;
import java.awt.Font;
import java.awt.Color;

public class InfoLibro extends JFrame {
	JLabel lblPortada;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public InfoLibro(Usuario user, Libro libro, String funcion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 725);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblPortada = new JLabel();
		lblPortada.setBounds(243, 55, 157, 250);
		lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
		// lblPortada.setIcon(new
		// ImageIcon(InfoLibro.class.getResource("/img/portada.jpg")));
//		lblPortada.setBounds(0, 6, 510, 220);

		mostrarImagen(libro.getPortada());
		contentPane.setLayout(null);

		contentPane.add(lblPortada);
		
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
		lblID.setBounds(128, 320, 101, 32);
		lblID.setText("ID: " + libro.getId_libro());
		contentPane.add(lblID);

		JLabel lblTitulo = new JLabel();
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(25, 350, 306, 32);
		lblTitulo.setText("Título: " + libro.getTitulo());
		contentPane.add(lblTitulo);

		JLabel lblAutor = new JLabel();
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutor.setBounds(23, 381, 310, 32);
		lblAutor.setText("Autor: " + libro.getAutor());
		contentPane.add(lblAutor);

		JLabel lblEditorial = new JLabel();
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditorial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEditorial.setBounds(2, 410, 353, 32);
		lblEditorial.setText("Editorial: " + libro.getEditorial());
		contentPane.add(lblEditorial);

		JLabel lblFecha = new JLabel();
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(3, 440, 351, 32);
		lblFecha.setText("Fecha de publicación: "
				+ (libro.getFechaPublicacion() == null ? "No especificada" : libro.getFechaPublicacion()));
		contentPane.add(lblFecha);

		JLabel lblGenero = new JLabel();
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGenero.setBounds(291, 320, 336, 32);
		lblGenero.setText("Género: " + libro.getGenero());
		contentPane.add(lblGenero);

		JLabel lblIdioma = new JLabel();
		lblIdioma.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdioma.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdioma.setBounds(301, 350, 316, 32);
		lblIdioma.setText("Idioma: " + libro.getIdioma());
		contentPane.add(lblIdioma);

		JLabel lblPublico = new JLabel();
		lblPublico.setHorizontalAlignment(SwingConstants.CENTER);
		lblPublico.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPublico.setBounds(276, 381, 366, 32);
		lblPublico.setText("Público Objetivo: " + libro.getPublicoObjetivo());
		contentPane.add(lblPublico);

		JLabel lblPaginas = new JLabel();
		lblPaginas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaginas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPaginas.setBounds(323, 410, 272, 32);
		lblPaginas.setText("Cantidad de páginas: " + libro.getNumPaginas());
		contentPane.add(lblPaginas);

		JLabel lblTapa = new JLabel();
		lblTapa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTapa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTapa.setBounds(328, 440, 262, 32);
		lblTapa.setText("Tapa: " + libro.getTapa());
		contentPane.add(lblTapa);

		JLabel lblFirmado = new JLabel();
		lblFirmado.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirmado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFirmado.setBounds(60, 496, 237, 32);
		lblFirmado.setText("¿Firmado?: " + (libro.getFirmado() == true ? "Sí" : "No"));
		contentPane.add(lblFirmado);

		JLabel lblEdicion = new JLabel();
		lblEdicion.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdicion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEdicion.setBounds(60, 527, 237, 32);
		lblEdicion.setText("¿Edición especial?: " + (libro.getEdicionEspecial() == true ? "Sí" : "No"));
		contentPane.add(lblEdicion);

		JLabel lblSaga = new JLabel();
		lblSaga.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaga.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSaga.setBounds(60, 560, 237, 32);
		lblSaga.setText("¿Pertenece a saga?: " + (libro.getSaga() == true ? "Sí" : "No"));
		contentPane.add(lblSaga);

		JLabel lblPrecio = new JLabel();
		lblPrecio.setForeground(new Color(0, 0, 128));
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecio.setBounds(341, 496, 237, 32);
		lblPrecio.setText("Precio: $" + libro.getPrecio());
		contentPane.add(lblPrecio);

		JLabel lblStock = new JLabel();
		lblStock.setForeground(new Color(0, 0, 128));
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStock.setBounds(341, 527, 237, 32);
		lblStock.setText("Stock: " + libro.getStock() + " unidades");
		contentPane.add(lblStock);

		JLabel lblEstado = new JLabel();
		lblEstado.setForeground(new Color(0, 0, 128));
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstado.setBounds(341, 560, 237, 32);
		lblEstado.setText("Estado: " + (libro.getEstado() == true ? "Activo" : "Inactivo"));
		contentPane.add(lblEstado);

		JButton btnCancelar = new JButton("Volver");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionarLibros frame = new GestionarLibros(user);
				frame.setVisible(true);
				dispose();
			}
		});

		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(268, 638, 109, 27);
		contentPane.add(btnCancelar);

	}

	private void mostrarImagen(byte[] imagenBytes) {
		lblPortada.setText("");
		if (imagenBytes != null && imagenBytes.length > 0) {
			ImageIcon icon = new ImageIcon(imagenBytes);
			Image portada = icon.getImage().getScaledInstance(157, 250, Image.SCALE_SMOOTH);
			lblPortada.setIcon(new ImageIcon(portada));
		} else {
//			File placeholder = InfoLibro.class.getResource("img/placeholder.jpg");
//			byte[] placeholderByte = Files.readAllBytes(placeholder.toPath());
			lblPortada.setIcon(new ImageIcon(InfoLibro.class.getResource("/img/placeholder.jpg")));
//			lblImagen.setIcon(null);
//          lblImagen.setText("Sin imagen");
		}
	}
}
