package BLL;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import DLL.LibroDTO;
import Repository.Validaciones;
import Enums.*;

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
	private String tapa;
	private boolean saga;
	private double precio;
	private int stock;
	private boolean estado;

	public Libro(int id_libro, String titulo, String autor, String editorial, String anioPublicacion, String genero,
			String idioma, String publicoObjetivo, int numPaginas, boolean firmado, boolean edicionEspecial,
			String materialTapa, boolean saga, double precio, int stock, boolean estado) {
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
		this.tapa = materialTapa;
		this.saga = saga;
		this.precio = precio;
		this.stock = stock;
		this.estado = estado;
	}

	public Libro(String titulo, String autor, String editorial, String anioPublicacion, String genero, String idioma,
			String publicoObjetivo, int numPaginas, boolean firmado, boolean edicionEspecial, String materialTapa,
			boolean saga, double precio, int stock, boolean estado) {
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
		this.tapa = materialTapa;
		this.saga = saga;
		this.precio = precio;
		this.stock = stock;
		this.estado = estado;
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

	public String getTapa() {
		return tapa;
	}

	public void setTapa(String tapa) {
		this.tapa = tapa;
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

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	// Métodos
	@Override
	public String toString() {
		return "\nID: " + id_libro + "\nTitulo: " + titulo + "\nAutor: " + autor + "\nEditorial: " + editorial
				+ "\nAño de publicación: " + anioPublicacion + "\nGénero: " + genero + "\nIdioma: " + idioma
				+ "\nPúblico objetivo: " + publicoObjetivo + "\nNúmero de páginas: " + numPaginas + "\n¿Firmado?: "
				+ (firmado == true ? "Sí" : "No") + "\n¿Edición especial?: " + (edicionEspecial == true ? "Sí" : "No")
				+ "\nTipo de tapa: " + tapa + "\n¿Pertenece a una saga?: " + (saga == true ? "Sí" : "No")
				+ "\nPrecio: $" + precio + "\nStock: " + stock + "\nEstado: "
				+ (estado == true ? "Activo" : "Inactivo") + "\n";
	}

	/**
	 * funcion para elegir los libros que se van a comprar.
	 * 
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
			String[] elegirLibros = new String[listaLibros.size()];

			do {
				flag = false;
				flagDos = false;
				vacio = false;
				// proceso de selección de libros por el cliente o vendedor.
				for (int i = 0; i < elegirLibros.length; i++) {
					elegirLibros[i] = listaLibros.get(i).getTitulo();
					if (listaLibros.get(i).getStock() > 0) {
						flagDos = true;
					}
				}

				if (flagDos) {
					seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione los libros",
							"Selección de Libros", 1, null, elegirLibros, elegirLibros[0]);
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
							cantidad = Integer
									.parseInt(Validaciones.validarInt(
											"Libro: " + elegido.getTitulo() + " | Stock disponible: "
													+ elegido.getStock() + "\n¿cuantos libros desea?",
											"Realizando una Venta", null));
							if (cantidad > elegido.getStock()) {
								JOptionPane.showMessageDialog(null,
										"No tenemos stock suficiente!!\nStock disponible: " + elegido.getStock());
								flag = true;
							}
						} while (flag);

						if (carrito.isEmpty()) {
							elegido.setStock(elegido.getStock() - cantidad);
							listaCarrito = new CarritoDetalle(cantidad, elegido);
							carrito.add(listaCarrito);

							opcion = Validaciones.menuSiNo("¿Desea agregar otro producto a su carrito?", null, null);
							flag = opcion.equalsIgnoreCase("Sí") ? true : false;

						} else {
							for (CarritoDetalle libroRepetido : carrito) {
								if (libroRepetido.getFkLibro().getTitulo().equals(elegido.getTitulo())) {
									libroRepetido.setCantidad(libroRepetido.getCantidad() + cantidad);
									elegido.setStock(elegido.getStock() - cantidad);
									flag = true;
									break;
								}
							}

							if (flag) {
								opcion = Validaciones.menuSiNo("¿Desea agregar otro producto a su carrito?", null,
										null);
								flag = opcion.equalsIgnoreCase("Sí") ? true : false;
							} else {
								elegido.setStock(elegido.getStock() - cantidad);
								listaCarrito = new CarritoDetalle(cantidad, elegido);
								carrito.add(listaCarrito);

								opcion = Validaciones.menuSiNo("¿Desea agregar otro producto a su carrito?", null,
										null);
								flag = opcion.equalsIgnoreCase("Sí") ? true : false;
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
	 * funcion para agregar nuevos libros al carrito existente del Cliente
	 * 
	 * @param cliente
	 * @return
	 */
	public static LinkedList<CarritoDetalle> agregarLibros(Cliente cliente, LinkedList<CarritoDetalle> carritoActual) {
		LinkedList<Libro> listaLibros = null;
		LinkedList<CarritoDetalle> carrito = carritoActual;

		String seleccion, opcion, condicion;
		int cantidad;
		boolean flag, flagDos, vacio;
		Libro elegido = null;
		CarritoDetalle listaCarrito = null;

		condicion = Validaciones.menuSiNo("Hola!! " + cliente.getNombre() + " actualmente tienes " + carrito.size()
				+ " libros.\n¿Deseas Agregar más?", null, null);

		if (condicion.equalsIgnoreCase("Sí")) {
			// traigo todos los libros de la BD en una lista.
			listaLibros = LibroDTO.elegirLibros();
			if (listaLibros != null) {
				String[] elegirLibros = new String[listaLibros.size()];

				do {
					flag = false;
					flagDos = false;
					vacio = false;
					// proceso de selección de libros por el cliente o vendedor.
					for (int i = 0; i < elegirLibros.length; i++) {
						elegirLibros[i] = listaLibros.get(i).getTitulo();
						if (listaLibros.get(i).getStock() > 0) {
							flagDos = true;
						}
					}

					if (flagDos) {
						seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione los libros",
								"Selección de Libros", 1, null, elegirLibros, elegirLibros[0]);
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
								cantidad = Integer
										.parseInt(Validaciones.validarInt(
												"Libro: " + elegido.getTitulo() + " | Stock disponible: "
														+ elegido.getStock() + "\n¿cuantos libros desea?",
												"Realizando una Venta", null));
								if (cantidad > elegido.getStock()) {
									JOptionPane.showMessageDialog(null,
											"No tenemos stock suficiente!!\nStock disponible: " + elegido.getStock());
									flag = true;
								}
							} while (flag);

							if (carrito.isEmpty()) {
								elegido.setStock(elegido.getStock() - cantidad);
								listaCarrito = new CarritoDetalle(cantidad, elegido);
								carrito.add(listaCarrito);

								opcion = Validaciones.menuSiNo("¿Desea agregar otro producto a su carrito?", null,
										null);
								flag = opcion.equalsIgnoreCase("Sí") ? true : false;

							} else {
								for (CarritoDetalle libroRepetido : carrito) {
									if (libroRepetido.getFkLibro().getTitulo().equals(elegido.getTitulo())) {
										libroRepetido.setCantidad(libroRepetido.getCantidad() + cantidad);
										elegido.setStock(elegido.getStock() - cantidad);
										flag = true;
										break;
									}
								}

								if (flag) {
									opcion = Validaciones.menuSiNo("¿Desea agregar otro producto a su carrito?", null,
											null);
									flag = opcion.equalsIgnoreCase("Sí") ? true : false;
								} else {
									elegido.setStock(elegido.getStock() - cantidad);
									listaCarrito = new CarritoDetalle(cantidad, elegido);
									carrito.add(listaCarrito);

									opcion = Validaciones.menuSiNo("¿Desea agregar otro producto a su carrito?", null,
											null);
									flag = opcion.equalsIgnoreCase("Sí") ? true : false;
								}
							}
						}

					} else {
						JOptionPane.showInternalMessageDialog(null, "Ya no tenemos Stock en ningun Libro!!");
					}
				} while (flag);
			}
			return carrito;
		} else {
			return null;
		}

	}

	/**
	 * funcion para traer un libro especifico de la BD.
	 * 
	 * @param fkLibro
	 * @return
	 */
	public static Libro verLibro(int fkLibro) {
		return LibroDTO.verLibro(fkLibro);
	}

	/**
	 * funcion para actualizar el Stock de la BD despues de una Venta.
	 * 
	 * @param carrito
	 */
	public static void actualizarStock(LinkedList<CarritoDetalle> carrito) {
		for (int i = 0; i < carrito.size(); i++) {
			CarritoDetalle carritoDetalle = new CarritoDetalle(carrito.get(i).getCantidad(),
					carrito.get(i).getFkLibro());
			LibroDTO.actualizarStock(carritoDetalle);
		}
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
		boolean firmado, edicionEspecial, saga, estado;
		double precio;
		Generos generoElegido;
		Idiomas idiomaElegido;
		Publico publicoObjetivoElegido;
		Tapas tapaElegida;

		titulo = Repository.Validaciones.validarVacio("Ingrese titulo del libro:", "Cargar libro", null);
		autor = Repository.Validaciones.validarString("Ingrese nombre del autor:", "Cargar libro", null);
		editorial = Repository.Validaciones.validarVacio("Ingrese nombre de la editorial:", "Cargar libro", null);
		anioPublicacion = Repository.Validaciones.validarAnio("Ingrese año de publicación del libro:", "Cargar libro");
		do {
			generoElegido = (Generos) JOptionPane.showInputDialog(null, "Seleccione el género literario del libro:",
					"Cargar libro", 1, null, Generos.values(), Generos.values()[0]);
		} while (generoElegido == null);
		genero = generoElegido.name();
		do {
			idiomaElegido = (Idiomas) JOptionPane.showInputDialog(null, "Seleccione el idioma del libro:",
					"Cargar libro", 1, null, Idiomas.values(), Idiomas.values()[0]);
		} while (idiomaElegido == null);
		idioma = idiomaElegido.name();
		do {
			publicoObjetivoElegido = (Publico) JOptionPane.showInputDialog(null,
					"Seleccione el público objetivo del libro:", "Cargar libro", 1, null, Publico.values(),
					Publico.values()[0]);
		} while (publicoObjetivoElegido == null);
		publicoObjetivo = publicoObjetivoElegido.name();
		numPaginas = Integer.parseInt(
				Repository.Validaciones.validarInt("Ingrese número de páginas del libro:", "Cargar libro", null));
		firmado = Repository.Validaciones.menuSiNo("¿El libro está firmado por su autor/a?", "Cargar libro", null)
				.equals("Sí") ? true : false;
		edicionEspecial = Repository.Validaciones.menuSiNo("¿El libro es edición especial?", "Cargar libro", null)
				.equals("Sí") ? true : false;
		do {
			tapaElegida = (Tapas) JOptionPane.showInputDialog(null, "Seleccione el tipo de tapa del libro:",
					"Cargar libro", 1, null, Tapas.values(), Tapas.values()[0]);
		} while (tapaElegida == null);
		tapa = tapaElegida.name();
		saga = Repository.Validaciones.menuSiNo("¿El libro pertenece a una saga?", "Cargar libro", null).equals("Sí")
				? true
				: false;
		do {
			precio = Repository.Validaciones.validarDouble("Ingrese precio del libro:", "Cargar libro", null);
		} while (precio <= 0);
		stock = Integer.parseInt(Repository.Validaciones.validarInt("Ingrese stock del libro:", "Cargar libro", null));

		return new Libro(titulo, autor, editorial, anioPublicacion, genero, idioma, publicoObjetivo, numPaginas,
				firmado, edicionEspecial, tapa, saga, precio, stock, true);
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
			String titulo, autor, editorial, anioPublicacion;
			int numPaginas, stock;
			boolean firmado, edicionEspecial, saga, estado;
			double precio;
			Generos genero;
			Idiomas idioma;
			Publico publicoObjetivo;
			Tapas tapa;

			Libro encontrado = LibroDTO.libroPorID(null);
			EditarLibro opcion;
			boolean flag = true;
			do {
				do {
					opcion = (EditarLibro) JOptionPane.showInputDialog(null, "Elija el campo que desea editar:",
							"Editar libro", 1, null, EditarLibro.values(), EditarLibro.values()[0]);
				} while (opcion == null);

				switch (opcion) {
				case Título:
					titulo = Repository.Validaciones.validarVacio("Ingrese titulo del libro:", "Editar libro", null);
					encontrado.setTitulo(titulo);
					break;
				case Autor:
					autor = Repository.Validaciones.validarString("Ingrese nombre del autor:", "Editar libro", null);
					encontrado.setAutor(autor);
					break;
				case Editorial:
					editorial = Repository.Validaciones.validarVacio("Ingrese nombre de la editorial:", "Editar libro",
							null);
					encontrado.setEditorial(editorial);
					break;
				case Año_Publicación:
					anioPublicacion = Repository.Validaciones.validarAnio("Ingrese año de publicación del libro:",
							"Editar libro");
					encontrado.setAnioPublicacion(anioPublicacion);
					break;
				case Género:
					do {
						genero = (Generos) JOptionPane.showInputDialog(null,
								"Seleccione el género literario del libro:", "Editar libro", 1, null, Generos.values(),
								Generos.values()[0]);
					} while (genero == null);
					encontrado.setGenero(genero.name());
					break;
				case Idioma:
					do {
						idioma = (Idiomas) JOptionPane.showInputDialog(null, "Seleccione el idioma del libro:",
								"Editar libro", 1, null, Idiomas.values(), Idiomas.values()[0]);
					} while (idioma == null);
					encontrado.setIdioma(idioma.name());
					break;
				case Publico_Objetivo:
					do {
						publicoObjetivo = (Publico) JOptionPane.showInputDialog(null,
								"Seleccione el público objetivo del libro:", "Editar libro", 1, null, Publico.values(),
								Publico.values()[0]);
					} while (publicoObjetivo == null);
					encontrado.setPublicoObjetivo(publicoObjetivo.name());
					break;
				case Número_Páginas:
					numPaginas = Integer.parseInt(Repository.Validaciones
							.validarInt("Ingrese número de páginas del libro:", "Editar libro", null));
					encontrado.setNumPaginas(numPaginas);
					break;
				case Firmado:
					firmado = Repository.Validaciones
							.menuSiNo("¿El libro está firmado por su autor/a?", "Editar libro", null).equals("Sí")
									? true
									: false;
					encontrado.setFirmado(firmado);
					break;
				case Edición_Especial:
					edicionEspecial = Repository.Validaciones
							.menuSiNo("¿El libro es edición especial?", "Editar libro", null).equals("Sí") ? true
									: false;
					encontrado.setEdicionEspecial(edicionEspecial);
					break;
				case Tapa:
					do {
						tapa = (Tapas) JOptionPane.showInputDialog(null, "Seleccione el tipo de tapa del libro:",
								"Editar libro", 1, null, Tapas.values(), Tapas.values()[0]);
					} while (tapa == null);
					encontrado.setTapa(tapa.name());
					break;
				case Saga:
					saga = Repository.Validaciones.menuSiNo("¿El libro pertenece a una saga?", "Editar libro", null)
							.equals("Sí") ? true : false;
					encontrado.setSaga(saga);
					break;
				case Precio:
					do {
						precio = Repository.Validaciones.validarDouble("Ingrese precio del libro:", "Editar libro",
								null);
					} while (precio <= 0);
					encontrado.setPrecio(precio);
					break;
				case Stock:
					stock = Integer.parseInt(
							Repository.Validaciones.validarInt("Ingrese stock del libro:", "Editar libro", null));
					encontrado.setStock(stock);
					break;
				case Estado:
					estado = Repository.Validaciones
							.menuSiNo("¿El libro está disponible para su venta?", "Editar libro", null).equals("Sí")
									? true
									: false;
					encontrado.setEstado(estado);
					break;
				default:
					break;
				}

				String continuar = Validaciones.menuSiNo(
						"¿Desea seguir editando el libro " + encontrado.getTitulo() + "?", "Editar Libro", null);
				if (continuar.equals("No")) {
					flag = false;
				}
			} while (flag);

			return LibroDTO.editarLibro(encontrado);
		}
	}

	/**
	 * Método que muestra la información del libro seleccionado de un menú
	 * desplegable.
	 */
	public static void mostrarLibro() {
		LinkedList<Libro> libros = LibroDTO.mostrarLibros();
		LinkedList<Libro> activos = new LinkedList<Libro>();
		LinkedList<Libro> inactivos = new LinkedList<Libro>();

		if (libros == null || libros.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay libros disponibles para mostrar.");
		} else {
			int mostrar = JOptionPane.showOptionDialog(null, "Elija qué libros desea ver:", "Mostrar libros",
					0, 1, null, Enums.MenuMostrar.values(), Enums.MenuMostrar.values());
			
			switch (mostrar) {
			// ACTIVOS
			case 0:
				for (Libro libro : libros) {
					if (libro.getEstado() == true) {
						activos.add(libro);
					}
				}
				if (activos == null || activos.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay libros activos");
				} else {
					JOptionPane.showMessageDialog(null, LibroDTO.libroPorID(activos));
				}
				break;
			// INACTIVOS
			case 1:
				for (Libro libro : libros) {
					if (libro.getEstado() == false) {
						inactivos.add(libro);
					}
				}
				if (inactivos == null || inactivos.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay libros inactivos");
				} else {
					JOptionPane.showMessageDialog(null, LibroDTO.libroPorID(inactivos));
				}
				break;
			// TODOS
			case 2:
				JOptionPane.showMessageDialog(null, LibroDTO.libroPorID(null));
				break;
			default:
				break;
			}
		}
	}

}
