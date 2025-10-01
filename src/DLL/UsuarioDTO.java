package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import BLL.Usuario;

public class UsuarioDTO {

	private static Connection con = Conexion.getInstance().getConnection();

	public static Usuario Login(String usuario, String contrasenia) {
		Usuario user = null;
		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM usuario WHERE usuario = ? AND contrasenia = ?");
			stmt.setString(1, usuario);
			stmt.setString(2, contrasenia);
			// executequery se utiliza cuando no hay cambios en la bdd
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id_usuario");
				String tipo = rs.getString("estado");

				user = new Usuario(usuario, contrasenia, tipo, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
