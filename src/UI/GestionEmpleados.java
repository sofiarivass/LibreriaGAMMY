package UI;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class GestionEmpleados {
	
	
	public static void MostrarEmpleados(){
		int opcion;
		
		LinkedList<String> empleados = new LinkedList<String>();
		
		if (empleados.isEmpty()) {
			JOptionPane.showMessageDialog(null, "La lista no tiene empleados");
		} else {
			String[] elegible = new String[empleados.size()];
			for (int i = 0; i < elegible.length; i++) {
				elegible[i] = empleados.get(i);
			}
		}

			
			JOptionPane.showMessageDialog(null, "");
		
		

		
		
	}
	
	public static void CrearEmpleados(){
		int opcion;
		
		

		
		
	}
	
	public static void BajarEmpleado(){
		int opcion;
		
		

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
