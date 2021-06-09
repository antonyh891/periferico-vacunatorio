package dataTypes;



public class DTInformacionVacunador {
	private String fecha;
	private int puesto;
	public DTInformacionVacunador() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DTInformacionVacunador(String fecha, int puesto) {
		super();
		this.fecha = fecha;
		this.puesto = puesto;
	}


	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getPuesto() {
		return puesto;
	}
	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	
}

