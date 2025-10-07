package Repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public interface Validaciones {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * Validar que el mail siga el formato de texto@texto.texto
	 * 
	 * @param emailStr
	 * @return boolean
	 */
	public static boolean validarMail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.matches();
	}

	/**
	 * Método para validar Strings. Evita que el usuario deje el campo vacío y que
	 * ingrese números
	 * 
	 * @param mensaje
	 * @param titulo
	 * @param img
	 * @return String
	 */
	public static String validarString(String mensaje, String titulo, String img) {
		String input = "";
		boolean flag = false;

		while (flag == false) {

			do {
				if (img == null) {
					input = JOptionPane.showInputDialog(mensaje);
				} else {
					input = (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Validaciones.class.getResource("/img/" + img)), null, null);
				}
			} while (input == null);

			if (input.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor complete este campo");
				flag = false;
			} else {
				int contN = 0;

				for (int i = 0; i < input.length(); i++) {
					if (Character.isDigit(input.charAt(i))) {
						contN++;
					}
				}

				if (contN > 0) {
					JOptionPane.showMessageDialog(null, "No se permiten números");
					flag = false;
				} else {
					flag = true;
				}
			}
		}
		return input;
	}

	/**
	 * Método para validar Strings. Evita que el usuario deje el campo vacío.
	 * 
	 * @param mensaje
	 * @param titulo
	 * @param img
	 * @return String
	 */
	public static String validarVacio(String mensaje, String titulo, String img) {
		String input = "";
		boolean flag = false;

		while (flag == false) {

			do {
				if (img == null) {
					input = JOptionPane.showInputDialog(mensaje);
				} else {
					input = (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Validaciones.class.getResource("/img/" + img)), null, null);
				}
			} while (input == null);

			if (input.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor complete este campo");
				flag = false;
			} else {
				flag = true;
			}
		}
		return input;
	}

	/**
	 * Método para validar Contraseñas. Evita que el usuario deje el campo vacío y
	 * pide que se incluya al menos una letra y un número
	 * 
	 * @param mensaje
	 * @param titulo
	 * @param img
	 * @return String
	 */
	public static String validarContrasenia(String mensaje, String titulo, String img) {
		String input = "";
		boolean flag = false;

		while (flag == false) {
			do {
				if (img == null) {
					input = JOptionPane.showInputDialog(mensaje);
				} else {
					input = (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Validaciones.class.getResource("/img/" + img)), null, null);
				}
			} while (input == null);

			if (input.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor complete este campo");
				flag = false;
			} else {

				boolean tieneLetra = false;
				boolean tieneDigito = false;

				for (int i = 0; i < input.length(); i++) {
					if (Character.isAlphabetic(input.charAt(i))) {
						tieneLetra = true;
					} else if (Character.isDigit(input.charAt(i))) {
						tieneDigito = true;
					}
				}

				if (tieneLetra == false || tieneDigito == false) {
					JOptionPane.showMessageDialog(null, "La contraseña debe incluir al menos una letra y un número.");
					flag = false;
				} else {
					flag = true;
				}
			}
		}
		return input;
	}
	
	/**
	 * Método para validar enteros. Evita que el usuario deje el campo vacío y se
	 * asegura que no se ingresen letras, números negativos o números de muchas
	 * cifras que no se puedan parsear más adelante
	 * 
	 * @param mensaje
	 * @param titulo
	 * @param img
	 * @return String
	 */
	public static String validarInt(String mensaje, String titulo, String img) {
		String input = "";
		boolean numero = false;

		while (numero == false) {
			do {
				if (img == null) {
					input = JOptionPane.showInputDialog(mensaje);
				} else {
					input = (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Validaciones.class.getResource("/img/" + img)), null, null);
				}
			} while (input == null);

			if (input.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe ingresar un número");
				numero = false;
			} else {
				int contL = 0;

				for (int i = 0; i < input.length(); i++) {
					if (Character.isAlphabetic(input.charAt(i))) {
						contL++;
					}
				}

				if (contL > 0) {
					JOptionPane.showMessageDialog(null, "No se permiten letras");
					numero = false;
				} else if (input.length() >= 10) {
					JOptionPane.showMessageDialog(null, "No puede ingresar numeros tan grandes.");
					numero = false;
				} else if (Integer.parseInt(input) > 0) {
					numero = true;
				} else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un número mayor a 0");
					numero = false;
				}
			}
		}
		return input;
	}

	
	/**
	 * Método para validar double. Evita que el usuario deje el campo vacío y se
	 * asegura que no se ingresen letras ni números negativos
	 * 
	 * @param mensaje
	 * @param titulo
	 * @param img
	 * @return double
	 */
	public static double validarDouble(String mensaje, String titulo, String img) {
		String input = "";
		boolean numero = false;

		while (numero == false) {

			if (img == null) {
				input = JOptionPane.showInputDialog(mensaje);
			} else {
				input = (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Validaciones.class.getResource("/img/" + img)), null, null);
			}
			if (input == null) {
				break;
			}

			if (input.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe ingresar un número");
				numero = false;
			} else {
				int contL = 0;

				for (int i = 0; i < input.length(); i++) {
					if (Character.isAlphabetic(input.charAt(i))) {
						contL++;
					}
				}

				if (contL > 0) {
					JOptionPane.showMessageDialog(null, "No se permiten letras");
					numero = false;
				} else if (Double.parseDouble(input) > 0) {
					numero = true;
				} else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un número mayor a 0");
					numero = false;
				}
			}
		}
		if (input == null) {
			return -1;
		} else {
			return Double.parseDouble(input);
		}

	}
	
	public static String menuSiNo(String mensaje, String titulo, String img) {
		String respuesta;
		String[] opciones = { "Sí", "No" };
		int eleccion = JOptionPane.showOptionDialog(null, mensaje, titulo, 0, JOptionPane.DEFAULT_OPTION,
				new ImageIcon(Validaciones.class.getResource("/img/" + img)), opciones, opciones[0]);

		if (eleccion == 0) {
			respuesta = "Sí";
			return respuesta;
		} else {
			respuesta = "No";
			return respuesta;
		}

	}
}
