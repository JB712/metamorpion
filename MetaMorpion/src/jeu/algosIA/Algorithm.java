package jeu.algosIA;

import data.BigGrille;
import data.Joueur;
import util.Constantes;
import util.Constantes.Case;

public abstract class Algorithm {
	
	protected int levelIA;
	protected BigGrille grilleDepart;
	protected int tourDepart;
	protected int tourMax;
	/**
	 * Symbole adversaire
	 */
	protected Case symboleMin;
	/**
	 * Symbole joueur courant
	 */
	protected Case symboleMax;
	
	/**
	 * Instanciation de l'algorithme de Jeu
	 * @param levelIA
	 * @param grilleDepart
	 * @param joueurActuel
	 * @param tour
	 */
	public Algorithm(int levelIA, BigGrille grilleDepart, Joueur joueurActuel, int tour)
	{
		this.levelIA=levelIA;
		this.grilleDepart=grilleDepart;
		this.tourDepart=tour;
		this.tourMax= Math.min(tourDepart+levelIA, Constantes.NB_TOUR_MAX);
		if(joueurActuel.getOrder()==Constantes.JOUEUR_1)
		{
			symboleMax=Constantes.SYMBOLE_J1;
			symboleMin=Constantes.SYMBOLE_J2;
		}
		else
		{
			symboleMax=Constantes.SYMBOLE_J2;
			symboleMin=Constantes.SYMBOLE_J1;
		}
	}

	/**
	 * Lancement de l'algorithme de Jeu
	 * @return le coup choisi pour la grille indiqué lors de l'instantiation de l'algo
	 */
	public abstract int choisirCoup();
	
}
