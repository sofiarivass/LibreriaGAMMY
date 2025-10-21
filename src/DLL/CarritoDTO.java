package DLL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;
import com.mysql.jdbc.Statement;
import BLL.Carrito;
import BLL.Cliente;

public class CarritoDTO {
	private static Connection con = Conexion.getInstance().getConnection();
	
	/**
	 * funcion para cargar los datos en la tabla carrito de la BD.
	 * @param carrito
	 * @return
	 */
	public static Carrito cargarCarrito(Carrito carrito) {
		try {
			PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO carrito (fecha, fk_cliente) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(carrito.getFecha()));
            statement.setInt(2, carrito.getFkCliente().getIdCliente());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("carrito agregado correctamente.");
            }
            
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                carrito.setIdCarrito(idGenerado);
            }
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}
	
	/**
	 * funcion para obtener todos los datos de la tabla carrito de la BD.
	 * @param cliente
	 * @return
	 */
	public static LinkedList<Carrito> obtenerCarrito(Cliente cliente) {
		LinkedList<Carrito> listaCarrito = new LinkedList<Carrito>();
		
		try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito WHERE fk_cliente = ?");
            stmt.setInt(1, cliente.getIdCliente());
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
            	int id_carrito = rs.getInt("id_carrito");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();

                listaCarrito.add(new Carrito(id_carrito,fecha,cliente));
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		if (listaCarrito.isEmpty()) {
			return null;
		} else {
			return listaCarrito;			
		}
		
	}
}
