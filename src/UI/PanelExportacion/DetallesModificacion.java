package UI.PanelExportacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Exportacion;
import BLL.Usuario;
import DLL.VentasExportDTO;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesModificacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetallesModificacion frame = new DetallesModificacion(null,null,null);
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
	public DetallesModificacion(Usuario user, Exportacion venta, String datosPasados) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDetallesModificadosDe = new JLabel("Detalles de las Modificaciones");
		lblDetallesModificadosDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetallesModificadosDe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDetallesModificadosDe.setBounds(98, 10, 325, 28);
		contentPane.add(lblDetallesModificadosDe);
		
		JLabel lblDatos = new JLabel(datosVenta(venta));
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setVerticalAlignment(SwingConstants.TOP);
		lblDatos.setBounds(25, 266, 471, 113);
		contentPane.add(lblDatos);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarVenta modificar = new ModificarVenta(user,venta,datosPasados,false);
				modificar.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(10, 405, 85, 21);
		contentPane.add(btnVolver);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentasExportDTO.actualizarVentaExportJframe(venta);
				ModificarVenta modificar = new ModificarVenta(user,null,null,true);
				modificar.setVisible(true);
				dispose();
			}
		});
		btnConfirmar.setBounds(415, 405, 96, 21);
		contentPane.add(btnConfirmar);
		
		JLabel lblAviso = new JLabel("Revise los cambios antes de confirmar!!");
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setForeground(new Color(20, 68, 235));
		lblAviso.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAviso.setBounds(123, 409, 275, 13);
		contentPane.add(lblAviso);
		
		JLabel lblDatosPasados = new JLabel(datosPasados);
		lblDatosPasados.setVerticalAlignment(SwingConstants.TOP);
		lblDatosPasados.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosPasados.setBounds(25, 96, 471, 113);
		contentPane.add(lblDatosPasados);
		
		JLabel lblSubtitulo = new JLabel("Datos Anteriores");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSubtitulo.setBounds(176, 58, 168, 28);
		contentPane.add(lblSubtitulo);
		
		JLabel lblSubtitulo2 = new JLabel("Datos Modificados");
		lblSubtitulo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSubtitulo2.setBounds(176, 219, 168, 28);
		contentPane.add(lblSubtitulo2);
	}
	
	public String datosVenta(Exportacion venta) {
		String datos =
			    "<html>"
			        + "<table cellspacing='20'>" // separa un poco las columnas
			        + "<tr>"
			            + "<td style='padding-right: 40px;'><b>Método de Pago:</b> " + venta.getMetodoPago() + ".</td>"
			            + "<td><b>Origen:</b> " + venta.getOrigen() + ".</td>"
			        + "</tr>"
			        + "<tr>"
			            + "<td style='padding-right: 40px;'><b>Tipo de Moneda:</b> " + venta.getMoneda() + ".</td>"
			            + "<td><b>Destino:</b> " + venta.getDestino() + ".</td>"
			        + "</tr>"
			        + "</table>"
			    + "</html>";
		return datos;
	}
}
