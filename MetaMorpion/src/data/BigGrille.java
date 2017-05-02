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

	public Case wintest() {
		//for()
		return Case.V;
	}
}
