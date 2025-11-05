package UI.PanelInventario;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import BLL.Usuario;
import Enums.*;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.Color;

public class CargarDatosLibro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JTextField txtEditorial;
	private JTextField textField;
	private JTextField txtPrecio;
	private JTextField txtStock;

	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CargarDatosLibro frame = new CargarDatosLibro();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	/**
	 * Create the frame.
	 */
	public CargarDatosLibro(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCargarNuevoLibro = new JLabel("Cargar Nuevo Libro al Sistema");
		lblCargarNuevoLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargarNuevoLibro.setBounds(118, 28, 355, 25);
		lblCargarNuevoLibro.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblCargarNuevoLibro);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(39, 108, 217, 30);
		contentPane.add(txtTitulo);
		
		JLabel lblTtulo = new JLabel("Título");
		lblTtulo.setBounds(39, 92, 46, 14);
		contentPane.add(lblTtulo);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(39, 150, 46, 14);
		contentPane.add(lblAutor);
		
		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		txtAutor.setBounds(39, 166, 217, 30);
		contentPane.add(txtAutor);
		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(39, 206, 46, 14);
		contentPane.add(lblEditorial);
		
		txtEditorial = new JTextField();
		txtEditorial.setColumns(10);
		txtEditorial.setBounds(39, 222, 217, 30);
		contentPane.add(txtEditorial);
		
		JLabel lblFecha = new JLabel("Fecha de publicación");
        lblFecha.setBounds(39, 262, 172, 14);
        contentPane.add(lblFecha);
        
        JDateChooser dateChooser1_1 = new JDateChooser();
        dateChooser1_1.getCalendarButton().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        dateChooser1_1.setDateFormatString("dd/MM/yyyy");
        dateChooser1_1.setBounds(39, 279, 217, 30);
        contentPane.add(dateChooser1_1);
        
        JComboBox comboBoxGenero = new JComboBox();
        comboBoxGenero.setBounds(308, 107, 217, 30);
        for (Generos genero : Generos.values()) {
            comboBoxGenero.addItem(genero.name());
        }
        contentPane.add(comboBoxGenero);
        
        JLabel lblGenero = new JLabel("Género");
        lblGenero.setBounds(309, 92, 41, 14);
        contentPane.add(lblGenero);
        
        JComboBox comboBoxIdioma = new JComboBox();
        comboBoxIdioma.setBounds(308, 166, 217, 30);
        for (Idiomas idioma : Idiomas.values()) {
        	comboBoxIdioma.addItem(idioma.name());
        }
        contentPane.add(comboBoxIdioma);
        
        JLabel lblIdioma = new JLabel("Idioma");
        lblIdioma.setBounds(310, 151, 41, 14);
        contentPane.add(lblIdioma);
        
        JLabel lblPublico = new JLabel("Público objetivo");
        lblPublico.setBounds(310, 207, 144, 14);
        contentPane.add(lblPublico);
        
        JComboBox comboBoxPublico = new JComboBox();
        comboBoxPublico.setBounds(308, 222, 217, 30);
        for (Publico publico : Publico.values()) {
        	comboBoxPublico.addItem(publico.name());
        }
        contentPane.add(comboBoxPublico);
        
        JLabel lblPaginas = new JLabel("Cantidad de páginas");
        lblPaginas.setBounds(39, 319, 172, 14);
        contentPane.add(lblPaginas);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(39, 335, 217, 30);
        contentPane.add(textField);
        
        JCheckBox chckbxFirmado = new JCheckBox("¿Libro firmado?");
        chckbxFirmado.setBounds(311, 334, 162, 20);
        contentPane.add(chckbxFirmado);
        
        JCheckBox chckbxesEdicion = new JCheckBox("¿Edición especial?");
        chckbxesEdicion.setBounds(311, 385, 162, 20);
        contentPane.add(chckbxesEdicion);
        
        JLabel lblTapa = new JLabel("Tipo de tapa");
        lblTapa.setBounds(310, 264, 87, 14);
        contentPane.add(lblTapa);
        
        JComboBox comboBoxTapa = new JComboBox();
        comboBoxTapa.setBounds(308, 279, 217, 30);
        for (Tapas tapa : Tapas.values()) {
        	comboBoxTapa.addItem(tapa.name());
        }
        contentPane.add(comboBoxTapa);
        
        JCheckBox chckbxSaga = new JCheckBox("¿Pertenece a una saga?");
        chckbxSaga.setBounds(311, 360, 200, 20);
        contentPane.add(chckbxSaga);
        
        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(143, 456, 46, 14);
        contentPane.add(lblPrecio);
        
        txtPrecio = new JTextField();
        txtPrecio.setColumns(10);
        txtPrecio.setBounds(143, 472, 87, 30);
        contentPane.add(txtPrecio);
        
        JLabel lblPrecio_1 = new JLabel("$");
        lblPrecio_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPrecio_1.setBounds(129, 473, 14, 29);
        contentPane.add(lblPrecio_1);
        
        txtStock = new JTextField();
        txtStock.setColumns(10);
        txtStock.setBounds(289, 472, 87, 30);
        contentPane.add(txtStock);
        
        JLabel lblStock_1 = new JLabel("unidades");
        lblStock_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblStock_1.setBounds(385, 473, 82, 29);
        contentPane.add(lblStock_1);
        
        JLabel lblStock = new JLabel("Stock");
        lblStock.setBounds(289, 456, 46, 14);
        contentPane.add(lblStock);
        
        JButton btnCargarPortada = new JButton("Cargar foto de portada");
        btnCargarPortada.setForeground(new Color(0, 0, 0));
        btnCargarPortada.setBackground(new Color(192, 192, 192));
        btnCargarPortada.setBounds(58, 380, 178, 25);
        contentPane.add(btnCargarPortada);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGuardar.setBounds(308, 545, 109, 27);
        contentPane.add(btnGuardar);
        
        JButton btnSalir = new JButton("Cancelar");
        btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarInv frame = new PanelGestionarInv(user);
				frame.setVisible(true);
				dispose();
			}
		});
      
        btnSalir.setForeground(new Color(153, 17, 20));
        btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSalir.setBounds(444, 545, 109, 27);
        contentPane.add(btnSalir);
        

	}
}
