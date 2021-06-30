package entidades;

import java.time.LocalDate;


public class Puesto_Vacunador {
	private int puesto;
	private Vacunador vacunador;
	
	public Puesto_Vacunador() {
		
	}

	public Puesto_Vacunador( int puesto) {
		super();
		this.puesto = puesto;
		this.vacunador = new Vacunador();
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
