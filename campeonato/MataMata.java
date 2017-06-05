package campeonato;

import entidades.Clube;

public class MataMata implements Fase {
	
	private Clube[] times;
	private int rodadaAtual;
	private int nroTimesAtivos;
	private boolean jogoUnico;
	private Partida[] partidasVolta;
	
	public MataMata(Clube[] times, boolean jogoUnico) {
		this.times = times;
		this.rodadaAtual = 0;
		this.nroTimesAtivos = times.length;
		this.jogoUnico = jogoUnico;
	}
	
	@Override
	public void proxRodada() {
		this.rodadaAtual++;
		Partida[] partidas = new Partida[this.nroTimesAtivos/2];
		if (partidasVolta == null && !jogoUnico) partidasVolta = new Partida[this.nroTimesAtivos/2];
		for (int i=0; i< nroTimesAtivos/2; i++){
			partidas[i] = new Partida(times[i], times[nroTimesAtivos - 1 - i]);
		}
		if (this.jogoUnico){
			this.nroTimesAtivos /= 2;
		} else if (this.rodadaAtual%2 == 0){
			this.nroTimesAtivos /= 2;
			partidasVolta = null;
		}
		if (nroTimesAtivos == 1){
			//this.melhor = 
		}
	}
	
	private class Partida{
		public Clube mandante;
		public Clube visitante;
		public Partida(Clube mandante, Clube visitante){
			this.mandante = mandante;
			this.visitante = visitante;			
		}
	}

	@Override
	public Clube[] getTimesAtivos() {
		Clube[] timesAtivos = new Clube[nroTimesAtivos];
		for (int i=0; i<nroTimesAtivos; i++)
			timesAtivos[i] = times[i];
		return timesAtivos;
	}

}
