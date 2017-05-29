package ui.ig;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CaseIG extends JButton
{
	private int idCase;
	private PetiteGrilleIG grille;

	public CaseIG(PetiteGrilleIG petiteGrilleIG, int idCase)
	{
		this.grille=petiteGrilleIG;
		this.idCase=idCase;
		setPreferredSize(new Dimension(50, 50));
		setBackground(Color.WHITE);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				grille.setCoup(idCase);
			}
		});
	}
}
