package Campeonato;

import java.util.ArrayList;

import Entidades.Clube;

public class Classificacao {	
	
	private ElementoClassificavel[] times;
	private int qtdeTimes;
	
	public Classificacao(ArrayList<Clube> times){
		this.qtdeTimes = times.size();
		this.times = new ElementoClassificavel[this.qtdeTimes];
		for (int i=0; i < this.qtdeTimes; i++ ){
			this.times[i] = new ElementoClassificavel(times.get(i));
		}		
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

	public void Ordenar (){
		boolean alterado = true;  
		while (alterado){
			alterado = false;
			for (int i = 0; i < this.qtdeTimes - 1; i++){
				alterado |= this.compOrd(i, i+1, 0);
			}
		}
		return;
	}
	
	private boolean compOrd(int pos1, int pos2, int dim){
		if (dim >= 5) return false;

		if (this.times[pos1].dados[dim] == this.times[pos2].dados[dim]) return (this.compOrd(pos1, pos2, (dim+1)));  

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