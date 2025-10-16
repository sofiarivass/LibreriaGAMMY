package BLL;
import javax.swing.JOptionPane;
import DLL.UsuarioDTO;
import Repository.Encriptador;

public class Usuario {
	private int id_usuario;
	private String usuario;
	private String nombre;
	private String contrasenia;
	private boolean estado;
	private int fk_tipo_empleado;

	public Usuario(int id_usuario, String usuario, String nombre, String contrasenia, boolean estado,
			int fk_tipo_empleado) {
		super();
		this.id_usuario = id_usuario;
		this.usuario = usuario;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.fk_tipo_empleado = fk_tipo_empleado;
	}
	public Usuario(int id_usuario, String usuario, String nombre, boolean estado, int fk_tipo_empleado) {
		this.id_usuario = id_usuario;
		this.usuario = usuario;
		this.nombre = nombre;
		this.estado = estado;
		this.fk_tipo_empleado = fk_tipo_empleado;
	}

	// Getter y Setters
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isEstado() {
		return estado;
	}
	
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getFk_tipo_empleado() {
		return fk_tipo_empleado;
	}

	public void setFk_tipo_empleado(int fk_tipo_empleado) {
		this.fk_tipo_empleado = fk_tipo_empleado;
	}

	@Override
	public String toString() {
		return "\n ID: " + id_usuario + "\nUsuario: " + usuario + "\nNombre: " + nombre + "\nEstado: " + (estado == true ? "Empleado" : "Desempleado") + "\nTipo de empleado: " + (fk_tipo_empleado == 1 ? "Admin" : "Vendedor Local");
		//Como hago para mostar dependiedno el tipo de empleado
		/*return "Usuario [id_usuario=" + id_usuario + ", usuario=" + usuario + ", nombre=" + nombre + ", contrasenia="
				+ contrasenia + ", estado=" + estado + ", fk_tipo_empleado=" + fk_tipo_empleado + "]";*/
	}

	/**
	 * Se piden los datos de login y se envian a la funcion del DTO
	 * 
	 * @return Usuario
	 */
	public static Usuario login() {

		String usuario = Repository.Validaciones.validarVacio("Ingrese nombre de usuario:", "Login", null);
		String contrasenia = Repository.Validaciones.validarVacio("Ingrese contraseña:", "Login", null);

		Usuario user = UsuarioDTO.login(usuario, Encriptador.encriptar(contrasenia));

		if (user == null) {
			JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
		} else {
			JOptionPane.showMessageDialog(null, "Bienvenido " + user.getNombre() + "!");			
		}

		return user;
	}

}
