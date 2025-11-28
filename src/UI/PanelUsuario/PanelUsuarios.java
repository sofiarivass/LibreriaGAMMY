package UI.PanelUsuario;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import BLL.Usuario;
import DLL.UsuarioDTO;
import Repository.Validaciones;
import UI.PanelAdmin;
import UI.PanelVendedorInternacional;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class PanelUsuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Usuario usuarioSeleccionado;

	
	/**
	 * Create the frame.
	 */
	public PanelUsuarios(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Gestion de Usuarios");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitulo.setBounds(271, 11, 307, 37);
		contentPane.add(lblTitulo);

		JLabel lblSeleccionado = new JLabel("Seleccionado: ");
		lblSeleccionado.setForeground(new Color(0, 0, 128));
		lblSeleccionado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblSeleccionado.setBounds(37, 336, 774, 20);
		contentPane.add(lblSeleccionado);

		model = new DefaultTableModel(new String[] { "ID", "USUARIO", "NOMBRE", "ROL", "ESTADO" }, 0);

		contentPane.setLayout(null);

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(37, 110, 774, 216);
		contentPane.add(scrollPane);

		JLabel lblError = new JLabel();
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(new Color(159, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblError.setBounds(113, 440, 601, 43);
		contentPane.add(lblError);

		JLabel lblExito = new JLabel();
		lblExito.setHorizontalAlignment(SwingConstants.CENTER);
		lblExito.setForeground(new Color(0, 128, 0));
		lblExito.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblExito.setBounds(113, 440, 601, 43);
		contentPane.add(lblExito);

		JButton btnNuevoEmpleado = new JButton("Nuevo Empleado");
		btnNuevoEmpleado.setIcon(Validaciones.getScaledImageIcon("/img/nuevo.png", 14, 14));
		btnNuevoEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoUsuario nuevoEmpleado = new NuevoUsuario(user);
				nuevoEmpleado.setVisible(true);
				dispose();
			}
		});
		btnNuevoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevoEmpleado.setBounds(37, 386, 156, 42);
		contentPane.add(btnNuevoEmpleado);

		JButton btnDarDeBaja = new JButton("Baja/Alta Empleado");
		btnDarDeBaja.setIcon(Validaciones.getScaledImageIcon("/img/altabaja.png", 19, 19));
		btnDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblExito.setText("");

				if (usuarioSeleccionado == null) {
					lblError.setText("");
					lblError.setText("Debe seleccionar un Usuario para darlo de baja/alta");
				} else {
					if (usuarioSeleccionado.getId_usuario() == user.getId_usuario()) {
						lblError.setText("No se puede cambiar el estado de este usuario");
					} else {
						lblError.setText("");
						lblExito.setText(UsuarioDTO.estadoUsuarioJFrame(usuarioSeleccionado));
						cargarTabla();
						usuarioSeleccionado = null;
					}
				}
				// EL ERROR PASABA POR QUE ESTABA MAL EL ORDEN DEL CODIGO
				/*
				 * if (usuarioSeleccionado.getId_usuario() == user.getId_usuario()) {
				 * lblError.setText("No se puede cambiar el estado de este usuario"); }else { if
				 * (usuarioSeleccionado != null) { lblError.setText("");
				 * lblExito.setText(UsuarioDTO.estadoUsuarioJFrame(usuarioSeleccionado));
				 * cargarTabla(); usuarioSeleccionado = null; // PROBLEMA: MENSAJES SE
				 * SUPERPONEN } else { lblError.setText("");
				 * lblError.setText("Debe seleccionar un Usuario para darlo de baja/alta"); }
				 * 
				 * }
				 */
			}
		});
		btnDarDeBaja.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDarDeBaja.setBounds(206, 386, 185, 42);
		contentPane.add(btnDarDeBaja);

		JButton btnRegresar = new JButton("");
		btnRegresar.setIcon(Validaciones.getScaledImageIcon("/img/volver.png", 22, 22));
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelAdmin admin = new PanelAdmin(user);
				admin.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(798, 440, 35, 29);
		contentPane.add(btnRegresar);

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {

					LinkedList<Usuario> listaUsuarios = UsuarioDTO.mostrarUsuarios();

					for (Usuario usuario : listaUsuarios) {
						if (usuario.getId_usuario() == (int) model.getValueAt(row, 0)) {
							usuarioSeleccionado = usuario;
							break;
						}
					}

					lblSeleccionado.setText("ID: " + usuarioSeleccionado.getId_usuario() + ", USUARIO: "
							+ usuarioSeleccionado.getUsuario() + ", NOMBRE: " + usuarioSeleccionado.getNombre()
							+ ", ROL: " + usuarioSeleccionado.getFkTipoEmpleado().getTipoEmpleado() + ", ESTADO: "
							+ (usuarioSeleccionado.getEstado() == true ? "Activo" : "Inactivo"));

				}
			}
		});

		cargarTabla();

		/*
		 * private void cargarTabla() { model.setRowCount(0); LinkedList<Usuario>
		 * listaUsuarios = UsuarioDTO.mostrarUsuarios(); for (Libro libro : listaLibros)
		 * { model.addRow(new Object[] { libro.getId_libro(), libro.getTitulo(),
		 * libro.getAutor(), libro.getEditorial(), libro.getIdioma(), "$" +
		 * libro.getPrecio(), libro.getStock(), (libro.getEstado() == true ? "Activo" :
		 * "Inactivo") }); } }
		 */
	}

	private void cargarTabla() {
		model.setRowCount(0);
		LinkedList<Usuario> listaUsuarios = UsuarioDTO.mostrarUsuarios();
		for (Usuario usuario : listaUsuarios) {
			model.addRow(new Object[] { usuario.getId_usuario(), usuario.getUsuario(), usuario.getNombre(),
					usuario.getFkTipoEmpleado().getTipoEmpleado(),
					(usuario.getEstado() == true ? "Activo" : "Inactivo") });
		}
	}
}
