package BLL;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import DLL.UsuarioDTO;

public class Admin extends Usuario {
	private LinkedList<Usuario> listaEmpleados = new LinkedList<Usuario>();

	public Admin(int id_usuario, String nombre, String usuario, String contrasenia, boolean estado,
			TipoEmpleado fkTipoEmpleado, LinkedList<Usuario> listaEmpleados) {
		super(id_usuario, nombre, usuario, contrasenia, estado, fkTipoEmpleado);
		this.listaEmpleados = listaEmpleados;
	}

	// Getter y Setters
	public LinkedList<Usuario> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(LinkedList<Usuario> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	// Métodos
	@Override
	public String toString() {
		return "Admin [listaEmpleados=" + listaEmpleados + "]";
	}
	
	public static LinkedList<Usuario> mostrarEmpleados() {
		LinkedList<Usuario> usuario = UsuarioDTO.mostrarUsuarios();
		if (usuario == null || usuario.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay empleados para mostrar.");
		}else {
			JOptionPane.showMessageDialog(null, UsuarioDTO.usuarioPorID(null));
		}
		return usuario;
	}
	
	public static Usuario cargarDatosUsuario(String accion) {
		String usuario, nombre, contrasenia;
		TipoEmpleado tipo_empleado;
		boolean estadoEmpleado;

		usuario = Repository.Validaciones.validarVacio("Ingrese usuario:", "Cargar usuario", null);
		nombre = Repository.Validaciones.validarString("Ingrese nombre del usuario:", "Cargar usuario", null);
		contrasenia = Repository.Validaciones.validarVacio("Ingrese la contrasenea:", "Cargar usuario", null);
		
		
		if (accion.equals("Crear")) {			
			return new Usuario(usuario,nombre, contrasenia, estadoEmpleado, tipo_empleado);
		}else {
			estadoEmpleado = Repository.Validaciones.menuSiNo("¿El Usuario ya fue creaod?", "Cargar usuario", null)
					.equals("Sí") ? true : false;
			return new Usuario(usuario,nombre, contrasenia, estadoEmpleado, tipo_empleado);
		}
	}
	
	

	public static void eliminarEmpleados() {
		String elegido;
		String []elegido2;
		int usuarioElegido;
		LinkedList<Usuario> usuarios = UsuarioDTO.mostrarUsuarios();
		String[] elegirUsuario = new String[usuarios.size()];
		
		for (int i = 0; i < elegirUsuario.length; i++) {
			elegirUsuario[i] = usuarios.get(i).getId_usuario() + "," + usuarios.get(i).getNombre();
		}
		
		elegido = (String) JOptionPane.showInputDialog(null, "Elija el usuario", "", 0, null, elegirUsuario, elegirUsuario[0]);
		elegido2 = elegido.split(",");
		
		usuarioElegido = Integer.parseInt(elegido2[0]);
		UsuarioDTO.eliminarUsuarioPorID(usuarioElegido);
	}
	
	public static boolean nuevoEmpleado() {
		Usuario nuevo = cargarDatosUsuario("Crear");
		return UsuarioDTO.agregarUsuario(nuevo);
	}
	
}
