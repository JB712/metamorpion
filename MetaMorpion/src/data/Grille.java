package data;

import util.Constantes.Case;

public abstract class Grille {

	public Grille getGrille(){
		return this;
	}

	public abstract Case wintest();

	//public abstract Object getCase(int big);

}
