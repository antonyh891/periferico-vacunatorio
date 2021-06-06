package entidades;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

public class Puesto {
	
	private String id;
	private Vacunatorio vacunatorio;
	private int numeroVacunadores;
	private Map<LocalDate, Puesto_Vacunador> vacunadoresAsignadosPorFecha;
	

	public Puesto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Puesto(String id, int numero, Vacunatorio idVac ) {
		super();
		this.id = id;
		this.vacunatorio = idVac;
		this.numeroVacunadores= numero;
		this.vacunadoresAsignadosPorFecha = new HashMap<LocalDate,Puesto_Vacunador>();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getNumeroVacunadores() {
		return numeroVacunadores;
	}


	public void setNumeroVacunadores(int numeroVacunadores) {
		this.numeroVacunadores = numeroVacunadores;
	}

	
	public Vacunatorio getVacunatorio() {
		return vacunatorio;
	}


	public void setVacunatorio(Vacunatorio vacunatorio) {
		this.vacunatorio = vacunatorio;
	}


	public Map<LocalDate, Puesto_Vacunador> getVacunadoresAsignadosPorFecha() {
		return vacunadoresAsignadosPorFecha;
	}


	public void setVacunadoresAsignadosPorFecha(Map<LocalDate, Puesto_Vacunador> vacunadoresAsignadosPorFecha) {
		this.vacunadoresAsignadosPorFecha = vacunadoresAsignadosPorFecha;
	}


	public void agregarPuestoVacunador (Puesto_Vacunador pv) {
		vacunadoresAsignadosPorFecha.put(pv.getFecha(), pv);
	}


	
	


	
	
	

	
	
	
}
