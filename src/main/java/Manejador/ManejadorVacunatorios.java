package Manejador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import dataTypes.DTInformacionVacunador;
import dataTypes.DTMsjVacunatorio;
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
	private Map<String,Puesto_Vacunador> puestoVacunador;
	
    public ManejadorVacunatorios() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    public void init() {
    	iniciarVacunatorios();
    	iniciarPuestoVacunador();
    	
    }
	
	@Override
	public DTMsjVacunatorio asignarVacunadores (String fecha, String idVac, List<Integer> cedulas)  {
		Vacunatorio v = this.vacunatorios.get(idVac);
		String mensaje="";
		DTMsjVacunatorio mensajeVac= new DTMsjVacunatorio() ;
		if (v!=null) {
			List<Integer> cedulasRebotadas = consultarDisponiblidadFechaVacunadores(cedulas, fecha);
			cedulas.removeAll(cedulasRebotadas);
			List<Puesto> puestos = v.getPuestos();
		if (cedulas!= null) {
			int canVacunadores = cedulas.size();
			int i= 0;
			 List<Integer> cedulasAux = new ArrayList<Integer>(cedulas);
			for (Puesto p : puestos) {
				Puesto_Vacunador pv = p.getVacunadorAsignadoPorFecha().get(LocalDate.parse(fecha));
					if (pv == null && (canVacunadores>0)) {
						
							pv = new Puesto_Vacunador(LocalDate.parse(fecha),p);
							puestoVacunador.put(Integer.toString(puestoVacunador.size()+1), pv);	
								Vacunador vac = new Vacunador(Integer.valueOf(cedulasAux.get(i)));
								pv.setVacunador(vac);
								p.agregarPuestoVacunador(pv);
								cedulas.remove(cedulasAux.get(i));
								i++;
								canVacunadores--;
					 }else if (canVacunadores==0) break;
																						 
			 }
					 
			
				if (canVacunadores!= 0 && !cedulasRebotadas.isEmpty()) {
					cedulas.addAll(cedulasRebotadas);
					mensaje= "En la fecha seleccionada,el vacunatorio no tiene mas lugar para los siguientes vacunadores: ";
					mensajeVac.setMensaje(mensaje);
					mensajeVac.setVacunadoresNoAsignados(cedulas);
				} 
				else if(canVacunadores!=0) {
					mensaje= "En la fecha seleccionada, el vacunatorio no tiene mas lugar para los siguientes vacunadores: ";
					mensajeVac.setMensaje(mensaje);
					mensajeVac.setVacunadoresNoAsignados(cedulas);
					
				}else if(!cedulasRebotadas.isEmpty()) {
					mensaje= "Los siguientes vacunadores ya tienen un lugar asignado para la fecha ingresada: ";
					mensajeVac.setMensaje(mensaje);
					mensajeVac.setVacunadoresNoAsignados(cedulasRebotadas);
						}
					
		         else {
		        	 	mensaje= "Vacunadores asignados correctamente";
						mensajeVac.setMensaje(mensaje);
		         }
			} 
		
	}else {
		mensaje=" No se encontro un Vacunatorio con el id especificado";
		mensajeVac.setMensaje(mensaje);
		  }
		return mensajeVac;
	}
	
	private List<Integer> consultarDisponiblidadFechaVacunadores(List<Integer> cedulas, String fecha) {
		List<Integer> cedulasRebotadas = new ArrayList<Integer>();
		for (Map.Entry<String, Puesto_Vacunador> p : puestoVacunador.entrySet()) {
			if(LocalDate.parse(fecha).equals(p.getValue().getFecha()) && cedulas.contains(p.getValue().getVacunador().getCi())) {
						
				cedulasRebotadas.add(p.getValue().getVacunador().getCi());
								
			 }
		 }
		return cedulasRebotadas;
	 }
	
	@Override
	public List<Integer> listarVacunatorios() {
		List<Integer> listavacunatorios = new ArrayList<Integer>();
		for (Map.Entry<String, Vacunatorio> p : vacunatorios.entrySet()) {
			listavacunatorios.add(Integer.parseInt(p.getValue().getId()));
		}
		return listavacunatorios;
	}
				

	private void iniciarPuestoVacunador() {
		puestoVacunador = new HashMap<String,Puesto_Vacunador>();
		 
	}
	
	@Override
	public List<DTInformacionVacunador> consultarLugarVacunador (String idVac, int cedula) {
		Vacunatorio v = this.vacunatorios.get(idVac);
		if(v != null) {
			List<DTInformacionVacunador> informacion = new ArrayList<DTInformacionVacunador>();
		
			for (Map.Entry<String, Puesto_Vacunador> p : puestoVacunador.entrySet()) {
				if(cedula == p.getValue().getVacunador().getCi() && idVac.equals( p.getValue().getPuesto().getVacunatorio().getId() )){
					DTInformacionVacunador info = new DTInformacionVacunador (p.getValue().getFecha().toString(), p.getValue().getPuesto().getId()); 
					informacion.add(info);
				}
			}
			return informacion;
		}else return null;
	}
	
	@Override
	public List<Integer> consultarVacunadoresPuestosVacXFecha(String idVac, String fecha) {
		Vacunatorio v = this.vacunatorios.get(idVac);
		
		if(v != null) {
			List<Integer> listaVacunadores = new ArrayList<Integer>();
			for (Puesto p: v.getPuestos()) {
				if (p.getVacunadorAsignadoPorFecha().containsKey(LocalDate.parse(fecha))) {
					listaVacunadores.add(p.getVacunadorAsignadoPorFecha().get(LocalDate.parse(fecha)).getVacunador().getCi());
				}
					
			}
			return listaVacunadores;
		}else return null;
		
	}
	

	private void iniciarVacunatorios() {
		vacunatorios = new HashMap<String,Vacunatorio>();
		
		Vacunatorio v1 = new Vacunatorio("hfx10");
		Puesto p1 = new Puesto(1,v1);
		Puesto p2 = new Puesto(2,v1);
		Puesto p3 = new Puesto(3,v1);
		Puesto p4 = new Puesto(4,v1);
		Puesto p5 = new Puesto(5,v1);
		List<Puesto> puestos = new ArrayList<Puesto>();
		puestos.add(p1);
		puestos.add(p2);
		puestos.add(p3);
		puestos.add(p4);
		puestos.add(p5);
		v1.setPuestos(puestos);
		vacunatorios.put(v1.getId(), v1);
		
		Vacunatorio v2 = new Vacunatorio("hfx08");
		Puesto p6 = new Puesto(1,v2);
		Puesto p7 = new Puesto(2,v2);
		Puesto p8 = new Puesto(3,v2);
		Puesto p9 = new Puesto(4,v2);
		Puesto p20 = new Puesto(5,v2);
		Puesto p21 = new Puesto(6,v2);
		Puesto p22 = new Puesto(7,v2);
		Puesto p23 = new Puesto(8,v2);
		Puesto p24 = new Puesto(9,v2);
		Puesto p25 = new Puesto(10,v2);
		List<Puesto> puestos2 = new ArrayList<Puesto>();
		puestos2.add(p6);
		puestos2.add(p7);
		puestos2.add(p8);
		puestos2.add(p9);
		puestos2.add(p20);
		puestos2.add(p21);
		puestos2.add(p22);
		puestos2.add(p23);
		puestos2.add(p24);
		puestos2.add(p25);
		v2.setPuestos(puestos2);
		vacunatorios.put(v2.getId(), v2);
		
		Vacunatorio v3 = new Vacunatorio("hfx09");
		Puesto p10 = new Puesto(1,v3);
		Puesto p12 = new Puesto(2,v3);
		Puesto p13 = new Puesto(3,v3);
		Puesto p14 = new Puesto(4,v3);
		Puesto p15 = new Puesto(5,v3);
		Puesto p19 = new Puesto(6,v3);
		List<Puesto> puestos3 = new ArrayList<Puesto>();
		puestos3.add(p10);
		puestos3.add(p12);
		puestos3.add(p13);
		puestos3.add(p14);
		puestos3.add(p15);
		puestos3.add(p19);
		v3.setPuestos(puestos3);
		vacunatorios.put(v3.getId(), v3);
		
		Vacunatorio v4 = new Vacunatorio("hfx11");
		Puesto p26 = new Puesto(1,v4);
		Puesto p27 = new Puesto(2,v4);
		Puesto p28 = new Puesto(3,v4);
		Puesto p29 = new Puesto(4,v4);
		Puesto p30 = new Puesto(5,v4);
		Puesto p31 = new Puesto(6,v4);
		Puesto p32 = new Puesto(7,v4);
		Puesto p33 = new Puesto(8,v4);
		Puesto p34 = new Puesto(9,v4);
		Puesto p35 = new Puesto(10,v4);
		List<Puesto> puestos4 = new ArrayList<Puesto>();
		puestos4.add(p26);
		puestos4.add(p27);
		puestos4.add(p28);
		puestos4.add(p29);
		puestos4.add(p30);
		puestos4.add(p31);
		puestos4.add(p32);
		puestos4.add(p33);
		puestos4.add(p34);
		puestos4.add(p35);
		v4.setPuestos(puestos4);
		vacunatorios.put(v4.getId(), v4);
		
	}

	

	

}


