package dataTypes;

import java.util.List;

public class DTReservaVacunatorio {
	private int ci;
	private long idVacuna;
	private long idReserva;
	private String fecha;
	
	public DTReservaVacunatorio() {
		
		
		
	}

	public int getCi() {
		return ci;
	}

	public void setCi(int ci) {
		this.ci = ci;
	}

	public long getIdVacuna() {
		return idVacuna;
	}

	public void setIdVacuna(long idVacuna) {
		this.idVacuna = idVacuna;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(long idReserva) {
		this.idReserva = idReserva;
	}

	@Override
	public String toString() {
		return "DTReservaVacunatorio [ci=" + ci + ", idVacuna=" + idVacuna + ", fecha=" + fecha + "]";
	}

}
