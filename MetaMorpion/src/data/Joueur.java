package data;

import ui.Console;
import util.Constantes;
import util.Constantes.Case;

public abstract class Joueur {
	/**
	 * Nom du joueur
	 */
	protected String nom;
	/**
	 * Indique si le joueur est le joueur 1 ou 2
	 */
	protected int order;
	
	protected Joueur(String nom, int order)
	{
		this.nom = nom;
		this.order = order;
	}
	
	/**
	 * Indique si le joueur est une IA ou un humain
	 * @return
	 */
	public abstract int getType();
	
	/**
	 * Renvoie "Humain" ou "IA" à des fins d'affichage
	 * @return
	 */
	public abstract String getTypeNom();
	
	/**
	 * Renvoie si le joueur est le joueur 1 ou 2
	 * @return Constantes.JOUEUR_1 ou Constantes.JOUEUR_2
	 */
	public int getOrder()
	{
		return order;
	}
	
	/**
	 * Renvoie le nom du joueur
	 * @return String nom
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Renvoie le symbole utilisé par le joueur (X ou O)
	 * @return Case symbole
	 */
	public Case getSymbole()
	{
		if(getOrder()==Constantes.JOUEUR_1)
		{
			return Constantes.SYMBOLE_J1;
		}
		else
		{
			return Constantes.SYMBOLE_J2;
		}
	}

	/**
	 * Fais jouer un tour "case" au joueur
	 * @param grille
	 * @param console
	 * @param tour
	 * @param cas 
	 * @param precedent
	 * @return
	 */
	public abstract int joue(BigGrille grille, Console console, int tour, Case cas, int precedent);

	/**
	 * Fais jouer un tour "grille" au joueur
	 * @param grille
	 * @param console
	 * @param tour
	 * @param cas 
	 * @param precedent
	 * @return
	 */
	public abstract int joue2(BigGrille grille, Console console, int tour, Case cas, int precedent);
	

}
