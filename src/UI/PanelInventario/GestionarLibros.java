package UI.PanelInventario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import javax.swing.JTextField;

public class GestionarLibros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPrueba;

	/**
	 * Create the frame.
	 */
	public GestionarLibros(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPrueba = new JTextField();
		txtPrueba.setBounds(174, 10, 86, 20);
		txtPrueba.setText("prueba");
		contentPane.add(txtPrueba);
		txtPrueba.setColumns(10);
	}

}
