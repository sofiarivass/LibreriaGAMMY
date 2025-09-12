package Logica;
import java.util.LinkedList;

public class Admin extends Usuario {
	private LinkedList<Usuario> listaEmpleados = new LinkedList<Usuario>();

	public Admin(String usuario, String contrasenia, String tipo, boolean estado, LinkedList<Usuario> listaEmpleados) {
		super(usuario, contrasenia, tipo, estado);
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
	
	
}
