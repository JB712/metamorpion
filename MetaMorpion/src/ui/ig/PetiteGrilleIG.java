package ui.ig;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import data.SmallGrille;
import jeu.algosIA.Coup;
import util.Constantes.Case;

public class PetiteGrilleIG extends Container{

	private CaseIG[] cases=new CaseIG[9];
	private int idGrille;
	private GrilleIG grille;
	private Icon iconX,iconO;
	
	public PetiteGrilleIG(GrilleIG grilleIG, int idGrille)
	{
		super();
		Image x = null,o = null;
		try {
			x=ImageIO.read(new File(getClass().getResource("images/X.png").toURI()));
			o=ImageIO.read(new File(getClass().getResource("images/O.jpg").toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		iconX=new ImageIcon(x.getScaledInstance(50,50, Image.SCALE_DEFAULT));
		iconO=new ImageIcon(o.getScaledInstance(50,50, Image.SCALE_DEFAULT));
		this.grille=grilleIG;
		this.idGrille=idGrille;

		GridLayout lay=new GridLayout(3, 3);
		this.setLayout(lay);

		for (int i=0;i<9;i++)
		{
			cases[i]=new CaseIG(this,i);
			this.add(cases[i]);
		}
	}

	public void setCoup(int c)
	{
		grille.setCoup(new Coup(idGrille,c));
	}

	public void activate(boolean b) {
		for (CaseIG c : cases)
		{
			c.setEnabled(b);
		}
	}

	public void update(SmallGrille g) {
		for (int i=0;i<9;i++)
		{
			if (g.getCase(i)==Case.V)
			{
				cases[i].setBackground(Color.white);
			}
			else if (g.getCase(i)==Case.X)
			{
				cases[i].setIcon(iconX);
				//cases[i].setBackground(Color.RED);
			}
			else if (g.getCase(i)==Case.O)
			{
				cases[i].setIcon(iconO);
				//cases[i].setBackground(Color.BLUE);
			}
		}
	}
}
