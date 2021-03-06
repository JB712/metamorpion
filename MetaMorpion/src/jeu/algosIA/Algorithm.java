package jeu.algosIA;

import data.BigGrille;
import data.Coup;
import data.Joueur;
import data.Partie;
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
	 * @param choix : 1 si on veut la grille optimale, 2 pour la case
	 * @param precedent : le coup pr�c�dent, pour savoir s'il doit choisir juste une case ou toute la grille
	 * @return le coup choisi pour la grille indiqué lors de l'instantiation de l'algo
	 */
	public abstract Coup choisirCoup();
	
public boolean terminalTest(Partie par, int tour) {
		
		if (tour == tourMax)
			return true;
		
		if (!par.isGameOver())
			return true;
		
		return false;
	}
}
