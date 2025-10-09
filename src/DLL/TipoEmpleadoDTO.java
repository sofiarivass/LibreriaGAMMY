package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import BLL.TipoEmpleado;

public class TipoEmpleadoDTO {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static TipoEmpleado tipoEmpleado(int fkEmpleado) {
		TipoEmpleado tipoEmpleado = null;
		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM tipo_empleado WHERE id_tipo_empleado = ?");
			stmt.setInt(1, fkEmpleado);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int id_tipo_empleado = rs.getInt("id_tipo_empleado");
				String tipo_empleado = rs.getString("tipo_empleado");
				
				tipoEmpleado = new TipoEmpleado(id_tipo_empleado, tipo_empleado);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tipoEmpleado;
	}
}
