package ui.ig;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends JFrame {
	
	private GrilleIG ig;
	
	public Frame(String s){
		ig=new GrilleIG();		
		JPanel c=new JPanel();
		BorderLayout lay=new BorderLayout();
		c.setLayout(lay);

		c.setPreferredSize(new Dimension(700, 700));
		
		/////////HEADER
		JPanel head=new Header();
		
		/////////CENTER
		ig.setMaximumSize(new Dimension(500, 500));;
		
		/////////BOTTOM
		JPanel bot=new Bottom();
		
		c.add(head, BorderLayout.NORTH);
		c.add(ig, BorderLayout.CENTER);
		c.add(bot, BorderLayout.SOUTH);
		

		this.setContentPane(c);
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(c.getPreferredSize());
		this.setTitle(s);
	}
	
	public GrilleIG getGrilleIG()
	{
		return ig;
	}
}
