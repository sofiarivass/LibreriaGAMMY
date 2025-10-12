package BLL;
import java.time.LocalDate;

public class Venta {
	private int idVenta;
	private double totalVenta;
	private LocalDate fechaVenta;
	private String metodoPago;
	private String moneda;
	private String estado;
	private TipoVenta fkTipoVenta;
	private Descuento fkDescuento;
	private Carrito fkCarrito;
	private Usuario fkUsuario;
	
	
	public Venta(int idVenta, double totalVenta, LocalDate fechaVenta, String metodoPago, String moneda, String estado,
			TipoVenta fkTipoVenta, Descuento fkDescuento, Carrito fkCarrito, Usuario fkUsuario) {
		this.idVenta = idVenta;
		this.totalVenta = totalVenta;
		this.fechaVenta = fechaVenta;
		this.metodoPago = metodoPago;
		this.moneda = moneda;
		this.estado = estado;
		this.fkTipoVenta = fkTipoVenta;
		this.fkDescuento = fkDescuento;
		this.fkCarrito = fkCarrito;
		this.fkUsuario = fkUsuario;
	}
	
	public Venta(int idVenta, LocalDate fechaVenta, String metodoPago, String moneda, String estado) {
		this.idVenta = idVenta;
		this.fechaVenta = fechaVenta;
		this.metodoPago = metodoPago;
		this.moneda = moneda;
		this.estado = estado;
	}

	public Venta(double totalVenta, LocalDate fechaVenta, String metodoPago, String moneda, String estado, TipoVenta fkTipoVenta,
			Carrito fkCarrito, Usuario fkUsuario) {
		this.totalVenta = totalVenta;
		this.fechaVenta = fechaVenta;
		this.metodoPago = metodoPago;
		this.moneda = moneda;
		this.estado = estado;
		this.fkTipoVenta = fkTipoVenta;
//		this.fkDescuento = fkDescuento; solo por ahora porque no es parte del MVP
		this.fkCarrito = fkCarrito;
		this.fkUsuario = fkUsuario;
	}


	// Getters y Setters
	public int getIdVenta() {
		return idVenta;
	}


	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}


	public double getTotalVenta() {
		return totalVenta;
	}


	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}


	public LocalDate getFechaVenta() {
		return fechaVenta;
	}


	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}


	public String getMetodoPago() {
		return metodoPago;
	}


	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}


	public String getMoneda() {
		return moneda;
	}


	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public TipoVenta getFkTipoVenta() {
		return fkTipoVenta;
	}


	public void setFkTipoVenta(TipoVenta fkTipoVenta) {
		this.fkTipoVenta = fkTipoVenta;
	}


	public Descuento getFkDescuento() {
		return fkDescuento;
	}


	public void setFkDescuento(Descuento fkDescuento) {
		this.fkDescuento = fkDescuento;
	}


	public Carrito getFkCarrito() {
		return fkCarrito;
	}


	public void setFkCarrito(Carrito fkCarrito) {
		this.fkCarrito = fkCarrito;
	}


	public Usuario getFkUsuario() {
		return fkUsuario;
	}


	public void setFkUsuario(Usuario fkUsuario) {
		this.fkUsuario = fkUsuario;
	}





	

	

	
	

	// Métodos
	

}
