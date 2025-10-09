package BLL;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.LibroDTO;
import Enums.*;

public class Libro {
	private int idLibro;
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
	private String tapa;
	private boolean saga;
	private double precio;
	private int stock;

	public Libro(int idLibro, String titulo, String autor, String editorial, String anioPublicacion, String genero,
			String idioma, String publicoObjetivo, int numPaginas, boolean firmado, boolean edicionEspecial,
			String tapa, boolean saga, double precio, int stock) {
		this.idLibro = idLibro;
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
		this.tapa = tapa;
		this.saga = saga;
		this.precio = precio;
		this.stock = stock;
	}

	public Libro(String titulo, String autor, String editorial, String anioPublicacion, String genero, String idioma,
			String publicoObjetivo, int numPaginas, boolean firmado, boolean edicionEspecial, String tapa, boolean saga,
			double precio, int stock) {
		this.idLibro = idLibro;
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
		this.tapa = tapa;
		this.saga = saga;
		this.precio = precio;
		this.stock = stock;
	}

	// Getters y Setters
	public int getidLibro() {
		return idLibro;
	}

	public void setidLibro(int idLibro) {
		this.idLibro = idLibro;
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

	public boolean isFirmado() {
		return firmado;
	}

	public void setFirmado(boolean firmado) {
		this.firmado = firmado;
	}

	public boolean isEdicionEspecial() {
		return edicionEspecial;
	}

	public void setEdicionEspecial(boolean edicionEspecial) {
		this.edicionEspecial = edicionEspecial;
	}

	public String getTapa() {
		return tapa;
	}

	public void setTapa(String tapa) {
		this.tapa = tapa;
	}

	public boolean isSaga() {
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
	@Override
	public String toString() {
		return "\nID: " + idLibro + "\nTitulo: " + titulo + "\nAutor: " + autor + "\nEditorial: " + editorial
				+ "\nAño de publicación: " + anioPublicacion + "\nGénero: " + genero + "\nIdioma: " + idioma
				+ "\nPúblico objetivo: " + publicoObjetivo + "\nNúmero de páginas: " + numPaginas + "\n¿Firmado?: "
				+ (firmado == true ? "Sí" : "No") + "\n¿Edición especial?: " + (edicionEspecial == true ? "Sí" : "No")
				+ "\nTipo de tapa: " + tapa + "\n¿Pertenece a una saga?: " + (saga == true ? "Sí" : "No")
				+ "\nPrecio: $" + precio + "\nStock: " + stock + "\n";
	}

	/**
	 * Función para ingresar los datos de un libro, que se utiliza tanto para cargar
	 * productos, como para editarlos. Incluye validaciones.
	 * 
	 * @return Libro
	 */
	public static Libro cargarDatosLibro() {
		String titulo, autor, editorial, anioPublicacion, genero, idioma, publicoObjetivo, tapa;
		int numPaginas, stock;
		boolean firmado, edicionEspecial, saga;
		double precio;
		Generos generoElegido;
		Idiomas idiomaElegido;
		Publico publicoObjetivoElegido;
		Tapa tapaElegida;

		titulo = Repository.Validaciones.validarVacio("Ingrese titulo del libro:", "Cargar libro", null);
		autor = Repository.Validaciones.validarString("Ingrese nombre del autor:", "Cargar libro", null);
		editorial = Repository.Validaciones.validarVacio("Ingrese nombre de la editorial:", "Cargar libro", null);
		anioPublicacion = Repository.Validaciones.validarInt("Ingrese año de publicación del libro:", "Cargar libro",
				null);
		do {
			generoElegido = (Generos) JOptionPane.showInputDialog(null, "Seleccione el género literario del libro:",
					"Cargar libro", 0, null, Generos.values(), Generos.values()[0]);
		} while (generoElegido == null);
		genero = generoElegido.toString();
		do {
			idiomaElegido = (Idiomas) JOptionPane.showInputDialog(null, "Seleccione el idioma del libro:",
					"Cargar libro", 0, null, Idiomas.values(), Idiomas.values()[0]);
		} while (idiomaElegido == null);
		idioma = idiomaElegido.toString();
		do {
			publicoObjetivoElegido = (Publico) JOptionPane.showInputDialog(null,
					"Seleccione el público objetivo del libro:", "Cargar libro", 0, null, Publico.values(),
					Publico.values()[0]);
		} while (publicoObjetivoElegido == null);
		publicoObjetivo = publicoObjetivoElegido.toString();
		numPaginas = Integer.parseInt(
				Repository.Validaciones.validarInt("Ingrese número de páginas del libro:", "Cargar libro", null));
		firmado = Repository.Validaciones.menuSiNo("¿El libro está firmado por su autor/a?", "Cargar libro", null)
				.equals("Sí") ? true : false;
		edicionEspecial = Repository.Validaciones.menuSiNo("¿El libro es edición especial?", "Cargar libro", null)
				.equals("Sí") ? true : false;
		do {
			tapaElegida = (Tapa) JOptionPane.showInputDialog(null, "Seleccione el tipo de tapa del libro:",
					"Cargar libro", 0, null, Tapa.values(), Tapa.values()[0]);
		} while (tapaElegida == null);
		tapa = tapaElegida.toString();
		saga = Repository.Validaciones.menuSiNo("¿El libro pertenece a una saga?", "Cargar libro", null).equals("Sí")
				? true
				: false;
		do {
			precio = Repository.Validaciones.validarDouble("Ingrese precio del libro:", "Cargar libro", null);
		} while (precio <= 0);
		stock = Integer.parseInt(Repository.Validaciones.validarInt("Ingrese stock del libro:", "Cargar libro", null));

		return new Libro(titulo, autor, editorial, anioPublicacion, genero, idioma, publicoObjetivo, numPaginas,
				firmado, edicionEspecial, tapa, saga, precio, stock);
	}

	/**
	 * Método para agregar libros a la base de datos.
	 * 
	 * @return boolean
	 */
	public static boolean nuevoLibro() {
		Libro nuevo = cargarDatosLibro();
		return LibroDTO.agregarLibro(nuevo);
	}

	/**
	 * Método para editar libros de la base de datos.
	 * 
	 * @return boolean
	 */
	public static boolean editarLibro() {
		LinkedList<Libro> libros = LibroDTO.mostrarLibros();
		if (libros == null || libros.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay libros disponibles para editar.");
			return false;
		} else {
			Libro encontrado = LibroDTO.libroPorID();
			Libro modificado = cargarDatosLibro();
			modificado.setidLibro(encontrado.getidLibro());
			return LibroDTO.editarLibro(modificado);
		}
	}

//	public static LinkedList<Libro> mostrarLibros() {
//		return LibroDTO.mostrarLibros();
//	}

	/**
	 * Método que muestra la información del libro seleccionado de un menú
	 * desplegable.
	 */
	public static void mostrarLibro() {
		LinkedList<Libro> libros = LibroDTO.mostrarLibros();
		if (libros == null || libros.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay libros disponibles para mostrar.");
		} else {
			JOptionPane.showMessageDialog(null, LibroDTO.libroPorID());
		}
	}

}
