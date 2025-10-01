package BLL;

import java.util.LinkedList;

public class VendedorInternacional extends Usuario {
	private static LinkedList<Venta> listaRegistros;
	private String nombre;
	
	public VendedorInternacional(String usuario, String contrasenia, String tipo, boolean estado, String nombre) {
		super(usuario, contrasenia, tipo, estado);
		this.nombre = nombre;
	}
	
	// Getters y Setters

	public static LinkedList<Venta> getListaRegistros() {
		return listaRegistros;
	}

	public static void setListaRegistros(LinkedList<Venta> listaRegistros) {
		VendedorInternacional.listaRegistros = listaRegistros;
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
		return "VendedorInternacional [nombre=" + nombre + "]";
	}
	
}
