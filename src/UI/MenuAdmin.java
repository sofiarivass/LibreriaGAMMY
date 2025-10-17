package UI;
import javax.swing.JOptionPane;
import BLL.Admin;
import BLL.Usuario;
import Enums.*;

public class MenuAdmin {
	public static void Menu(Usuario user) {
		
		int opcionAdmin, opcionAdmin1,opcionAdmin2,opcionAdmin3;
		
		do {
			opcionAdmin = JOptionPane.showOptionDialog(null, "Elija una opción", "Menú Admin", 0, 1, null,
					Enums.MenuAdmin.values(), Enums.MenuAdmin.values());
			switch (opcionAdmin) {
			case 0: // gestionar empleados
				do {
					opcionAdmin1 = JOptionPane.showOptionDialog(null, "Elija una opción",
							"Menú Admin | Gestion de Empleados", 0, 1, null, GestionarEmpleados.values(),
							GestionarEmpleados.values());
					switch (opcionAdmin1) {
					case 0: // mostrar empleados
						Admin.mostrarEmpleados();
						/*JOptionPane.showMessageDialog(null, "mostrando empleados");
						
						GestionEmpleados.MostrarEmpleados();*/
						
						break;
					case 1: // crear empleado
						/*JOptionPane.showMessageDialog(null, "crear empleado");
						
						GestionEmpleados.CrearEmpleados();*/
						
						break;
					case 2: // dar de baja empleado
						Admin.eliminarEmpleados();
						/*JOptionPane.showMessageDialog(null, "dar de baja empleado");
						
						GestionEmpleados.BajarEmpleado();*/
						
						break;
					}
				} while (opcionAdmin1 != 3);
				break;
			case 1: // estadisticas de venta y popularidad
				do {
					opcionAdmin2 = JOptionPane.showOptionDialog(null, "Elija una opción",
							"Menú Admin | Estadisticas de Venta y Popularidad", 0, 1, null,
							EstadisticasVentaPopularidad.values(), EstadisticasVentaPopularidad.values());
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
				do {
					opcionAdmin3 = JOptionPane.showOptionDialog(null, "Elija una opción",
							"Menú Admin | Gestion de Descuentos", 0, 1, null, GestionarDescuentos.values(),
							GestionarDescuentos.values());
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
					case 3: // mostrar descuentos
						JOptionPane.showMessageDialog(null, "mostrando descuentos");
						break;
					}
				} while (opcionAdmin3 != 4);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Hasta pronto, " + user.getNombre() + " (" + user.getUsuario() +")!");
				// opcion salir
				break;
			}
		} while (opcionAdmin != 3);
	}
}
