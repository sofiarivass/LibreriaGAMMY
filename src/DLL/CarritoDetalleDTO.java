package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import BLL.CarritoDetalle;

public class CarritoDetalleDTO {
	private static Connection con = Conexion.getInstance().getConnection();
	
	/**
	 * funcion para cargar los datos en la tabla carrito_detalle de la BD.
	 * @param carrito
	 */
	public static void cargarDetalle(CarritoDetalle carrito) {
		try {
			PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO carrito_detalle (cantidad, fk_libro, fk_carrito) VALUES (?, ?, ?)"
	            );
            statement.setInt(1, carrito.getCantidad());
            statement.setInt(2, carrito.getFkLibro().getId_libro());
            statement.setInt(3, carrito.getFkCarrito().getIdCarrito());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("carrito_detalle agregado correctamente.");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
