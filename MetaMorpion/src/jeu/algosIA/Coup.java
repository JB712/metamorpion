package jeu.algosIA;

public class Coup {

	private int grille,c;
	
	public Coup(int grille, int c)
	{
		this.grille=grille;
		this.c=c;
	}

	public int getGrille() {
		return grille;
	}

	public void setGrille(int grille) {
		this.grille = grille;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
}