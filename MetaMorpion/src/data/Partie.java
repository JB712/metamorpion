package data;

import util.*;

public class Partie {
	
	private Joueur j1, j2;
	private Joueur jCourant;
	private int tour;
	private BigGrille grille;
	private int etatPartie;
	private int precedent=-1;
	
	public Partie(Joueur joueur1, Joueur joueur2){
		j1=joueur1;
		j2=joueur2;
		jCourant=j1;
		etatPartie=Constantes.PARTIE_EN_COURS;
		grille = new BigGrille();
	}
	
	/**
	 * Renvoie le joueur 1
	 * @return Joueur joueur1
	 */
	public Joueur getJ1(){
		return j1;
	}
	
	/**
	 * Renvoie le joueur 2
	 * @return Joueur joueur2
	 */

	public Joueur getJ2(){
		return j2;
	}
	
	/**
	 * Renvoie le joueur courant
	 * @return Joueur
	 */
	public Joueur getJCourant(){
		return jCourant;
	}
	
	/**
	 * Renvoie le numÃ©ro du tour actuel
	 * @return
	 */
	public int getTour(){
		return tour;
	}
	
	public boolean isGrilleLibre(int big){
		if(big>8 || big<0) return false;
		if(grille.getCase(big).getEtat()!=Constantes.Case.V) return false;
		if(grille.getCase(big).isFull()) return false;
		return true;
	}

	public BigGrille getGrille() {
		return grille;
	}

	public int getEtatPartie() {
		return etatPartie;
	}

	public int getPrecedent() {
		return precedent;
	}

	/**
	 * Renvoie un booleen qui dit si la partie est terminée.
	 * Si la partie est terminée, met la variable etatPartie à jour.
	 * @return
	 */
	public boolean isGameOver(){
		if(grille.wintest(j1.getSymbole()).equals(j1.getSymbole())){
			etatPartie = Constantes.VICTOIRE_JOUEUR_1;
			return true;
		}
		if(grille.wintest(j2.getSymbole()).equals(j2.getSymbole())){
			etatPartie = Constantes.VICTOIRE_JOUEUR_2;
			return true;
		}
		if(tour == Constantes.NB_TOUR_MAX){
			etatPartie = Constantes.MATCH_NUL;
			return true;
		}
		return false;
	}

	public boolean jouerCoupSimple(int cas, long tempsReflexion) {
		
		if(!grille.getCase(precedent).isCaseLibre(cas))	//pour la case. if wrong, juste redemander la case
		{
			return false;
		}

		if(jCourant==j1)
		{
			grille.ajouterCoup(cas, precedent , Constantes.SYMBOLE_J1);
			//verificationFinPartie();
			grille.wintest(Constantes.SYMBOLE_J1);
			jCourant= j2;
		}
		else
		{
			grille.ajouterCoup(cas, precedent, Constantes.SYMBOLE_J2);
			//verificationFinPartie();
			grille.wintest(Constantes.SYMBOLE_J2);
			jCourant=j1;
		}
		precedent=cas;
		tour++;
		return true;
	}

	public boolean jouerCoupDouble(int bg, int cas, long tempsReflexion) {
		if(!grille.isCoupPossible(bg, cas))
		{
			return false;
		}
		
		if(jCourant==j1)
		{
			grille.ajouterCoup(cas, bg , Constantes.SYMBOLE_J1);
			//verificationFinPartie();
			grille.wintest(Constantes.SYMBOLE_J1);
			jCourant= j2;
		}
		else
		{
			grille.ajouterCoup(cas, bg, Constantes.SYMBOLE_J2);
			//verificationFinPartie();
			grille.wintest(Constantes.SYMBOLE_J2);
			jCourant=j1;
		}
		precedent=cas;
		tour++;
		return true;
	}
}
