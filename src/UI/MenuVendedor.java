package UI;
import javax.swing.JOptionPane;
import BLL.Usuario;
import Enums.*;

public class MenuVendedor {
	public static void Menu(Usuario user) {
		
		int opcionVenta, opcionVenta1, opcionVenta2;
		
		do {
			opcionVenta = JOptionPane.showOptionDialog(null, "Elija una opción", "Menú Vendedor Local", 0,
					1, null, Enums.MenuVendedor.values(), Enums.MenuVendedor.values());
			switch (opcionVenta) {
			case 0: // gestionar clientes
				do {
					opcionVenta1 = JOptionPane.showOptionDialog(null, "Elija una opción",
							"Menú Vendedor Local | Gestion de Clientes", 0, 1, null,
							GestionarClientes.values(), GestionarClientes.values());
					switch (opcionVenta1) {
					case 0: // Modificar o eliminar datos de un cliente
						JOptionPane.showMessageDialog(null, "Modificar o eliminar datos de un cliente");
						break;
					case 1: // Mostrar Clientes
						JOptionPane.showMessageDialog(null, "mostrando clientes");
						break;
					}
				} while (opcionVenta1 != 2);
				break;
			case 1: // gestionar ventas
				do {
					opcionVenta2 = JOptionPane.showOptionDialog(null, "Elija una opción",
							"Menú Vendedor Local | Gestion de Ventas", 0, 1, null, GestionarVentas.values(),
							GestionarVentas.values());
					switch (opcionVenta2) {
					case 0: // nueva venta
						JOptionPane.showMessageDialog(null, "nueva venta");
						break;
					case 1: // modificar venta
						JOptionPane.showMessageDialog(null, "modificar venta");
						break;
					case 2: // anular venta
						JOptionPane.showMessageDialog(null, "anular venta");
						break;
					case 3: // mostrar ventas
						JOptionPane.showMessageDialog(null, "mostrar ventas");
						break;
					}
				} while (opcionVenta2 != 4);
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Hasta pronto, " + user.getNombre() + " (" + user.getUsuario() +")!");
				// opcion salir
				break;
			}
		} while (opcionVenta != 2);
		
	}
}
