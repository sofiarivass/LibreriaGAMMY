package UI;
import javax.swing.JOptionPane;

import BLL.Cliente;
import BLL.Usuario;
import BLL.Venta;
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
						Cliente.eliminarCliente(user);
						break;
					case 1: // Mostrar Clientes
						Cliente.mostrarClientes();
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
						Venta.nuevaVenta(user);
						break;
					case 1: // modificar venta
						Venta.modificarVentaLocal(user);
						break;
					case 2: // anular venta
						Venta.anularVentaLocal(user);
						break;
					case 3: // mostrar ventas
						JOptionPane.showMessageDialog(null, "mostrar ventas");
						break;
					}
				} while (opcionVenta2 != 4);
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Hasta pronto, " + user.getNombre() + " (" + user.getUsuario() +")!");
				Main frame = new Main();
				frame.setVisible(true);
				// opcion salir
				break;
			}
		} while (opcionVenta != 2);
		
	}
}
