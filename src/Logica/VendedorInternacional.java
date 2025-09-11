package Logica;

import java.util.LinkedList;

public class VendedorInternacional extends Usuario {
	private static LinkedList<Venta> listaRegistros;
	private String nombre;
	
	public VendedorInternacional(String usuario, String contrasenia, String tipo, boolean estado, String nombre) {
		super(usuario, contrasenia, tipo, estado);
		this.nombre = nombre;
	}
	
	// Getters y Setters
	
	// Métodos
	
}
