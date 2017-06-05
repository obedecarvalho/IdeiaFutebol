package campeonato;

import entidades.Clube;
import entidades.Constantes;

public class SemCampeonato implements Promovivel {
	
	private Clube[] times;
	private Flag[] flags;
	
	public SemCampeonato(Clube[] times){
		this.times = times;
		this.createFlags();
	}
	
	public void createFlags(){
		this.flags = new Flag[this.times.length];
		for (int i=0; i<this.times.length; i++){
			this.flags[i] = new Flag(this.times[i], Constantes.FLAG_NULL);
		}
	}
	
	public void ordenarFlag(){
		boolean haTroca = true;
		while (haTroca){
			haTroca = false;
			Flag temp;
			for (int i=0; i<flags.length-1; i++){
				if (flags[i].flag < flags[i+1].flag){
					temp = flags[i];
					flags[i] = flags[i+1];
					flags[i+1] = temp;
					haTroca = true;
				}
			}
		}
	}

	@Override
	public Clube[] getTimesEntre(int inicio, int fim) {
		Clube[] clubes = new Clube[fim-inicio];
		ordenarFlag();
		for (int i=0; i<clubes.length; i++){
			clubes[i] = this.times[inicio+i];
		}
		return clubes;
	}

	@Override
	public void setTimesEntre(int inicio, Clube[] times) {
		ordenarFlag();
		for (int i=0; i<times.length; i++){
			this.times[inicio+i] = times[i];
		}
		return;
	}

	@Override
	public Clube getTimeComFlag(int flag) {
		for (Flag f : this.flags){
			if (f.flag == flag){
				return f.time;
			}
		}
		return null;
	}

	@Override
	public void novaTemporada() {
		this.createFlags();
	}
	
	public class Flag{
		public int flag;
		public Clube time;
		public Flag(Clube time, int flag) {
			this.flag = flag;
			this.time = time;
		}
	}

}
