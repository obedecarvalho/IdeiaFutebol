package temporada;

import campeonato.Campeonato;

public class Temporada {
	private int semana = 0;
	private Campeonato mundial;
	private Campeonato mundial2;
	private Campeonato[] recopaContinental = new Campeonato[4];
	private Campeonato[] continental = new Campeonato[4];
	private Campeonato[] continental2 = new Campeonato[4];
	
	
	private Campeonato[] recopaNacional = new Campeonato[16];
	private Campeonato[] copaNacional = new Campeonato[16];
	private Campeonato[] nacional = new Campeonato[16];
	private Campeonato[] nacional2 = new Campeonato[16];
	
	
	public boolean proxSemana(){
		this.semana++;
		System.out.println(this.semana);
		if (this.semana > 31){
			return false;
		}
		if (this.semana >= 30){
			System.out.println("M/M2");
		} else if (this.semana == 29){
			System.out.println("RC");
		} else if (this.semana == 28){
			System.out.println("RN");
		} else if (this.semana%2 == 1 || this.semana == 26){
			System.out.println("N/N2");
		} else if (this.semana%4 == 0 && this.semana != 24){
			System.out.println("CN");
		} else {
			System.out.println("C/C2");
		}
		return true;
	}

}
