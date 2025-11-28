package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Repository.Validaciones;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class Informacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Informacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titulo = new JLabel("Sistema interno de Librería GAMMY");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 22, 434, 31);
		contentPane.add(titulo);

		JLabel version = new JLabel("Versión 5.5");
		version.setForeground(new Color(102, 102, 102));
		version.setHorizontalAlignment(SwingConstants.CENTER);
		version.setFont(new Font("Tahoma", Font.ITALIC, 16));
		version.setBounds(0, 53, 434, 31);
		contentPane.add(version);

		JLabel desarrolladores = new JLabel("Desarrollado por:");
		desarrolladores.setHorizontalAlignment(SwingConstants.CENTER);
		desarrolladores.setForeground(new Color(0, 0, 0));
		desarrolladores.setFont(new Font("Tahoma", Font.BOLD, 16));
		desarrolladores.setBounds(0, 95, 434, 37);
		contentPane.add(desarrolladores);

		JLabel nombre1 = new JLabel("- Aldeir Aponte");
		nombre1.setHorizontalAlignment(SwingConstants.CENTER);
		nombre1.setForeground(Color.BLACK);
		nombre1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nombre1.setBounds(0, 131, 434, 37);
		contentPane.add(nombre1);

		JLabel nombre2 = new JLabel("- Juan Caamaño Pagniez");
		nombre2.setHorizontalAlignment(SwingConstants.CENTER);
		nombre2.setForeground(Color.BLACK);
		nombre2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nombre2.setBounds(0, 161, 434, 37);
		contentPane.add(nombre2);

		JLabel nombre3 = new JLabel("- Mariano Dominguez");
		nombre3.setHorizontalAlignment(SwingConstants.CENTER);
		nombre3.setForeground(Color.BLACK);
		nombre3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nombre3.setBounds(0, 191, 434, 37);
		contentPane.add(nombre3);

		JLabel nombre4 = new JLabel("- Sofía Rivas");
		nombre4.setHorizontalAlignment(SwingConstants.CENTER);
		nombre4.setForeground(Color.BLACK);
		nombre4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nombre4.setBounds(0, 221, 434, 37);
		contentPane.add(nombre4);

		JLabel copyright = new JLabel("© DaVinci 2025");
		copyright.setHorizontalAlignment(SwingConstants.CENTER);
		copyright.setForeground(new Color(102, 102, 102));
		copyright.setFont(new Font("Tahoma", Font.PLAIN, 12));
		copyright.setBounds(132, 273, 170, 31);
		contentPane.add(copyright);

		JButton btnVolver = new JButton("");
		btnVolver.setIcon(Validaciones.getScaledImageIcon("/img/volver.png", 22, 22));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main frame = new Main(null);
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(388, 284, 36, 27);
		contentPane.add(btnVolver);
	}
}
