package data;

import jeu.algosIA.Coup;
import ui.Console;
import util.Constantes;
import util.Constantes.Case;

public class Humain extends Joueur {

	public Humain(String nom, int order) {
		super(nom, order);		
	}

	@Override
	public int getType() {
		return Constantes.JOUEUR_HUMAN;
	}

	@Override
	public String getTypeNom() {
		return "Humain";
	}

	/**
	 * Retourne un int scanné par la console, -1 pour correspondre aux tableaux
	 * @return int scanné-1
	 */
	public Coup joue(BigGrille grille, Console console, int tour, Case cas) {
		return (console.getHumanCoup(grille,this.getNom(), cas));
	}
	
	public int joue2(BigGrille grille, Console console, int tour, Case cas, int precedent) {
		return (console.getHuman2Coup(this.getNom(), cas)-1);
	}

}

