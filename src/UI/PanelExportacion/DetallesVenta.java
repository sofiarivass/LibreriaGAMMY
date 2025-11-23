package UI.PanelExportacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesVenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetallesVenta frame = new DetallesVenta(null, null, null);
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
	public DetallesVenta(String datosVenta, String datosPago, Usuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Detalles de la Venta");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setBounds(194, 62, 187, 28);
		contentPane.add(lblTitulo);
		
		JLabel lblMensajeExito = new JLabel("Venta Realizada con Exito!!");
		lblMensajeExito.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajeExito.setForeground(new Color(28, 157, 21));
		lblMensajeExito.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblMensajeExito.setBounds(103, 15, 369, 28);
		contentPane.add(lblMensajeExito);
		
		JLabel lblDatosVenta = new JLabel(datosVenta);
		lblDatosVenta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDatosVenta.setVerticalAlignment(SwingConstants.TOP);
		lblDatosVenta.setBounds(23, 101, 529, 162);
		contentPane.add(lblDatosVenta);
		
		JLabel lblDatosPago = new JLabel(datosPago);
		lblDatosPago.setVerticalAlignment(SwingConstants.TOP);
		lblDatosPago.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDatosPago.setBounds(23, 305, 529, 153);
		contentPane.add(lblDatosPago);
		
		JButton btnBotonAceptar = new JButton("Aceptar");
		btnBotonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelGestionarExport gestionExport = new PanelGestionarExport(user);
				gestionExport.setVisible(true);
				dispose();
			}
		});
		btnBotonAceptar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBotonAceptar.setBounds(240, 468, 96, 35);
		contentPane.add(btnBotonAceptar);
	}
}
