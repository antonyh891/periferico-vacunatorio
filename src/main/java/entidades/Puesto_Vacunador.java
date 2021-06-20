package entidades;

import java.time.LocalDate;


public class Puesto_Vacunador {
	private LocalDate fecha;
	private int puesto;
	private Vacunador vacunador;
	
	public Puesto_Vacunador() {
		
	}

	public Puesto_Vacunador(LocalDate fecha, int puesto) {
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

	public int getPuesto() {
		return puesto;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	public Vacunador getVacunador() {
		return vacunador;
	}

	public void setVacunador(Vacunador vacunador) {
		this.vacunador = vacunador;
	}



	
	

}
