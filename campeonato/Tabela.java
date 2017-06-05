package campeonato;

import entidades.Clube;
import entidades.Constantes;

public class Tabela {

	private Rodada[] rodadas;
	
	public Tabela (Clube[] times, boolean jogoUnico){
		if (jogoUnico == Constantes.JOGO_IDAVOLTA){
			this.rodadas = new Rodada[(times.length - 1) * 2];
		} else {
			this.rodadas = new Rodada[times.length - 1];
		}
		for (int i=0; i<this.rodadas.length; i++) this.rodadas[i] = new Rodada(times.length);		
		this.calcularRodadas(times);
	}
	
	private void calcularRodadas(Clube[] times){
		int totalJogosRodada = times.length/2;
		Clube[] timesA = new Clube[totalJogosRodada];
		Clube[] timesB = new Clube[totalJogosRodada];

		for (int i=0; i<totalJogosRodada; i+=2){
			timesA[i] = times[i];
			timesB[i+1] = times[i+1];
		}
		
		for (int rodada = 1; rodada <= this.rodadas.length; rodada++){
			for (int jogo = 1; jogo <= totalJogosRodada; jogo++){
				if (rodada > (times.length - 1)){
					Rodada rodada1Turno = this.rodadas[rodada - times.length];
					this.rodadas[rodada].jogo(jogo, rodada1Turno.partidas[jogo].visitante, rodada1Turno.partidas[jogo].mandante);
				} else if (rodada%2 == 0 && jogo == 1){
					this.rodadas[rodada].jogo(jogo, timesB[jogo], timesA[jogo]);
				} else if (jogo %2 == 0){
					this.rodadas[rodada].jogo(jogo, timesB[jogo], timesA[jogo]);
				} else {
					this.rodadas[rodada].jogo(jogo, timesA[jogo], timesB[jogo]);
				}
				
			}
			Clube t1 = timesA[totalJogosRodada-1];
			Clube t2 = timesB[0];
			for (int i = totalJogosRodada-1; i > 1; i--){
				timesA[i] = timesA[i-1];
			}
			timesA[1]= t2;
			for (int i = 0; i<totalJogosRodada-1; i++){
				timesB[i] = timesB[i+1];
			}
			timesB[totalJogosRodada-1] = t1;			
		}
			
	}
	
	public Rodada getRodada(int nro){
		return this.rodadas[nro];
	}
	
	private class Partida{
		public Clube mandante;
		public Clube visitante;
		public Partida(Clube mandante, Clube visitante){
			this.mandante = mandante;
			this.visitante = visitante;			
		}
	}
	
	private class Rodada{
		
		public Partida[] partidas;
		
		public Rodada(int nroTimes){
			this.partidas = new Partida[nroTimes/2];
		}
		
		public void jogo(int nro, Clube mandante, Clube visitante){
			partidas[nro-1] = new Partida(mandante, visitante);
		}
	}

}
