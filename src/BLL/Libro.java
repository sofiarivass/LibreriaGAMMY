package BLL;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import DLL.LibroDTO;
import Repository.Validaciones;

public class Libro {
	private int isbn;
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
	
	public Libro(int isbn, String titulo, String autor, String editorial, String anioPublicacion, String genero,
			String idioma, String publicoObjetivo, int numPaginas, boolean firmado,
			boolean edicionEspecial, String materialTapa, boolean saga, double precio, int stock) {
		super();
		this.isbn = isbn;
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
	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
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
	public static LinkedList<Libro> elegirLibros() {
		LinkedList<Libro> listaLibros = null;
		LinkedList<Libro> carrito = new LinkedList<Libro>();
		
		String seleccion, opcion; 
		int cantidad;
		boolean flag;
		Libro elegido = null;
		
		// traigo todos los libros de la BD en una lista.
		listaLibros = LibroDTO.elegirLibros();
		String []elegirLibros = new String[listaLibros.size()];
		
		// proceso de seleccion de libros por el cliente o vendedor.
		for (int i = 0; i < elegirLibros.length; i++) {
			elegirLibros[i] = listaLibros.get(i).getTitulo();
		}
		
		do {
			flag = false;
			seleccion = (String)JOptionPane.showInputDialog(null, "Seleccione los libros", null, 0, null, elegirLibros, elegirLibros[0]);
			for (Libro libro : listaLibros) {
				if (libro.getTitulo().equals(seleccion)) {
					elegido = libro;
					break;
				}
			}
			
			do {
				flag = false;
				cantidad = Integer.parseInt(JOptionPane.showInputDialog("¿cuantos libros desea?"));
				if (cantidad > elegido.getStock()) {
					JOptionPane.showMessageDialog(null, "No tenemos stock suficiente!!\nStock disponible: " + elegido.getStock());
					flag = true;
				}				
			} while (flag);
			
			elegido.setStock(cantidad);
			carrito.add(elegido);
			opcion = Validaciones.menuSiNo("¿Desea agregar otro producto a su carrito?", null, null);
			flag = opcion.equalsIgnoreCase("Si")?true:false;
			
		} while (flag);
		
		return carrito;
	}
	
}
