package Logica;
import java.util.LinkedList;

public class Admin extends Usuario {
	// nota: --> no se si la lista hacerla static desde aqui o crear un singleton aparte
	private LinkedList<Usuario> listaEmpleados;

	public Admin(String usuario, String contrasenia, String tipo, boolean estado, LinkedList<Usuario> listaEmpleados) {
		super(usuario, contrasenia, tipo, estado);
		this.listaEmpleados = listaEmpleados;
	}
	
	// Getter y Setters
	
	// Métodos
}
