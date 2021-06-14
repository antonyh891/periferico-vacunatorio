package entidades;

import java.util.ArrayList;
import java.util.List;






public class Vacunatorio {
	
	private int id;
	private List<Puesto> puestos = new ArrayList<>();
	
	
	public Vacunatorio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vacunatorio(int id) {
		super();
		this.id = id;
		
	}







	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public List<Puesto> getPuestos() {
		return puestos;
	}

	public void setPuestos(List<Puesto> puestos) {
		this.puestos = puestos;
	}

	
	
}
