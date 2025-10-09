package BLL;

public class TipoVenta {
	private int idTipoVenta;
	private String tipo;
	
	public TipoVenta(int idTipoVenta, String tipo) {
		this.idTipoVenta = idTipoVenta;
		this.tipo = tipo;
	}
	
	public TipoVenta(String tipo) {
		this.tipo = tipo;
	}
	
	public int getIdTipoVenta() {
		return idTipoVenta;
	}
	public void setIdTipoVenta(int idTipoVenta) {
		this.idTipoVenta = idTipoVenta;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
