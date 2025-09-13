package Usuario;

import javax.swing.JOptionPane;
import Enums.*;

public class Main {

	public static void main(String[] args) {

		int opcionLogin, opcionUsuario, opcionAdmin, opcionAdmin1, opcionAdmin2, opcionAdmin3, opcionVI, opcionVI1,
				opcionVI2, opcionVI3, opcionVenta, opcionVenta1, opcionVenta2;

		// MENU PRINCIPAL
		do {
			opcionLogin = JOptionPane.showOptionDialog(null,
					"¡Bienvenido al sistema de Librería GAMMY!\nPor favor elija una opción", "Menú Principal", 0, 1,
					null, MenuPrincipal.values(), MenuPrincipal.values());

			switch (opcionLogin) {
			// login
			case 0:
				// MENU PROVISIONAL ELEGIR USUARIO
				do {
					opcionUsuario = JOptionPane.showOptionDialog(null,
							"Elija un usuario para acceder a su menú\n(este menú es provisional, después el login llevará directo al menú correspondiente)",
							"Menú Usuario", 0, 1, null, MenuUsuario.values(), MenuUsuario.values());

					switch (opcionUsuario) {
					case 0:
						// MENU ADMIN
						do {
							opcionAdmin = JOptionPane.showOptionDialog(null, "Elija una opción", "Menú Admin", 0, 1,
									null, MenuAdmin.values(), MenuAdmin.values());
							switch (opcionAdmin) {
							case 0: // gestionar empleados
								do {
									opcionAdmin1 = JOptionPane.showOptionDialog(null, "Elija una opción",
											"Menú Admin | Gestion de Empleados", 0, 1, null,
											GestionarEmpleados.values(), GestionarEmpleados.values());
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
								do {
									opcionAdmin2 = JOptionPane.showOptionDialog(null, "Elija una opción",
											"Menú Admin | Estadisticas de Venta y Popularidad", 0, 1, null,
											EstadisticasVentaPopularidad.values(),
											EstadisticasVentaPopularidad.values());
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
											"Menú Admin | Gestion de Descuentos", 0, 1, null,
											GestionarDescuentos.values(), GestionarDescuentos.values());
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
								JOptionPane.showMessageDialog(null, "Hasta pronto, Admin!!");
								// opcion salir
								break;
							}
						} while (opcionAdmin != 3);
						break;
					case 1:
						// MENU VENDEDOR
						do {
							opcionVenta = JOptionPane.showOptionDialog(null, "Elija una opción",
									"Menú Vendedor Local", 0, 1, null, MenuVendedor.values(),
									MenuVendedor.values());
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
									}
								} while (opcionVenta1 != 1);
								break;
							case 1: // gestionar ventas
								do {
									opcionVenta2 = JOptionPane.showOptionDialog(null, "Elija una opción",
											"Menú Vendedor Local | Gestion de Ventas", 0, 1, null,
											GestionarVentas.values(),
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
								JOptionPane.showMessageDialog(null, "Hasta pronto, Vendedor Local!!");
								// opcion salir
								break;
							}
						} while (opcionVenta != 2);
						break;
					case 2:
						// MENU VENDEDOR INTERNACIONAL
						do {
							opcionVI = JOptionPane.showOptionDialog(null, "Elija una opción",
									"Menú Vendedor Internacional", 0, 1, null, MenuVendedorInternacional.values(),
									MenuVendedorInternacional.values());
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
									}
								} while (opcionVI1 != 1);
								break;
							case 1: // gestionar exportaciones
								do {
									opcionVI2 = JOptionPane.showOptionDialog(null, "Elija una opción",
											"Menú Vendedor Internacional | Gestion de Exportaciones", 0, 1, null,
											GestionarExportaciones.values(),
											GestionarExportaciones.values());
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
											GestionarInventario.values(),
											GestionarInventario.values());
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
								JOptionPane.showMessageDialog(null, "Hasta pronto, Vendedor Internacional!!");
								// opcion salir
								break;
							}
						} while (opcionVI != 4);
						break;
					}
				} while (opcionUsuario != 3);
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
