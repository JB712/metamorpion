package data;

import java.util.Arrays;

import util.Constantes;
import util.Constantes.Case;

public class BigGrille{


	private SmallGrille[] cases = new SmallGrille[9];		// les lignes sont [0-2], [3-5], [6-8]
	private int coupPrecedent=-666;
	private int[] avantages = new int[16];
	private int EtatPartie = Constantes.PARTIE_EN_COURS;

	public BigGrille(){
		for (int i=0; i<9; i++){
			cases[i]=new SmallGrille(this,i);
		}
		Arrays.fill(avantages, 0);
	}

	public BigGrille(BigGrille bg){
		for (int i=0; i<9; i++)
		{
			cases[i]=new SmallGrille(this,i);
			for (int j=0;j<9;j++)
			{
				cases[i].setCase(j, bg.getCase(i).getCase(j));
			}
			//cases.setAvantages(Arrays.copyOf(bg.getCase(i).getAvantages(), 16));
		}
	}

	public BigGrille clone(){
		BigGrille copy = new BigGrille(this);
		return copy;
	}

	public SmallGrille getCase(int cas){
		return cases[cas];
	}

	public int getEtatPartie(Case symboleJoueurCourant){
		wintest(symboleJoueurCourant);
		return this.EtatPartie;
		/*int victoirec, victoirea; //Pourra �tre simplifi�e .... + c = courant et a = adversaire
		Case symboleAdverse = (symboleJoueurCourant==Constantes.SYMBOLE_J1)?Constantes.SYMBOLE_J2:Constantes.SYMBOLE_J1;
		boolean matchnul = true;

		for (int i=0; i<9; i++) {
			if(isGrilleLibre(i)) {
				for(int j=0; j<9; j++){
					if(isCoupPossible(new Coup(i, j))){
						matchnul = false;
						break;
					}
				}
			}
		}

		if(matchnul){
			return Constantes.MATCH_NUL;
		}

		/*if(wintest(symboleJoueurCourant)==symboleJoueurCourant){
			return (symboleJoueurCourant==Constantes.SYMBOLE_J1)?Constantes.VICTOIRE_JOUEUR_1:Constantes.VICTOIRE_JOUEUR_2;
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

		//Si le match n'est pas nul, on v�rifie les victoires
		if(this.wintest(symboleJoueurCourant)==symboleJoueurCourant){
			return victoirec;
		}

		if (this.wintest(symboleAdverse)==symboleAdverse){
			return victoirea;
		}
		return Constantes.PARTIE_EN_COURS;*/
	}

	public int getCoupPrecedent()
	{
		return coupPrecedent;
	}

	/**
	 * fonction qui renvoie le symbole en parametre si il gagne, renvoie V (vide) sinon
	 * @param symbole de celui qui peut gagner
	 * @return le symbole (type Case) du gagnant
	 */
	public Case wintest(Case s) {
		//winCases(s);
		/*if(s==Constantes.SYMBOLE_J1){
			for(int i=0; i<8; i++){	//Boucle pour les X
				if(avantages[i]==3){
					this.EtatPartie=Constantes.VICTOIRE_JOUEUR_1;
					return Constantes.SYMBOLE_J1;
				}
			}
		}
		else{
			for(int i=8;i<16;i++){	//Boucle pour les O
				if(avantages[i]==3){
					this.EtatPartie=Constantes.VICTOIRE_JOUEUR_2;
					return Constantes.SYMBOLE_J2;
				}
			}
		}*/
		if(this.EtatPartie==Constantes.VICTOIRE_JOUEUR_1) return Constantes.SYMBOLE_J1;
		else if(this.EtatPartie==Constantes.VICTOIRE_JOUEUR_2) return Constantes.SYMBOLE_J2;
		boolean nul = true;
		for(int i=0;i<16;i++){
			if(avantages[i]!=Constantes.Avantage_Impossible){
				nul=false;
				break;
			}
		}
		if(nul){
			this.EtatPartie=Constantes.MATCH_NUL;
			return Case.F;
		}
		/*if((cases[4].getEtat()==s &&
				((cases[0].getEtat()==s && cases[8].getEtat()==s) || (cases[1].getEtat()==s && cases[7].getEtat()==s) 
						|| (cases[2].getEtat()==s && cases[6].getEtat()==s) || (cases[3].getEtat()==s && cases[5].getEtat()==s)))
				|| (cases[0].getEtat()==s && cases[3].getEtat()==s && cases[6].getEtat()==s)
				|| (cases[2].getEtat()==s && cases[5].getEtat()==s && cases[8].getEtat()==s)
				|| (cases[0].getEtat()==s && cases[1].getEtat()==s && cases[2].getEtat()==s)
				|| (cases[6].getEtat()==s && cases[7].getEtat()==s && cases[8].getEtat()==s)){
			return s;
		}*/
		return Case.V;
	}

	/*public void winCases(Case s){	//suposed useless
		if(coupPrecedent != -666 && cases[coupPrecedent].getEtat()!=Case.V) changeAv(coupPrecedent,s);

		for (int i=0;i<8; i++) {
			if(!cases[i].isFull()){	//USELESS (a priori)
				cases[i].wintest(s);
				changeAv(i,s);
			}
		}
	}*/

	public void changeAv(int pos, Case symbol){
		switch(pos){
		case 0:
			majAv(0,symbol);
			majAv(3,symbol);
			majAv(6,symbol);
			break;
		case 1:
			majAv(1, symbol);
			majAv(3, symbol);
			break;
		case 2:
			majAv(2,symbol);
			majAv(3,symbol);
			majAv(7,symbol);
			break;
		case 3:	//Deuxieme ligne
			majAv(0,symbol);
			majAv(4,symbol);
			break;
		case 4:
			majAv(1,symbol);
			majAv(4,symbol);
			majAv(6,symbol);
			majAv(7,symbol);
			break;
		case 5:
			majAv(2, symbol);
			majAv(4, symbol);
			break;
		case 6:	//Troisi�me ligne
			majAv(0,symbol);
			majAv(5,symbol);
			majAv(7,symbol);
			break;
		case 7:
			majAv(1, symbol);
			majAv(5, symbol);
			break;
		case 8:
			majAv(2,symbol);
			majAv(5,symbol);
			majAv(6,symbol);
			break;
		default:
			break;
		}
	}

	private void majAv(int pos, Case symbol){
		if(symbol==Case.F){
			avantages[pos]=Constantes.Avantage_Impossible;
			avantages[pos+8]=Constantes.Avantage_Impossible;
			return;
		}
		if(symbol==Constantes.SYMBOLE_J1){
			if(avantages[pos]!=Constantes.Avantage_Impossible){
				if(avantages[8+pos]>0){
					avantages[pos]=Constantes.Avantage_Impossible;
					avantages[8+pos]=Constantes.Avantage_Impossible;
				}
				else{
					if(++avantages[pos]==3) this.EtatPartie=Constantes.VICTOIRE_JOUEUR_1;
					avantages[8+pos]=Constantes.Avantage_Impossible;
				}
			}
			else{
				avantages[8+pos]=Constantes.Avantage_Impossible;
			}
			return;
		}
		else{
			if(avantages[8+pos]!=Constantes.Avantage_Impossible){
				if(avantages[pos]>0){
					avantages[8+pos]=Constantes.Avantage_Impossible;
					avantages[pos]=Constantes.Avantage_Impossible;
				}
				else{
					if(++avantages[8+pos]==3) this.EtatPartie=Constantes.VICTOIRE_JOUEUR_2;
					avantages[pos]=Constantes.Avantage_Impossible;
				}
			}
			else{
				avantages[pos]=Constantes.Avantage_Impossible;
			}
		}
	}

	public boolean isGrilleLibre(int big){
		if(big>8 || big<0) return false;
		if(this.getCase(big).getEtat()!=Constantes.Case.V) return false;
		if(this.getCase(big).isFull()) return false;
		return true;
	}

	public boolean isCoupPossible(int grille)
	{
		for (int i=0;i<9;i++)
		{
			Coup coup=new Coup(grille, i);
			if (isCoupPossible(coup))
				return true;
		}
		return false;
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
		if (grille!=coupPrecedent && this.isGrilleLibre(coupPrecedent))
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

	public void ajouterCoup(Coup coup, Case symbol) {
		this.coupPrecedent=coup.getC();
		cases[coup.getGrille()].setCase(coup.getC(), symbol);
	}

	public double evaluer(Case symboleJoueurCourant){
		int poidDuCoup = 0;                        //repr�sente la valeur de l'heuristique des choix de l'ordinateur
		Case symboleAdverse = (symboleJoueurCourant==Constantes.SYMBOLE_J1)?Constantes.SYMBOLE_J2:Constantes.SYMBOLE_J1;

		if(wintest(symboleJoueurCourant)==symboleJoueurCourant) poidDuCoup += 80000;
		if(wintest(symboleAdverse)==symboleAdverse) poidDuCoup -= 80000;

		/*boolean matchnul = true;

		//On regarde d'abord s'il reste un coup � jouer.
		for (int i=0; i<9; i++) {
			if(isGrilleLibre(i)){
				for(int j=0; j<9; j++){
					if(isCoupPossible(new Coup(i, j))){
						matchnul = false;
					}
				}
			}
		}

		if(matchnul){
			return 0;
		}

		//Si le match n'est pas nul, on v�rifie les victoires
		if(this.wintest(symboleJoueurCourant)==symboleJoueurCourant){
			poidDuCoup += 300000;
		}

		else if (this.wintest(symboleAdverse)==symboleAdverse){
			poidDuCoup -= 300000;
		}*/

		//Calcul des victoires uniques sur les petites grilles, et sinon les alignements de cases
		for (SmallGrille sg : cases) {
			poidDuCoup += sg.evaluer(symboleJoueurCourant);
			poidDuCoup -= sg.evaluer(symboleAdverse);
		}

		//Calcul des alignements de SmallGrilles
		poidDuCoup += alignSG(symboleJoueurCourant);
		poidDuCoup -= alignSG(symboleAdverse);

		return poidDuCoup;
	}

	public int alignSG(Case s){

		int poidAllign = 0;
		int poidUnitaire = 4000;

		if(s==Constantes.SYMBOLE_J1){
			for(int i=0;i<8;i++){
				if(avantages[i]==2) poidAllign += poidUnitaire;
			}
		}
		else{
			for(int i=8;i<16;i++){
				if(avantages[i]==2) poidAllign += poidUnitaire;
			}
		}

		/*
		// en ligne et colonnes
		for(int i=0; i<3; i++){
			if(cases[3*i].getEtat()==s){		//Test des solutions en ligne
				if(cases[3*i+1].getEtat()==s && cases[3*i+2].getEtat()==Case.V){
					poidAllignToReturn += poidUnitaire;
				}
				else if(cases[3*i+1].getEtat()==Case.V && cases[3*i+2].getEtat()==s){
					poidAllignToReturn += poidUnitaire;
				}
			}
			else if (cases[3*i+1].getEtat()==s && cases[3*i+2].getEtat()==s && cases[3*i].getEtat()==Case.V){
				poidAllignToReturn += poidUnitaire;
			}
			if(cases[i].getEtat()==s){			//Test des solutions en colonne
				if(cases[3+i].getEtat()==s && cases[6+i].getEtat()==Case.V){
					poidAllignToReturn += poidUnitaire;
				}
				else if(cases[3+i].getEtat()==Case.V && cases[6+i].getEtat()==s){
					poidAllignToReturn += poidUnitaire;
				}
			}
			else if(cases[3+i].getEtat()==s && cases[6+i].getEtat()==s && cases[i].getEtat()==Case.V){
				poidAllignToReturn += poidUnitaire;
			}
		}
		//Test du reste = les diagonales
		if(cases[0].getEtat()==s && cases[4].getEtat()==s && cases[8].getEtat()==Case.V){
			poidAllignToReturn += poidUnitaire;
		}
		else if(cases[0].getEtat()==s && cases[4].getEtat()==Case.V && cases[8].getEtat()==s){
			poidAllignToReturn += poidUnitaire;
		}
		else if(cases[0].getEtat()==Case.V && cases[4].getEtat()==s && cases[8].getEtat()==s){
			poidAllignToReturn += poidUnitaire;
		}
		if(cases[2].getEtat()==s && cases[4].getEtat()==s && cases[6].getEtat()==Case.V){
			poidAllignToReturn += poidUnitaire;
		}
		else if(cases[2].getEtat()==s && cases[4].getEtat()==Case.V && cases[6].getEtat()==s){
			poidAllignToReturn += poidUnitaire;
		}
		else if(cases[2].getEtat()==Case.V && cases[4].getEtat()==s && cases[6].getEtat()==s){
			poidAllignToReturn += poidUnitaire;
		}*/

		return poidAllign;
	}

	public String printAv(){
		String s="[";
		for(int i=0;i<8;i++){
			s+=avantages[i]+" ";
		}
		s+=" || ";
		for(int i=8;i<16;i++){
			s+=avantages[i]+" ";
		}
		s+="]";
		return s;
	}

}