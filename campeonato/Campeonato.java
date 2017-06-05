package campeonato;

import entidades.Clube;
import entidades.Constantes;

public class Campeonato implements Promovivel {

	private int rodadaAtual;
	private Clube[] times;
	private Flag[] flags;
	
	private int flag;
	
	private MataMata _1Fase;
	private PontosCorridos[] _2Fase;
	private MataMata _3Fase;
	
	private int qtdeClubes1Fase;
	private int qtdeGrupos2Fase;
	private int qtdeClubes3Fase;
	
	private boolean jogoUnico3Fase;
	private boolean jogoUnico2Fase;
	private boolean jogoUnico1Fase;

	private int qtdeRod1Fase;
	private int qtdeRod2Fase;
	private int qtdeRod3Fase;
	
	public Campeonato(Clube[] times, int flag,
			int qtdeTimes1Fase, int qtdeGrupos2Fase,int qtdeTimes3Fase, 
			boolean jogoUnico1Fase, boolean jogoUnico2Fase, boolean jogoUnico3Fase ){
		
		this.rodadaAtual = 0;
		this.flag = flag;
		this.times = times;
		this.inicializar(qtdeTimes1Fase, qtdeGrupos2Fase, qtdeTimes3Fase,
				jogoUnico1Fase, jogoUnico2Fase, jogoUnico3Fase);
		this.calcularRodadas();
		this.createFlags();
	}
	
	private void inicializar(
			int qtdeTimes1Fase, int qtdeGrupos2Fase,int qtdetTimes3Fase, 
			boolean jogoUnico1Fase, boolean jogoUnico2Fase, boolean jogoUnico3Fase){
		if (qtdeTimes1Fase%2 == 0)
			this.qtdeClubes1Fase = qtdeTimes1Fase;
		else
			this.qtdeClubes1Fase = 0;
		this.jogoUnico1Fase = jogoUnico1Fase;
		
		if (qtdeGrupos2Fase != 0 
				&& (this.times.length - this.qtdeClubes1Fase/2)%qtdeGrupos2Fase == 0)
			this.qtdeGrupos2Fase = qtdeGrupos2Fase;
		else
			this.qtdeGrupos2Fase = 0;
		this.jogoUnico2Fase = jogoUnico2Fase;
		
		if (this.potencia2(qtdetTimes3Fase))
			this.qtdeClubes3Fase = qtdetTimes3Fase;			
		else
			this.qtdeClubes3Fase = 0;		
		this.jogoUnico3Fase = jogoUnico3Fase;
	}
	
	public int qtdeJogos(boolean jogoUnico){
		if (jogoUnico) return 1;
		return 2;
	}
	
	private void calcularRodadas(){
		if (this.qtdeClubes1Fase != 0)
			this.qtdeRod1Fase = qtdeJogos(this.jogoUnico1Fase);
		else
			this.qtdeRod1Fase = 0;
		
		if (this.qtdeGrupos2Fase != 0)
			this.qtdeRod2Fase = qtdeJogos(this.jogoUnico2Fase) 
					* (((this.times.length - this.qtdeClubes1Fase/2)/this.qtdeGrupos2Fase)-1);					
		else
			this.qtdeRod2Fase = 0;
		
		this.qtdeRod3Fase = qtdeJogos(this.jogoUnico3Fase) * this.qtdeFases3Fase(this.qtdeClubes3Fase);
		
	}
	
	public void proxRodada(){		
		if (this.rodadaAtual < this.qtdeRod1Fase){
			this.rodadaAtual += 1;
			this.primeiraFase();
		} else if (this.rodadaAtual < (this.qtdeRod2Fase+this.qtdeRod1Fase)) {
			this.rodadaAtual += 1;
			this.segundaFase();			
		} else if ( this.rodadaAtual < (this.qtdeRod3Fase + this.qtdeRod2Fase+this.qtdeRod1Fase)) {
			this.rodadaAtual += 1;
			this.terceiraFase();
		}
		
		if (rodadaAtual == (this.qtdeRod3Fase + this.qtdeRod2Fase+this.qtdeRod1Fase)){
			if (this.qtdeRod3Fase != 0){
				
			} else if (this.qtdeRod2Fase != 0){
				Clube melhor = this._2Fase[0].getMelhorTime();
				for (Flag f : this.flags){
					if (f.time.equals(melhor)){
						f.setFlag(this.flag);
					}
				}
			}
			this.ordenarFlag();
		}
		
	}
	
	private void primeiraFase(){
		System.out.println("Primeira Fase");
		if (this._1Fase == null){
			Clube[] times1Fase = new Clube[this.qtdeClubes1Fase];
			for (int i=0; i < this.qtdeClubes1Fase; i++){
				times1Fase[i] = this.times[this.times.length - 1 - i];
			}
			this._1Fase = new MataMata(times1Fase, this.jogoUnico1Fase);
		}
		this._1Fase.proxRodada();
	}
	
	private void segundaFase(){
		System.out.println("Segunda Fase");
		if (this._2Fase == null){
			this._2Fase = new PontosCorridos[this.qtdeGrupos2Fase];
			Clube[] times2Fase = new Clube[this.times.length - this.qtdeClubes1Fase/2];
			for (int i=0; i< (this.times.length - this.qtdeClubes1Fase) ; i++){
				times2Fase[i] = times[i];
			}
			Clube[] timesAtivos1Fase = this._1Fase.getTimesAtivos();
			for (int i=0; i<this.qtdeClubes1Fase/2; i++){
				times2Fase[i+(this.times.length - this.qtdeClubes1Fase)] = timesAtivos1Fase[i];
			}
			Clube[] timesGrupo = new Clube[(this.times.length - this.qtdeClubes1Fase/2)/this.qtdeGrupos2Fase];
			for (int g = 0; g< this.qtdeGrupos2Fase; g++){
				for (int i=0; i<timesGrupo.length; i++){
					timesGrupo[i] = times[g + timesGrupo.length * i];
				}
				this._2Fase[g] = new PontosCorridos(timesGrupo, this.jogoUnico2Fase, this.qtdeClubes3Fase/this.qtdeGrupos2Fase);
			}	
		}
		for (int i = 0; i< this.qtdeGrupos2Fase; i++)
			this._2Fase[i].proxRodada();
	}
	
	private void terceiraFase(){
		System.out.println("Terceira Fase");
		if (this._3Fase == null){
			int pos = 0;
			Clube[] times3Fase = new Clube[this.qtdeClubes3Fase];
			for (int i=0; i<this.qtdeGrupos2Fase; i++){
				Clube[] clubesAtivoGrupo = this._2Fase[i].getTimesAtivos();
				for (Clube c : clubesAtivoGrupo){
					times3Fase[pos] = c;
					pos++;
				}
			}
			this._3Fase = new MataMata(times3Fase, jogoUnico3Fase);				
		}
		this._3Fase.proxRodada();
	}
	
	private boolean potencia2(int n){
		int i = 1;
		while (i < n){
			i = i << 1;
		}
		if (i == n)
			return true;
		return false;
	}
	
	private int qtdeFases3Fase(int n){
		int qtde = 0;
		while (n >= 2){
			n = n/2;
			qtde ++;
		}
		if (n == 1)
			return qtde;
		return 0;		
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
		this._1Fase = null;
		this._2Fase = null;
		this._3Fase = null;
		this.rodadaAtual = 0;
		this.createFlags();
	}
	
	public class Flag{
		public int flag;
		public Clube time;
		public Flag(Clube time, int flag) {
			this.flag = flag;
			this.time = time;
		}
		public void setFlag(int flag){
			if (flag > this.flag)
				this.flag = flag;
			return;
		}
	}

}
