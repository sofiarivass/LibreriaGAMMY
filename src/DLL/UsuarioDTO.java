package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import BLL.TipoEmpleado;
import BLL.Usuario;

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
}
