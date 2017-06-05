package campeonato;

import entidades.Clube;

public interface Promovivel {
	
	public Clube[] getTimesEntre(int inicio, int fim);
	public void setTimesEntre(int inicio, Clube[] times);
	public Clube getTimeComFlag(int flag);
	public void novaTemporada();

}
