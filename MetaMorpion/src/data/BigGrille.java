package data;

import jeu.algosIA.Coup;
import util.Constantes;
import util.Constantes.Case;

public class BigGrille{


	private SmallGrille[] cases = new SmallGrille[9];		// les lignes sont [0-2], [3-5], [6-8]
	private int coupPrecedent=-666;

	public BigGrille(){
		for (int i=0; i<9; i++){
			cases[i]=new SmallGrille();
		}
	}

	public BigGrille(BigGrille bg){
		for (int i=0; i<9; i++)
		{
			cases[i]=new SmallGrille();
			for (int j=0;j<9;j++)
			{
				cases[i].setCase(j, bg.getCase(i).getCase(j));
			}
		}
	}

	public BigGrille clone(){
		BigGrille copy = new BigGrille(this);
		return copy;
	}

	public BigGrille getGrille(){
		return this;
	}

	public SmallGrille getCase(int cas){
		return cases[cas];
	}

	public int getEtatPartie(Case symboleJoueurCourant){
		int victoirec, victoirea; //Pourra être simplifiée .... + c = courant et a = adversaire
		Case symboleAdverse = (symboleJoueurCourant==Constantes.SYMBOLE_J1)?Constantes.SYMBOLE_J2:Constantes.SYMBOLE_J1;
		boolean matchnul = true;

		for (int i=0; i<9; i++) {
			for(int j=0; j<9; j++){
				if(isCoupPossible(new Coup(i, j))){
					matchnul = false;
					break;
				}
			}
		}

		if(symboleJoueurCourant==Constantes.SYMBOLE_J1)
		{
			victoirec=Constantes.VICTOIRE_JOUEUR_1;
			victoirea=Constantes.VICTOIRE_JOUEUR_2;
		}
		else
		{
			victoirec=Constantes.VICTOIRE_JOUEUR_2;
			victoirea=Constantes.VICTOIRE_JOUEUR_1;
		}

		if(matchnul){
			return Constantes.MATCH_NUL;
		}
		//Si le match n'est pas nul, on vérifie les victoires
		if(this.wintest(symboleJoueurCourant).equals(symboleJoueurCourant)){
			return victoirec;
		}

		if (this.wintest(symboleAdverse).equals(symboleAdverse)){
			return victoirea;
		}
		return Constantes.PARTIE_EN_COURS;
	}

	/**
	 * fonction qui renvoie le symbole en parametre si il gagne, renvoie V (vide) sinon
	 * @param symbole de celui qui peut gagner
	 * @return le symbole (type Case) du gagnant
	 */
	public Case wintest(Case s) {
		winCases(s);
		for(int i=0; i<3; i++){
			if(cases[3*i].getEtat().equals(s)){		//Test des solutions en ligne
				if(cases[3*i+1].getEtat().equals(s) && cases[3*i+2].getEtat().equals(s))return s;
			}
			if(cases[i].getEtat().equals(s)){			//Test des solutions en colonne
				if(cases[3+i].getEtat().equals(s) && cases[6+i].getEtat().equals(s)) return s;
			}
		}
		//Test du reste = les diagonales
		if(cases[0].getEtat().equals(s) && cases[4].getEtat().equals(s) && cases[8].getEtat().equals(s)) return s;
		if(cases[2].getEtat().equals(s) && cases[4].getEtat().equals(s) && cases[6].getEtat().equals(s)) return s;
		return Case.V;
	}

	public void winCases(Case s){
		for (SmallGrille sg : cases) {
			sg.wintest(s);
		}
	}

	public boolean isGrilleLibre(int big){
		if(big>8 || big<0) return false;
		if(this.getCase(big).getEtat()!=Constantes.Case.V) return false;
		if(this.getCase(big).isFull()) return false;
		return true;
	}

	public int getCoupPrecedent()
	{
		return coupPrecedent;
	}

	public boolean isCoupPossible(Coup coup)
	{
		int grille=coup.getGrille();
		int c=coup.getC();
		if(grille>8 || grille<0 || c>8 || c<0) 
		{
			return false;
		}
		if (coupPrecedent==-666)
		{
			return true;
		}
		if (grille!=coupPrecedent && cases[coupPrecedent].getEtat()==Case.V)
		{
			return false;
		}
		if (cases[grille].getEtat()!=Case.V)
		{
			return false;
		}
		if (cases[grille].getCase(c)!=Case.V)
		{
			return false;
		}
		return true;
	}

	/*public boolean isCoupPossible(int grille)
	{
		if(grille>8 || grille<0)
		{
			return false;
		}
		if (coupPrecedent==-666)
		{
			return true;
		}
		if (cases[grille].getEtat()==Case.V)
		{
			return true;
		}
		return false;
	}*/

	public void ajouterCoup(Coup coup, Case symbol) {
		this.coupPrecedent=coup.getC();
		cases[coup.getGrille()].setCase(coup.getC(), symbol);
	}

	public int evaluer(Case symboleJoueurCourant){
		int valeurDesChoix = 0;                        //représente la valeur de l'heuristique des choix de l'ordinateur
		Case symboleAdverse = (symboleJoueurCourant==Constantes.SYMBOLE_J1)?Constantes.SYMBOLE_J2:Constantes.SYMBOLE_J1;
		boolean matchnul = true;

		//On regarde d'abord s'il reste un coup à jouer
		for (int i=0; i<9; i++) {
			for(int j=0; j<9; j++){
				if(isCoupPossible(new Coup(i, j))){
					matchnul = false;
				}
			}
		}

		if(matchnul){
			return 0;
		}



		/*

		//ajout de la valeur du coup si la petite grille est gagnée ( de différentes façons)
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(symboleJoueurCourant)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(symboleJoueurCourant) && cases[3*i+2].equals(symboleJoueurCourant)){
					valeurDesChoix += 100;
				}
			}
			if(cases[i].equals(symboleJoueurCourant)){			//Test des solutions en colonne
				if(cases[3+i].equals(symboleJoueurCourant) && cases[6+i].equals(symboleJoueurCourant)){
					valeurDesChoix += 100;
				}
			}
		}
		//Test du reste = les diagonales
		if(cases[0].equals(symboleJoueurCourant) && cases[4].equals(symboleJoueurCourant) && cases[8].equals(symboleJoueurCourant)){
			valeurDesChoix += 100;
		}
		if(cases[2].equals(symboleJoueurCourant) && cases[4].equals(symboleJoueurCourant) && cases[6].equals(symboleJoueurCourant)){
			valeurDesChoix += 100;
		}


		//réduction de la valeur du coup si la petite grille est perdue ( de différentes façons)
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(symboleAdverse)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(symboleAdverse) && cases[3*i+2].equals(symboleAdverse)){
					valeurDesChoix -= 100;
				}
			}
			if(cases[i].equals(symboleAdverse)){			//Test des solutions en colonne
				if(cases[3+i].equals(symboleAdverse) && cases[6+i].equals(symboleAdverse)){
					valeurDesChoix -= 100;
				}
			}
		}
		//Test du reste = les diagonales
		if(cases[0].equals(symboleAdverse) && cases[4].equals(symboleAdverse) && cases[8].equals(symboleAdverse)){
			valeurDesChoix -= 100;
		}
		if(cases[2].equals(symboleAdverse) && cases[4].equals(symboleAdverse) && cases[6].equals(symboleAdverse)){
			valeurDesChoix -= 100;
		}

		//ajout de la valeur du coup si 2 cases de même nature sont alignées ( de différentes façons)
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(symboleJoueurCourant)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(symboleJoueurCourant) || cases[3*i+2].equals(symboleJoueurCourant)){ //ATTENTION ne passes qu'une seule fois, dangereux en cas de plusieurs doublons (2 cases sur le même alignement) 
					valeurDesChoix += 10;
				}				
			}
			if(cases[3*i+1].equals(symboleJoueurCourant) && cases[3*i+2].equals(symboleJoueurCourant))
				valeurDesChoix += 10;

			if(cases[i].equals(symboleJoueurCourant)){			//Test des solutions en colonne
				if(cases[3+i].equals(symboleJoueurCourant) || cases[6+i].equals(symboleJoueurCourant)){
					valeurDesChoix += 10;
				}
			}
			if(cases[3+i].equals(symboleJoueurCourant) && cases[6+i].equals(symboleJoueurCourant))
				valeurDesChoix += 10;
		}
		//Test du reste = les diagonales
		if(cases[0].equals(symboleJoueurCourant) && (cases[4].equals(symboleJoueurCourant) || cases[8].equals(symboleJoueurCourant))){
			valeurDesChoix += 10;
		}
		if(cases[4].equals(symboleJoueurCourant) && cases[8].equals(symboleJoueurCourant)){
			valeurDesChoix += 10;
		}
		if(cases[2].equals(symboleJoueurCourant) && (cases[4].equals(symboleJoueurCourant) || cases[6].equals(symboleJoueurCourant))){
			valeurDesChoix += 10;
		}
		if(cases[4].equals(symboleJoueurCourant) && cases[6].equals(symboleJoueurCourant)){
			valeurDesChoix += 10;
		}

		//réduction de la valeur du coup si 2 cases de même nature sont alignées ( de différentes façons)
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(symboleAdverse)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(symboleAdverse) || cases[3*i+2].equals(symboleAdverse)){ //ATTENTION ne passes qu'une seule fois, dangereux en cas de plusieurs doublons (2 cases sur le même alignement) 
					valeurDesChoix -= 10;
				}				
			}
			if(cases[3*i+1].equals(symboleAdverse) && cases[3*i+2].equals(symboleAdverse))
				valeurDesChoix -= 10;

			if(cases[i].equals(symboleAdverse)){			//Test des solutions en colonne
				if(cases[3+i].equals(symboleAdverse) || cases[6+i].equals(symboleAdverse)){
					valeurDesChoix -= 10;
				}
			}
			if(cases[3+i].equals(symboleAdverse) && cases[6+i].equals(symboleAdverse))
				valeurDesChoix -= 10;
		}
		//Test du reste = les diagonales
		if(cases[0].equals(symboleAdverse) && (cases[4].equals(symboleAdverse) || cases[8].equals(symboleAdverse))){
			valeurDesChoix -= 10;
		}
		if(cases[4].equals(symboleAdverse) && cases[8].equals(symboleAdverse)){
			valeurDesChoix -= 10;
		}
		if(cases[2].equals(symboleAdverse) && (cases[4].equals(symboleAdverse) || cases[6].equals(symboleAdverse))){
			valeurDesChoix -= 10;
		}
		if(cases[4].equals(symboleAdverse) && cases[6].equals(symboleAdverse)){
			valeurDesChoix -= 10;
		}*/


		for (SmallGrille sg : cases) {
			valeurDesChoix += sg.rechercheValeur(symboleJoueurCourant);
		}
		
		//Si le match n'est pas nul, on vérifie les victoires
		if(this.wintest(symboleJoueurCourant).equals(symboleJoueurCourant)){
			valeurDesChoix += 1000;
		}

		if (this.wintest(symboleAdverse).equals(symboleAdverse)){
			valeurDesChoix -= 1000;
		}

		//Si la partie n'est pas terminée:
		return valeurDesChoix;
	}

}