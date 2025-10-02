package BLL;

import java.util.LinkedList;

public class Vendedor extends Usuario {
	private static LinkedList<Venta> listaRegistros;

	public Vendedor(int id_usuario, String usuario, String nombre, String contrasenia, boolean estado,
			int fk_tipo_empleado) {
		super(id_usuario, usuario, nombre, contrasenia, estado, fk_tipo_empleado);
	}

	// Getters y Setters
	public static LinkedList<Venta> getListaRegistros() {
		return listaRegistros;
	}

	public static void setListaRegistros(LinkedList<Venta> listaRegistros) {
		Vendedor.listaRegistros = listaRegistros;
	}

}
