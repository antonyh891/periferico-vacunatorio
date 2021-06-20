package Manejador;

import java.util.List;

import javax.ejb.Local;

import dataTypes.DTInformacionVacunador;

@Local
public interface ManejadorVacunatoriosLocal {

	public Boolean asignarVacunadores(String fecha, List<Integer> cedulas) ;
	

	public Integer consultarVacunadorPuestoXFecha(String fecha, int cedula);


}
