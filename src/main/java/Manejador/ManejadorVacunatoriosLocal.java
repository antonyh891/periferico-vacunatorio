package Manejador;

import java.util.List;

import javax.ejb.Local;

import dataTypes.DTInformacionVacunador;

@Local
public interface ManejadorVacunatoriosLocal {

	public Boolean asignarVacunadores(List<Integer> cedulas) ;
	

	public Integer consultarVacunadorPuesto(int cedula);


}
