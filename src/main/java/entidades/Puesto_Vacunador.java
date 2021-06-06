package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Puesto_Vacunador {
	private LocalDate fecha;
	private Puesto puesto;
	private List<Vacunador> vacunadores;
	private int codigoVacunatorio;
	
	public Puesto_Vacunador() {
		
	}

	public Puesto_Vacunador(LocalDate fecha,int codigoVacunatorio, Puesto puesto) {
		super();
		this.fecha = fecha;
		this.codigoVacunatorio= codigoVacunatorio;
		this.puesto = puesto;
		this.vacunadores = new ArrayList<Vacunador>();
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

	public int getCodigoVacunatorio() {
		return codigoVacunatorio;
	}

	public void setCodigoVacunatorio(int codigoVacunatorio) {
		this.codigoVacunatorio = codigoVacunatorio;
	}

	public List<Vacunador> getVacunadores() {
		return vacunadores;
	}

	public void setVacunadores(List<Vacunador> vacunadores) {
		this.vacunadores = vacunadores;
	}
	
	public void agregarVacunador(Vacunador vac) {
		this.vacunadores.add(vac);
	}

	
	

}
