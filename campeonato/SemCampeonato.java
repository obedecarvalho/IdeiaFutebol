package campeonato;

import java.util.ArrayList;

import entidades.Clube;

public class SemCampeonato implements Promovivel {
	
	private ArrayList<Clube> times;
	
	public SemCampeonato(ArrayList<Clube> times){
		this.times = times;
	}

}
