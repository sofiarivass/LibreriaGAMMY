package DLL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import BLL.CarritoDetalle;

public class HistorialComprasDTO {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static void cargarHistorial(CarritoDetalle carrito) {
		try {
			PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO historial_compras (fk_libro, fk_cliente, fecha) VALUES (?, ?, ?)"
	            );
            statement.setInt(1, carrito.getFkLibro().getIsbn());
            statement.setInt(2, carrito.getFkCarrito().getFkCliente().getIdCliente());
            statement.setDate(3, Date.valueOf(carrito.getFkCarrito().getFecha()));

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("historial de compras agregado correctamente.");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
