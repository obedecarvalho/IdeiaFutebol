package IdeiaFutebol;

import Campeonato.Campeonato;

public class IdeiaFutebol {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Campeonato c = new Campeonato(null,0,1,0,Campeonato.SEM_FASE,Campeonato.JOGO_UNICO,Campeonato.SEM_FASE);
		while (c.proxRodada());

	}

}
