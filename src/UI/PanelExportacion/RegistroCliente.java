package UI.PanelExportacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Cliente;
import BLL.Usuario;
import DLL.ClienteDTO;
import Repository.Validaciones;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegistroCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textTelefono;
	private JTextField textMail;
	private JTextField textDni;
	private Cliente cliente;

	/**
	 * Create the frame.
	 */
	public RegistroCliente(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registrar Cliente");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(83, 14, 220, 36);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(71, 68, 60, 22);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(152, 70, 145, 19);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblTelefono = new JLabel("telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefono.setBounds(71, 154, 60, 22);
		contentPane.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(152, 156, 145, 19);
		contentPane.add(textTelefono);
		
		JLabel lblMail = new JLabel("mail:");
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMail.setBounds(71, 199, 60, 22);
		contentPane.add(lblMail);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(152, 201, 145, 19);
		contentPane.add(textMail);
		
		JLabel lblDni = new JLabel("dni:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDni.setBounds(71, 109, 60, 22);
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBounds(152, 111, 145, 19);
		contentPane.add(textDni);
		
		JLabel lblErrorNombre = new JLabel("");
		lblErrorNombre.setForeground(new Color(255, 0, 0));
		lblErrorNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorNombre.setBounds(128, 90, 226, 13);
		contentPane.add(lblErrorNombre);
		
		JLabel lblErrorDni = new JLabel("");
		lblErrorDni.setForeground(Color.RED);
		lblErrorDni.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorDni.setBounds(128, 131, 226, 13);
		contentPane.add(lblErrorDni);
		
		JLabel lblErrorTelefono = new JLabel("");
		lblErrorTelefono.setForeground(Color.RED);
		lblErrorTelefono.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorTelefono.setBounds(128, 176, 226, 13);
		contentPane.add(lblErrorTelefono);
		
		JLabel lblErrorMail = new JLabel("");
		lblErrorMail.setForeground(Color.RED);
		lblErrorMail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblErrorMail.setBounds(128, 222, 226, 13);
		contentPane.add(lblErrorMail);
		
		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblError.setBounds(80, 245, 226, 13);
		contentPane.add(lblError);
		
		textNombre.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	lblErrorNombre.setText("");
		    }
		});
		
		textDni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblErrorDni.setText("");
			}
		});
		
		textTelefono.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblErrorTelefono.setText("");
			}
		});
		
		textMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblErrorMail.setText("");
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaExportacion venta = new NuevaExportacion(user);
				venta.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(10, 292, 85, 21);
		contentPane.add(btnVolver);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarCliente(lblError,lblErrorNombre,lblErrorDni,lblErrorTelefono,lblErrorMail,user);
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRegistrar.setBounds(152, 261, 97, 29);
		contentPane.add(btnRegistrar);
	}
	
	//funcion registrar cliente
	public void registrarCliente(JLabel error, JLabel errorN, JLabel errorD, JLabel errorT, JLabel errorM, Usuario user) {
		String texto;
		boolean flag = Validaciones.validarDniJframe(textDni.getText());
		
		if (textNombre.getText().isEmpty() && textDni.getText().isEmpty() && textTelefono.getText().isEmpty() && textMail.getText().isEmpty()) {
			errorN.setText("No puede dejar Vacio el Nombre!!");			
			errorD.setText("No puede dejar Vacio el Dni!!");
			errorT.setText("No puede dejar Vacio el Telefono!!");
			errorM.setText("No puede dejar Vacio el Mail!!");			
		} else if (textNombre.getText().isEmpty()) {
			errorN.setText("No puede dejar Vacio el Nombre!!");			
		} else if (textDni.getText().isEmpty()){
			errorD.setText("No puede dejar Vacio el Dni!!");
		} else if (textTelefono.getText().isEmpty()) {
			errorT.setText("No puede dejar Vacio el Telefono!!");
		} else if (textMail.getText().isEmpty()) {
			errorM.setText("No puede dejar Vacio el Mail!!");			
		} else {
			if (flag != false) {
				errorD.setText("Número de Dni invalido!!");
				textDni.setText("");
			} else {
				
				texto = Validaciones.validarIntJframe(textTelefono.getText());
				
				switch (texto) {
				case "letra":
					errorT.setText("No puede ingresar Letras!!");
					break;
				case "tamaño":
					errorT.setText("Solo puede ingresar 9 digitos!!");
					break;
				case "ok":
					
					flag = Validaciones.validarMail(textMail.getText());
					
					if (flag) {
						cliente = Cliente.buscarClienteJframe(textDni.getText());
						
						if (cliente != null) {
							error.setText("El Cliente Ya se encuentra Registrado!!");
							errorD.setText("El dni ya esta Registrado!!");
						} else {
							int dni = Integer.parseInt(textDni.getText());
							
							cliente = ClienteDTO.registrarClienteJframe(new Cliente(dni,textNombre.getText(),textTelefono.getText(),textMail.getText(),true)); 
							if (cliente != null) {
								ElegirLibros libros = new ElegirLibros(user,cliente);
								libros.setVisible(true);
								dispose();						
							} else {
								error.setText("No se pudo Registrar al cliente!!");
							}
						}						
					} else {
						errorM.setText("Formato mail incorrecto!!");
					}
					break;
				}
			}
		}
	}
}
