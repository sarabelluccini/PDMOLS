
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;



public class PDMOLSIMPL {
	ArrayList<HashMap<String, int[]>> f = new ArrayList<HashMap<String, int[]>>();

	public ArrayList<HashMap<String, int[]>> insertFactor(String nomefattore, int[] livelli) {
		HashMap<String, int[]> fattore = new HashMap<String, int[]>();
		fattore.put(nomefattore, livelli);
		f.add(fattore);
		return f;
	}

	/*
	 * STEP 1: Ordina i fattori in base al loro modulo, cioè in base al numero
	 * di livelli, dal maggoiore al minore.
	 * STEP 1: organize the factor based on their module, that is based on the number of levels, from highest to lowest
	 */
	public ArrayList<HashMap<String, int[]>> order() {
		ArrayList<HashMap<String, int[]>> orderFac = new ArrayList<HashMap<String, int[]>>();
		int numEle = 0;
		
		for (int i = 0; i < f.size(); i++) {
			
			for (Entry<String, int[]> a : f.get(i).entrySet()) {
				int f1 = a.getValue().length;
				numEle++;
				if (!orderFac.isEmpty()) {
					for (int j = 0; j < numEle; j++) {
						if (j != orderFac.size()) {
							for (Entry<String, int[]> entry2 : orderFac.get(j).entrySet()) {
								if (f1 >= entry2.getValue().length) {
									orderFac.add(j, f.get(i));
									// Break fittizio
									j = numEle;
								}
							}
						} else {
							orderFac.add(f.get(i));
						}
					}
				} else {
					//First factor
					orderFac.add(f.get(i));
				}
			}
		}
		return orderFac;
	}

	// Creazione tabella PDMOLS da cui generiamo i test
	// Creation of PDMOLS table for generation of test
	public void computePDMOLSTable() {
		f = order();
		int b = 0, k = 0, n = 0;
		n = f.size();
		for (Entry<String, int[]> entry : f.get(0).entrySet()) {
			b = entry.getValue().length;
		}
		for (Entry<String, int[]> entry : f.get(1).entrySet()) {
			k = entry.getValue().length;
		}
		// Compute MOLS
		ArrayList<int[][]> molsList = new ArrayList<int[][]>();
		molsList.addAll(MOLS(k));
		//b*k rows e n columns
		int[][] tableRes = new int[b * k][n];
		for (int c = 0; c < n; c++) {
			int d = 0;
			int prima = 0;
			int colTemp = 0;
			int rigaTemp = 0;
			while (d < b * k) {
				d = d + k;
				if (!(d / k > k)) {
					if (c == 0) {
						// La prima colonna metto tutti 1 su B1, 2 su B2 ...
						// fino a k
						for (int r = 0; r < k; r++) {
							tableRes[r + d - k][c] = prima;
						}
					} else if (c == 1) {
						// La seconda colonna inserisco i valore da 0...k per
						// ogni blocco
						for (int r = 0; r < k; r++) {
							tableRes[r + d - k][c] = r;
						}
					} else {
						// Prendo i valori dalle MOLS
						int[][] temp = molsList.get(c - 2);
						for (int r = 0; r < k; r++) {
							tableRes[r + d - k][c] = temp[colTemp][r];
						}
					}
					colTemp++;
					prima++;
				} else {
					// Entro qui nel caso in cui b>k
					if (c < (molsList.size())) {
						// riutilizzo lo MOLS fino a quando posso
						int[][] t = molsList.get(c);
						for (int r = 0; r < k; r++) {
							tableRes[r + d - k][c] = t[rigaTemp][r];
						}
					} else {
						// Se le MOLS non bastano inserisco numeri random
						for (int r = 0; r < k; r++) {
							Random random = new Random();
							tableRes[r + d - k][c] = random.nextInt(4);
						}
					}
					rigaTemp++;
				}
			}
		}
		printTable(tableRes, b, k, n);
	}

	// Fx = a*x + y dove a è un elemento diverso da zero
	// a aumenta ad ogni nuova MOLS che creo
	public ArrayList<int[][]> MOLS(int k) {
		int a = 1;
		int fx = 0;
		ArrayList<int[][]> molsList = new ArrayList<int[][]>();
		if ( !(k<=2) && (k != 6)) {
			// Creazione di una MOLS
			for (int m = 1; m < k; m++) {
				int[][] table = new int[k][k];
				int y = 0;
				for (int j = 0; j < k; j++) {
					int x = 0;
					for (int i = 0; i < k; i++) {
						fx = (a * x + y);
						if (fx < k) {
							table[i][j] = fx;
						} else {
							fx = (fx % k);
							table[i][j] = fx;
						}
						x++;
					}
					y++;
				}
				molsList.add(m - 1, table);
				a++;
			}
		} else {
			System.out.println("Non esistono MOLS per questo k= "+ k);
			System.exit(0);
		}
		checkMOLS(molsList);
		return molsList;
	}

	// Check MOLS list
	public ArrayList<int[][]> checkMOLS(ArrayList<int[][]> molsList) {
		for (int c = 0; c < molsList.size(); c++) {
			if (c != molsList.size() - 1) {
				int[][] temp = molsList.get(c);
				for (int c1 = c+1; c1 < molsList.size(); c1++) {
					int[][] temp1 = molsList.get(c1);
					if (!(temp.equals(temp1))) {
				//		System.out.println("MOLS ok");
					} else {
						System.out.println("Le MOLS sono uguali!");
						molsList.remove(temp1);
					}
				}
			}else{
			//	System.out.println("Finito");
			}
		}
		return molsList;
	}

	// Mostra la tabella dei risultati
	public void printTable(int[][] table, int b, int k, int n) {
		int barra = 1;
		for (int i = 1; i <= n; i++) {
			for (Entry<String, int[]> entry : f.get(i - 1).entrySet()) {
				System.out.print("F" + i + " : " + entry.getKey() + " ");
			}
		}
		System.out.print("\n");
		for (int i = 0; i < b * k; i++) {
			for (int j = 0; j < n; j++) {
				if (j != n - 1) {
					System.out.print(table[i][j] + "-");
				} else {
					System.out.print(table[i][j]);
				}
			}
			if (barra == k) {
				System.out.print("\n -----");
				barra = 0;
			}
			barra++;
			System.out.print("\n");
		}
	}

	// Mostra le MOLS che sono state calcolate
	public void printMOLS(int[][] table, int k) {
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				System.out.print(table[i][j] + "-");
			}
			System.out.print("\n");
		}
	}

	// Mostra i fattori con i relativi livelli
	public void printFact(ArrayList<HashMap<String, int[]>> printFact) {
		for (int i = 0; i < printFact.size(); i++) {
			for (Entry<String, int[]> a : printFact.get(i).entrySet()) {
				System.out.print(a.getKey() + " : ");
				int j = 0;
				while (j < a.getValue().length) {
					if (j != a.getValue().length - 1) {
						System.out.print(a.getValue()[j]);
						j++;
					} else {
						System.out.println(a.getValue()[j]);
						j++;
					}
				}
			}
		}
	}

}
