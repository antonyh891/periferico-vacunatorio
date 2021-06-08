package Manejador;

import java.util.List;

import javax.ejb.Local;

import dataTypes.DTMsjVacunatorio;

@Local
public interface ManejadorVacunatoriosLocal {

	public DTMsjVacunatorio asignarVacunadores(String fecha, String idVac, List<Integer> cedulas) ;
	
	public List<Integer> listarVacunatorios();

}
