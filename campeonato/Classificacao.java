package campeonato;

import entidades.Clube;

public class Classificacao {	
	
	private ElementoClassificavel[] times;
	
	public Classificacao(Clube[] times){
		this.times = new ElementoClassificavel[times.length];
		for (int i=0; i < times.length; i++ ){
			this.times[i] = new ElementoClassificavel(times[i]);
		}		
	}
	
	public Clube[] getTimesEntre(int inicio, int fim){
		ordenar();
		Clube[] timesRetorno = new Clube[fim-inicio];
		for(int i=0; i<timesRetorno.length; i++){
			timesRetorno[i] = times[i+inicio].clube;
		}
		return timesRetorno;
	}
	
	private class ElementoClassificavel{
		
		public Clube clube;
		public int [] dados = new int[5]; // P V SG GP J
		
		public ElementoClassificavel(Clube time){
			this.clube = time;
			for(int i = 0; i<5; i++){
				dados[i] = 0;
			}
		}
	}

	public void ordenar (){
		boolean alterado = true;  
		while (alterado){
			alterado = false;
			for (int i = 0; i < this.times.length - 1; i++){
				alterado |= this.compOrd(i, i+1, 0);
			}
		}
		return;
	}
	
	private boolean compOrd(int pos1, int pos2, int dim){
		if (dim >= 5) return false;

		else if (this.times[pos1].dados[dim] == this.times[pos2].dados[dim]) return (this.compOrd(pos1, pos2, (dim+1)));  

		else if (this.times[pos1].dados[dim] < this.times[pos2].dados[dim]){
			ElementoClassificavel temp = this.times[pos1];
			this.times[pos1] = this.times[pos2];
			this.times[pos2] = temp;
			return true;
		}		
		//if (this.times[pos1].dados[dim] > this.times[pos2].dados[dim]) return false;
		return false;
	}
	
	
}