package Manejador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import entidades.Puesto;
import entidades.Puesto_Vacunador;
import entidades.Vacunador;
import entidades.Vacunatorio;

/**
 * Session Bean implementation class ManejadorVacunatorios
 */
@Singleton
@LocalBean
public class ManejadorVacunatorios implements ManejadorVacunatoriosLocal {

	private Map<String,Vacunatorio> vacunatorios;
	private Map<String,Puesto> puestos;
	private Map<String,Puesto_Vacunador> puestoVacunador;
	
    public ManejadorVacunatorios() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    public void init() {
    	iniciarVacunatorios();
    	iniciarPuestos();
    	iniciarPuestoVacunador();
    	
    }
	
	@Override
	public void asignarVacunadores (String fecha, String idVac, List<Integer> cedulas) throws Exception {
		Vacunatorio v = this.vacunatorios.get(idVac);
		
		if (v!=null) {
			List<Integer> cedulasRebotadas = consultarDisponiblidadFechaVacunadores(cedulas, fecha);
			cedulas.removeAll(cedulasRebotadas);
			List<Puesto> puestos = v.getPuestos();
		if (cedulas!= null) {
			int canVacunadores = cedulas.size();
			for (Puesto p : puestos) {
				Puesto_Vacunador pv = p.getVacunadoresAsignadosPorFecha().get(LocalDate.parse(fecha));
					if (pv == null) {
							pv = new Puesto_Vacunador(LocalDate.parse(fecha),Integer.parseInt(v.getId()),p);
							puestoVacunador.put(Integer.toString(puestoVacunador.size()+1), pv);
					}if (canVacunadores>0){
								List<Integer> cedulasAux = new ArrayList<Integer>(cedulas);	
									if(pv.getVacunadores().size()<=p.getNumeroVacunadores()) {
										for(int i = 0;i<cedulasAux.size();i++) {
											if (pv.getVacunadores().size()<p.getNumeroVacunadores()) {	
												Vacunador vac = new Vacunador(Integer.valueOf(cedulasAux.get(i)));
												pv.agregarVacunador(vac);
												p.agregarPuestoVacunador(pv);
												cedulas.remove(cedulasAux.get(i));
												canVacunadores--;
											}else break;
										}												 
									}
					}else break;			 
				
			 }
			
				String cedulasOut="";
				String cedulasConAsignacion = "";
				if (canVacunadores!= 0 && !cedulasRebotadas.isEmpty()) {
					
					for (Integer i:cedulasRebotadas) {
						cedulasConAsignacion = cedulasConAsignacion + "-" + Integer.valueOf(i).toString();
					}
					for (Integer i:cedulas) {
						cedulasOut = cedulasOut + "-" + Integer.valueOf(i).toString();
					}
					throw new Exception("\nEl vacunatorio no tiene mas lugares para los siguientes vacunadores: " + cedulasOut +
							"\nLos siguientes vacunadores ya tienen un lugar asignado para la fecha ingresada: " + cedulasConAsignacion);
				} else if(canVacunadores!=0) {
					for (Integer i:cedulas) {
						cedulasOut = cedulasOut + "-" + Integer.valueOf(i).toString();
					}
					throw new Exception("\nEl vacunatorio no tiene mas lugares para los siguientes vacunadores: " + cedulasOut);
				}else if(!cedulasRebotadas.isEmpty()) {
					for (Integer i:cedulasRebotadas) {
						cedulasConAsignacion = cedulasConAsignacion + "-" + Integer.valueOf(i).toString();
					}
					throw new Exception("\nLos siguientes vacunadores ya tienen un lugar asignado para la fecha ingresada: " + cedulasConAsignacion);
				}
		 }else {
			 throw new Exception("\nTodos Los vacunadores ya tienen un lugar asignado para la fecha ingresada:");
		 }
		}else {
			throw new Exception("\nNo se encontro un Vacunatorio con el id especificado");
		  }
	}
	
	private List<Integer> consultarDisponiblidadFechaVacunadores(List<Integer> cedulas, String fecha) {
		List<Integer> cedulasRebotadas = new ArrayList<Integer>();
		for (Map.Entry<String, Puesto_Vacunador> p : puestoVacunador.entrySet()) {
			if(LocalDate.parse(fecha).equals(p.getValue().getFecha())) {
				List<Vacunador> vacunadores = p.getValue().getVacunadores();
					for (int i = 0;i<cedulas.size();i++) {
						for (Vacunador vac : vacunadores) {
							if (vac.getCi() == cedulas.get(i)) {
								cedulasRebotadas.add(cedulas.get(i));
								break;
							}
						}
					}
				
			}
		}
		return cedulasRebotadas;
	}

	private void iniciarPuestoVacunador() {
		puestoVacunador = new HashMap<String,Puesto_Vacunador>();
		 
	}

	private void iniciarPuestos() {
		// TODO Auto-generated method stub
		
	}

	private void iniciarVacunatorios() {
		vacunatorios = new HashMap<String,Vacunatorio>();
		
		Vacunatorio v1 = new Vacunatorio("1");
		Puesto p1 = new Puesto("1",5,v1);
		Puesto p2 = new Puesto("2",5,v1);
		Puesto p3 = new Puesto("3",3,v1);
		Puesto p4 = new Puesto("4",5,v1);
		Puesto p5 = new Puesto("5",2,v1);
		List<Puesto> puestos = new ArrayList<Puesto>();
		puestos.add(p1);
		puestos.add(p2);
		puestos.add(p3);
		puestos.add(p4);
		puestos.add(p5);
		v1.setPuestos(puestos);
		vacunatorios.put(v1.getId(), v1);
		
		Vacunatorio v2 = new Vacunatorio("2");
		Puesto p6 = new Puesto("1",5,v2);
		Puesto p7 = new Puesto("2",3,v2);
		Puesto p8 = new Puesto("3",3,v2);
		Puesto p9 = new Puesto("4",4,v2);
		Puesto p11 = new Puesto("5",2,v2);
		List<Puesto> puestos2 = new ArrayList<Puesto>();
		puestos2.add(p6);
		puestos2.add(p7);
		puestos2.add(p8);
		puestos2.add(p9);
		puestos2.add(p11);
		v2.setPuestos(puestos2);
		vacunatorios.put(v2.getId(), v2);
		
		Vacunatorio v3 = new Vacunatorio("3");
		Puesto p10 = new Puesto("1",4,v3);
		Puesto p12 = new Puesto("2",5,v3);
		Puesto p13 = new Puesto("3",3,v3);
		Puesto p14 = new Puesto("4",5,v3);
		Puesto p15 = new Puesto("5",3,v3);
		List<Puesto> puestos3 = new ArrayList<Puesto>();
		puestos3.add(p10);
		puestos3.add(p12);
		puestos3.add(p13);
		puestos3.add(p14);
		puestos3.add(p15);
		v3.setPuestos(puestos3);
		vacunatorios.put(v3.getId(), v3);
		
		Vacunatorio v4 = new Vacunatorio("4");
		Puesto p16 = new Puesto("1",2,v4);
		Puesto p17 = new Puesto("2",2,v4);
		List<Puesto> puestos4 = new ArrayList<Puesto>();
		puestos4.add(p16);
		puestos4.add(p17);
		v4.setPuestos(puestos4);
		vacunatorios.put(v4.getId(), v4);
		
	}

	@Override
	public List<Integer> listarVacunatorios() {
		List<Integer> listavacunatorios = new ArrayList<Integer>();
		for (Map.Entry<String, Vacunatorio> p : vacunatorios.entrySet()) {
			listavacunatorios.add(Integer.parseInt(p.getValue().getId()));
		}
		return listavacunatorios;
	}

}


