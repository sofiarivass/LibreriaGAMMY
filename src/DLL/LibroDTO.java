package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Libro;

public class LibroDTO {
	
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static boolean agregarLibro(Libro libro) {
//    	boolean flag = true;
//    	for (Usuario elemento : Usuario.mostrarUsuarios()) {
//			if (elemento.getEmail().equals(usuario.getEmail())) {
//				flag = false;
//				break;
//			}
//		}
//    	if (flag) {
//			descomentar para usar for para validar
	
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO libro (titulo, autor, editorial, anio_publicacion, genero, idioma, publico_objetivo, num_paginas, firmado, edicion_especial, tapa, saga, precio, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getEditorial());
            statement.setString(4, libro.getAnioPublicacion());
            //SEGUIR DESDE ACA!!!

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario agregado correctamente.");
                return true;
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
           	JOptionPane.showMessageDialog(null, "Usuario con mail ya creado");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
        return false;

//    	}else {
//    		JOptionPane.showMessageDialog(null, "Usuario con mail ya creado(con for)");
//		}
    }

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

                libros.add(new Libro(id_libro,titulo,autor,editorial,anio_publicacion,genero,idioma,publico_objetivo,num_paginas,firmado,edicion_especial,tapa,saga,precio,stock));
                }
      
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }
	
}
