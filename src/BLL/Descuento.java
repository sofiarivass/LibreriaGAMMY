package BLL;

public class Descuento {
	private int idDescuento;
	private String tipoDescuento;
	private int porcentaje;
	
	public Descuento(int idDescuento, String tipoDescuento, int porcentaje) {
		this.idDescuento = idDescuento;
		this.tipoDescuento = tipoDescuento;
		this.porcentaje = porcentaje;
	}
	
	public int getIdDescuento() {
		return idDescuento;
	}
	public void setIdDescuento(int idDescuento) {
		this.idDescuento = idDescuento;
	}
	public String getTipoDescuento() {
		return tipoDescuento;
	}
	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
}
