package DLL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import BLL.Exportacion;

public class VentasExportDTO {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static void nuevaVentaExport(Exportacion venta) {
		try {
			PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO `venta`(`total_venta`, `fecha_venta`, `metodo_pago`, `moneda`, `estado`, `origen`, `destino`, `estado_envio`, `fk_descuento`, `fk_tipo_venta`, `fk_carrito`, `fk_usuario`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
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
                System.out.println("Venta realizada correctamente.");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
