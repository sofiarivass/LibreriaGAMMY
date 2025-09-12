package Usuario;
import javax.swing.JOptionPane;
import Enums.EstadisticasVentaPopularidad;
import Enums.GestionarDescuentos;
import Enums.GestionarEmpleados;
import Enums.MenuAdmin;
import Enums.MenuPrincipal;

public class Main {

	public static void main(String[] args) {
		// MENU PRINCIPAL
		int opcion = JOptionPane.showOptionDialog(null,
				"¡Bienvenido al sistema de Librería GAMMY!\nPor favor elija una opción", "Menú Principal", 0, 1, null, MenuPrincipal.values(), MenuPrincipal.values());
		
		// MENU ADMIN
		int opcionAdmin;
		do {
			opcionAdmin = JOptionPane.showOptionDialog(null,
					"Elija una opción", "Menú Admin", 0, 1, null, MenuAdmin.values(), MenuAdmin.values());
			switch (opcionAdmin) {
			case 0: // gestionar empleados
				int opcionAdmin1;
				do {
					opcionAdmin1 = JOptionPane.showOptionDialog(null,
							"Elija una opción", "Menú Admin | Gestion de Empleados", 0, 1, null, GestionarEmpleados.values(), GestionarEmpleados.values());
					switch (opcionAdmin1) {
					case 0: // mostrar empleados
						JOptionPane.showMessageDialog(null, "mostrando empleados");
						break;
					case 1: // crear empleado
						JOptionPane.showMessageDialog(null, "crear empleado");					
						break;
					case 2: // dar de baja empleado
						JOptionPane.showMessageDialog(null, "dar de baja empleado");										
						break;
					}
				} while (opcionAdmin1 != 3);
				break;
			case 1: // estadisticas de venta y popularidad
				int opcionAdmin2;
				do {
					opcionAdmin2 = JOptionPane.showOptionDialog(null,
							"Elija una opción", "Menú Admin | Estadisticas de Venta y Popularidad", 0, 1, null, EstadisticasVentaPopularidad.values(), EstadisticasVentaPopularidad.values());
					switch (opcionAdmin2) {
					case 0: // filtrar saga
						JOptionPane.showMessageDialog(null, "filtrando por saga");															
						break;
					case 1: // filtrar autor
						JOptionPane.showMessageDialog(null, "filtrando por autor");																				
						break;
					case 2: // filtrar pelicula/serie
						JOptionPane.showMessageDialog(null, "filtrando por pelicula y/o serie");																				
						break;
					}
				} while (opcionAdmin2 != 3);
				break;
			case 2: // gestionar descuentos
				int opcionAdmin3;
				do {
					opcionAdmin3 = JOptionPane.showOptionDialog(null,
							"Elija una opción", "Menú Admin | Gestion de Descuentos", 0, 1, null, GestionarDescuentos.values(), GestionarDescuentos.values());		
					switch (opcionAdmin3) {
					case 0: // agregar descuento
						JOptionPane.showMessageDialog(null, "agregando descuento");																									
						break;
					case 1: // editar descuento
						JOptionPane.showMessageDialog(null, "editando descuento");																														
						break;
					case 2: // eliminar descuento
						JOptionPane.showMessageDialog(null, "eliminando descuento");																									
						break;
					}					
				} while (opcionAdmin3 != 3);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "nos vemos!!");
				// opcion salir
				break;
			}
		} while (opcionAdmin != 3);
		
	}

}
