package UI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BLL.Usuario;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main(Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (user == null) {
					Login frame = new Login();
					frame.setVisible(true);
					dispose();
				}
			}
		});
		btnLogin.setBounds(177, 323, 157, 35);
		contentPane.add(btnLogin);

		JButton btnInfo = new JButton("Información");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Informacion frame = new Informacion();
				frame.setVisible(true);
				dispose();
			}
		});
		btnInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnInfo.setBounds(177, 368, 157, 35);
		contentPane.add(btnInfo);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(153, 17, 20));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalir.setBounds(177, 438, 157, 28);
		contentPane.add(btnSalir);

		JLabel lblNewLabel = new JLabel("¡Bienvenido al sistema de Librería GAMMY!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 216, 491, 44);
		contentPane.add(lblNewLabel);

		JLabel lblPorFavorElija = new JLabel("Por favor elija una opción");
		lblPorFavorElija.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorFavorElija.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPorFavorElija.setBounds(72, 255, 366, 44);
		contentPane.add(lblPorFavorElija);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Main.class.getResource("/img/logo.png")));
		lblLogo.setBounds(0, 6, 510, 220);
		contentPane.add(lblLogo);

		if (user != null) {
			int tipo_empleado = user.getFkTipoEmpleado().getIdTipoEmpleado();
			System.out.println(tipo_empleado);
			switch (tipo_empleado) {
			// MENU ADMIN
			case 1:
				PanelAdmin admin = new PanelAdmin(user);
				admin.setVisible(true);
				dispose();
				break;
			// MENU VENDEDOR
			case 2:
//				MenuVendedor.Menu(user);
				
				PanelVendedor vendedor = new PanelVendedor(user);
				vendedor.setVisible(true);
				dispose();
//				MenuVendedorInternacional.Menu(user);
				break;
			// MENU VENDEDOR INTERNACIONAL
			case 3:
//				PanelVendedorInternacional vendedorInt = new PanelVendedorInternacional(user);
//				vendedorInt.setVisible(true);
//				dispose();
//				MenuVendedorInternacional.Menu(user);
				break;
			}
		}
	}

}
