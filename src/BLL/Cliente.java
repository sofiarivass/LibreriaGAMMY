package BLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import DLL.ClienteDTO;
import DLL.Conexion;
import Repository.Validaciones;

public class Cliente {
	private String id;
	private int dni;
	private String nombre;
	private String telefono;
	private String mail;
	private LinkedList<Libro> listaProductos = new LinkedList<>();
	
	public Cliente(String id, int dni, String nombre, String telefono, String mail) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
//		this.listaProductos = listaProductos;
	}
	
	public Cliente(int dni, String nombre, String telefono, String mail) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
	}
	
	

	// Getters y Setters
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getId() {
		return id;
	}

	public void setId(int dni) {
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
		return "Cliente [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", mail=" + mail
				+ ", listaProductos=" + listaProductos + "]";
	}
	
	
	// comprobando si existe el cliente
	public static Cliente buscarCliente() {
		int dni = Integer.parseInt(Validaciones.validarInt("ingrese su número de DNI", null, null));
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
	
	
	
//	public static Cliente cargarCliente() {
//		Connection con = Conexion.getInstance().getConnection();
//		LinkedList<Libro> listaLibros = new LinkedList<Libro>();
//		LinkedList<Libro> carrito = new LinkedList<Libro>();
//		
//		try {
//            PreparedStatement stmt = con.prepareStatement("SELECT * FROM libro");
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                int isbn = rs.getInt("id_libro");
//                String titulo = rs.getString("titulo");
//                String autor = rs.getString("autor");
//                String editorial = rs.getString("editorial");
//                String anio = rs.getString("anio_publicacion");
//                String genero = rs.getString("genero");
//                String idioma = rs.getString("idioma");
//                String publico_objetivo = rs.getString("publico_objetivo");
//                int numPaginas = rs.getInt("num_paginas");
//                boolean firmado = rs.getBoolean("firmado");
//                String edicion = rs.getString("edicion");
//                boolean edicionEspecial = rs.getBoolean("edicion_especial");
//                String materialTapa = rs.getString("tapa");
//                boolean saga = rs.getBoolean("saga");
//                double precio = rs.getDouble("precio");
//                int stock = rs.getInt("stock");
//                
//                listaLibros.add(new Libro(isbn,titulo,autor,editorial,anio,genero,idioma,publico_objetivo,numPaginas,firmado,edicion,edicionEspecial,materialTapa,saga,precio,stock));
//                }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		
//		String seleccion; 
//		String []condicion = {"Si", "No"};
//		int cantidad, opcion;
//		Libro elegido = null;
//		String []elegirLibros = new String[listaLibros.size()];
//		
//		for (int i = 0; i < elegirLibros.length; i++) {
//			elegirLibros[i] = listaLibros.get(i).getTitulo();
//		}
//		
//		do {
//			
//			seleccion = (String)JOptionPane.showInputDialog(null, "Seleccione los libros", null, 0, null, elegirLibros, elegirLibros[0]);
//			cantidad = Integer.parseInt(JOptionPane.showInputDialog("¿cuantos libros desea?"));
//			for (Libro libro : listaLibros) {
//				if (libro.getTitulo().equals(seleccion)) {
//					elegido = libro;
//					break;
//				}
//			}
//			carrito.add(elegido);
//			elegido.setStock(elegido.getStock()-cantidad);
//			opcion = JOptionPane.showOptionDialog(null, "¿Desea agregar otro producto a su carrito?", null, 0, 0, null, condicion, condicion[0]);
//			
//		} while (opcion == 0);
//		Cliente pepito = new Cliente("1","Pepito Pérez", "123456789", "pepito@hotmail.com", carrito);
//		
//		return pepito;
//	}
	
}
