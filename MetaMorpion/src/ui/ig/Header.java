package ui.ig;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Header extends JPanel {
	
	private JTextField nom;
	private JComboBox<String> type;
	private JComboBox<Integer> lvl;
	
	public Header()
	{
		init();
		add(nom);
		add(type);
		add(lvl);
	}
	
	public void init()
	{
		nom=new JTextField("");
		type=new JComboBox<>();
		type.addItem("IA");
		type.addItem("Humain");
		lvl=new JComboBox<>();
		int i=1;
		while (i!=15)
		{
			lvl.addItem(i);
			i++;
		}
	}
}
