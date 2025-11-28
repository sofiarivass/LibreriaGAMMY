package DLL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import BLL.TipoVenta;
import BLL.Venta;

public class VentasLocalDTO {
private static Connection con = Conexion.getInstance().getConnection();
	
	/**
	 * funcion para cargar una venta en la BD.
	 * @param venta
	 * @param detalles
	 */
	public static void nuevaVentaLocal(Venta venta, String detalles) {
		try {
			PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO venta (total_venta, fecha_venta, metodo_pago, moneda, estado, fk_descuento, fk_tipo_venta, fk_carrito, fk_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
	            );
			statement.setDouble(1, venta.getTotalVenta());
            statement.setDate(2, Date.valueOf(venta.getFechaVenta()));
            statement.setString(3, venta.getMetodoPago());
            statement.setString(4, venta.getMoneda());
            statement.setString(5, venta.getEstado());
            statement.setInt(6, 1); // descuento por default por ahora
            statement.setInt(7, venta.getFkTipoVenta().getIdTipoVenta());
            statement.setInt(8, venta.getFkCarrito().getIdCarrito());
            statement.setInt(9, venta.getFkUsuario().getId_usuario());

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
	public static boolean nuevaVentaLocalJframe(Venta venta) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO venta (total_venta, fecha_venta, metodo_pago, moneda, estado, fk_descuento, fk_tipo_venta, fk_carrito, fk_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
					);
			statement.setDouble(1, venta.getTotalVenta());
			statement.setDate(2, Date.valueOf(venta.getFechaVenta()));
			statement.setString(3, venta.getMetodoPago());
			statement.setString(4, venta.getMoneda());
			statement.setString(5, venta.getEstado());
			statement.setInt(6, 1); // descuento por default por ahora
			statement.setInt(7, venta.getFkTipoVenta().getIdTipoVenta());
			statement.setInt(8, venta.getFkCarrito().getIdCarrito());
			statement.setInt(9, venta.getFkUsuario().getId_usuario());
			
			int filas = statement.executeUpdate();
			if (filas > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * funcion para ver las ventas de un Cliente en especifico
	 * @param fkCarrito
	 * @return
	 */
	public static Venta verVentasLocales(int fkCarrito, TipoVenta tipo_venta) {
		Venta venta = null; 
		
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

                venta = new Venta(id_venta,totalVenta,fecha_venta,metodo_pago,moneda,estado);
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
	public static void actualizarVentaLocal(Venta venta, String detalles) {
		try {
			PreparedStatement statement = con.prepareStatement(
	                "UPDATE venta SET metodo_pago =?, moneda =?, estado =? WHERE id_venta =?"
	            );
            statement.setString(1, venta.getMetodoPago());
            statement.setString(2, venta.getMoneda());
            statement.setString(3, venta.getEstado());
            statement.setInt(4, venta.getIdVenta());
           

            int filas = statement.executeUpdate();
            if (filas > 0) {
            	JOptionPane.showMessageDialog(null, "Venta actualizada con Exito!!\n" + (detalles!=null?"\n" +detalles:""));
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * funcion para actualizar los datos de una Venta especifica en la BD con Jframe.
	 * @param venta
	 * @param detalles
	 */
	public static boolean actualizarVentaLocalJframe(Venta venta) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"UPDATE venta SET metodo_pago =?, moneda =?, estado =? WHERE id_venta =?"
					);
			statement.setString(1, venta.getMetodoPago());
			statement.setString(2, venta.getMoneda());
			statement.setString(3, venta.getEstado());
			statement.setInt(4, venta.getIdVenta());
			
			
			int filas = statement.executeUpdate();
			if (filas > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * funcion para anular una venta especifica en la BD.
	 * @param venta
	 * @param detalles
	 */
	public static void anularVentaLocal(Venta venta, String detalles) {
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
	
	/**
	 * funcion para anular una venta especifica en la BD con Jframe.
	 * @param venta
	 * @param detalles
	 */
	public static boolean anularVentaLocalJframe(Venta venta) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"UPDATE venta SET estado =? WHERE id_venta =?"
					);
			
			statement.setString(1, venta.getEstado());
			statement.setInt(2, venta.getIdVenta());
			
			
			int filas = statement.executeUpdate();
			if (filas > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
