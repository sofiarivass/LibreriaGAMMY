package UI;

import javax.swing.JOptionPane;

import BLL.Usuario;
import Enums.GestionarClientes;
import Enums.GestionarExportaciones;
import Enums.GestionarInventario;

public class MenuVendedorInternacional {

	public static void Menu(Usuario user) {
		
		int opcionVI, opcionVI1, opcionVI2, opcionVI3;
		
		do {
			opcionVI = JOptionPane.showOptionDialog(null, "Elija una opción", "Menú Vendedor Internacional",
					0, 1, null, Enums.MenuVendedorInternacional.values(), Enums.MenuVendedorInternacional.values());
			switch (opcionVI) {
			case 0: // gestionar clientes
				do {
					opcionVI1 = JOptionPane.showOptionDialog(null, "Elija una opción",
							"Menú Vendedor Internacional | Gestion de Clientes", 0, 1, null,
							GestionarClientes.values(), GestionarClientes.values());
					switch (opcionVI1) {
					case 0: // Modificar o eliminar datos de un cliente
						JOptionPane.showMessageDialog(null, "Modificar o eliminar datos de un cliente");
						break;
					case 1: // Mostrar Clientes
						JOptionPane.showMessageDialog(null, "mostrando clientes");
						break;
					}
				} while (opcionVI1 != 2);
				break;
			case 1: // gestionar exportaciones
				do {
					opcionVI2 = JOptionPane.showOptionDialog(null, "Elija una opción",
							"Menú Vendedor Internacional | Gestion de Exportaciones", 0, 1, null,
							GestionarExportaciones.values(), GestionarExportaciones.values());
					switch (opcionVI2) {
					case 0: // nueva exportación
						JOptionPane.showMessageDialog(null, "nueva exportación");
						break;
					case 1: // modificar exportación
						JOptionPane.showMessageDialog(null, "modificar exportación");
						break;
					case 2: // anular exportación
						JOptionPane.showMessageDialog(null, "anular exportación");
						break;
					case 3: // mostrar exportaciones
						JOptionPane.showMessageDialog(null, "mostrar exportaciones");
						break;
					}
				} while (opcionVI2 != 4);
				break;
			case 2: // seguimiento de envíos
				JOptionPane.showMessageDialog(null, "seguimiento de envios");
				break;
			case 3: // gestionar inventario
				do {
					opcionVI3 = JOptionPane.showOptionDialog(null, "Elija una opción",
							"Menú Vendedor Internacional | Gestion de Inventario", 0, 1, null,
							GestionarInventario.values(), GestionarInventario.values());
					switch (opcionVI3) {
					case 0: // cargar productos
						JOptionPane.showMessageDialog(null, "cargar productos");
						break;
					case 1: // modificar productos
						JOptionPane.showMessageDialog(null, "modificar productos");
						break;
					case 2: // eliminar productos
						JOptionPane.showMessageDialog(null, "eliminar productos");
						break;
					case 3: // mostrar productos
						JOptionPane.showMessageDialog(null, "mostrar productos");
						break;
					}
				} while (opcionVI3 != 4);
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Hasta pronto, " + user.getNombre() + " (" + user.getUsuario() +")!");
				// opcion salir
				break;
			}
		} while (opcionVI != 4);
		
	
	}

	
}
