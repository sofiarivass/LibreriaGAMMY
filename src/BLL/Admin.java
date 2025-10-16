package BLL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JOptionPane;

import DLL.UsuarioDTO;
import Repository.Validaciones;

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
	
	/*
	public static void eliminarEmpleados() {
		LinkedList<Usuario> empleado = mostrarEmpleados();
		LinkedList<Usuario> usuarioDisp = new LinkedList<Usuario>();
		if (empleado == null || empleado.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No existen empleados para eliminar");
		}else {
			for (Usuario usuario : empleado) {
				if (null) {
					usuarioDisp.add(usuario);
				}
			}
		}
	}
	*/
	/*public static void mostrarEmpleados() {
		
	}*/

}
