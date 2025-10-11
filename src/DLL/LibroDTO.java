package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import BLL.CarritoDetalle;
import BLL.Libro;

public class LibroDTO {
	private static Connection con = Conexion.getInstance().getConnection();
	
	/**
	 * funcion para traer todos los libros de la BD.
	 * @return
	 */
	public static LinkedList<Libro> elegirLibros() {
		boolean flag = false;
		LinkedList<Libro> listaLibros = new LinkedList<Libro>();
		
		try {
          PreparedStatement stmt = con.prepareStatement("SELECT * FROM libro");
          ResultSet rs = stmt.executeQuery();

          while (rs.next()) {
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
              
              listaLibros.add(new Libro(id_libro,titulo,autor,editorial,anio,genero,idioma,publico_objetivo,numPaginas,firmado,edicionEspecial,materialTapa,saga,precio,stock));
          }
          
          for (int i = 0; i < listaLibros.size(); i++) {
			if (listaLibros.get(i).getStock() > 0) {
				flag = true;
				break;
			}
          }
          
		} catch (Exception e) {
		}
		
		if (listaLibros.isEmpty()) {
			return null;				
		} else if(!(listaLibros.isEmpty()) && flag != false){
			return listaLibros;			
		} else {
			return null;
		}
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
                System.out.println("Usuario editado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
