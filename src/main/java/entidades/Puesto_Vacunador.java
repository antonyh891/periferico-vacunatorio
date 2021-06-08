package entidades;

import java.time.LocalDate;


public class Puesto_Vacunador {
	private LocalDate fecha;
	private Puesto puesto;
	private Vacunador vacunador;
	
	public Puesto_Vacunador() {
		
	}

	public Puesto_Vacunador(LocalDate fecha, Puesto puesto) {
		super();
		this.fecha = fecha;
		this.puesto = puesto;
		this.vacunador = new Vacunador();
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public Vacunador getVacunador() {
		return vacunador;
	}

	public void setVacunador(Vacunador vacunador) {
		this.vacunador = vacunador;
	}



	
	

}
