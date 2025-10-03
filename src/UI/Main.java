package UI;

import javax.swing.JOptionPane;

import BLL.Usuario;
import Enums.*;

public class Main {

	public static void main(String[] args) {

		int opcionLogin;

		// MENU PRINCIPAL
		do {
			opcionLogin = JOptionPane.showOptionDialog(null,
					"¡Bienvenido al sistema de Librería GAMMY!\nPor favor elija una opción", "Menú Principal", 0, 1,
					null, MenuPrincipal.values(), MenuPrincipal.values());

			switch (opcionLogin) {
			// login
			case 0:
				Usuario user = Usuario.login();
				if (user == null) {
					break;
				}

				int tipo_empleado = user.getFk_tipo_empleado();

				switch (tipo_empleado) {
				// MENU ADMIN :)
				case 1:
					MenuAdmin.Menu(user);
					break;
				// MENU VENDEDOR
				case 2:
					MenuVendedor.Menu(user);
					break;
				// MENU VENDEDOR INTERNACIONAL
				case 3:
					MenuVendedorInternacional.Menu(user);
					break;
				}
				break;
			case 1: // info
				JOptionPane.showMessageDialog(null, "info sobre el programa");
				break;
			case 2: // salir
				JOptionPane.showMessageDialog(null, "Vuelva pronto!!");
				break;
			}
		} while (opcionLogin != 2);

	}

}
