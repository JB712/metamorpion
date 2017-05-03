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

	@Override 
	public int joue(BigGrille grille, Console console, int tour) {
		return (console.getHumanCoup(this.getNom())-1);
	}

}

