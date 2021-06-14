package webServiceSoap;


import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import Manejador.ManejadorVacunatoriosLocal;
import dataTypes.DTInformacionVacunador;
import dataTypes.DTMsjVacunatorio;

@WebService
public class VacunatorioServicio {
	
	@EJB
	private ManejadorVacunatoriosLocal manejador;
	
	@WebMethod
	public void asignarVacunadores(String fecha, int idVac, List<Integer> cedulas)  {
		
		manejador.asignarVacunadores(fecha, idVac, cedulas);
		
			
		
	}
	
	@WebMethod
	public List<Integer> listarVacunatorios (){
		return manejador.listarVacunatorios();
	}
	
	
	
	@WebMethod
	public Integer consultarVacunadorPuestoXFecha (String fecha, int idVac,int cedula ) {
		return manejador.consultarVacunadorPuestoXFecha(idVac, fecha, cedula);
		
	}

}
