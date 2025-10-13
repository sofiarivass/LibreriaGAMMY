package BLL;
import DLL.TipoEmpleadoDTO;

public class TipoEmpleado {
	private int idTipoEmpleado;
	private String tipoEmpleado;
	
	public TipoEmpleado(int idTipoEmpleado, String tipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
		this.tipoEmpleado = tipoEmpleado;
	}
	
	public int getIdTipoEmpleado() {
		return idTipoEmpleado;
	}
	public void setIdTipoEmpleado(int idTipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
	}
	
	public String getTipoEmpleado() {
		return tipoEmpleado;
	}
	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
	
	// Métodos
	
	// me trae la tabla tipo empleado de la BD y me crea el objeto TipoEmpleado
	public static TipoEmpleado tipoEmpleado(int fkEmpleado) {
		return TipoEmpleadoDTO.tipoEmpleado(fkEmpleado);
	}
	
	
}
