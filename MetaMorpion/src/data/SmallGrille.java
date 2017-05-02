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

	public Case wintest() {
		// TODO Auto-generated method stub
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
