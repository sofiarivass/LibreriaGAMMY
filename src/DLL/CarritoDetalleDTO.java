package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import BLL.Carrito;
import BLL.CarritoDetalle;
import BLL.Libro;

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
	
	// funcion para traer los datos de la tabla carrito_detalle
	public static LinkedList<CarritoDetalle> verDetalle(Carrito fkCarrito) {
		LinkedList<CarritoDetalle> carrito_detalle = new LinkedList<CarritoDetalle>();
		
		try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM carrito_detalle WHERE fk_carrito = ?"
            );
            stmt.setInt(1, fkCarrito.getIdCarrito());
  
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	int cantidad = rs.getInt("cantidad");
            	int fkLibro = rs.getInt("fk_libro");
            	
            	Libro libro = Libro.verLibro(fkLibro);
               
                carrito_detalle.add(new CarritoDetalle(cantidad,libro,fkCarrito));
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return carrito_detalle;
	}
}
