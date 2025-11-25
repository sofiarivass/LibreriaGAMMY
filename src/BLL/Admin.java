package BLL;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import DLL.UsuarioDTO;
import Repository.Encriptador;

public class Admin extends Usuario {
	private LinkedList<Usuario> listaEmpleados = new LinkedList<Usuario>();

	public Admin(int id_usuario, String nombre, String usuario, String contrasenia, boolean estado,
			TipoEmpleado fkTipoEmpleado, LinkedList<Usuario> listaEmpleados) {
		super(id_usuario, nombre, usuario, contrasenia, estado, fkTipoEmpleado);
		this.listaEmpleados = listaEmpleados;
	}

	// Getter y Setters
	public LinkedList<Usuario> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(LinkedList<Usuario> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	// Métodos
	@Override
	public String toString() {
		return "Admin [listaEmpleados=" + listaEmpleados + "]";
	}
	
//	public static LinkedList<Usuario> mostrarEmpleados() {
//		LinkedList<Usuario> usuario = UsuarioDTO.mostrarUsuarios();
//		
//		if (usuario == null || usuario.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "No hay empleados para mostrar.");
//		}else {
//			JOptionPane.showMessageDialog(null, UsuarioDTO.usuarioPorID(usuario));
//		}
//		return usuario;
//	}
	
	public static LinkedList<Usuario> mostrarEmpleados() {
		LinkedList<Usuario> usuarios = UsuarioDTO.mostrarUsuarios();
		
		if (usuarios == null || usuarios.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay empleados para mostrar.");
			return null;
		}else {
//			JOptionPane.showMessageDialog(null, UsuarioDTO.usuarioPorID(usuario));
			return usuarios;
		}
	}
	
	public static Usuario cargarDatosUsuario(String accion) {
		LinkedList<Usuario> usuarios = UsuarioDTO.mostrarUsuarios();
		LinkedList<TipoEmpleado> listaTipos = UsuarioDTO.seleccionarTipoEmpleado();
		String[] tipos = new String[listaTipos.size()];
		String[] eleccion;
		String usuario, nombre, contrasenia, seleccion;
		boolean estadoEmpleado, flag;
		Usuario usuario2 = null;
		TipoEmpleado tipo_empleado = null;

		if (accion.equals("Crear")) {
			do {
				flag = false;
				usuario = Repository.Validaciones.validarVacio("Ingrese usuario:", "Cargar usuario", null);
				for (Usuario user : usuarios) {
					if (user.getUsuario().equalsIgnoreCase(usuario)) {
						JOptionPane.showMessageDialog(null, "El nombre de Usuario ya Existe!!");
						flag = true;
						break;
					}
				}
			} while (flag);
			nombre = Repository.Validaciones.validarString("Ingrese nombre del usuario:", "Cargar usuario", null);
			contrasenia = Repository.Validaciones.validarVacio("Ingrese la contraseña:", "Cargar usuario", null);
			estadoEmpleado = true;
			
			for (int i = 0; i < tipos.length; i++) {
				tipos[i] = listaTipos.get(i).getIdTipoEmpleado() + " - " + listaTipos.get(i).getTipoEmpleado();
			}
			
			seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de Empleado", "Seleccion Tipo Empleado", 1, null, tipos, tipos[0]);
			eleccion = seleccion.split(" - ");
			
			for (TipoEmpleado lista : listaTipos) {
				if (lista.getIdTipoEmpleado() == Integer.parseInt(eleccion[0])) {
					tipo_empleado = lista;
					break;
				}
			}
			
			usuario2 = new Usuario(nombre,usuario, Encriptador.encriptar(contrasenia), estadoEmpleado, tipo_empleado);
		}
		return usuario2;
	}
	
	

	public static void eliminarEmpleados(Usuario admin) {
		String elegido;
		String []elegido2;
		int usuarioElegido;
		LinkedList<Usuario> usuarios = UsuarioDTO.mostrarUsuarios();
		LinkedList<Usuario> usuariosActivos = new LinkedList<Usuario>();
		
		for (Usuario usuario : usuarios) {
			if (usuario.getEstado() != false && admin.getId_usuario() != usuario.getId_usuario()) {
				System.out.println("entre porque mi estado es true");
				usuariosActivos.add(usuario);					
			}
		}
		
		if (usuariosActivos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay más Empleados Activos!!");
		} else {
			String[] elegirUsuario = new String[usuariosActivos.size()];
			
			for (int i = 0; i < elegirUsuario.length; i++) {
				elegirUsuario[i] = usuariosActivos.get(i).getId_usuario() + "," + usuariosActivos.get(i).getUsuario();
			}
			
			elegido = (String) JOptionPane.showInputDialog(null, "Elija el usuario", "", 0, null, elegirUsuario, elegirUsuario[0]);
			elegido2 = elegido.split(",");
			
			usuarioElegido = Integer.parseInt(elegido2[0]);
			UsuarioDTO.eliminarUsuarioPorID(usuarioElegido);			
		}
	}
	
	public static boolean nuevoEmpleado() {
		Usuario nuevo = cargarDatosUsuario("Crear");
		return UsuarioDTO.agregarUsuario(nuevo);			
	}
	
}
