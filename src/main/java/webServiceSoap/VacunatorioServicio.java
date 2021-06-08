package webServiceSoap;


import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import Manejador.ManejadorVacunatoriosLocal;
import dataTypes.DTMsjVacunatorio;

@WebService
public class VacunatorioServicio {
	
	@EJB
	private ManejadorVacunatoriosLocal manejador;
	
	@WebMethod
	public DTMsjVacunatorio asignarVacunadores(String fecha, String idVac, List<Integer> cedulas)  {
		
		DTMsjVacunatorio mensaje = manejador.asignarVacunadores(fecha, idVac, cedulas);
		return mensaje;
			
		
	}
	
	@WebMethod
	public List<Integer> listarVacunatorios (){
		return manejador.listarVacunatorios();
	}

}
