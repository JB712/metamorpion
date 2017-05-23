package data;

import jeu.algosIA.AlphaBeta;
import jeu.algosIA.Algorithm;
import jeu.algosIA.Coup;
import jeu.algosIA.Minimax;
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

	//Doit renvoyer le numero de la case
	public Coup joue(BigGrille grille, Console console, int tour, Case cas) {
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
	}

	//Doit renvoyer le numero de la grille
	public int joue2(BigGrille grille, Console console, int tour, Case cas, int precedent) {
		console.reflexionIA(this.getNom());
		Algorithm iA;
		if(algoIA==Constantes.IA_MINIMAX)
		{
			iA = new Minimax(levelIA, grille, this, tour);
		}
		else
		{
			iA = new AlphaBeta(levelIA,grille,this, tour);
		}
		return 0;
	}

}