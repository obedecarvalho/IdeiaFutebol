package Campeonato;

import java.util.ArrayList;

import Entidades.Clube;

public class Campeonato {
	
	public final static int SEM_FASE = 0;
	public final static int JOGO_UNICO = 1;
	public final static int JOGO_IDAVOLTA = 2;
	
	private int rodadaAtual = 0;
	private int qtdeClubesAtual;
	private int qtdeClubesTotal;
	
	private int qtdeClubesPrimeiraFase;
	private int qtdeGruposSegundaFase;
	private int qtdeClubesTerceiraFase;
	
	private int modoTerceiraFase;
	private int modoSegundaFase;
	private int modoPrimeiraFase;
	
	private int qtdeRod1Fase;
	private int qtdeRod2Fase;
	private int qtdeRod3Fase;
	
	private ArrayList<Clube> listaClubes;
	
	public Campeonato(ArrayList<Clube> clubes, 
			int qtde1Fase, int qtdeGrupos2Fase,int qtde3Fase, 
			int modo1Fase, int modo2Fase, int modo3Fase ){
		
		this.listaClubes = clubes;
		this.qtdeClubesAtual = clubes.size();
		//this.qtdeClubesAtual = 16; //teste
		this.qtdeClubesTotal = this.qtdeClubesAtual;
		this.inicializar(qtde1Fase, qtdeGrupos2Fase, qtde3Fase, modo1Fase, modo2Fase, modo3Fase);
		this.calcularRodadas();
		
	}
	
	private void inicializar(
			int qtde1Fase, int qtdeGrupos2Fase,int qtde3Fase, 
			int modo1Fase, int modo2Fase, int modo3Fase){
		if (qtde1Fase%2 == 0 && (0 < modo1Fase && modo1Fase <= 2)){
			this.qtdeClubesPrimeiraFase = qtde1Fase;
			this.modoPrimeiraFase = modo1Fase;
		} else {
			this.qtdeClubesPrimeiraFase = 0;
			this.modoPrimeiraFase = SEM_FASE;
		}
		
		if (qtdeGrupos2Fase != 0 
				&& (this.qtdeClubesTotal - this.qtdeClubesPrimeiraFase/2)%qtdeGrupos2Fase == 0 
				&& (0 < modo2Fase && modo2Fase <= 2)){
			this.qtdeGruposSegundaFase = qtdeGrupos2Fase;
			this.modoSegundaFase = modo2Fase;
		} else {
			this.qtdeGruposSegundaFase = 0;
			this.modoSegundaFase = SEM_FASE;
		}
		
		if (this.potencia2(qtde3Fase) && (0 < modo3Fase && modo3Fase <= 2)){
			this.qtdeClubesTerceiraFase = qtde3Fase;
			this.modoTerceiraFase = modo3Fase;
		} else {
			this.qtdeClubesTerceiraFase = 0;
			this.modoTerceiraFase = SEM_FASE;
		}
		
		
	}
	
	private void calcularRodadas(){
		this.qtdeRod1Fase = this.modoPrimeiraFase;
		
		if (this.qtdeGruposSegundaFase != 0){
			this.qtdeRod2Fase = this.qtdeRod1Fase + this.modoSegundaFase 
					* (((this.qtdeClubesTotal - this.qtdeClubesPrimeiraFase/2)/this.qtdeGruposSegundaFase)-1);					
		} else {
			this.qtdeRod2Fase = 0;
		}
		
		this.qtdeRod3Fase = this.qtdeRod2Fase + this.modoTerceiraFase * this.qtdeFases3Fase(this.qtdeClubesTerceiraFase);
		
	}
	
	public boolean proxRodada(){
		
		if (this.rodadaAtual < this.qtdeRod1Fase){
			this.rodadaAtual += 1;
			this.primeiraFase();
		} else if (this.rodadaAtual < this.qtdeRod2Fase) {
			this.rodadaAtual += 1;
			this.segundaFase();
			
		} else if ( this.rodadaAtual < this.qtdeRod3Fase) {
			this.rodadaAtual += 1;
			this.terceiraFase();			
		} else {
			return false;
		}
		System.out.println(this.rodadaAtual);//teste
		return true;	
	}
	
	private void primeiraFase(){
		System.out.println("Primeira Fase");
	}
	
	private void segundaFase(){
		System.out.println("Segunda Fase");
	}
	
	private void terceiraFase(){
		System.out.println("Terceira Fase");
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

}
