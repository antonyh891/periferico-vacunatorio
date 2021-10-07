package Manejador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.springframework.web.util.UriComponentsBuilder;

import dataTypes.DTInformacionVacunador;
import dataTypes.DTRegistroVacuna;
import dataTypes.DTReservaVacunatorio;
import entidades.Puesto_Vacunador;
import entidades.Vacunador;


/**
 * Session Bean implementation class ManejadorVacunatorios
 */
@Singleton
@LocalBean
public class ManejadorVacunatorios implements ManejadorVacunatoriosLocal {
	
	@Resource
	TimerService timerService;
	
	long id = 1; 
	private List<Puesto_Vacunador> puestoVacunador;
	LocalDate fechaInicio = LocalDate.of(2020, 12, 30);
    public ManejadorVacunatorios() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    public void init() {
  
    	iniciarPuestoVacunador();
    	
    }
	
	@Override
	public Boolean asignarVacunadores (List<Integer> cedulas)  {
		
		if (cedulas!= null) {
			if(puestoVacunador!= null) {
				puestoVacunador = new ArrayList<Puesto_Vacunador>();
			}
			int posicion= 1;
			List<Puesto_Vacunador> puestosAsignados = new ArrayList<Puesto_Vacunador>();
				for (Integer i : cedulas) {
						
						Puesto_Vacunador pv = new Puesto_Vacunador(posicion);	
						Vacunador vac = new Vacunador(i.intValue());
						pv.setVacunador(vac);
						puestosAsignados.add(pv);
						posicion++;
						puestoVacunador.add(pv);
				}
				return true;
			 }
			return false;
		}
				

	private void iniciarPuestoVacunador() {
		puestoVacunador = new ArrayList<Puesto_Vacunador>();
		 
	}
	
	
	@Override
	public Integer consultarVacunadorPuesto(int cedula) {
		
		
		if(puestoVacunador!= null) {
			
		
			for (Puesto_Vacunador p : puestoVacunador) {
				if (p.getVacunador().getCi() == cedula) {
					return p.getPuesto();
				}
			}
		}
		return 0;
		
		
	}
	
	public void setTimer(long interval) {
    	timerService.createTimer(interval, "Seteando timer");
    }
   
    @Schedule(second="59", minute="*/1", hour="0-23", dayOfWeek="*", month="*", year="*", info="TimerSocioLogistico")
    private void consultarAgenda(final Timer t) {
    	fechaInicio = fechaInicio.plusDays(1);
    	// Cliente para la conexión
        Client client = ClientBuilder.newClient();
       UriComponentsBuilder builder = UriComponentsBuilder.fromUriString( "http://localhost:8080/comp-cent-web/rest/vacunatorio/obtenerAgenda")
    		   .queryParam("id", id)
    		   .queryParam("fecha", fechaInicio.toString());
                
       // Definición de URL
       WebTarget target = client.target(builder.buildAndExpand().toUri());
       
       List<DTReservaVacunatorio> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType <List<DTReservaVacunatorio>>() {});
       System.out.println(response.toString());
       List<DTRegistroVacuna> registros = new ArrayList<DTRegistroVacuna>();
       if (response != null) {
	       int numero = (int)(Math.random()*(response.size()-(response.size()/2)+1)+(response.size()/2));
	       Random rand = new Random();
	       for (int i = 0; i < numero; i++) {
	           int nAleatorio = rand.nextInt(response.size());
	           DTReservaVacunatorio reserva = response.get(nAleatorio);
	           DTRegistroVacuna registro = new DTRegistroVacuna(reserva.getCi(),reserva.getIdVacuna(),id,reserva.getIdReserva(),reserva.getFecha());
	           registros.add(registro);
	           response.remove(nAleatorio);
	       }
	       
	    // Cliente para la conexión
	        Client client2 = ClientBuilder.newClient();
	       
	    // Definición de URL
	       WebTarget target2 = client2.target("http://localhost:8080/comp-cent-web/rest/vacunatorio/obtenerRegistroVacuna");
	       
	        // Enviamos registros
	       target2.request().post(Entity.entity(registros, MediaType.APPLICATION_JSON));
	  
	       
       }   
    }
	


	

}


