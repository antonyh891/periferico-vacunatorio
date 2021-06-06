package webServiceSoap;


import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import Manejador.ManejadorVacunatoriosLocal;

@WebService
public class VacunatorioServicio {
	
	@EJB
	private ManejadorVacunatoriosLocal manejador;
	
	@WebMethod
	public String asignarVacunadores(String fecha, String idVac, List<Integer> cedulas) throws Exception {
		
		try {
		manejador.asignarVacunadores(fecha, idVac, cedulas);
			return "Vacunadores asignados correctamente";
		} catch (Exception e) {
			return("Error: "+e.getMessage());
		}
	}
	
	@WebMethod
	public List<Integer> listarVacunatorios (){
		return manejador.listarVacunatorios();
	}

}
