package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import BLL.Cliente;

public class ClienteDTO {
	private static Connection con = Conexion.getInstance().getConnection();

	public static Cliente buscarCliente(int dni) {
		Cliente encontrado = null;
		try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM cliente WHERE dni = ?"
            );
            stmt.setInt(1, dni);
  
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	int id = rs.getInt("id_cliente");
            	int dni_cliente = rs.getInt("dni");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                encontrado = new Cliente(id,dni_cliente,nombre,telefono,email);
                }
       
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return encontrado;
	}
	
	public static boolean registrarCliente(Cliente cliente) {
		try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO cliente (nombre, telefono, mail, dni) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getTelefono());
            statement.setString(3, cliente.getMail());
            statement.setInt(4, cliente.getDni());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Cliente creado correctamente.");
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
           	JOptionPane.showMessageDialog(null, "Cliente con mail ya creado");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
		return true;
	}
	
	
}
