package BLL;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import DLL.UsuarioDTO;

public class Admin extends Usuario {
	private LinkedList<Usuario> listaEmpleados = new LinkedList<Usuario>();

	public Admin(int id_usuario, String usuario, String nombre, String contrasenia, boolean estado,
			int fk_tipo_empleado, LinkedList<Usuario> listaEmpleados) {
		super(id_usuario, usuario, nombre, contrasenia, estado, fk_tipo_empleado);
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
	
}
