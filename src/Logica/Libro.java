package Logica;

public class Libro {
	private int isbn;
	private String titulo;
	private String autor;
	private String editorial;
	private String anioPublicacion;
	private String genero;
	private String idioma;
	private String publicoObjetivo;
	private String numPaginas;
	private boolean firmado;
	private String edicion;
	private boolean edicionEspecial;
	private String materialTapa;
	private boolean saga;
	private double precio;
	private int stock;
	
	public Libro(int isbn, String titulo, String autor, String editorial, String anioPublicacion, String genero,
			String idioma, String publicoObjetivo, String numPaginas, boolean firmado, String edicion,
			boolean edicionEspecial, String materialTapa, boolean saga, double precio, int stock) {
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
		this.edicion = edicion;
		this.edicionEspecial = edicionEspecial;
		this.materialTapa = materialTapa;
		this.saga = saga;
		this.precio = precio;
		this.stock = stock;
	}
	
	// Getters y Setters
	
	// Métodos
	
	
}
