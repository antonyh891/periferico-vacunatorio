package Manejador;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ManejadorVacunatoriosLocal {

	public void asignarVacunadores(String fecha, String idVac, List<Integer> cedulas) throws Exception;
	
	public List<Integer> listarVacunatorios();

}
