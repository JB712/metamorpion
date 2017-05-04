package data;

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
	 * Retourne un int scann� par la console, -& pour correspondre aux tableaux
	 * @return int scann�-1
	 */
	public int joue(BigGrille grille, Console console, int tour, Case cas) {
		return (console.getHumanCoup(this.getNom(), cas)-1);
	}
	
	public int joue2(BigGrille grille, Console console, int tour, Case cas) {
		return (console.getHuman2Coup(this.getNom(), cas)-1);
	}

}

