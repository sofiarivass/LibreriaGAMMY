package BLL;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.ClienteDTO;
import DLL.UsuarioDTO;
import Repository.Validaciones;

public class Cliente {
	private int idCliente;
	private int dni;
	private String nombre;
	private String telefono;
	private String mail;
	private LinkedList<Libro> listaProductos = new LinkedList<>();
	
	public Cliente(int idCliente, int dni, String nombre, String telefono, String mail) {
		super();
		this.idCliente = idCliente;
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
		this.listaProductos = new LinkedList<Libro>();
	}
	
	public Cliente(int dni, String nombre, String telefono, String mail) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
		this.listaProductos = new LinkedList<Libro>();
	}

	
	// Getters y Setters
	
	
	// Métodos
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", nombre=" + nombre + ", telefono=" + telefono
				+ ", mail=" + mail + ", listaProductos=" + listaProductos + "]";
	}

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

	/**
	 * funcion para pedir los datos al Cliente registrado.
	 * @return
	 */
	public static Cliente buscarCliente() {
		int dni = Integer.parseInt(Validaciones.validarInt("ingrese el número de DNI del Cliente", null, null));
		Cliente encontrado = ClienteDTO.buscarCliente(dni);
		return encontrado;
	}
	
	/**
	 * funcion para pedir los datos a un nuevo Cliente.
	 * @return
	 */
	public static Cliente registrarCliente() {
		Cliente cliente = null;
		int dni;
		String nombre, telefono, mail;
		
		JOptionPane.showMessageDialog(null, "Registrar nuevo Cliente");
		
		dni = Integer.parseInt(Validaciones.validarDigitos("ingrese su número de DNI", "Registrando un Cliente", null, 8, 0));
		cliente = ClienteDTO.buscarCliente(dni);
		if (cliente != null) {
			JOptionPane.showMessageDialog(null, "El cliente " + cliente.getNombre() + " Ya esta registrado!!");
			return cliente;
		} else {
			nombre = Validaciones.validarString("ingrese su nombre", "Registrando un Cliente", null);
			telefono = Validaciones.validarDigitos("ingrese su número de telefono", "Registrando un Cliente", null, 10, 0);
			do {
				mail = Validaciones.validarString("Ingrese su mail", "Registrando un Cliente", null);
			} while (!Validaciones.validarMail(mail));
			
			Cliente nuevo = new Cliente(dni,nombre,telefono,mail);
			
			if (ClienteDTO.registrarCliente(nuevo)) {
				return ClienteDTO.buscarCliente(nuevo.getDni());
			} else {
				return null;
			}
		}
	}
	
	
	public static void eliminarCliente(Usuario admin) {
		String elegido;
		String []elegido2;
		int usuarioElegido;
		LinkedList<Cliente> clientes = ClienteDTO.consultarClientes();
		LinkedList<Cliente> clientesActivos = new LinkedList<Cliente>();
		
		for (Cliente cliente : clientes) {
			if (cliente.getEstado() != false ) {
				System.out.println("entre porque mi estado es true");
				clientesActivos.add(cliente);					
			}
		}
		
		if (usuariosActivos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay más Empleados Activos!!");
		} else {
			String[] elegirUsuario = new String[usuariosActivos.size()];
			
			for (int i = 0; i < elegirUsuario.length; i++) {
				elegirUsuario[i] = usuariosActivos.get(i).getId_usuario() + "," + usuariosActivos.get(i).getUsuario();
			}
			
			elegido = (String) JOptionPane.showInputDialog(null, "Elija el usuario", "", 0, null, elegirUsuario, elegirUsuario[0]);
			elegido2 = elegido.split(",");
			
			usuarioElegido = Integer.parseInt(elegido2[0]);
			UsuarioDTO.eliminarUsuarioPorID(usuarioElegido);			
		}
	}
	
}
