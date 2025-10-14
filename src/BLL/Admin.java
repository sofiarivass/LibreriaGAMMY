package BLL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

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
	
	public static void mostrarEmpleados() {
		String datos = "";
		LinkedList<Usuario> usuario = UsuarioDTO.mostrarUsuarios();
		for (Usuario usuario2 : usuario) {
			datos = datos + usuario2.toString() + "\n";
		}
		JOptionPane.showMessageDialog(null,datos);
	}
	
	public static void eliminarEmpleados() {
		LinkedList<Usuario> empleado = mostrarEmpleados();
		if (empleado == null || usuario.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No existen empleados");
		}else {
			Usuario seleccionado = UsuarioDTO.usuarioPorID();
		}
	}
	
	/*public static void mostrarEmpleados() {
		
	}*/

}
