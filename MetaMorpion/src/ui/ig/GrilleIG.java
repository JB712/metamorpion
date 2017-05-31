package ui.ig;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import data.BigGrille;
import jeu.algosIA.Coup;

public class GrilleIG extends Container
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
			cases[i].update(grille.getCase(i));
		}
	}

	public void activate(boolean b) 
	{
		for (PetiteGrilleIG g : cases)
		{
			g.activate(b);
		}
	}
}