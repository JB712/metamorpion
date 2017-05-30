package data;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ui.ig.GrilleIG;

public class Frame extends JFrame {
	
	private JTextField header = new JTextField("le header"), bottom = new JTextField("le bottom");
	private GrilleIG ig = new GrilleIG();
	
	public Frame(String s){
		header.setPreferredSize(new Dimension(50,100));
		bottom.setPreferredSize(new Dimension(50,100));
		ig=new GrilleIG();
		this.setSize(ig.getPreferredSize());
		this.add(header);
		this.add(ig);
		this.add(bottom);
		this.setResizable(false);
		this.setVisible(true);
		this.setTitle(s);
	}
}
