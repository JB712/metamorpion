package data;

import util.Constantes.Case;

public class BigGrille{


	private SmallGrille[] cases = new SmallGrille[9];		// les lignes sont [0-2], [3-5], [6-8]

	public BigGrille(){
		for (int i=0; i<9; i++){
			cases[i]=new SmallGrille();
		}
	}
	
	public BigGrille getGrille(){
		return this;
	}

	public SmallGrille getCase(int cas){
		return cases[cas];
	}
	
	public void setCase(int cas, int bg, Case symbol){
		cases[bg].setCase(cas, symbol);
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

	public boolean isCoupPossible(int bg, int cas) {
		//Pour la grille
		if(this.getCase(bg).isFull()) return false;
		if(!this.getCase(bg).getEtat().equals(Case.V)) return false;
		//Pour la case
		if(!this.getCase(bg).getCase(cas).equals(Case.V)) return false;
		return true;
	}

	public void ajouterCoup(int cas, int bg, Case symbol) {
		this.setCase(cas, bg, symbol);	
	}
}
