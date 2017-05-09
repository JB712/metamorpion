package jeu.algosIA;

import java.util.ArrayList;
import java.util.Random;

import data.BigGrille;
import data.Joueur;
import data.Partie;
import util.Constantes;


public class Minimax extends Algorithm {

	public static int profondeur = 1;

	public Minimax(int levelIA, BigGrille grilleDepart, Joueur joueurActuel, int tour) {
		super(levelIA, grilleDepart, joueurActuel, tour);
	}

	@Override
	public int choisirCoup() {
		
		//System.out.println(grilleDepart.evaluer(symboleMax, tourDepart));
		
		ArrayList<Integer> colonne = new ArrayList<Integer>();
		
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if (grilleDepart.isCoupPossible(i,j)){
					colonne.add(i+1);
				}
			}
		}
		
		double valeur = Constantes.SCORE_MIN_NON_DEFINI;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++){
				if (grilleDepart.isCoupPossible(i, j)) {
					BigGrille grillebis = grilleDepart.clone();
					grillebis.ajouterCoup(i,j, symboleMax);
					double valeurCourante = min(party, grillebis, tourDepart);
					if (valeurCourante > valeur) {
						colonne.clear();
						colonne.add(i);
						valeur = valeurCourante;
					}
					if (valeurCourante == valeur){
						colonne.add(i);
					}
				}
			}
		}
		return colonne.get(new Random().nextInt(colonne.size()));
	}

	private double min(Partie par,BigGrille grille, int tour) {
		if (terminalTest(par, tour)) {
			return grille.evaluer(symboleMax);
		}
		else{


			double valeur = Constantes.SCORE_MAX_NON_DEFINI;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++){
					if (grille.isCoupPossible(i,j)){
						BigGrille grillebis = grille.clone();
						grillebis.ajouterCoup(i,j, symboleMin);
						valeur = Math.min(valeur, this.max(par, grillebis, tour+1)); //tour ici sinon boucle infini avec min max min etc..
					}
				}
			}
			return valeur;
		}
	}


	private double max(Partie par, BigGrille grille,int tour) {
		if (terminalTest(par, tour)) {
			return grille.evaluer(symboleMax);
		}
		else{
			double valeur = Constantes.SCORE_MIN_NON_DEFINI;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++){
					if (grille.isCoupPossible(i,j)){
						BigGrille grillebis = grille.clone();
						grillebis.ajouterCoup(i,j, symboleMax);
						valeur = Math.max(valeur, this.min(par, grillebis, tour+1)); 
					}
				}
			}
			return valeur;
		}

	}
}