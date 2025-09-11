package Logica;
import java.util.LinkedList;

public class Cliente {
	private String id;
	private String nombre;
	private String telefono;
	private String mail;
	private LinkedList<Libro> listaProductos = new LinkedList<>();
	
	public Cliente(String id, String nombre, String telefono, String mail, LinkedList<Libro> listaProductos) {
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
		this.listaProductos = listaProductos;
	}

	// Getters y Setters
	
	// Métodos
	
}
