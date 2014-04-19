package com.kucharz.patryk.li;

import java.util.LinkedList;

public class ProblemNKrolowych
{
	int[] x;
	private boolean koniec;

	public ProblemNKrolowych(int n)
	{
		x = new int[n];
	}

	public void rozwiaz()
	{
		umiescKrolowa(0,x.length);
	}
	
	private void narysuj()
	{
		System.out.println("\n");
		for(int i = 0; i < x.length; i++)
		{
			
			for(int j = 0; j < x.length; j++)
			{
				if(x[i] == j)
				{
					System.out.print("Q ");
				}
				else
				{
					System.out.print("* ");
				}
				
			}
			System.out.println();
		}
		System.out.println("\n");
		
	}
	
	private void umiescKrolowa(int wiersz, int n)
	{
		for (int kolumna = 0; kolumna < n; kolumna++)
		{
			if (moznaUmiescicKrolowa(wiersz, kolumna) && !koniec)
			{
				x[wiersz] = kolumna;
//				System.out.println("x["+wiersz+"] = " + x[wiersz] );
				if(wiersz == (x.length - 1))
				{
					System.out.println("Koniec");
					koniec = true;
					narysuj();
				}
				else
				{
					umiescKrolowa(wiersz + 1, n);
				}
			}
		}
	}

	private boolean moznaUmiescicKrolowa(int wiersz, int kolumna)
	{
//		System.out.println("moznaUmiescicKrolowa("+wiersz +"," + kolumna+")");
		if (wiersz == 0)
		{
			if (kolumna > (x.length/2))
				return false;
			else
				return true;
		}
		else
		{

			int[][] tablicaZmiennych = new int[3][wiersz + 1];
			
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < tablicaZmiennych[i].length; j++)
				{
					tablicaZmiennych[i][tablicaZmiennych[i].length-1-j] = kolumna - (j*(i-1));
				}
			}
			
//			for(int i = 0; i < tablicaZmiennych.length; i++)
//			{
//				System.out.print("tablicaZmiennych: ");
//				for(int j = 0; j < tablicaZmiennych[i].length; j++)
//				{
//					System.out.print(j + "," + tablicaZmiennych[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			int liczbaZmiennych = (tablicaZmiennych[0].length +tablicaZmiennych[1].length + tablicaZmiennych[2].length - 2);
			
//			System.out.println("Liczba zmiennych: " + liczbaZmiennych);
			
			
			int[][] klauzule = new int[3][];
			LinkedList<LinkedList<Integer>> listaKlauzul = new LinkedList<LinkedList<Integer>>();
			//Przeksztalcanie klauzul
			for(int i = 0; i < klauzule.length; i++)
			{
				klauzule[i] = new int[tablicaZmiennych[i].length];
				for(int j = 0; j < klauzule[i].length; j++)
				{
					//Tworzenie postaci zmiennych, tak aby sie nie powtarzaly
					klauzule[i][j] = -(tablicaZmiennych[i][j]*10 + 100 * j +1);
				}
			}
			
//			for(int i = 0; i < klauzule.length; i++)
//			{
//				System.out.print("Klauzule ");
//				for(int j = 0; j < klauzule[i].length; j++)
//				{
//					System.out.print(j + "," + klauzule[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			//Przeksztalcanie klauzul
			for(int i = 0; i < klauzule.length; i++)
			{
				listaKlauzul.add(new LinkedList<Integer>());
				for (int j = 0; j < klauzule[i].length; j++)
				{
					if(tablicaZmiennych[i][j]>=0)
					{
						int tmp = -(tablicaZmiennych[i][j] * 10 + 400 * j + 1);
						listaKlauzul.get(i).add(tmp);
					}
				}
				
				if(listaKlauzul.get(i).size() == 1)
					listaKlauzul.removeLast();
			}
			
			
//			for(int i = 0; i < listaKlauzul.size(); i++)
//			{
//				System.out.print("Lista klauzul ");
//				for(int j = 0; j < listaKlauzul.get(i).size(); j++)
//				{
//					System.out.print(j + "," + listaKlauzul.get(i).get(j) + " ");
//				}
//				System.out.println();
//			}
			
			int[] jedynki = new int[wiersz+1];
			jedynki[0] = wiersz * 400 + kolumna * 10 + 1; 
			for(int i = 1; i <= wiersz; i++)
			{
				jedynki[i] = x[i-1]*10 + 400 * (i-1) + 1;
			}	
			
			//Przeksztalcanie klauzul
			klauzule = new int[listaKlauzul.size()][];
			for(int i = 0; i < listaKlauzul.size(); i++)
			{
				klauzule[i] = new int[listaKlauzul.get(i).size()];
//				System.out.print("KlauzuleLast: ");
				for(int j = 0; j < listaKlauzul.get(i).size(); j++)
				{
					klauzule[i][j] = listaKlauzul.get(i).get(j).intValue();
//					System.out.print(klauzule[i][j] + " ");
				}
//				System.out.println();
			}
			
			
			//Przeksztalcanie klauzul
			LinkedList<int[]> listaOstateczna = new LinkedList<int[]>();
			
			for(int i = 0; i < klauzule.length; i ++)
			{
				for(int j = 0; j < klauzule[i].length - 1; j++)
				{
					int[] tmp = new int[2];
					tmp[0] = klauzule[i][j];
					tmp[1] = klauzule[i][klauzule[i].length-1];
					listaOstateczna.add(tmp);		
				}
			}
			//Przeksztalcanie klauzul
			int[][] tabOstateczna = new int[listaOstateczna.size()][];
			for(int i = 0; i < listaOstateczna.size(); i++)
			{
				tabOstateczna[i] = listaOstateczna.get(i);
			}
			
			//Tutaj tworzymy solver i sprawdzamy spelnialnosc wyrazen
			Solver instance = Solver.getInstance();
			if(!instance.solveProblem(tabOstateczna, jedynki, wiersz, liczbaZmiennych))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		

	}

	public static void main(String... args)
	{
		int n = 18;
		
		long milis = System.currentTimeMillis();
		for(int i = 4; i < n; i ++)
		{
			ProblemNKrolowych krolowych = new ProblemNKrolowych(i);
			krolowych.rozwiaz();
		}
		
		milis = ( System.currentTimeMillis() - milis )/ 100;
		System.out.println("Czas: " + milis + " sekund ");
	}
}
