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
	int m = 0, n = 0, c = 0, cn = 0, cn2 = 0, r = 0; //obede
	
	public boolean proxSemana(){
		this.semana++;
		if (this.semana > 29){
			return false;
		}
		//System.out.println(this.semana);
		if (this.semana >= 28){
			m++;
			r++;
			System.out.println("M: " + m+ " / M2: " +m+ " / R: " + r);
		} else if (this.semana == 27){
			r++;
			System.out.println("RC/R: " + r);
		} else if (this.semana == 26){
			System.out.println("RN/RN2");
		} else if (this.semana == 4){
			cn++;
			c++;
			System.out.println("C: " + c + " /C2: " + c + " / CN: " + cn);
		} else if (this.semana%2 == 1 || this.semana == 2 || this.semana == 14){
			n++;
			System.out.println("N:/N2: " + n);
		} else if (this.semana%4 == 0){
			c++;
			System.out.println("C/C2 " + c);
		} else {
			cn++;
			cn2++;
			System.out.println("CN: " + cn + " / CN2: " + cn2);
		}
		return true;
	}

}
