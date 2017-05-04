package data;

import ui.Console;
import util.Constantes;

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
	 * Retourne un int scanné par la console, -& pour correspondre aux tableaux
	 * @return int scanné-1
	 */
	public int joue(BigGrille grille, Console console, int tour) {
		return (console.getHumanCoup(this.getNom())-1);
	}
	
	public int joue2(BigGrille grille, Console console, int tour) {
		return (console.getHuman2Coup(this.getNom())-1);
	}

}

