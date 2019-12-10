package trabalhoSO;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int x;
		int y = 0;
		int contador = 0;

		System.out.println("::::ALGORITMOS DE ALOCA��O DE PARTI��O::::");

		System.out.println("Quantidade de processos: ");
		int qntProcessos = sc.nextInt();

		System.out.println("Quantidade de parti��es: ");
		int qntParticoes = sc.nextInt();

		int tamProcessos[] = new int[qntProcessos];
		
		ParticaoMemoria particaoMemoria[] = new ParticaoMemoria[qntParticoes];

		System.out.println("Selecione qual parti��o deseja usar");
		System.out.println("\n 1: First-Fit \n 2: Best-Fit \n 3: Worst-Fit");
		int decisao = sc.nextInt();

		switch (decisao) {
		case 1: // First-Fit

			for (x = 0; x < qntProcessos; x++) { // loop enquanto todas as entradas do i forem maior que a quantidade processos
				System.out.println("Tamanho do processo: " + (x + 1)); // +1 pois vetor iniciar no 0
				tamProcessos[x] = sc.nextInt();
			}

			for (x = 1; x <= qntParticoes; x++) {
				System.out.println("Tamanho da parti��o: " + x);
				ParticaoMemoria seg = new ParticaoMemoria();
				seg.id = x;
				seg.tamanho = sc.nextInt();
				particaoMemoria[x - 1] = seg;
			}

			for (x = 0; x < qntProcessos; x++) {
				for (y = 0; y < qntParticoes; y++) {
					if (particaoMemoria[y].tamanho >= tamProcessos[x] && particaoMemoria[y].utilizada == false) { 
						// se o tamanho do processo for menor que a da parti��o e ainda nao tiver sido utilizada, executa!

						if (particaoMemoria[y].tamanho - tamProcessos[x] > 0) { // se a subtra��o da
							System.out.println("Houve fragmenta��o interna!");
						} else {
							System.out.println("N�o houve fragmenta��o interna!");
						}

						particaoMemoria[y].tamanho -= tamProcessos[x];
						particaoMemoria[y].utilizada = true;

						System.out.println(
								"Processo " + (x + 1) + " executado no espa�o de mem�ria " + particaoMemoria[y].id
										+ ". Espa�o restante ap�s a aloca��o " + particaoMemoria[y].tamanho);

						System.out.println("---------------------");

						contador = contador + particaoMemoria[y].tamanho;
						break;
					}

				}

				if (y == qntParticoes) {
					System.out.println("Fragmenta��o Externa! N�o h� mais mem�ria disponivel para o processo!");
				}

			}

			
			System.out.println("Quantidade de mem�ria livre no total: " + contador);

			break;

		case 2: // Best-Fit

			for (x = 0; x < qntProcessos; x++) {
				System.out.println("Tamanho do processo: " + (x + 1));
				tamProcessos[x] = sc.nextInt();
			}

			for (x = 1; x <= qntParticoes; x++) {
				System.out.println("Tamanho da parti��o: " + x);
				ParticaoMemoria seg = new ParticaoMemoria();
				seg.id = x;
				seg.tamanho = sc.nextInt();
				particaoMemoria[x - 1] = seg;
			}

			for (x = 0; x < qntParticoes; x++) {
				for (y = x + 1; y < qntParticoes; y++) {
					if (particaoMemoria[x].tamanho > particaoMemoria[y].tamanho) {
						ParticaoMemoria t = particaoMemoria[x];
						particaoMemoria[x] = particaoMemoria[y];
						particaoMemoria[y] = t;
					}
				}
			}

			for (x = 0; x < qntProcessos; x++) {
				for (y = 0; y < qntParticoes; y++) {
					if (particaoMemoria[y].tamanho >= tamProcessos[x] && particaoMemoria[y].utilizada == false) {
						
						// se o tamanho do processo for menor que a da parti��o e ainda nao tiver sido utilizada, executa!
						
						if (particaoMemoria[y].tamanho - tamProcessos[x] > 0) { // se a subtra��o da
							System.out.println("Houve fragmenta��o interna!");
						} else {
							System.out.println("N�o houve fragmenta��o interna!");
						}

						particaoMemoria[y].tamanho -= tamProcessos[x];
						particaoMemoria[y].utilizada = true;

						System.out.print("Processo " + (x + 1) + " executado no espa�o de mem�ria "
								+ particaoMemoria[y].id + ". ");
						System.out.println("Espa�o restante ap�s a aloca��o " + particaoMemoria[y].tamanho);
						
						contador = contador + particaoMemoria[y].tamanho;
						
						System.out.println("---------------------");
						break;
					}

				}
				if (y == qntParticoes) {
					System.out.println("Fragmenta��o Externa! N�o h� mais mem�ria disponivel para o processo!");
					break;
				}
			}

			System.out.println("Quantidade de mem�ria livre no total: " + contador);
			break;

		case 3: // Worst-Fit

			for (x = 0; x < qntProcessos; x++) {
				System.out.println("Tamanho do processo " + (x + 1));
				tamProcessos[x] = sc.nextInt();
			}

			for (x = 1; x <= qntParticoes; x++) {
				System.out.println("Tamanho da parti��o: " + x);
				ParticaoMemoria seg = new ParticaoMemoria();
				seg.id = x;
				seg.tamanho = sc.nextInt();
				particaoMemoria[x - 1] = seg;
			}

			ordenar(particaoMemoria, qntParticoes);

			for (x = 0; x < qntProcessos; x++) {
				for (y = 0; y < qntParticoes; y++) {
					if (particaoMemoria[y].tamanho >= tamProcessos[x]) {
						

						if (particaoMemoria[y].tamanho - tamProcessos[x] > 0) { // se a subtra��o da
							System.out.println("Houve fragmenta��o interna!");
						} else {
							System.out.println("N�o houve fragmenta��o interna!");
						}

						particaoMemoria[y].tamanho -= tamProcessos[x];

						System.out.print("Processo " + (x + 1) + " executado no espa�o de memoria "
								+ particaoMemoria[y].id + ". ");
						System.out.println("Espa�o restante ap�s a aloca��o " + particaoMemoria[y].tamanho);
						ordenar(particaoMemoria, qntParticoes);

						contador = contador + particaoMemoria[y].tamanho;

						System.out.println("---------------------");
						break;
					}

				}
				if (y == qntParticoes) {
					System.out.println("Fragmenta��o Externa! N�o h� mais mem�ria disponivel para o processo!");
					break;
				}
			}

			System.out.println("Quantidade de mem�ria livre no total: " + contador);
			break;

		default:

			break;
		}

	}

	private static void ordenar(ParticaoMemoria particaoMemoria[], int m) {
		int i, j;
		for (i = 0; i < m; i++) {
			for (j = i + 1; j < m; j++) {
				if (particaoMemoria[i].tamanho < particaoMemoria[j].tamanho) {
					ParticaoMemoria t = particaoMemoria[i];
					particaoMemoria[i] = particaoMemoria[j];
					particaoMemoria[j] = t;

				}
			}
		}
	}

}
