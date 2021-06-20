package dataTypes;

import java.util.List;

public class DTAsignarVacunadores {
	private String fecha;
	private List<Integer> cedulas;
	
	public DTAsignarVacunadores() {
		
	}
	

	public DTAsignarVacunadores(String fecha, List<Integer> cedulas) {
		super();
		this.fecha = fecha;
		this.cedulas = cedulas;
	}


	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<Integer> getCedulas() {
		return cedulas;
	}

	public void setCedulas(List<Integer> cedulas) {
		this.cedulas = cedulas;
	}
	

}
