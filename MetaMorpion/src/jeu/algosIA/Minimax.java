package jeu.algosIA;

import data.BigGrille;
import data.Joueur;
import util.Constantes;


public class Minimax extends Algorithm {

	public static int profondeur = 1;
	int meilleurCoupG = Constantes.COUP_NON_DEFINI;
	int meilleurCoupC = Constantes.COUP_NON_DEFINI;

	public Minimax(int levelIA, BigGrille grilleDepart, Joueur joueurActuel, int tour) {
		super(levelIA, grilleDepart, joueurActuel, tour);
	}

	public int choisirCoup(int choix) {
		
		System.out.println(" Evaluation : "+grilleDepart.evaluer(symboleMax));
		
		Thread[] threads = new Thread[Constantes.NB_TOUR_MAX];
		
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
						temp=(int)min(bg2,levelIA);
						
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
		if(choix == 1) return meilleurCoupG;
		else return meilleurCoupC;
		
	}

	private double min(BigGrille bg, int profondeur) {
		if (profondeur == 0 || bg.getEtatPartie(symboleMin)!=Constantes.PARTIE_EN_COURS) {
			return bg.evaluer(symboleMax);
		}
		else{
			double min = Constantes.SCORE_MAX_NON_DEFINI;
			for (int i = 0; i < 9; i++) {
				
				for (int j = 0; j < 9; j++){
					if (bg.isCoupPossible(i,j)){
						BigGrille bg2 = bg.clone();
						bg2.ajouterCoup(i,j, symboleMin);
						min = Math.min(min, max(bg2, profondeur-1)); //tour ici sinon boucle infini avec min max min etc..
					}
				}
			}
			return min;
		}
	}


	private double max(BigGrille bg,int profondeur) {
		if (profondeur == 0 || bg.getEtatPartie(symboleMax)!=Constantes.PARTIE_EN_COURS) {
			return bg.evaluer(symboleMax);
		}
		else{
			double max = Constantes.SCORE_MIN_NON_DEFINI;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++){
					if (bg.isCoupPossible(i,j)){
						BigGrille bg2 = bg.clone();
						bg2.ajouterCoup(i,j, symboleMax);
						max = Math.max(max, this.min(bg2, profondeur-1)); 
					}
				}
			}
			return max;
		}

	}
}