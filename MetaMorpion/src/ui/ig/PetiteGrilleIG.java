package ui.ig;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import data.Coup;
import data.SmallGrille;
import util.Constantes.Case;

public class PetiteGrilleIG extends JPanel{

	private CaseIG[] cases=new CaseIG[9];
	private int idGrille;
	private GrilleIG grille;
	private Icon iconX,iconO;
	private Case finished=Case.V;
	private boolean canPlay;

	public PetiteGrilleIG(GrilleIG grilleIG, int idGrille)
	{
		super();
		Image x = null,o = null;
		try {
			x=ImageIO.read(getClass().getResourceAsStream("images/X.png"));
			o=ImageIO.read(getClass().getResourceAsStream("images/O.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//iconX=new ImageIcon(x.getScaledInstance(20,20, Image.SCALE_DEFAULT));
		//iconO=new ImageIcon(o.getScaledInstance(20,20, Image.SCALE_DEFAULT));
		this.grille=grilleIG;
		this.idGrille=idGrille;

		GridLayout lay=new GridLayout(3, 3);
		this.setLayout(lay);

		for (int i=0;i<9;i++)
		{
			cases[i]=new CaseIG(this,i);
			this.add(cases[i]);
		}

		iconX=new ImageIcon(x.getScaledInstance(15,15, Image.SCALE_DEFAULT));
		iconO=new ImageIcon(o.getScaledInstance(15,15, Image.SCALE_DEFAULT));

	}

	public void setCoup(int c)
	{
		grille.setCoup(new Coup(idGrille,c));
	}

	public void activate(boolean b) {
		if (finished==Case.O)
		{
			cases[0].setBackground(Color.RED);
			cases[1].setBackground(Color.RED);
			cases[2].setBackground(Color.RED);
			cases[3].setBackground(Color.RED);
			cases[4].setBackground(Color.WHITE);
			cases[5].setBackground(Color.RED);
			cases[6].setBackground(Color.RED);
			cases[7].setBackground(Color.RED);
			cases[8].setBackground(Color.RED);
		}
		else if (finished==Case.X)
		{
			cases[0].setBackground(Color.BLUE);
			cases[1].setBackground(Color.WHITE);
			cases[2].setBackground(Color.BLUE);
			cases[3].setBackground(Color.WHITE);
			cases[4].setBackground(Color.BLUE);
			cases[5].setBackground(Color.WHITE);
			cases[6].setBackground(Color.BLUE);
			cases[7].setBackground(Color.WHITE);
			cases[8].setBackground(Color.BLUE);
		}
		else
		for (int i=0;i<9;i++)
		{
			CaseIG c = cases[i];
			c.setEnabled(b);
		}
	}

	public void update(SmallGrille g) {
		for (int i=0;i<9;i++)
		{
			if (finished!=Case.V)
			{
				cases[i].setIcon(null);
			}
			else if (g.getCase(i)==Case.V)
			{
				if (canPlay)
				{
					cases[i].setBackground(Color.green);
				}
				else
					cases[i].setBackground(Color.white);
			}
			else if (g.getCase(i)==Case.X)
			{
				cases[i].setIcon(iconX);
				cases[i].setBackground(Color.WHITE);
			}
			else if (g.getCase(i)==Case.O)
			{
				cases[i].setIcon(iconO);
				cases[i].setBackground(Color.WHITE);
			}
		}
	}

	public void clean() {
		for (int i=0;i<9;i++)
		{
			cases[i].setIcon(null);
		}
	}

	public void setFinished(Case c) {
		this.finished=c;
	}

	public void setCanPlay(boolean b)
	{
		this.canPlay=b;
	}
}
