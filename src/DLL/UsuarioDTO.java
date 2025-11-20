package DLL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import BLL.TipoEmpleado;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Statement;
import BLL.Admin;
import BLL.Libro;
import BLL.Usuario;
import Repository.Validaciones;

public class UsuarioDTO {
	private static Connection con = Conexion.getInstance().getConnection();

	public static Usuario login(String usuario, String contrasenia) {
		Usuario user = null;
		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM usuario WHERE BINARY usuario = ? AND BINARY contrasenia = ?");
				stmt.setString(1, usuario);
				stmt.setString(2, contrasenia);
			// executequery se utiliza cuando no hay cambios en la bdd
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int id_usuario = rs.getInt("id_usuario");
				String nombre = rs.getString("nombre");
				boolean estado = rs.getBoolean("estado");
				int fkEmpleado = rs.getInt("fk_tipo_empleado");
				
				// tenemos que mandar a llamar a la tabla tipo empleado para que con el fk que llega poder crear un objeto tipo empleado
				TipoEmpleado fk_tipo_empleado =  TipoEmpleado.tipoEmpleado(fkEmpleado);

				user = new Usuario(id_usuario,nombre,usuario,contrasenia,estado,fk_tipo_empleado);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/*public static boolean estadoEmpleado() {
		boolean estadoUser = true;
		try {
			PerparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE BINARY usuario = ? AND BINARY contrasenia = ?");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estadoUser;
		
	}*/
	
	/*
	public static boolean buscarUsuarioExistente (String usuario){
		boolean flag;
		LinkedList<Usuario> usuarios = UsuarioDTO.mostrarUsuarios();
		
		flag = false;
		for (Usuario userSys : usuarios) {
			if(userSys.getUsuario().equalsIgnoreCase(usuario)) {
				flag = true;
				System.out.println("El usuario esta tomado");
			}
			break;
		}while(flag);
		
		return flag;
		
	}
	*/
	public static TipoEmpleado buscarEmpleado(int fkEmpleado) {
		TipoEmpleado empleado = null;
		
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM tipo_empleado WHERE id_tipo_empleado =?");
			stmt.setInt(1, fkEmpleado);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int id_empleado = rs.getInt("id_tipo_empleado");
				String tipoEmpleado = rs.getString("tipo_empleado");
				
				empleado = new TipoEmpleado(id_empleado,tipoEmpleado);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return empleado;
	}
	
	public static LinkedList<TipoEmpleado> seleccionarTipoEmpleado() {
		LinkedList<TipoEmpleado> listaTipos = new LinkedList<TipoEmpleado>();
		TipoEmpleado empleado = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM tipo_empleado");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id_empleado = rs.getInt("id_tipo_empleado");
				String tipoEmpleado = rs.getString("tipo_empleado");
				
				empleado = new TipoEmpleado(id_empleado,tipoEmpleado);
				listaTipos.add(empleado);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTipos;
	}
	
	
	public static LinkedList<Usuario> mostrarUsuarios(){
		LinkedList<Usuario> usuario = new LinkedList<Usuario>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id_usuario = rs.getInt("id_usuario");
				String usuario_empleado = rs.getString("usuario");
				String nom_empleado = rs.getString("nombre");
				boolean estado = rs.getBoolean("estado");
				int tipo_empleado = rs.getInt("fk_tipo_empleado");
				
				TipoEmpleado empleado = buscarEmpleado(tipo_empleado);
				
				
				usuario.add(new Usuario(id_usuario, nom_empleado, usuario_empleado, estado, empleado));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario;
		
	}
	
	public static String estadoUsuarioJFrame(Usuario user) {
		boolean nuevoEstado;
		String texto;
		if (user.getEstado() == true) {
			nuevoEstado = false;
			texto = "El usuario fue dado de baja correctamente";
		} else {
			nuevoEstado = true;
			texto = "El usuario fue dado de alta correctamente";
		}

		try {
			PreparedStatement statement = con.prepareStatement("UPDATE usuario SET estado = ? WHERE id_usuario = ?");
			statement.setBoolean(1, nuevoEstado);
			statement.setInt(2, user.getId_usuario());

			int filas = statement.executeUpdate();
			if (filas > 0) {
				System.out.println("se cambió el estado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return texto;
	}
	
	public static boolean eliminarUsuarioPorID(int usuario) {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE `usuario` SET `estado`=? WHERE `id_usuario` = ?");
			stmt.setInt(1, 0);
			stmt.setInt(2, usuario);
			
			int filas = stmt.executeUpdate();
			if (filas > 0) {
				JOptionPane.showMessageDialog(null, "Usuario dio de baja correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static Usuario usuarioPorID(int id) {
		Usuario usuario = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
			stmt.setInt(1, id);

			// executequery se utiliza cuando no hay cambios en la bdd
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String usuario_empleado = rs.getString("usuario");
				String nom_empleado = rs.getString("nombre");
				boolean estado = rs.getBoolean("estado");
				int tipo_empleado = rs.getInt("fk_tipo_empleado");
				
				TipoEmpleado empleado2 = buscarEmpleado(tipo_empleado);
				
				
				usuario = new Usuario(id, nom_empleado, usuario_empleado, estado, empleado2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	/*
		int id_usuario = 0;
		List<Usuario> usuario = UsuarioDTO.mostrarUsuarios();
		
		if(j == null) {
			String[] usuarioArray = new String[usuario.size()];
			for (int i = 0; i < usuarioArray.length; i++) {
				usuarioArray[i] = usuario.get(i).getId_usuario() + " - " + usuario.get(i).getUsuario(); 
			}
			String elegido = (String) JOptionPane.showInputDialog(null, "Elija empleado:", null, 0, null, usuarioArray, usuarioArray[0]);
			id_usuario = Integer.parseInt(elegido.split(" - ")[0]);
			Usuario empleado = null;
			
			try {
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
				stmt.setInt(1, id_usuario);
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					String usuario_empleado = rs.getString("usuario");
					String nom_empleado = rs.getString("nombre");
					boolean estado = rs.getBoolean("estado");
					int tipo_empleado = rs.getInt("fk_tipo_empleado");
					
					TipoEmpleado empleado2 = buscarEmpleado(tipo_empleado);
					
					
					empleado = new Usuario(id_usuario, nom_empleado, usuario_empleado, estado, empleado2);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return empleado;
		}else {
			String [] usuariosArray = new String[j.size()];
			for (int i = 0; i < usuariosArray.length; i++) {
				usuariosArray[i] = j.get(i).getId_usuario() + " - " + j.get(i).getUsuario();
			}
			String elegido = (String) JOptionPane.showInputDialog(null, "Elija usuario:", null, 0, null, usuariosArray, usuariosArray[0]);
			id_usuario = Integer.parseInt(elegido.split(" - ")[0]);
			Usuario empleado = null;
			try {
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
				stmt.setInt(1, id_usuario);
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					String usuario_empleado = rs.getString("usuario");
					String nom_empleado = rs.getString("nombre");
					boolean estado = rs.getBoolean("estado");
					int tipo_empleado = rs.getInt("fk_tipo_empleado");
					
					TipoEmpleado empleado2 = buscarEmpleado(tipo_empleado);
					
					
					empleado = new Usuario(id_usuario, nom_empleado, usuario_empleado, estado, empleado2);
				} 
			}catch(Exception e) {
				e.printStackTrace();
			}
			return empleado;
		}
	}*/	
	
	public static boolean agregarUsuario(Usuario nuevo) {
			try {
				PreparedStatement statement = con.prepareStatement( "INSERT INTO `usuario`(`usuario`, `nombre`, `contrasenia`, `estado`, `fk_tipo_empleado`) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, nuevo.getUsuario());
				statement.setString(2, nuevo.getNombre());
				statement.setString(3, nuevo.getContrasenia());
				statement.setBoolean(4, nuevo.getEstado());
				statement.setInt(5, nuevo.getFkTipoEmpleado().getIdTipoEmpleado());

				int filas = statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
	            
				if (rs.next()) {
					int idGenerado = rs.getInt(1);
					nuevo.setId_usuario(idGenerado);
				}
				
				if (filas > 0) {
					JOptionPane.showMessageDialog(null, "Usuario agregado correctamente\n" + nuevo.toString());
					return true;
				}
			} catch (Exception e) {
			}
			
		return false;
	}
	
	
	/*
	public static void eliminarEmpleados() {
		LinkedList<Usuario> usuarios = Admin.mostrarEmpleados();
		LinkedList<Usuario> empleadosDisp = new LinkedList<Usuario>();
		if (usuarios.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay empleados para eliminar");
		}else {
			for (Usuario usuario : usuarios) {
				if(usuario.getEstado() == true) {
					empleadosDisp.add(usuario);
				}
			}
			
			if (empleadosDisp.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay empleados para eliminar");
			} else {
				Usuario seleccionado = UsuarioDTO.usuarioPorID(empleadosDisp);
				
				String confirmacion = Validaciones.menuSiNo("Seguro que desea eliminar el empleado?\n" + seleccionado.toString() + "\nEsta accion es irreversible!!", "Eliminar Empleado", null);
				if (confirmacion.equals("Si")) {
					try {
						PreparedStatement statement = con.prepareStatement ("UPDATE usuario SET  estado = ? WHERE id_usuario = ?");
						statement.setBoolean(1, false);
						statement.setInt(2, seleccionado.getId_usuario());
						
						int filas = statement.executeUpdate();
						if (filas > 0) {
							JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}

	
*/	
}
	
