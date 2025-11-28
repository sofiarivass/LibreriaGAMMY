package UI.PanelCliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Cliente;
import BLL.Usuario;
import DLL.ClienteDTO;
import Repository.Validaciones;
import UI.PanelVendedor;
import UI.PanelVendedorInternacional;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JTable;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.awt.Color;

import javax.swing.SwingConstants;

public class GestionarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Cliente clienteSeleccionado;

	/**
	 * Create the frame.
	 */

	public GestionarCliente(Usuario user) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 841, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("");
		if (user.getFkTipoEmpleado().getTipoEmpleado().equals("Vendedor Local")) {
			lblTitulo = new JLabel("Gestionar Clientes (minoristas)");
		} else {
			lblTitulo = new JLabel("Gestionar Clientes (mayoristas)");
		}
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(207, 17, 413, 32);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblTitulo);

		JLabel lblSeleccionado = new JLabel("Seleccionado: ");
		lblSeleccionado.setForeground(new Color(0, 0, 128));
		lblSeleccionado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblSeleccionado.setBounds(26, 336, 774, 20);
		contentPane.add(lblSeleccionado);

		model = new DefaultTableModel(new String[] { "ID", "NOMBRE", "TELÉFONO", "MAIL", "DNI", "ESTADO" }, 0);

		contentPane.setLayout(null);

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 110, 774, 216);
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Clientes cargados en el sistema:");
		lblNewLabel.setBounds(25, 84, 233, 21);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel);

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

		JButton btnVerDetalle = new JButton("Ver detalle");
		btnVerDetalle.setIcon(Validaciones.getScaledImageIcon("/img/info.png", 17, 17));
		btnVerDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clienteSeleccionado != null) {
					InfoCliente infoCliente = new InfoCliente(user, clienteSeleccionado, "ver");
					infoCliente.setVisible(true);
					dispose();
				} else {
					lblExito.setText("");
					lblError.setText("Debe seleccionar un cliente para ver su detalle");
				}
			}
		});
		btnVerDetalle.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVerDetalle.setBounds(64, 380, 190, 35);
		contentPane.add(btnVerDetalle);

		JButton btnEditarDatos = new JButton("Modificar datos");
		btnEditarDatos.setIcon(Validaciones.getScaledImageIcon("/img/editar.png", 17, 17));
		btnEditarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clienteSeleccionado != null) {
					CargarDatosCliente modificarCliente = new CargarDatosCliente(user, clienteSeleccionado);
					modificarCliente.setVisible(true);
					dispose();
				} else {
					lblExito.setText("");
					lblError.setText("Debe seleccionar un cliente para modificar sus datos");
				}

			}
		});
		btnEditarDatos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditarDatos.setBounds(318, 380, 190, 35);
		contentPane.add(btnEditarDatos);

		JButton btnDeshabilitar = new JButton("Dar de baja/alta");
		btnDeshabilitar.setIcon(Validaciones.getScaledImageIcon("/img/altabaja.png", 19, 19));
		btnDeshabilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblExito.setText("");
				if (clienteSeleccionado != null) {
					lblError.setText("");
					lblExito.setText(ClienteDTO.eliminarClienteJFrame(clienteSeleccionado));
					cargarTabla(user);
					clienteSeleccionado = null;
					// PROBLEMA: MENSAJES SE SUPERPONEN
				} else {
					lblError.setText("");
					lblError.setText("Debe seleccionar un Cliente para darlo de baja/alta");
				}

			}
		});
		btnDeshabilitar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDeshabilitar.setBounds(572, 380, 190, 35);
		contentPane.add(btnDeshabilitar);

		JLabel lblDasd = new JLabel("");
		lblDasd.setHorizontalAlignment(SwingConstants.CENTER);
		lblDasd.setForeground(new Color(159, 0, 0));
		lblDasd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblDasd.setBounds(190, 319, 275, 32);
		contentPane.add(lblDasd);

		// Acción al seleccionar fila
		table.getSelectionModel().addListSelectionListener(e -> {
			lblError.setText("");
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {

					clienteSeleccionado = ClienteDTO.clientePorID((int) model.getValueAt(row, 0));

					lblSeleccionado.setText("ID: " + clienteSeleccionado.getIdCliente() + ", NOMBRE: "
							+ clienteSeleccionado.getNombre() + ", TELÉFONO: " + clienteSeleccionado.getTelefono()
							+ ", MAIL: " + clienteSeleccionado.getMail() + ", DNI: " + clienteSeleccionado.getDni()
							+ ", ESTADO: " + (clienteSeleccionado.getEstado() == true ? "Activo" : "Inactivo"));

				}
			}
		});

		// Cargar datos
		cargarTabla(user);
		if (cargarTabla(user) == false) {
			JLabel lblActualmenteNoHay = new JLabel("Actualmente no hay clientes para mostrar");
			lblActualmenteNoHay.setForeground(new Color(255, 0, 0));
			lblActualmenteNoHay.setFont(new Font("Tahoma", Font.ITALIC, 13));
			lblActualmenteNoHay.setBounds(245, 84, 289, 21);
			contentPane.add(lblActualmenteNoHay);
		}
		
		JButton btnRegresar = new JButton("");
		btnRegresar.setIcon(Validaciones.getScaledImageIcon("/img/volver.png", 22, 22));
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.getFkTipoEmpleado().getTipoEmpleado().equals("Vendedor Local")) {
					PanelVendedor frame = new PanelVendedor(user);
					frame.setVisible(true);
				} else {
					PanelVendedorInternacional frame = new PanelVendedorInternacional(user);
					frame.setVisible(true);
				}
				dispose();
			}
		});
		btnRegresar.setBounds(775, 485, 35, 29);
		contentPane.add(btnRegresar);

	}

	private boolean cargarTabla(Usuario user) {
		model.setRowCount(0);
		LinkedList<Cliente> listaClientes;
		if (user.getFkTipoEmpleado().getTipoEmpleado().equals("Vendedor Local")) {
			listaClientes = ClienteDTO.filtrarClientes(1);
		} else {
			listaClientes = ClienteDTO.filtrarClientes(2);
		}
		for (Cliente cliente : listaClientes) {
			model.addRow(new Object[] { cliente.getIdCliente(), cliente.getNombre(), cliente.getTelefono(),
					cliente.getMail(), cliente.getDni(), (cliente.getEstado() == true ? "Activo" : "Inactivo") });
		}
		if (model.getRowCount() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
