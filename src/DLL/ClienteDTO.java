package DLL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import BLL.Cliente;
import BLL.Libro;
import BLL.TipoEmpleado;
import BLL.Usuario;

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
	
	public static LinkedList<Cliente> mostrarClientes(){
		LinkedList<Cliente> clientes = new LinkedList<Cliente>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM cliente");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id_cliente");
            	int dni_cliente = rs.getInt("dni");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("mail");
                boolean estado= rs.getBoolean("estado");
				
				clientes.add( new Cliente(id,dni_cliente,nombre,telefono,email,estado));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientes;
		
	}
	
	
	public static LinkedList<Cliente> filtrarClientes(int filtro){
		LinkedList<Cliente> clientes = new LinkedList<Cliente>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM cliente WHERE id_cliente IN (SELECT c.fk_cliente FROM carrito c JOIN venta v ON v.fk_carrito = c.id_carrito WHERE v.fk_tipo_venta = ?)");
			stmt.setInt(1, filtro);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id_cliente");
            	int dni_cliente = rs.getInt("dni");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("mail");
                boolean estado= rs.getBoolean("estado");
				
				clientes.add( new Cliente(id,dni_cliente,nombre,telefono,email,estado));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientes;
		
	}
	
	public static Cliente clientePorID(LinkedList<Cliente> clienteDisp) {
		int id_cliente = 0;
		List<Cliente> clientes = ClienteDTO.mostrarClientes();
		
		if(clienteDisp == null) {
			String[] clienteArray = new String[clientes.size()];
			for (int i = 0; i < clienteArray.length; i++) {
				clienteArray[i] = clientes.get(i).getIdCliente() + " - " + clientes.get(i).getNombre(); 
			}
			String elegido = (String) JOptionPane.showInputDialog(null, "Elija cliente:", null, 0, null, clienteArray, clienteArray[0]);
			id_cliente = Integer.parseInt(elegido.split(" - ")[0]);
			Cliente cliente = null;
			
			try {
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");
				stmt.setInt(1, id_cliente);
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					int id = rs.getInt("id_cliente");
	            	int dni_cliente = rs.getInt("dni");
	                String nombre = rs.getString("nombre");
	                String telefono = rs.getString("telefono");
	                String email = rs.getString("mail");
	                boolean estado= rs.getBoolean("estado");
					
	                cliente = new Cliente(id,dni_cliente,nombre,telefono,email,estado);
					
				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cliente;
		}else {
			String [] clientesArray = new String[clienteDisp.size()];
			for (int i = 0; i < clientesArray.length; i++) {
				clientesArray[i] = clienteDisp.get(i).getIdCliente() + " - " + clienteDisp.get(i).getNombre();
			}
			String elegido = (String) JOptionPane.showInputDialog(null, "Elija cliente:", null, 0, null, clientesArray, clientesArray[0]);
			id_cliente = Integer.parseInt(elegido.split(" - ")[0]);
			Cliente cliente = null;
			try {
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");
				stmt.setInt(1, id_cliente);
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					
					int id = rs.getInt("id_cliente");
	            	int dni_cliente = rs.getInt("dni");
	                String nombre = rs.getString("nombre");
	                String telefono = rs.getString("telefono");
	                String email = rs.getString("mail");
	                boolean estado= rs.getBoolean("estado");
					
	                cliente = new Cliente(id,dni_cliente,nombre,telefono,email,estado);	
				
				
				} 
			}catch(Exception e) {
				e.printStackTrace();
			}
			return cliente;
		}
	}
	
	
	public static String eliminarClienteJFrame(Cliente cliente) {
		boolean nuevoEstado;
		String texto;
		if (cliente.getEstado() == true) {
			nuevoEstado = false;
			texto = "El cliente fue dado de baja correctamente";
		} else {
			nuevoEstado = true;
			texto = "El cliente fue dado de alta correctamente";
		}

		try {
			PreparedStatement statement = con.prepareStatement("UPDATE cliente SET estado = ? WHERE id_cliente = ?");
			statement.setBoolean(1, nuevoEstado);
			statement.setInt(2, cliente.getIdCliente());

			int filas = statement.executeUpdate();
			if (filas > 0) {
				System.out.println("se cambió el estado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return texto;
	}
	
//	public static Cliente buscarCliente() {
//		Cliente cliente = null;
//		
//		try {
//			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cliente WHERE id_tipo_empleado =?");
//			stmt.setInt(1, fk_cliente);
//			
//			ResultSet rs = stmt.executeQuery();
//			
//			if (rs.next()) {
//				int id_empleado = rs.getInt("id_tipo_empleado");
//				String tipoEmpleado = rs.getString("tipo_empleado");
//				
//				empleado = new TipoEmpleado(id_empleado,tipoEmpleado);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return cliente;
//	}
	
	public static Cliente clientePorID(int id) {
		Cliente cliente = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");
			stmt.setInt(1, id);

			// executequery se utiliza cuando no hay cambios en la bdd
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

            	int dni_cliente = rs.getInt("dni");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("mail");
                boolean estado= rs.getBoolean("estado");
				
                cliente = new Cliente(id,dni_cliente,nombre,telefono,email,estado);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}
}
