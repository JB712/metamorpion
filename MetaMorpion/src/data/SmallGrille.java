package data;

import util.Constantes.*;

public class SmallGrille extends Grille {
	
	private Case etat = Case.V;
	private Case[][] cases = new Case[3][3];
	
	public SmallGrille(){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				cases[i][j]=Case.V;
			}
		}
	}
	
	public Case getEtat(){
		return etat;
	}
}
