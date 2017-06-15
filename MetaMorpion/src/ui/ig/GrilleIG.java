package ui.ig;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.BigGrille;
import data.Coup;
import util.Constantes.Case;

public class GrilleIG extends JPanel
{
	private PetiteGrilleIG[] cases = new PetiteGrilleIG[9];
	private Coup coup=new Coup(-1,-1);

	public GrilleIG()
	{
		super();
		GridLayout lay=new GridLayout(3,3,10,10);
		this.setLayout(lay);
		for (int i=0;i<9;i++)
		{
			cases[i]=new PetiteGrilleIG(this,i);
			this.add(cases[i]);
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
			{
				cases[i].setCanPlay(grille.isCoupPossible(i));
				cases[i].setFinished(grille.getCase(i).getEtat());
				cases[i].update(grille.getCase(i));
			}
		}
	}

	public void activate(boolean b) 
	{
		for (PetiteGrilleIG g : cases)
		{
			g.activate(b);
		}
	}

	public void clean() {
		for (int i=0;i<9;i++)
		{
			cases[i].clean();
		}
	}

}