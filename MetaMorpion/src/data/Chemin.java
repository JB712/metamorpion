package data;

import java.util.ArrayList;
import java.util.List;

import jeu.algosIA.Coup;

public class Chemin {
	
	private List<Coup> coups = new ArrayList<>();
	private double poid;
	
	public Chemin(Coup coup, double valeurCourante) {
		this.coups.add(coup);
		this.poid=valeurCourante;
	}
	public List<Coup> getCoups() {
		return coups;
	}

	public double getPoid() {
		return poid;
	}
	public void setPoid(int poid) {
		this.poid = poid;
	}
}
