package BLL;
import java.util.LinkedList;
import DLL.ClienteDTO;
import Repository.Validaciones;

public class Cliente {
	private int idCliente;
	private int dni;
	private String nombre;
	private String telefono;
	private String mail;
	private LinkedList<Libro> listaProductos = new LinkedList<>();
	
	public Cliente(int idCliente, int dni, String nombre, String telefono, String mail,
			LinkedList<Libro> listaProductos) {
		this.idCliente = idCliente;
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
		this.listaProductos = listaProductos;
	}
	
	public Cliente(int dni, String nombre, String telefono, String mail) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
		this.listaProductos = new LinkedList<Libro>();
	}

	// Getters y Setters
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
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
		return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", nombre=" + nombre + ", telefono=" + telefono
				+ ", mail=" + mail + ", listaProductos=" + listaProductos + "]";
	}

	// comprobando si existe el cliente
	public static Cliente buscarCliente() {
		int dni = Integer.parseInt(Validaciones.validarInt("ingrese el número de DNI del Cliente", null, null));
		Cliente encontrado = ClienteDTO.buscarCliente(dni);
		return encontrado;
	}
	
	// registrar un cliente nuevo
	public static Cliente registrarCliente() {
		int dni;
		String nombre, telefono, mail;
		
		dni = Integer.parseInt(Validaciones.validarInt("ingrese su número de DNI", null, null));
		nombre = Validaciones.validarString("ingrese su nombre", null, null);
		telefono = Validaciones.validarDigitos("ingrese su número de telefono", null, null, 10, 0);
		mail = Validaciones.validarString("ingrese su mail", null, null);
		
		Cliente nuevo = new Cliente(dni,nombre,telefono,mail);
		
		if (ClienteDTO.registrarCliente(nuevo)) {
			return nuevo;
		} else {
			return null;
		}		
	}
	
}
