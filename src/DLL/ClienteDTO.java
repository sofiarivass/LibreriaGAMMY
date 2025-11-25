package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import BLL.Cliente;

public class ClienteDTO {
	private static Connection con = Conexion.getInstance().getConnection();

	/**
	 * funcion para buscar al cliente en la BD por el DNI.
	 * 
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
				boolean estado = rs.getBoolean("estado");

				encontrado = new Cliente(id, dni_cliente, nombre, telefono, email, estado);
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
				boolean estado = rs.getBoolean("estado");

				listaClientes.add(new Cliente(id_cliente, dni, nombre, telefono, mail, estado));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaClientes;
	}

	/**
	 * funcion para registrar un nuevo Cliente en la BD.
	 * 
	 * @param cliente
	 * @return
	 */
	public static boolean registrarCliente(Cliente cliente) {
		boolean flag = true;
		LinkedList<Cliente> listaClientes = consultarClientes();

		for (Cliente cliente2 : listaClientes) {
			if (cliente2.getNombre().equalsIgnoreCase(cliente.getNombre())
					|| cliente2.getMail().equals(cliente.getMail())) {
				flag = false;
				break;
			}
		}

		if (flag) {
			try {
				PreparedStatement statement = con
						.prepareStatement("INSERT INTO cliente (nombre, telefono, mail, dni) VALUES (?, ?, ?, ?)");
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

	/**
	 * funcion para registrar un nuevo Cliente en la BD con Jframe.
	 * 
	 * @param cliente
	 * @return
	 */
	public static Cliente registrarClienteJframe(Cliente cliente) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO cliente (nombre, telefono, mail, dni, estado) VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, cliente.getNombre());
			statement.setString(2, cliente.getTelefono());
			statement.setString(3, cliente.getMail());
			statement.setInt(4, cliente.getDni());
			statement.setBoolean(5, cliente.getEstado());

			int filas = statement.executeUpdate();
			if (filas > 0) {
				JOptionPane.showMessageDialog(null, "cliente dio de baja correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cliente;
	}

	public static LinkedList<Cliente> mostrarClientes() {
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
				boolean estado = rs.getBoolean("estado");

				clientes.add(new Cliente(id, dni_cliente, nombre, telefono, email, estado));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clientes;

	}

	public static LinkedList<Cliente> filtrarClientes(int filtro) {
		LinkedList<Cliente> clientes = new LinkedList<Cliente>();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT * FROM cliente WHERE id_cliente IN (SELECT c.fk_cliente FROM carrito c JOIN venta v ON v.fk_carrito = c.id_carrito WHERE v.fk_tipo_venta = ?)");
			stmt.setInt(1, filtro);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_cliente");
				int dni_cliente = rs.getInt("dni");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String email = rs.getString("mail");
				boolean estado = rs.getBoolean("estado");

				clientes.add(new Cliente(id, dni_cliente, nombre, telefono, email, estado));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clientes;

	}

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
				boolean estado = rs.getBoolean("estado");

				cliente = new Cliente(id, dni_cliente, nombre, telefono, email, estado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
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
//				System.out.println("Cliente Registrado correctamente!!");

			}
		} catch (Exception e) {
//			System.out.println("no se pudo registrar al cliente!! " + e);
		}
		return texto;
	}

	public static boolean modificarCliente(Cliente cliente) {
		boolean flag = true;
		Cliente coincidencia = null;
		for (Cliente elemento : ClienteDTO.mostrarClientes()) {
			System.out.println(
					"Comparando elemento.id=" + elemento.getIdCliente() + " con cliente.id=" + cliente.getIdCliente());
			if (elemento.getDni() == cliente.getDni() || elemento.getTelefono() == cliente.getTelefono() || elemento.getMail() == cliente.getMail() && elemento.getIdCliente() != cliente.getIdCliente()) {
				flag = false;
				coincidencia = elemento;
				break;
			}
		}
		if (flag) {
			try {
				PreparedStatement statement;

				statement = con.prepareStatement(
						"UPDATE cliente SET nombre=?, telefono=? ,mail=?,dni=?,estado=? WHERE id_cliente=?");

				statement.setString(1, cliente.getNombre());
				statement.setString(2, cliente.getTelefono());
				statement.setString(3, cliente.getMail());
				statement.setInt(4, cliente.getDni());
				statement.setBoolean(5, cliente.getEstado());
				statement.setInt(6, cliente.getIdCliente());

				int filas = statement.executeUpdate();
				if (filas > 0) {
					System.out.println("Cliente editado correctamente");
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			System.out.println("Ya hay un cliente con las mismas caracteristicas cargado en el sistema: "
					+ coincidencia.toString());
			return false;
		}
	}

}
