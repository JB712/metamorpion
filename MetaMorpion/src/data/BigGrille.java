package data;

public class BigGrille extends Grille {
	
	private Grille[][] cases = new Grille[3][3];
	
	public BigGrille(){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				cases[i][j]=new SmallGrille();
			}
		}
	}
}
