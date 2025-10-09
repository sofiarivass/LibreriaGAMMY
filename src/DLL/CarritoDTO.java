package DLL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import BLL.Carrito;
import BLL.Cliente;

public class CarritoDTO {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static void cargarCarrito(Carrito carrito) {
		try {
			PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO carrito (fecha, fk_cliente) VALUES (?, ?)"
	            );
            statement.setDate(1, Date.valueOf(carrito.getFecha()));
            statement.setInt(2, carrito.getFkCliente().getIdCliente());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("carrito agregado correctamente.");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Carrito obtenerCarrito(Cliente cliente) {
		Carrito carrito = null;
		
		try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM carrito WHERE fk_cliente = ?"
            );
            stmt.setInt(1, cliente.getIdCliente());
  
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	int id_carrito = rs.getInt("id_carrito");
                LocalDate fecha = rs.getDate("nombre").toLocalDate();

                carrito = new Carrito(id_carrito,fecha,cliente);
                }
       
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return carrito;
	}
}
