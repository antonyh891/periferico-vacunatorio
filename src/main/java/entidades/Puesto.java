package entidades;



import java.util.HashMap;

import java.util.Map;
import java.time.LocalDate;

public class Puesto {
	
	private int id;
	private Vacunatorio vacunatorio;
	private Map<LocalDate, Puesto_Vacunador> vacunadorAsignadoPorFecha;
	

	public Puesto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Puesto(int id,  Vacunatorio idVac ) {
		super();
		this.id = id;
		this.vacunatorio = idVac;
		this.vacunadorAsignadoPorFecha = new HashMap<LocalDate,Puesto_Vacunador>();
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Vacunatorio getVacunatorio() {
		return vacunatorio;
	}


	public void setVacunatorio(Vacunatorio vacunatorio) {
		this.vacunatorio = vacunatorio;
	}


	


	public Map<LocalDate, Puesto_Vacunador> getVacunadorAsignadoPorFecha() {
		return vacunadorAsignadoPorFecha;
	}


	public void setVacunadorAsignadoPorFecha(Map<LocalDate, Puesto_Vacunador> vacunadorAsignadoPorFecha) {
		this.vacunadorAsignadoPorFecha = vacunadorAsignadoPorFecha;
	}


	public void agregarPuestoVacunador (Puesto_Vacunador pv) {
		vacunadorAsignadoPorFecha.put(pv.getFecha(), pv);
	}


	
	


	
	
	

	
	
	
}
