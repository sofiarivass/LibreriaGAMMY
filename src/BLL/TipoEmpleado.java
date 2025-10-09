package BLL;

public class TipoEmpleado {
	private int idTipoEmpleado;
	private String tipoEmpleado;
	
	public TipoEmpleado(int idTipoEmpleado, String tipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
		this.tipoEmpleado = tipoEmpleado;
	}
	public TipoEmpleado(int idTipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
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
	
	
}
