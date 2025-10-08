package DLL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import BLL.Usuario;
import BLL.VendedorInternacional;

public class VentasExportDTO {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static void nuevaVentaExport(Usuario user) {
		
		double totalVenta;
		LocalDate fecha = LocalDate.now();
		String metodoPago, moneda, estado, origen, destino, estadoEnvio;
		
		try {
			PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO `venta`(`total_venta`, `fecha_venta`, `metodo_pago`, `moneda`, `estado`, `origen`, `destino`, `estado_envio`, `fk_descuento`, `fk_tipo_venta`, `fk_carrito`, `fk_usuario`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	            );
			statement.setDouble(1, totalVenta);
            statement.setDate(2, Date.valueOf(fecha));
            statement.setString(3, metodoPago);
            statement.setString(4, moneda);
            statement.setString(5, estado);
            statement.setString(6, origen);
            statement.setString(7, destino);
            statement.setString(8, estadoEnvio);
            statement.setInt(9, 1);
            statement.setInt(10, 2);
            statement.setInt(11, 1);
            statement.setInt(12, user.getFk_tipo_empleado());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Venta realizada correctamente.");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
