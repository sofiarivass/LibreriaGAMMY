package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import BLL.CarritoDetalle;
import BLL.Libro;
import Repository.Validaciones;

public class LibroDTO {

	private static Connection con = Conexion.getInstance().getConnection();

	/**
	 * Método para agregar libros a la base de datos. Se valida que no exista un
	 * libro con las mismas caractéristicas.
	 * 
	 * @param libro
	 * @return boolean
	 */
	public static boolean agregarLibro(Libro libro) {
		boolean flag = true;
		Libro coincidencia = null;
		for (Libro elemento : LibroDTO.mostrarLibros()) {
			if (elemento.getTitulo().equals(libro.getTitulo()) && elemento.getAutor().equals(libro.getAutor())
					&& elemento.getEditorial().equals(libro.getEditorial())
					&& elemento.getIdioma().equals(libro.getIdioma())) {
				flag = false;
				coincidencia = elemento;
				break;
			}
		}
		if (flag) {
			try {
				PreparedStatement statement = con.prepareStatement(
						"INSERT INTO libro (titulo, autor, editorial, anio_publicacion, genero, idioma, publico_objetivo, num_paginas, firmado, edicion_especial, tapa, saga, precio, stock, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
						, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, libro.getTitulo());
				statement.setString(2, libro.getAutor());
				statement.setString(3, libro.getEditorial());
				statement.setString(4, libro.getAnioPublicacion());
				statement.setString(5, libro.getGenero());
				statement.setString(6, libro.getIdioma());
				statement.setString(7, libro.getPublicoObjetivo());
				statement.setInt(8, libro.getNumPaginas());
				statement.setBoolean(9, libro.getFirmado());
				statement.setBoolean(10, libro.getEdicionEspecial());
				statement.setString(11, libro.getTapa());
				statement.setBoolean(12, libro.getSaga());
				statement.setDouble(13, libro.getPrecio());
				statement.setInt(14, libro.getStock());
				statement.setBoolean(15, true);

				int filas = statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
	            
				if (rs.next()) {
					int idGenerado = rs.getInt(1);
					libro.setId_libro(idGenerado);
				}
				
				if (filas > 0) {
					JOptionPane.showMessageDialog(null, "Libro agregado correctamente\n" + libro.toString());
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;

			}

		} else {
			JOptionPane.showMessageDialog(null,
					"ERROR! Ya hay un libro con las mismas carácteristicas cargado en el sistema:\n"
							+ coincidencia.toString());
			return false;
		}
	}

	/**
	 * Método para editar libros de la base de datos. Se valida que no exista un
	 * libro con las mismas caractéristicas.
	 * 
	 * @param libro
	 * @return boolean
	 */
	public static boolean editarLibro(Libro libro) {
		boolean flag = true;
		Libro coincidencia = null;
		for (Libro elemento : LibroDTO.mostrarLibros()) {
			if (elemento.getTitulo().equals(libro.getTitulo()) && elemento.getAutor().equals(libro.getAutor())
					&& elemento.getEditorial().equals(libro.getEditorial())
					&& elemento.getIdioma().equals(libro.getIdioma())) {
				flag = false;
				coincidencia = elemento;
				break;
			}
		}
		if (flag) {
			try {
				PreparedStatement statement = con.prepareStatement(
						"UPDATE libro SET titulo=?, autor=?, editorial=?, anio_publicacion=?, genero=?, idioma=?, publico_objetivo=?, num_paginas=?, firmado=?, edicion_especial=?, tapa=?, saga=?, precio=?, stock=?, estado=? WHERE id_libro=?");
				statement.setString(1, libro.getTitulo());
				statement.setString(2, libro.getAutor());
				statement.setString(3, libro.getEditorial());
				statement.setString(4, libro.getAnioPublicacion());
				statement.setString(5, libro.getGenero());
				statement.setString(6, libro.getIdioma());
				statement.setString(7, libro.getPublicoObjetivo());
				statement.setInt(8, libro.getNumPaginas());
				statement.setBoolean(9, libro.getFirmado());
				statement.setBoolean(10, libro.getEdicionEspecial());
				statement.setString(11, libro.getTapa());
				statement.setBoolean(12, libro.getSaga());
				statement.setDouble(13, libro.getPrecio());
				statement.setInt(14, libro.getStock());
				statement.setBoolean(15, libro.getEstado());
				statement.setInt(16, libro.getId_libro());

				int filas = statement.executeUpdate();
				if (filas > 0) {
					JOptionPane.showMessageDialog(null, "Libro editado correctamente:\n" + libro.toString());
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"ERROR! Ya hay un libro con las mismas carácteristicas cargado en el sistema:\n"
							+ coincidencia.toString());
			return false;
		}
	}

	/**
	 * Método que devuelve todos los libros de la base de datos en una lista.
	 * 
	 * @return LinkedList<Libro>
	 */
	public static LinkedList<Libro> mostrarLibros() {
		LinkedList<Libro> libros = new LinkedList<Libro>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM libro");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id_libro = rs.getInt("id_libro");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String editorial = rs.getString("editorial");
				String anio_publicacion = rs.getString("anio_publicacion");
				String genero = rs.getString("genero");
				String idioma = rs.getString("idioma");
				String publico_objetivo = rs.getString("publico_objetivo");
				int num_paginas = rs.getInt("num_paginas");
				boolean firmado = rs.getBoolean("firmado");
				boolean edicion_especial = rs.getBoolean("edicion_especial");
				String tapa = rs.getString("tapa");
				boolean saga = rs.getBoolean("saga");
				double precio = rs.getDouble("precio");
				int stock = rs.getInt("stock");
				boolean estado = rs.getBoolean("estado");

				libros.add(new Libro(id_libro, titulo, autor, editorial, anio_publicacion, genero, idioma,
						publico_objetivo, num_paginas, firmado, edicion_especial, tapa, saga, precio, stock, estado));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return libros;
	}

	/**
	 * Método que permite seleccionar un libro de la base de datos desde un menú
	 * desplegable.
	 * 
	 * @return Libro
	 */
	public static Libro libroPorID(LinkedList<Libro> librosDisp) {
		int id_libro = 0;
		List<Libro> libros = LibroDTO.mostrarLibros();

		if (librosDisp == null) {
			String[] librosArray = new String[libros.size()];
			for (int i = 0; i < librosArray.length; i++) {
				librosArray[i] = libros.get(i).getId_libro() + " - " + libros.get(i).getTitulo();
			}
			String elegido = (String) JOptionPane.showInputDialog(null, "Elija libro:", null, 0, null, librosArray,
					librosArray[0]);
			id_libro = Integer.parseInt(elegido.split(" - ")[0]);
			Libro libro = null;
			try {
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM libro WHERE id_libro = ?");
				stmt.setInt(1, id_libro);

				// executequery se utiliza cuando no hay cambios en la bdd
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					String titulo = rs.getString("titulo");
					String autor = rs.getString("autor");
					String editorial = rs.getString("editorial");
					String anio_publicacion = rs.getString("anio_publicacion");
					String genero = rs.getString("genero");
					String idioma = rs.getString("idioma");
					String publico_objetivo = rs.getString("publico_objetivo");
					int num_paginas = rs.getInt("num_paginas");
					boolean firmado = rs.getBoolean("firmado");
					boolean edicion_especial = rs.getBoolean("edicion_especial");
					String tapa = rs.getString("tapa");
					boolean saga = rs.getBoolean("saga");
					double precio = rs.getDouble("precio");
					int stock = rs.getInt("stock");
					boolean estado = rs.getBoolean("estado");
					
					libro = new Libro(id_libro, titulo, autor, editorial, anio_publicacion, genero, idioma,
							publico_objetivo, num_paginas, firmado, edicion_especial, tapa, saga, precio, stock, estado);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return libro;
		} else {
			String[] librosArray = new String[librosDisp.size()];
			for (int i = 0; i < librosArray.length; i++) {
				librosArray[i] = librosDisp.get(i).getId_libro() + " - " + librosDisp.get(i).getTitulo();
			}
			String elegido = (String) JOptionPane.showInputDialog(null, "Elija libro:", null, 0, null, librosArray,
					librosArray[0]);
			id_libro = Integer.parseInt(elegido.split(" - ")[0]);
			Libro libro = null;
			try {
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM libro WHERE id_libro = ?");
				stmt.setInt(1, id_libro);

				// executequery se utiliza cuando no hay cambios en la bdd
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					String titulo = rs.getString("titulo");
					String autor = rs.getString("autor");
					String editorial = rs.getString("editorial");
					String anio_publicacion = rs.getString("anio_publicacion");
					String genero = rs.getString("genero");
					String idioma = rs.getString("idioma");
					String publico_objetivo = rs.getString("publico_objetivo");
					int num_paginas = rs.getInt("num_paginas");
					boolean firmado = rs.getBoolean("firmado");
					boolean edicion_especial = rs.getBoolean("edicion_especial");
					String tapa = rs.getString("tapa");
					boolean saga = rs.getBoolean("saga");
					double precio = rs.getDouble("precio");
					int stock = rs.getInt("stock");
					boolean estado = rs.getBoolean("estado");
					
					libro = new Libro(id_libro, titulo, autor, editorial, anio_publicacion, genero, idioma,
							publico_objetivo, num_paginas, firmado, edicion_especial, tapa, saga, precio, stock, estado);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return libro;
		}
		
		
	}

//	/**
//	 * Función para eliminar un libro de la base de datos.
//	 */
//	public static void eliminarLibro() {
//		LinkedList<Libro> libros = mostrarLibros();
//		if (libros == null || libros.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "No hay libros disponibles para eliminar.");
//		} else {
//			Libro seleccionado = LibroDTO.libroPorID();
//			String confirmacion = Validaciones.menuSiNo("Seguro que desea eliminar el libro?\n"
//					+ seleccionado.toString() + "\nEsta acción es irreversible!!", "Eliminar Libro", null);
//			if (confirmacion.equals("Sí")) {
//				try {
//					PreparedStatement statement = con.prepareStatement("DELETE FROM libro WHERE id_libro = ?");
//					statement.setInt(1, seleccionado.getId_libro());
//
//					int filas = statement.executeUpdate();
//					if (filas > 0) {
//						JOptionPane.showMessageDialog(null, "Libro eliminado correctamente.");
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} else {
//				JOptionPane.showMessageDialog(null, "No se han eliminado libros.");
//			}
//		}
//	}
	
	/**
	 * Función para eliminar un libro de la base de datos (no elimina, edita el estado).
	 */
	public static void eliminarLibro() {
		LinkedList<Libro> libros = mostrarLibros();
		LinkedList<Libro> librosDisp = new LinkedList<Libro>();
		if (libros == null || libros.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay libros disponibles para eliminar.");
		} else {
			for (Libro libro : libros) {
				if (libro.getEstado() == true) {
					librosDisp.add(libro);
				}
			}
			
			if (librosDisp.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No hay libros disponibles para eliminar.");
			} else {
				
				Libro seleccionado = LibroDTO.libroPorID(librosDisp);				
				
				String confirmacion = Validaciones.menuSiNo("Seguro que desea eliminar el libro?\n"
						+ seleccionado.toString() + "\nEsta acción es irreversible!!", "Eliminar Libro", null);
				if (confirmacion.equals("Sí")) {
					try {
						PreparedStatement statement = con.prepareStatement("UPDATE libro SET estado = ? WHERE id_libro = ?");
						statement.setBoolean(1, false);
						statement.setInt(2, seleccionado.getId_libro());
						
						int filas = statement.executeUpdate();
						if (filas > 0) {
							JOptionPane.showMessageDialog(null, "Libro eliminado correctamente.");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se han eliminado libros.");
				}
			}
		}
	}
		
	/**
	 * funcion para traer todos los libros de la BD.
	 * @return
	 */
	public static LinkedList<Libro> elegirLibros() {
		boolean flag = false;
		LinkedList<Libro> listaLibros = mostrarLibros();
		LinkedList<Libro> listaLibrosDisponibles = new LinkedList<Libro>();
		
		for (Libro libro : listaLibros) {
			if (libro.getEstado() != false && libro.getStock() > 0) {
				flag = true;
				listaLibrosDisponibles.add(libro);
			}
		}
     
		if (listaLibros.isEmpty()) {
			return listaLibrosDisponibles;
		} else if (flag) {
			return listaLibrosDisponibles;				
		} else {
			return null;			
		} 
	}
	
	/**
	 * funcion para traer un libro en especifico de la BD.
	 * @param fkLibro
	 * @return
	 */
	public static Libro verLibro(int fkLibro) {
		Libro libro = null;
		
		try {
	          PreparedStatement stmt = con.prepareStatement("SELECT * FROM libro WHERE id_libro=?");
	          stmt.setInt(1, fkLibro);
	          
	          ResultSet rs = stmt.executeQuery();

	          if (rs.next()) {
	              int id_libro = rs.getInt("id_libro");
	              String titulo = rs.getString("titulo");
	              String autor = rs.getString("autor");
	              String editorial = rs.getString("editorial");
	              String anio = rs.getString("anio_publicacion");
	              String genero = rs.getString("genero");
	              String idioma = rs.getString("idioma");
	              String publico_objetivo = rs.getString("publico_objetivo");
	              int numPaginas = rs.getInt("num_paginas");
	              boolean firmado = rs.getBoolean("firmado");
	              boolean edicionEspecial = rs.getBoolean("edicion_especial");
	              String materialTapa = rs.getString("tapa");
	              boolean saga = rs.getBoolean("saga");
	              double precio = rs.getDouble("precio");
	              int stock = rs.getInt("stock");
	              boolean estado = rs.getBoolean("estado");
	              
	              libro = new Libro(id_libro,titulo,autor,editorial,anio,genero,idioma,publico_objetivo,numPaginas,firmado,edicionEspecial,materialTapa,saga,precio,stock,estado);
	          }
		} catch (Exception e) {
		}
		
		return libro;
	}
	
	/**
	 * funcion para actualizar el Stock de la BD.
	 * @param carritoDetalle
	 */
	public static void actualizarStock(CarritoDetalle carritoDetalle) {
		try {
            PreparedStatement statement = con.prepareStatement(
                "UPDATE Libro SET stock=? WHERE id_libro=?"
            );
            statement.setInt(1, carritoDetalle.getFkLibro().getStock());
            statement.setInt(2, carritoDetalle.getFkLibro().getId_libro());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Libro editado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}


