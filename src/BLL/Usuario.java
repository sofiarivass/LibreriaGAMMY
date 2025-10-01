package BLL;

import javax.swing.JOptionPane;

import DLL.UsuarioDTO;

public class Usuario {
	private String usuario;
	private String contrasenia;
	private String tipo;
	private boolean estado;

	public Usuario(String usuario, String contrasenia, String tipo, boolean estado) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.tipo = tipo;
		this.estado = estado;
	}

	// Getter y Setters

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contrasenia=" + contrasenia + ", tipo=" + tipo + ", estado=" + estado
				+ "]";
	}
	
	public static Usuario Login() {
		//aca se piden los datos de login y se envian a la funcion del DTO
		
		Usuario pepe = UsuarioDTO.Login("pepe1", "123");
		if (pepe == null) {
			JOptionPane.showMessageDialog(null, "error");
		} else {
			JOptionPane.showMessageDialog(null, pepe.toString());
		}
		
		return pepe;
	}

}
