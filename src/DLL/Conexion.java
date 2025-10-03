package DLL;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Conexion {
	private static String URL = "jdbc:mysql://localhost:3306/libreria";
	private static String USER = "root";
	private static String PASSWORD = "";

	private static Connection connect;
	private static Conexion instance;

	private Conexion() {
		try {
			connect = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Se connectó");
		} catch (SQLException e) {
			System.out.println("No se connectó " + e.getMessage());

		}
	}

	public static Conexion getInstance() {
		if (instance == null) {
			instance = new Conexion();
		}
		return instance;
	}

	public Connection getConnection() {
		return connect;
	}
}
