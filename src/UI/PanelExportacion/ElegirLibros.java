package UI.PanelExportacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BLL.Cliente;
import BLL.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;

public class ElegirLibros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ElegirLibros(Usuario user,Cliente cliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 30, 800, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Datos de la Venta");
		lblTitulo.setBounds(300, 10, 185, 32);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblTitulo);
		
		JLabel lblCliente = new JLabel("Cliente: " + cliente.toString());
		lblCliente.setBounds(29, 57, 727, 13);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblCliente);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(16, 79, 754, 634);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Elegir Libros", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
	}
}
