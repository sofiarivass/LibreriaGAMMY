package BLL;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import DLL.LibroDTO;
import Repository.Validaciones;

public class Libro {
	private int id_libro;
	private String titulo;
	private String autor;
	private String editorial;
	private String anioPublicacion;
	private String genero;
	private String idioma;
	private String publicoObjetivo;
	private int numPaginas;
	private boolean firmado;
	private boolean edicionEspecial;
	private String materialTapa;
	private boolean saga;
	private double precio;
	private int stock;
	
	public Libro(int id_libro, String titulo, String autor, String editorial, String anioPublicacion, String genero,
			String idioma, String publicoObjetivo, int numPaginas, boolean firmado,
			boolean edicionEspecial, String materialTapa, boolean saga, double precio, int stock) {
		this.id_libro = id_libro;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.anioPublicacion = anioPublicacion;
		this.genero = genero;
		this.idioma = idioma;
		this.publicoObjetivo = publicoObjetivo;
		this.numPaginas = numPaginas;
		this.firmado = firmado;
		this.edicionEspecial = edicionEspecial;
		this.materialTapa = materialTapa;
		this.saga = saga;
		this.precio = precio;
		this.stock = stock;
	}
	
	public Libro(String titulo, String autor, String editorial, String anioPublicacion, String genero,
			String idioma, String publicoObjetivo, int numPaginas, boolean firmado,
			boolean edicionEspecial, String materialTapa, boolean saga, double precio, int stock) {
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.anioPublicacion = anioPublicacion;
		this.genero = genero;
		this.idioma = idioma;
		this.publicoObjetivo = publicoObjetivo;
		this.numPaginas = numPaginas;
		this.firmado = firmado;
		this.edicionEspecial = edicionEspecial;
		this.materialTapa = materialTapa;
		this.saga = saga;
		this.precio = precio;
		this.stock = stock;
	}

	// Getters y Setters
	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(String anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getPublicoObjetivo() {
		return publicoObjetivo;
	}

	public void setPublicoObjetivo(String publicoObjetivo) {
		this.publicoObjetivo = publicoObjetivo;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public boolean getFirmado() {
		return firmado;
	}

	public void setFirmado(boolean firmado) {
		this.firmado = firmado;
	}

	public boolean getEdicionEspecial() {
		return edicionEspecial;
	}

	public void setEdicionEspecial(boolean edicionEspecial) {
		this.edicionEspecial = edicionEspecial;
	}

	public String getMaterialTapa() {
		return materialTapa;
	}

	public void setMaterialTapa(String materialTapa) {
		this.materialTapa = materialTapa;
	}

	public boolean getSaga() {
		return saga;
	}

	public void setSaga(boolean saga) {
		this.saga = saga;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	// Métodos
	
	/**
	 * funcion para elegir los libros que se van a comprar.
	 * @param cliente
	 * @return
	 */
	public static LinkedList<CarritoDetalle> elegirLibros(Cliente cliente) {
		LinkedList<Libro> listaLibros = null;
		LinkedList<CarritoDetalle> carrito = new LinkedList<CarritoDetalle>();
		
		String seleccion, opcion; 
		int cantidad;
		boolean flag, flagDos, vacio;
		Libro elegido = null;
		CarritoDetalle listaCarrito = null;
		
		JOptionPane.showMessageDialog(null, "Hola!! " + cliente.getNombre() + "\nElija los libros que desee");
		
		// traigo todos los libros de la BD en una lista.
		listaLibros = LibroDTO.elegirLibros();
		if (listaLibros != null) {
			String []elegirLibros = new String[listaLibros.size()];
			
			do {
				flag = false;
				flagDos = false;
				vacio = false;
				// proceso de selección de libros por el cliente o vendedor.
				for (int i = 0; i < elegirLibros.length; i++) {
					elegirLibros[i] = listaLibros.get(i).getTitulo();
					if (listaLibros.get(i).getStock()>0) {
						flagDos = true;
					}
				}
				
				if (flagDos) {
					seleccion = (String)JOptionPane.showInputDialog(null, "Seleccione los libros", "Selección de Libros", 0, null, elegirLibros, elegirLibros[0]);
					for (Libro libro : listaLibros) {
						if (libro.getTitulo().equals(seleccion)) {
							elegido = libro;
							if (elegido.getStock() == 0) {
								vacio = true;
							}
							break;
						}
					}
					
					if (vacio) {
						JOptionPane.showMessageDialog(null, "No tenemos Stock del libro: " + elegido.getTitulo());
						flag = true;
					} else {
						do {
							flag = false;
							cantidad = Integer.parseInt(Validaciones.validarInt("Libro: " + elegido.getTitulo() + " | Stock disponible: " 
									+ elegido.getStock() +"\n¿cuantos libros desea?", "Realizando una Venta", null));
							if (cantidad > elegido.getStock()) {
								JOptionPane.showMessageDialog(null, "No tenemos stock suficiente!!\nStock disponible: " + elegido.getStock());
								flag = true;
							}				
						} while (flag);
						
						if (carrito.isEmpty()) {
							elegido.setStock(elegido.getStock()-cantidad);
							listaCarrito = new CarritoDetalle(cantidad,elegido);
							carrito.add(listaCarrito);
							
							opcion = Validaciones.menuSiNo("¿Desea agregar otro producto a su carrito?", null, null);
							flag = opcion.equalsIgnoreCase("Sí")?true:false;
							
						} else {
							for (CarritoDetalle libroRepetido : carrito) {
								if (libroRepetido.getFkLibro().getTitulo().equals(elegido.getTitulo())) {
									libroRepetido.setCantidad(libroRepetido.getCantidad()+cantidad);
									elegido.setStock(elegido.getStock()-cantidad);
									flag = true;
									break;
								}
							}
							
							if (flag) {
								opcion = Validaciones.menuSiNo("¿Desea agregar otro producto a su carrito?", null, null);
								flag = opcion.equalsIgnoreCase("Sí")?true:false;					
							} else {
								elegido.setStock(elegido.getStock()-cantidad);
								listaCarrito = new CarritoDetalle(cantidad,elegido);
								carrito.add(listaCarrito);
								
								opcion = Validaciones.menuSiNo("¿Desea agregar otro producto a su carrito?", null, null);
								flag = opcion.equalsIgnoreCase("Sí")?true:false;
							}
						}
					}
					
				} else {
					JOptionPane.showInternalMessageDialog(null, "Ya no tenemos Stock en ningun Libro!!");
				}
			} while (flag);
		}
		return carrito;
	}
	
	/**
	 * funcion para actualizar el Stock de la BD despues de una Venta.
	 * @param carrito
	 */
	public static void actualizarStock(LinkedList<CarritoDetalle>carrito) {
		CarritoDetalle carritoDetalle = null;
		
		for (int i = 0; i < carrito.size(); i++) {
			carritoDetalle = new CarritoDetalle(carrito.get(i).getCantidad(),carrito.get(i).getFkLibro());
			LibroDTO.actualizarStock(carritoDetalle);
		}
	}
	
}
