package campeonato;

import entidades.Clube;

public class PontosCorridos implements Fase {
	
	private Tabela tabela;
	private Classificacao classificacao;
	private Clube[] times;
	private int rodadaAtual;
	private int classificadosProxFase;
	
	public PontosCorridos(Clube[] times, boolean jogoUnico, int classificadosProxFase) {
		this.rodadaAtual = 0;
		this.times = times;
		this.classificacao = new Classificacao(times);
		this.tabela = new Tabela(times, jogoUnico);
		this.classificadosProxFase = classificadosProxFase;
	}
	
	public Clube getMelhorTime(){
		return this.classificacao.getTimesEntre(0, 1)[0];
	}
	
	@Override
	public void proxRodada() {
		
	}

	@Override
	public Clube[] getTimesAtivos() {
		return this.classificacao.getTimesEntre(0, classificadosProxFase);
	}
}
