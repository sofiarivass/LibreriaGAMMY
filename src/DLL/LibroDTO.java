package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import BLL.Libro;

public class LibroDTO {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static LinkedList<Libro> elegirLibros() {
		LinkedList<Libro> listaLibros = new LinkedList<Libro>();
		
		try {
          PreparedStatement stmt = con.prepareStatement("SELECT * FROM libro");
          ResultSet rs = stmt.executeQuery();

          while (rs.next()) {
              int isbn = rs.getInt("id_libro");
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
              
              listaLibros.add(new Libro(isbn,titulo,autor,editorial,anio,genero,idioma,publico_objetivo,numPaginas,firmado,edicionEspecial,materialTapa,saga,precio,stock));
          }
          
		} catch (Exception e) {
          e.printStackTrace();
		}
		return listaLibros;
	}
	
	
	
	
	
}
