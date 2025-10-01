package BLL;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public LinkedList<Libro> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(LinkedList<Libro> listaProductos) {
		this.listaProductos = listaProductos;
	}

	// Métodos
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", mail=" + mail
				+ ", listaProductos=" + listaProductos + "]";
	}
	
}
