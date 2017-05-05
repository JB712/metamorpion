package data;

import ui.Console;
import util.Constantes;
import util.Constantes.Case;

public class IA extends Joueur {
	
	private int algoIA;
	private int levelIA;
	
	
	public IA(String nom, int order, int algoIA, int levelIA) {
		super(nom, order);
		this.algoIA=algoIA;
		this.levelIA=levelIA;
	}

	@Override
	public int getType() {
		return Constantes.JOUEUR_IA;
	}
	
	public int getAlgoIA()
	{
		return algoIA;
	}

	@Override
	public String getTypeNom() {
		return "IA";
	}
	/*

	@Override
	public int joue(BigGrille grille, Console console, int tour) {
		console.reflexionIA(this.getNom());
		Algorithm iA;
		if(algoIA==Constantes.IA_MINIMAX)
		{
			iA = new Minimax(levelIA,grille, this, tour);
		}
		else
		{
			iA = new AlphaBeta(levelIA,grille,this, tour);
		}
		return iA.choisirCoup();
	}*/

	@Override
	public int joue(BigGrille grille, Console console, int tour, Case cas) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int joue2(BigGrille grille, Console console, int tour, Case cas) {
		// TODO Auto-generated method stub
		return 0;
	}
}