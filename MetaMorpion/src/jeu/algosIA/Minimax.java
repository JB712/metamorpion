package jeu.algosIA;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data.BigGrille;
import data.Joueur;
import util.Constantes;


public class Minimax extends Algorithm {


	public Minimax(int levelIA, BigGrille grilleDepart, Joueur joueurActuel, int tour) {
		super(levelIA, grilleDepart, joueurActuel, tour);
	}

	private double max=Constantes.SCORE_MAX_NON_DEFINI;

	public Coup choisirCoup()
	{
		List<Coup> meilleursCoups=new ArrayList<>();
		Thread[] threads = new Thread[9];

		for (int i=0;i<9;i++)
		{
			final int g=i;
			threads[i]=new Thread(new Runnable() {

				@Override
				public void run() {
					for (int c=0;c<9;c++)
					{
						if(grilleDepart.isCoupPossible(new Coup(g,c))){ 
							BigGrille bg2 = grilleDepart.clone();
							bg2.ajouterCoup(new Coup(g, c), symboleMax);
							double temp = min(bg2,tourDepart);

							if (temp==max)
							{
								meilleursCoups.add(new Coup(g,c));
							}
							else if(temp>max)
							{
								max = temp;
								meilleursCoups.clear();
								meilleursCoups.add(new Coup(g,c));
							}
						}
					}
				}
			});
			threads[i].start();
		}

		for (Thread t : threads)
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Les meilleurs coups sont : ");
		for (Coup coup : meilleursCoups) {
			System.out.println(coup);
		}
		return meilleursCoups.get(new Random().nextInt(meilleursCoups.size()));
	}

	public double min(BigGrille g, int tour)
	{
		if(tour == tourMax || g.getEtatPartie(symboleMax)!=Constantes.PARTIE_EN_COURS)
		{
			return g.evaluer(symboleMax)/*-profondeur*/;
		}

		double min = Constantes.SCORE_MIN_NON_DEFINI;
		double temp;

		for(int i=0;i<9;i++)
		{
			int grille=i;
			for (int j=0;j<9;j++)
			{
				if(g.isCoupPossible(new Coup(grille, j)))
				{
					BigGrille bg2 = g.clone();
					bg2.ajouterCoup(new Coup(grille, j), symboleMin);
					temp=max(bg2,tour+1);

					if(temp < min)
					{
						min = temp;
					}
				}
			}
		}
		return min;
	}

	double max(BigGrille g, int tour){
		if(tour == tourMax || g.getEtatPartie(symboleMin)!=Constantes.PARTIE_EN_COURS)
		{
			return g.evaluer(symboleMax)/*+profondeur*/;
		}

		double max = Constantes.SCORE_MAX_NON_DEFINI;
		double temp;

		for(int i=0;i<9;i++)
		{
			int grille=i;
			for (int j=0;j<9;j++)
			{
				if(g.isCoupPossible(new Coup(grille, j)))
				{
					BigGrille bg2 = g.clone();
					bg2.ajouterCoup(new Coup(grille, j), symboleMax);
					temp=min(bg2,tour+1);

					if(temp > max)
					{
						max = temp;
					}
				}
			}
		}
		return max;
	}

	/*public int choisirCoup(int choix, int precedent) {

		System.out.println(" Evaluation : "+grilleDepart.evaluer(symboleMax));

		if(!grilleDepart.isGrilleLibre(precedent)){		//Si le joueur choisis la grille et la case


			for(int i=0; i<Constantes.NB_TOUR_MAX; i++){
				int g = i/8;
				int c = i%8;
				threads[i]=new Thread(new Runnable() {

					@Override
					public void run() {
						double temp;
						double max = Constantes.SCORE_MAX_NON_DEFINI;
						BigGrille bg2 = grilleDepart.clone();
						if(bg2.isCoupPossible(g, c)){
							bg2.ajouterCoup(c, g, symboleMax);
							temp=(int)min(bg2,levelIA,g);

							if(temp>max){
								max = temp;
								meilleurCoupG = g;
								meilleurCoupC = c;
							}
						}
					}
				});
				threads[i].start();
			}

			for (Thread t : threads){
				try{
					t.join();
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}

		}else{		//Si le joueur ne choisis que la case
			Thread[] threads = new Thread[9];

			for(int i=0; i<9; i++){
				final int c=i;
				threads[i]=new Thread(new Runnable(){

					@Override
					public void run() {
						double temp;
						double max = Constantes.SCORE_MAX_NON_DEFINI;
						BigGrille bg2 = grilleDepart.clone();
						if(bg2.isCoupPossible(precedent, c)){
							bg2.ajouterCoup(c, precedent, symboleMax);
							temp=(int)min(bg2,levelIA,precedent);

							if(temp>max){
								max = temp;
								meilleurCoupG = precedent;
								meilleurCoupC = c;
							}
						}
					}
				});
				threads[i].start();
			}
			for (Thread t : threads){
				try{
					t.join();
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		if(choix == 1) return meilleurCoupG+1;
		else return meilleurCoupC+1;
	}

	private double min(BigGrille bg, int profondeur, int precedent) {
		if (profondeur == 0 || bg.getEtatPartie(symboleMin)!=Constantes.PARTIE_EN_COURS) {
			return bg.evaluer(symboleMax);
		}
		else{
			double min = Constantes.SCORE_MAX_NON_DEFINI;
			if(!grilleDepart.isGrilleLibre(precedent)){
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++){
						if (bg.isCoupPossible(i,j)){
							BigGrille bg2 = bg.clone();
							bg2.ajouterCoup(j,i, symboleMin);
							min = Math.min(min, max(bg2, profondeur-1, i)); //tour ici sinon boucle infini avec min max min etc..
						}
					}
				}
			}else{
				for (int j = 0; j < 9; j++){
					if (bg.isCoupPossible(precedent,j)){
						BigGrille bg2 = bg.clone();
						bg2.ajouterCoup(j, precedent, symboleMin);
						min = Math.min(min, max(bg2, profondeur-1, precedent)); //tour ici sinon boucle infini avec min max min etc..
					}
				}
			}
			return min;
		}
	}


	private double max(BigGrille bg,int profondeur, int precedent) {
		if (profondeur == 0 || bg.getEtatPartie(symboleMax)!=Constantes.PARTIE_EN_COURS) {
			return bg.evaluer(symboleMax);
		}
		else{
			double max = Constantes.SCORE_MIN_NON_DEFINI;
			if(!grilleDepart.isGrilleLibre(precedent)){
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++){
						if (bg.isCoupPossible(i,j)){
							BigGrille bg2 = bg.clone();
							bg2.ajouterCoup(i,j, symboleMax);
							max = Math.max(max, this.min(bg2, profondeur-1, j)); 
						}
					}
				}
			}else{
				for (int j = 0; j < 9; j++){
					if (bg.isCoupPossible(precedent,j)){
						BigGrille bg2 = bg.clone();
						bg2.ajouterCoup(j, precedent, symboleMax);
						max = Math.max(max, this.min(bg2, profondeur-1, j)); 
					}
				}
			}
			return max;
		}

	}*/
}