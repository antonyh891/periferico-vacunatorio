package Manejador;

import java.util.List;

import javax.ejb.Local;

import dataTypes.DTInformacionVacunador;

@Local
public interface ManejadorVacunatoriosLocal {

	public void asignarVacunadores(String fecha, int idVac, List<Integer> cedulas) ;
	
	public List<Integer> listarVacunatorios();

	public List<DTInformacionVacunador> consultarLugarVacunador(String idVac, int cedula);

	

	public Integer consultarVacunadorPuestoXFecha(int idVac, String fecha, int cedula);

}
