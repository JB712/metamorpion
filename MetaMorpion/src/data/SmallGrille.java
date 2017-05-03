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

	public Case wintest(Case s) {
		for(int i=0; i<3; i++){
			if(cases[3*i].equals(s)){		//Test des solutions en ligne
				if(cases[3*i+1].equals(s) && cases[3*i+2].equals(s)){
					this.etat = s;
					return s;
				}
			}
			if(cases[i].equals(s)){			//Test des solutions en colonne
				if(cases[3+i].equals(s) && cases[6+i].equals(s)){
					this.etat = s;
					return s;
				}
			}
		}
		//Test du reste = les diagonales
		if(cases[0].equals(s) && cases[4].equals(s) && cases[8].equals(s)){
			this.etat = s;
			return s;
		}
		if(cases[2].equals(s) && cases[4].equals(s) && cases[6].equals(s)){
			this.etat = s;
			return s;
		}
		return Case.V;
	}

	public Case getCase(int big) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isFull(){
		for (Case cel : cases) {
			if(cel==Case.V) return false;
		}
		return true;
	}
}
