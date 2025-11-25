package BLL;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import DLL.ClienteDTO;
import Repository.Validaciones;

public class Cliente {
	private int idCliente;
	private int dni;
	private String nombre;
	private String telefono;
	private String mail;
	private LinkedList<Libro> listaProductos = new LinkedList<>();
	private boolean estado;
	
	public Cliente(int idCliente, int dni, String nombre, String telefono, String mail, boolean estado) {
		super();
		this.idCliente = idCliente;
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
		this.estado = estado;
		this.listaProductos = new LinkedList<Libro>();
		this.estado= estado;
	}
	
	public Cliente(int dni, String nombre, String telefono, String mail, boolean estado) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
		this.estado = estado;
		this.listaProductos = new LinkedList<Libro>();
		this.estado= estado;
	}

	

	// Métodos
	@Override
	public String toString() {
		return "[id: " + idCliente + ", dni: " + dni + ", nombre: " + nombre + ", telefono: " + telefono
				+ ", mail: " + mail + "]";
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
	
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
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
	
	public static Cliente buscarClienteJframe(String dni) {
		int documento = Integer.parseInt(dni);
		Cliente encontrado = ClienteDTO.buscarCliente(documento);
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
			
			Cliente nuevo = new Cliente(dni,nombre,telefono,mail,true);
			
			if (ClienteDTO.registrarCliente(nuevo)) {
				return ClienteDTO.buscarCliente(nuevo.getDni());
			} else {
				return null;
			}
		}
	}
	
	
//	public static void eliminarCliente(Usuario admin) {
//		String elegido;
//		String []elegido2;
//		int clienteElegido;
//		LinkedList<Cliente> clientes = ClienteDTO.consultarClientes();
//		LinkedList<Cliente> clientesActivos = new LinkedList<Cliente>();
//		
//		for (Cliente cliente : clientes) {
//			if (cliente.getEstado() != false ) {
//				System.out.println("entre porque mi estado es true");
//				clientesActivos.add(cliente);					
//			}
//		}
//		
//		if (clientesActivos.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "No hay más Clientes Activos!!");
//		} else {
//			String[] elegirCliente = new String[clientesActivos.size()];
//			
//			for (int i = 0; i < elegirCliente.length; i++) {
//				elegirCliente[i] = clientesActivos.get(i).getIdCliente() + "," + clientesActivos.get(i).getNombre();
//			}
//			
//			elegido = (String) JOptionPane.showInputDialog(null, "Elija el Cliente", "", 0, null, elegirCliente, elegirCliente[0]);
//			elegido2 = elegido.split(",");
//			
//			clienteElegido = Integer.parseInt(elegido2[0]);
//			ClienteDTO.eliminarClientePorID(clienteElegido);			
//		}
//	}
//	
//	public static LinkedList<Cliente> mostrarClientes() {
//		LinkedList<Cliente> cliente = ClienteDTO.mostrarClientes();
//		
//		if (cliente == null || cliente.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "No hay clientes para mostrar.");
//		}else {
//			JOptionPane.showMessageDialog(null, ClienteDTO.clientePorID(cliente));
//		}
//		return cliente;
//	}
	
}
