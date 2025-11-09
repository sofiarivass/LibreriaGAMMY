package DLL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import BLL.Exportacion;
import BLL.TipoVenta;

public class VentasExportDTO {
	private static Connection con = Conexion.getInstance().getConnection();
	
	/**
	 * funcion para cargar una venta en la BD.
	 * @param venta
	 * @param detalles
	 */
	public static void nuevaVentaExport(Exportacion venta, String detalles) {
		try {
			PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO venta (total_venta, fecha_venta, metodo_pago, moneda, estado, origen, destino, estado_envio, fk_descuento, fk_tipo_venta, fk_carrito, fk_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	            );
			statement.setDouble(1, venta.getTotalVenta());
            statement.setDate(2, Date.valueOf(venta.getFechaVenta()));
            statement.setString(3, venta.getMetodoPago());
            statement.setString(4, venta.getMoneda());
            statement.setString(5, venta.getEstado());
            statement.setString(6, venta.getOrigen());
            statement.setString(7, venta.getDestino());
            statement.setString(8, venta.getEstadoEnvio());
            statement.setInt(9, 1); // descuento por default por ahora
            statement.setInt(10, venta.getFkTipoVenta().getIdTipoVenta());
            statement.setInt(11, venta.getFkCarrito().getIdCarrito());
            statement.setInt(12, venta.getFkUsuario().getId_usuario());

            int filas = statement.executeUpdate();
            if (filas > 0) {
            	JOptionPane.showMessageDialog(null, "Venta Realizada con Exito!!\n\n" + detalles);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * funcion para cargar una venta en la BD.
	 * @param venta
	 * @param detalles
	 */
	public static boolean nuevaVentaExportJframe(Exportacion venta, String detalles) {
		boolean flag = false;
		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO venta (total_venta, fecha_venta, metodo_pago, moneda, estado, origen, destino, estado_envio, fk_descuento, fk_tipo_venta, fk_carrito, fk_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
					);
			statement.setDouble(1, venta.getTotalVenta());
			statement.setDate(2, Date.valueOf(venta.getFechaVenta()));
			statement.setString(3, venta.getMetodoPago());
			statement.setString(4, venta.getMoneda());
			statement.setString(5, venta.getEstado());
			statement.setString(6, venta.getOrigen());
			statement.setString(7, venta.getDestino());
			statement.setString(8, venta.getEstadoEnvio());
			statement.setInt(9, 1); // descuento por default por ahora
			statement.setInt(10, venta.getFkTipoVenta().getIdTipoVenta());
			statement.setInt(11, venta.getFkCarrito().getIdCarrito());
			statement.setInt(12, venta.getFkUsuario().getId_usuario());
			
			int filas = statement.executeUpdate();
			if (filas > 0) {
//				JOptionPane.showMessageDialog(null, "Venta Realizada con Exito!!\n\n" + detalles);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * funcion para ver las ventas de un Cliente en especifico
	 * @param fkCarrito
	 * @return
	 */
	public static Exportacion verVentas(int fkCarrito, TipoVenta tipo_venta) {
		Exportacion venta = null; 
		
		try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM venta WHERE fk_carrito = ? AND fk_tipo_venta = ?"
            );
            stmt.setInt(1, fkCarrito);
            stmt.setInt(2, tipo_venta.getIdTipoVenta());
  
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	int id_venta = rs.getInt("id_venta");
            	double totalVenta = rs.getDouble("total_venta");
                LocalDate fecha_venta = rs.getDate("fecha_venta").toLocalDate();
                String metodo_pago = rs.getString("metodo_pago");
                String moneda = rs.getString("moneda");
                String estado = rs.getString("estado");
                String origen = rs.getString("origen");
                String destino = rs.getString("destino");
                String estado_envio = rs.getString("estado_envio");

                venta = new Exportacion(id_venta,totalVenta,fecha_venta,metodo_pago,moneda,estado,origen,destino,estado_envio);
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return venta;
	}
	
	/**
	 * funcion para actualizar los datos de una Venta especifica en la BD.
	 * @param venta
	 * @param detalles
	 */
	public static void actualizarVentaExport(Exportacion venta, String detalles) {
		try {
			PreparedStatement statement = con.prepareStatement(
	                "UPDATE venta SET metodo_pago =?, moneda =?, estado =?, origen =?, destino =? WHERE id_venta =?"
	            );
            statement.setString(1, venta.getMetodoPago());
            statement.setString(2, venta.getMoneda());
            statement.setString(3, venta.getEstado());
            statement.setString(4, venta.getOrigen());
            statement.setString(5, venta.getDestino());
            statement.setInt(6, venta.getIdVenta());
           

            int filas = statement.executeUpdate();
            if (filas > 0) {
            	JOptionPane.showMessageDialog(null, "Venta actualizada con Exito!!\n" + (detalles!=null?"\n" +detalles:""));
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * funcion para anular una venta especifica en la BD.
	 * @param venta
	 * @param detalles
	 */
	public static void anularVentaExport(Exportacion venta, String detalles) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"UPDATE venta SET estado =? WHERE id_venta =?"
					);
	
			statement.setString(1, venta.getEstado());
			statement.setInt(2, venta.getIdVenta());
			
			
			int filas = statement.executeUpdate();
			if (filas > 0) {
				JOptionPane.showMessageDialog(null, "Venta anulada con Exito!!\n" + (detalles!=null?"\n" +detalles:""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
