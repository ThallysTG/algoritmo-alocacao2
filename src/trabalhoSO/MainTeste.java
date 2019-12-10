package trabalhoSO;

import java.util.Scanner;

public class MainTeste {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int x = 0;
		int y = 0;
		int contadorF = 0;
		int contadorB = 0;
		int contadorW = 0;
		int fragmentacaoF = 0;
		int fragmentacaoB = 0;
		int fragmentacaoW = 0;

		System.out.println("::::ALGORITMOS DE ALOCACAO DE PARTICAO::::");

		System.out.println("Quantidade de processos: ");
		int qntProcessos = sc.nextInt();
		int qntProcessosF = qntProcessos;
		int qntProcessosB = qntProcessos;
		int qntProcessosW = qntProcessos;

		System.out.println("Quantidade de particoes: ");
		int qntParticoes = sc.nextInt();
		int qntParticoesF = qntParticoes;
		int qntParticoesB = qntParticoes;
		int qntParticoesW = qntParticoes;

		
		int tamProcessoF[] = new int[qntProcessosF];
		int tamProcessoB[] = new int[qntProcessosB];
		int tamProcessoW[] = new int[qntProcessosW];
	
		ParticaoMemoria particaoMemoriaF[] = new ParticaoMemoria[qntParticoesF];
		ParticaoMemoria particaoMemoriaB[] = new ParticaoMemoria[qntParticoesB];
		ParticaoMemoria particaoMemoriaW[] = new ParticaoMemoria[qntParticoesW];

		

			for (x = 0; x < qntProcessos; x++) { // loop enquanto todas as entradas do i forem maior que a quantidade processos
				System.out.println("Tamanho do processo: " + (x + 1)); // +1 pois vetor iniciar no 0
				tamProcessoF[x] = sc.nextInt();
				tamProcessoB[x] = tamProcessoF[x];
				tamProcessoW[x] = tamProcessoF[x];
			}

			for (x = 1; x <= qntParticoes; x++) {
				System.out.println("Tamanho da particao: " + x);
				ParticaoMemoria segF = new ParticaoMemoria();
				ParticaoMemoria segB = new ParticaoMemoria();
				ParticaoMemoria segW = new ParticaoMemoria();
				
				segF.id = x;
				segF.tamanho = sc.nextInt();
				particaoMemoriaF[x - 1] = segF;
				
				segB.id = segF.id;
				segB.tamanho = segF.tamanho;
				particaoMemoriaB[x - 1] = segB;
				
				segW.id = segF.id;
				segW.tamanho = segF.tamanho;
				particaoMemoriaW[x - 1] = segW;
			}
			
			System.out.println("");
			System.out.println("------First-Fit------");

			for (x = 0; x < qntProcessos; x++) {
				
				for (y = 0; y < qntParticoes; y++) {
					
					if (particaoMemoriaF[y].tamanho >= tamProcessoF[x] && particaoMemoriaF[y].utilizada == false) { 
						
						// se o tamanho do processo for menor que a da partição e ainda nao tiver sido utilizada, executa!

						
						if (particaoMemoriaF[y].tamanho - tamProcessoF[x] > 0) { // se a subtração da
							System.out.println("Houve fragmentação interna!");
						} else {
							System.out.println("Não houve fragmentação interna!");
						}

						
						particaoMemoriaF[y].tamanho -= tamProcessoF[x];
						particaoMemoriaF[y].utilizada = true;
				
						System.out.println(
								"Processo " + (x + 1) + " executado no espaço de memória " + particaoMemoriaF[y].id
										+ ". Espaço restante após a alocação " + particaoMemoriaF[y].tamanho);
						System.out.println("---------------------");		
						
						break;
					}
					
				}

				if (y == qntParticoes) {
					System.out.println("Fragmentação Externa! Não há mais memória disponivel para o processo: " + (x + 1) );
					fragmentacaoF = fragmentacaoF + 1;
				}

			}

			for (int j = 0; j < particaoMemoriaF.length; j++) {
				contadorF = particaoMemoriaF[j].tamanho + contadorF;
			}
			
			System.out.println("Quantidade de memória livre no total: " + contadorF);


			// Best-Fit
//
			System.out.println("");
			System.out.println("\n------Best-Fit-------");

			for (x = 0; x < qntParticoesB; x++) {
				for (y = x + 1; y < qntParticoesB; y++) {
					if (particaoMemoriaB[x].tamanho > particaoMemoriaB[y].tamanho) {
						ParticaoMemoria t = particaoMemoriaB[x];
						particaoMemoriaB[x] = particaoMemoriaB[y];
						particaoMemoriaB[y] = t;
					}
				}
			}

			for (x = 0; x < qntProcessosB; x++) {
				for (y = 0; y < qntParticoesB; y++) {
					if (particaoMemoriaB[y].tamanho >= tamProcessoB[x] && particaoMemoriaB[y].utilizada == false) {
						
						// se o tamanho do processo for menor que a da partição e ainda nao tiver sido utilizada, executa!
						
						if (particaoMemoriaB[y].tamanho - tamProcessoB[x] > 0) { // se a subtração da
							System.out.println("Houve fragmentação interna!");
						} else {
							System.out.println("Não houve fragmentação interna!");
						}

						particaoMemoriaB[y].tamanho -= tamProcessoB[x];
						particaoMemoriaB[y].utilizada = true;

						System.out.print("Processo " + (x + 1) + " executado no espaço de memória "
								+ particaoMemoriaB[y].id + ". ");
						System.out.println("Espaço restante após a alocação " + particaoMemoriaB[y].tamanho);
						
					
						
						System.out.println("---------------------");
						break;
					}

				}
				if (y == qntParticoesB) {
					System.out.println("Fragmentação Externa! Não há mais memória disponivel para o processo: " + (x + 1) );
					fragmentacaoB = fragmentacaoB + 1;
					
				}
			}
			
			for (int j = 0; j < particaoMemoriaB.length; j++) {
				contadorB = particaoMemoriaB[j].tamanho + contadorB;
			}

			System.out.println("Quantidade de memória livre no total: " + contadorB);


			// Worst-Fit
			
			System.out.println("");
			System.out.println("\n------Worst-Fit------");


			ordenar(particaoMemoriaW, qntParticoesW);

			for (x = 0; x < qntProcessosW; x++) {
				for (y = 0; y < qntParticoesW; y++) {
					if (particaoMemoriaW[y].tamanho >= tamProcessoW[x] && particaoMemoriaW[y].utilizada == false) {
						

						if (particaoMemoriaW[y].tamanho - tamProcessoW[x] > 0) { // se a subtração da
							System.out.println("Houve fragmentação interna!");
						} else {
							System.out.println("Não houve fragmentação interna!");
						}

						particaoMemoriaW[y].tamanho -= tamProcessoW[x];
						particaoMemoriaW[y].utilizada = true;

						System.out.print("Processo " + (x + 1) + " executado no espaço de memoria "
								+ particaoMemoriaW[y].id + ". ");
						System.out.println("Espaço restante após a alocação " + particaoMemoriaW[y].tamanho);
						ordenar(particaoMemoriaW, qntParticoes);


						System.out.println("---------------------");
						break;
					}

				}
				if (y == qntParticoes) {
					System.out.println("Fragmentação Externa! Não há mais memória disponivel para o processo: " + (x + 1));
					fragmentacaoW = fragmentacaoW + 1;
					
				}
			}
			
			for (int j = 0; j < particaoMemoriaW.length; j++) {
				contadorW = particaoMemoriaW[j].tamanho + contadorW;
			}

			System.out.println("Quantidade de memória livre no total: " + contadorW);
			
			
			
			
			
			if (contadorF < contadorB && contadorF < contadorW &&
					fragmentacaoF < fragmentacaoB &&  fragmentacaoF < fragmentacaoW ) {
				System.out.println("\nPara essa simulação, o melhor algoritmo: First-Fit");
			}
			
			if (contadorB < contadorF && contadorB < contadorW &&
					fragmentacaoB < fragmentacaoF &&  fragmentacaoB < fragmentacaoW ) {
				System.out.println("\nPara essa simulação, o melhor algoritmo: Best-Fit");
			}
			
			if (contadorW < contadorF && contadorW < contadorB &&
					fragmentacaoW < fragmentacaoF &&  fragmentacaoW < fragmentacaoB ) {
				System.out.println("\nPara essa simulação, o melhor algoritmo: Worst-Fit");
			}
			


	}
	
	

	private static void ordenar(ParticaoMemoria particaoMemoriaW[], int m) {
		int i, j;
		for (i = 0; i < m; i++) {
			for (j = i + 1; j < m; j++) {
				if (particaoMemoriaW[i].tamanho < particaoMemoriaW[j].tamanho) {
					ParticaoMemoria t = particaoMemoriaW[i];
					particaoMemoriaW[i] = particaoMemoriaW[j];
					particaoMemoriaW[j] = t;

				}
			}
		}
	}

}
