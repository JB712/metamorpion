package ui.ig;

import java.awt.Dimension;

import javax.swing.JPanel;

import data.BigGrille;
import jeu.algosIA.Coup;

public class GrilleIG extends JPanel
{
	private PetiteGrilleIG[] cases = new PetiteGrilleIG[9];
	private Coup coup=new Coup(-1,-1);

	public GrilleIG()
	{
		setPreferredSize(new Dimension(700, 700));
		for (int i=0;i<9;i++)
		{
			cases[i]=new PetiteGrilleIG(this,i);
			add(cases[i]);
		}
	}

	public synchronized void setCoup(Coup c)
	{
		coup=c;
		notify();
	}

	public synchronized Coup getCoup() {
		while(coup.getGrille() == -1 || coup.getC() ==-1) {
			try {
				wait();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}

		Coup c=coup;
		coup=new Coup(-1,-1);
		return c;
	}

	public void update(BigGrille grille) 
	{
		for (int i=0;i<9;i++)
		{
			cases[i].update(grille.getCase(i));
		}
	}

	public void activate(boolean b) {
		for (PetiteGrilleIG g : cases)
		{
			g.activate(b);
		}
	}
}