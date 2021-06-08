package dataTypes;

import java.io.Serializable;
import java.util.List;

public class DTMsjVacunatorio implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	private List <Integer> vacunadoresNoAsignados;
	public DTMsjVacunatorio() {
		// TODO Auto-generated constructor stub
	}
	
	public DTMsjVacunatorio(String mensaje, List<Integer> vacunadoresNoAsignados) {
		super();
		this.mensaje = mensaje;
		this.vacunadoresNoAsignados = vacunadoresNoAsignados;
	}

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public List<Integer> getVacunadoresNoAsignados() {
		return vacunadoresNoAsignados;
	}
	public void setVacunadoresNoAsignados(List<Integer> vacunadoresNoAsignados) {
		this.vacunadoresNoAsignados = vacunadoresNoAsignados;
	}

	@Override
	public String toString() {
		return "DTMsjVacunatorio [vacunadoresNoAsignados=" + vacunadoresNoAsignados + "]";
	}

}
