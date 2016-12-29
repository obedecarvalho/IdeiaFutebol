class Campeonato ():
	def __init__(self, clubes, qtde1Fase, qtdeGrupos2Fase, qtde3Fase, modo1Fase, modo2Fase, modo3Fase): #modo: 1 - jogo unico, 2 - ida e volta, 0 - nao ha fase
		self.listaClubes = clubes
		self.qtdeClubesAtual = len(clubes)
		self.qtdeClubesTotal = self.qtdeClubesAtual
		
		if qtde1Fase%2 == 0 and (0 < modo1Fase <= 2):
			self.qtdeClubesPrimeiraFase = qtde1Fase
			self.modoPrimeiraFase = modo1Fase
		else:
			self.qtdeClubesPrimeiraFase = 0
			self.modoPrimeiraFase = 0
		
		if qtdeGrupos2Fase != 0 and (self.qtdeClubesTotal - self.qtdeClubesPrimeiraFase/2)%qtdeGrupos2Fase == 0 and (0 < modo2Fase <= 2):
			self.qtdeGruposSegundaFase = qtdeGrupos2Fase
			self.modoSegundaFase = modo2Fase
		else:
			self.qtdeGruposSegundaFase = 0
			self.modoSegundaFase = modo2Fase
		
		
		if self.potencia2(qtde3Fase) and 0 < modo3Fase <= 2:
			self.qtdeClubesTerceiraFase = qtde3Fase
			self.modoTerceiraFase = modo3Fase
		else:
			self.qtdeClubesTerceiraFase = 0
			self.modoTerceiraFase = 0
		
		self.rodadaAtual = 0
		
	def proxRodada(self):
		qtdeRod1Fase = self.modoPrimeiraFase
		if self.qtdeGruposSegundaFase != 0:
			qtdeRod2Fase = qtdeRod1Fase + self.modoSegundaFase*(((self.qtdeClubesTotal- self.qtdeClubesPrimeiraFase/2)/self.qtdeGruposSegundaFase)-1)
		else:
			qtdeRod2Fase = 0
		qtdeRod3Fase = qtdeRod2Fase + self.modoTerceiraFase*self.qtdeFases3Fase(self.qtdeClubesTerceiraFase)
		if self.rodadaAtual < qtdeRod1Fase:
			self.rodadaAtual += 1
			self.primeiraFase()
			
		elif self.rodadaAtual < qtdeRod2Fase:
			self.rodadaAtual += 1
			self.segundaFase()
			
		elif  self.rodadaAtual < qtdeRod3Fase:
			self.rodadaAtual += 1
			self.terceiraFase()
			
		else:
			return False
		return True
	
	def primeiraFase(self):
		print('segunda Fase')
		
	def segundaFase(self):
		print('segunda Fase')
	
	def terceiraFase(self):
		print('terceira Fase')
		
	def potencia2(self, n):
		i = 1
		while i < n:
			i = i << 1
		if i == n:
			return True
		return False
		
	def qtdeFases3Fase(self, n):
		qtde = 0
		while n >= 2:
			n = n/2
			qtde += 1
		if n == 1:
			return qtde
		return 0
