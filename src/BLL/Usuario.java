package BLL;
import DLL.UsuarioDTO;
import Repository.Encriptador;

public class Usuario {
	private int id_usuario;
	private String nombre;
	private String usuario;
	private String contrasenia;
	private boolean estado;
	private TipoEmpleado fkTipoEmpleado;

	public Usuario(int id_usuario, String nombre, String usuario, String contrasenia, boolean estado,
			TipoEmpleado fkTipoEmpleado) {
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.fkTipoEmpleado = fkTipoEmpleado;
	}
	
	public Usuario(String nombre, String usuario, String contrasenia, boolean estado,
			TipoEmpleado fkTipoEmpleado) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.fkTipoEmpleado = fkTipoEmpleado;
	}
	
	public Usuario(int id_usuario, String nombre, String usuario, boolean estado,
			TipoEmpleado fkTipoEmpleado) {
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.usuario = usuario;
		this.estado = estado;
		this.fkTipoEmpleado = fkTipoEmpleado;
	}
	
	public Usuario(String usuario2, String nombre2, String contrasenia2, int tipo_empleado) {
	}

	// Getter y Setters
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public TipoEmpleado getFkTipoEmpleado() {
		return fkTipoEmpleado;
	}

	public void setFkTipoEmpleado(TipoEmpleado fkTipoEmpleado) {
		this.fkTipoEmpleado = fkTipoEmpleado;
	}

	@Override
	public String toString() {
		return "\n ID: " + id_usuario 
				+ "\nUsuario: " + usuario 
				+ "\nNombre: " + nombre 
				+ "\nEstado: " + (estado == true ? "Empleado" : "Desempleado") 
				+ "\nTipo de empleado: " + fkTipoEmpleado.getTipoEmpleado();
		//Como hago para mostar dependiedno el tipo de empleado
		/*return "Usuario [id_usuario=" + id_usuario + ", usuario=" + usuario + ", nombre=" + nombre + ", contrasenia="
				+ contrasenia + ", estado=" + estado + ", fk_tipo_empleado=" + fk_tipo_empleado + "]";*/
	}

	/**
	 * Se piden los datos de login y se envian a la funcion del DTO
	 * 
	 * @return Usuario
	 */

	public static Usuario login(String usuario,String contrasenia) {		

		Usuario user = UsuarioDTO.login(usuario, Encriptador.encriptar(contrasenia));
		
//		if (user == null) {
//			JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
//		} else {
//			if (user.getEstado() == false) {
//				JOptionPane.showMessageDialog(null, "El usuario ingresado, esta dado de baja");
//				user = null;
//			}else {
//				JOptionPane.showMessageDialog(null, "Bienvenido " + user.getNombre() + "!");
//			}
//		}
		return user;
	}



}
