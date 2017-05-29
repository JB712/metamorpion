package data;


import util.Constantes;
import util.Constantes.*;

public class SmallGrille{

	private Case etat = Case.V;
	private Case[] cases = new Case[9];		// les lignes sont [0-2], [3-5], [6-8]

	public SmallGrille(){
		for (int i=0; i<9; i++){
			cases[i]=Case.V;
		}
	}

	public Case getEtat(){
		return etat;
	}

	public Case getCase(int small) {
		return cases[small];
	}

	public void setCase (int small, Case symbol){
		cases[small] = symbol;

	}

	public Case wintest(Case s) {
		Case win = Case.V;
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(s)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(s) && cases[3*i+2].equals(s)){
					this.etat = s;
					win = s;
				}
			}
			if(cases[i].equals(s)){			//Test des solutions en colonne
				if(cases[3+i].equals(s) && cases[6+i].equals(s)){
					this.etat = s;
					win = s;
				}
			}
		}
		//Test du reste = les diagonales
		if(cases[0].equals(s) && cases[4].equals(s) && cases[8].equals(s)){
			this.etat = s;
			win = s;
		}
		if(cases[2].equals(s) && cases[4].equals(s) && cases[6].equals(s)){
			this.etat = s;
			win = s;
		}
		return win;
	}

	public boolean isFull(){
		for (Case cel : cases) {
			if(cel==Case.V) return false;
		}
		return true;
	}

	public boolean isCaseLibre(int cas) {
		return (cases[cas].equals(Case.V));
	}

	public int evaluer(Case s){
		int poidAllign = 0;
		int poidUnitaire = 1;

		// en ligne et colonnes
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(s)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(s) ^ cases[3*i+2].equals(s)){
					poidAllign += poidUnitaire;
				}
			}
			else if (cases[3*i+1].equals(s) && cases[3*i+2].equals(s)){
				poidAllign += poidUnitaire;
			}
			if(cases[i].equals(s)){			//Test des solutions en colonne
				if(cases[3+i].equals(s) ^ cases[6+i].equals(s)){
					poidAllign += poidUnitaire;
				}
			}
			else if(cases[3+i].equals(s) && cases[6+i].equals(s)){
				poidAllign += poidUnitaire;
			}
		}
		//Test du reste = les diagonales
		if(cases[0].equals(s) && (cases[4].equals(s) ^ cases[8].equals(s))){
			poidAllign += poidUnitaire;
		}
		else if((cases[0].equals(s) ^ cases[4].equals(s)) && cases[8].equals(s)){
			poidAllign += poidUnitaire;
		}
		if(cases[2].equals(s) && (cases[4].equals(s) ^ cases[6].equals(s))){
			poidAllign += poidUnitaire;
		}
		else if((cases[2].equals(s) ^ cases[4].equals(s)) && cases[6].equals(s)){
			poidAllign += poidUnitaire;
		}

		return poidAllign;
	}
}
