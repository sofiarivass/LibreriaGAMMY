package BLL;

import java.util.LinkedList;

public class VendedorInternacional extends Usuario {
	private static LinkedList<Venta> listaRegistros;

	public VendedorInternacional(int id_usuario, String nombre, String usuario, String contrasenia, boolean estado,
			TipoEmpleado fkTipoEmpleado) {
		super(id_usuario, nombre, usuario, contrasenia, estado, fkTipoEmpleado);
	}

	// Getters y Setters
	public static LinkedList<Venta> getListaRegistros() {
		return listaRegistros;
	}
	public static void setListaRegistros(LinkedList<Venta> listaRegistros) {
		VendedorInternacional.listaRegistros = listaRegistros;
	}

}
