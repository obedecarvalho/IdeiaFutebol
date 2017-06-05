package campeonato;

import entidades.Constantes;

public class Promotor {
	
	private Promovivel origem;
	private Promovivel destino;
	private int inicio;
	private int fim;
	private int flag;
		
	public Promotor( Promovivel origem, Promovivel destino,
			boolean modoPromocao, int inicio, int fim ){
		this.origem = origem;
		this.destino = destino;
		this.inicio = inicio;
		this.fim = fim;
		this.flag = Constantes.FLAG_OFF;
	}
	
	public Promotor(Promovivel origem, Promovivel destino, boolean modoPromocao, int flag){
		this.origem = origem;
		this.destino = destino;
		this.inicio = 0;
		this.fim = 0;
		this.flag = flag;
	}
	
	public void promover(){
		
	}

}
