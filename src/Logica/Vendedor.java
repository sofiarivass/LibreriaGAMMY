package Logica;

import java.util.LinkedList;

public class Vendedor extends Usuario {
	private static LinkedList<Venta> listaRegistros;
	private String nombre;

	public Vendedor(String usuario, String contrasenia, String tipo, boolean estado, String nombre) {
		super(usuario, contrasenia, tipo, estado);
		this.nombre = nombre;
	}

	// Getters y Setters
	public static LinkedList<Venta> getListaRegistros() {
		return listaRegistros;
	}

	public static void setListaRegistros(LinkedList<Venta> listaRegistros) {
		Vendedor.listaRegistros = listaRegistros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Métodos
	@Override
		public String toString() {
			return "Vendedor [nombre=" + nombre + "]";
		}
	
}
