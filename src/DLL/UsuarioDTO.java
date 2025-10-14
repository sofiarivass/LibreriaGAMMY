package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.sun.tools.javac.util.List;

import BLL.Usuario;

public class UsuarioDTO {
	private static Connection con = Conexion.getInstance().getConnection();

	public static Usuario login(String usuario, String contrasenia) {
		Usuario user = null;
		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM usuario WHERE usuario = ? AND contrasenia = ?");
			stmt.setString(1, usuario);
			stmt.setString(2, contrasenia);
			// executequery se utiliza cuando no hay cambios en la bdd
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int id_usuario = rs.getInt("id_usuario");
				String nombre = rs.getString("nombre");
				boolean estado = rs.getBoolean("estado");
				int fk_tipo_empleado = rs.getInt("fk_tipo_empleado");

				user = new Usuario(id_usuario, usuario, nombre, contrasenia, estado, fk_tipo_empleado);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
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
				
				usuario.add(new Usuario(id_usuario, usuario_empleado, nom_empleado, estado, tipo_empleado));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuario;
		
	}
	
	public static Usuario usuarioPorID() {
		int id_usuario = 0;
		List<Usuario> usuario = UsuarioDTO.mostrarUsuarios();
		
		String[] usuarioArray = new String[usuario.size()];
		for (int i = 0; i < usuarioArray.length; i++) {
			usuarioArray[i] = usuario.get(i).getId_usuario() + " - " + usuario.get(i).getNombre(); 
		}
		String elegido = (String) JOptionPane.showInputDialog(null, "Elija empleado:", null, 0, null, usuarioArray, usuarioArray[0]);
		id_usuario = Integer.parseInt(elegido.split(" - ")[0]);
		Usuario usuario = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
			stmt.setInt(1, id_usuario);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int id_user = rs.getInt("id_usuario");
				String usuario_empleado = rs.getString("usuario");
				String nom_empleado = rs.getString("nombre");
				boolean estado = rs.getBoolean("estado");
				int tipo_empleado = rs.getInt("fk_tipo_empleado");
				
				usuario = new Usuario(id_usuario, usuario_empleado, nom_empleado, estado, tipo_empleado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
}
