package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import BLL.Cliente;

public class ClienteDTO {
	private static Connection con = Conexion.getInstance().getConnection();

	/**
	 * funcion para buscar al cliente en la BD por el DNI.
	 * @param dni
	 * @return
	 */
	public static Cliente buscarCliente(int dni) {
		Cliente encontrado = null;
		
		try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM cliente WHERE dni = ?");
            stmt.setInt(1, dni);
  
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	int id = rs.getInt("id_cliente");
            	int dni_cliente = rs.getInt("dni");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("mail");
                boolean estado= rs.getBoolean("estado");

                encontrado = new Cliente(id,dni_cliente,nombre,telefono,email,estado);
                }
        } catch (Exception e) {
        	
        }
		return encontrado;
	}
	
	// funcion para traer todos los clientes de la BD.
	public static LinkedList<Cliente> consultarClientes() {
		LinkedList<Cliente> listaClientes = new LinkedList<Cliente>();
		
		try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM cliente");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String mail = rs.getString("mail");
                int dni = rs.getInt("dni");
                boolean estado= rs.getBoolean("estado");
                
                listaClientes.add(new Cliente(id_cliente,dni,nombre,telefono,mail,estado));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return listaClientes;
	}
	
	/**
	 * funcion para registrar un nuevo Cliente en la BD.
	 * @param cliente
	 * @return
	 */
	public static boolean registrarCliente(Cliente cliente) {
		boolean flag = true;
		LinkedList<Cliente> listaClientes = consultarClientes();
		
		for (Cliente cliente2 : listaClientes) {
			if (cliente2.getNombre().equalsIgnoreCase(cliente.getNombre()) || cliente2.getMail().equals(cliente.getMail())) {
				flag = false;
				break;
			}
		}
		
		if (flag) {
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
					JOptionPane.showMessageDialog(null, "Cliente registrado correctamente!!");
				}
			} catch (Exception e) {
			}			
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "El Cliente " + cliente.getNombre() + ", Ya existe!!");
			return false;			
		}
	}
	
	
	public static boolean eliminarClientePorID(int cliente) {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE `cliente` SET `estado`=? WHERE `id_cliente` = ?");
			stmt.setInt(1, 0);
			stmt.setInt(2, cliente);
			
			int filas = stmt.executeUpdate();
			if (filas > 0) {
				JOptionPane.showMessageDialog(null, "cliente dio de baja correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
