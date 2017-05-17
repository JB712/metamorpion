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
		int victoirec, victoirea; //Pourra être simplifiée ....
		Case symboleAdverse = (symboleJoueurCourant==Constantes.SYMBOLE_J1)?Constantes.SYMBOLE_J2:Constantes.SYMBOLE_J1;
		boolean matchnul = true;

		for (int i=0; i<9; i++) {
			for(int j=0; j<9; j++){
				if(isCoupPossible(new Coup(i, j))){
					matchnul = false;
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
		if(this.wintest(symboleJoueurCourant).equals(symboleJoueurCourant)){		// Ici peu importe le tour, c'est juste pour obtenir le résultat
			return victoirec;
		}

		if (this.wintest(symboleJoueurCourant).equals(symboleAdverse)){		// Ici peu importe le tour, c'est juste pour obtenir le résultat
			return victoirea;
		}
		return Constantes.PARTIE_EN_COURS;
	}


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
		Case symboleAdverse = (symboleJoueurCourant==Constantes.SYMBOLE_J1)?Constantes.SYMBOLE_J2:Constantes.SYMBOLE_J1;
		boolean matchnul = true;

		//On regarde d'abord si la partie est terminée
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
		//Si le match n'est pas nul, on vérifie les victoires
		if(this.wintest(symboleJoueurCourant).equals(symboleJoueurCourant)){		// Ici peu importe le tour, c'est juste pour obtenir le résultat
			return 100;
		}

		if (this.wintest(symboleJoueurCourant).equals(symboleAdverse)){		// Ici peu importe le tour, c'est juste pour obtenir le résultat
			return -100;
		}

		//Si la partie n'est pas terminée:
		return 0;
	}
}
