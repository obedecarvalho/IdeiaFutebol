package campeonato;

import java.util.ArrayList;

import entidades.Clube;

public class Tabela {

	private Rodada[] rodadas;
	private int nroRodadas;
	public Tabela (ArrayList<Clube> times, int modoJogo){
		if (modoJogo == Campeonato.JOGO_IDAVOLTA){
			this.nroRodadas = (times.size() - 1) * 2;
		} else {
			this.nroRodadas = times.size() - 1;
		}
		this.rodadas = new Rodada[this.nroRodadas];
		this.inicializar(times);
	}
	
	private void inicializar(ArrayList<Clube> times){
		int totalJogos = times.size()/2;
		ArrayList<Clube> timesA = (ArrayList<Clube>) times.subList(0, totalJogos);
		ArrayList<Clube> timesB = (ArrayList<Clube>) times.subList(totalJogos, times.size());
		
		for (int rodada = 1; rodada < this.nroRodadas; rodada++){
			for (int jogo = 0; jogo < totalJogos; jogo++){
				if (rodada%2== 0 && jogo == 0){
					this.rodadas[rodada].jogo(jogo, timesB.get(jogo), timesA.get(jogo));
					//this.rodadas[rodada].jogos[jogo] = new Jogo(timesB.get(jogo), timesA.get(jogo));
				} else if (jogo %2 != 0){
					this.rodadas[rodada].jogo(jogo, timesB.get(jogo), timesA.get(jogo));
					//this.rodadas[rodada].jogos[jogo] = new Jogo(timesB.get(jogo), timesA.get(jogo));
				} else {
					this.rodadas[rodada].jogo(jogo, timesA.get(jogo), timesB.get(jogo));
					//this.rodadas[rodada].jogos[jogo] = new Jogo(timesA.get(jogo), timesB.get(jogo));
				}
				
			}
			Clube t1 = timesA.get(totalJogos-1);
			Clube t2 = timesB.get(0);
			for (int i = totalJogos-1; i > 0; i--){
				timesA.set(i, timesA.get(i-1));
			}
			timesA.set(1, t2);
			for (int i = 0; i<totalJogos-1; i++){
				timesB.set(i,timesB.get(i+1));
			}
			timesB.set(totalJogos-1, t1);
			
		}
			
	}
	
	private class Jogo{
		public Clube mandante;
		public Clube visitante;
		public Jogo(Clube mandante, Clube visitante){
			this.mandante = mandante;
			this.visitante = visitante;			
		}
	}
	
	private class Rodada{
		public int nroJogos;
		public Jogo[] jogos;		
		public Rodada(int nroTimes){
			this.nroJogos = nroTimes/2;
			this.jogos = new Jogo[this.nroJogos];
		}
		public void jogo(int nro, Clube mandante, Clube visitante){
			jogos[nro-1] = new Jogo(mandante, visitante);
		}
	}

}
