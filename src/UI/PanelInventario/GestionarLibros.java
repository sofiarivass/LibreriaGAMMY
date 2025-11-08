package UI.PanelInventario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.CarritoDetalle;
import BLL.Cliente;
import BLL.Libro;
import BLL.Usuario;
import DLL.LibroDTO;
import Enums.MetodoPago;
import Enums.Sucursales;
import Enums.TipoMoneda;
import Repository.Validaciones;
import UI.Main;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.SwingConstants;

public class GestionarLibros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Libro libroSeleccionado;
	private DefaultTableModel model;

	/**
	 * Create the frame.
	 */

	public GestionarLibros(Usuario user) {
		LinkedList<CarritoDetalle> librosCarrito = new LinkedList<CarritoDetalle>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Gestionar Libros");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(250, 17, 185, 32);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblTitulo);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 52, 660, 574);
		contentPane.add(tabbedPane);
		
		JLabel lblSeleccionado = new JLabel("Seleccionado: ");
		lblSeleccionado.setForeground(new Color(0, 0, 128));
		lblSeleccionado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblSeleccionado.setBounds(12, 233, 224, 20);
		contentPane.add(lblSeleccionado);

		model = new DefaultTableModel(new String[] { "ID", "TITULO", "AUTOR", "EDITORIAL", "IDIOMA", "ESTADO" }, 0);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Elegir Libros", null, panel, null);

		LinkedList<Libro> listaLibros = LibroDTO.elegirLibros();
		for (Libro libro : listaLibros) {
			System.out.println(libro.getStock());
		}

		if (listaLibros != null) {
			for (Libro libro : listaLibros) {
				lblSeleccionado.setText("ID: " + libro.getId_libro() + "TITULO: " + libro.getTitulo());
			}
		}
		panel.setLayout(null);

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 37, 635, 187);
		panel.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Libros cargados en el sistema:");
		lblNewLabel.setBounds(10, 11, 233, 21);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNewLabel);

		JButton btnVerDetalle = new JButton("Ver detalle");
		btnVerDetalle.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVerDetalle.setBounds(68, 263, 157, 35);
		panel.add(btnVerDetalle);

		JButton btnEditarDatos = new JButton("Modificar datos");
		btnEditarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarDatosLibro frame = new CargarDatosLibro(user, "editar", libroSeleccionado);
				frame.setVisible(true);
				dispose();
			}
		});
		btnEditarDatos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditarDatos.setBounds(249, 263, 157, 35);
		panel.add(btnEditarDatos);

		JButton btnDeshabilitar = new JButton("Dar de baja");
		btnDeshabilitar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDeshabilitar.setBounds(430, 263, 157, 35);
		panel.add(btnDeshabilitar);

		JLabel lblDasd = new JLabel("");
		lblDasd.setHorizontalAlignment(SwingConstants.CENTER);
		lblDasd.setForeground(new Color(159, 0, 0));
		lblDasd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblDasd.setBounds(190, 319, 275, 32);
		panel.add(lblDasd);

		
		// Acción al seleccionar fila
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                	libroSeleccionado = new Libro(
                        (int) model.getValueAt(row, 0),
                        (String) model.getValueAt(row, 1),
                        (String) model.getValueAt(row, 2),
                        (String) model.getValueAt(row, 3),
                        (String) model.getValueAt(row, 4),
                        (String) model.getValueAt(row, 5),
                    );
                	
//                	"ID", "TITULO", "AUTOR", "EDITORIAL", "IDIOMA", "ESTADO"
                	
//                	int id_libro, String titulo, String autor, String editorial, Date fechaPublicacion, String genero,
//        			String idioma, String publicoObjetivo, int numPaginas, boolean firmado, boolean edicionEspecial,
//        			String materialTapa, boolean saga, double precio, int stock, boolean estado, byte[] portada
                	
                	lblSeleccionado.setText("ID: " + libroSeleccionado.getId_libro() + ", TITULO: " + libroSeleccionado.getTitulo());
                    
                }
            }
        });

        // Cargar datos
        cargarTabla();
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarInv frame = new PanelGestionarInv(user);
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(new Color(153, 17, 20));
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(567, 631, 109, 27);
		contentPane.add(btnVolver);

	}
	
	private void cargarTabla() {
        model.setRowCount(0);
        LinkedList<Libro> listaLibros = LibroDTO.elegirLibros();
        for (Libro libro : listaLibros) {
            model.addRow(
            		new Object[]{
            				libro.getId_libro(),
            				libro.getTitulo(),
            				libro.getAutor(),
            				libro.getEditorial(),
            				libro.getIdioma(),
            				libro.getEstado()}
            		);
        }
    }
}
