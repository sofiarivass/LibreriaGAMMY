package Usuario;

import javax.swing.JOptionPane;

import Enums.MenuPrincipal;

public class Main {

	public static void main(String[] args) {
		// MENU PRINCIPAL
		int opcion = JOptionPane.showOptionDialog(null,
				"¡Bienvenido al sistema de Librería GAMMY!\nPor favor elija una opción", "", 0, 0, null, MenuPrincipal.values(), MenuPrincipal.values());

	}

}
