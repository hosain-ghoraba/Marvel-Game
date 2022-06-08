package views;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;

import engine.Game;
import engine.Player;

public class loginWindow extends JFrame implements ActionListener  {
	JTextField textField_Palyer1 ;
	JTextField textField_Palyer2 ;

	public loginWindow ()
	{   

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Marvel: Ultimate War");
		setExtendedState(JFrame.MAXIMIZED_BOTH); // what does this do ? (hosain) 
		this.setSize(700,525);
		this.setResizable(false);
		this.setIconImage(getIconImage()); // what does this do ? where is the icon image?

		
		ImageIcon logo = new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());				
		//panel
		JLabel loginJlabel = new JLabel();
	
		ImageIcon BackgroundImage = new ImageIcon("Background7.jpg");
		loginJlabel.setIcon(BackgroundImage);
		
		this.add(loginJlabel);
		
		loginJlabel.setLayout(null);
		textField_Palyer1 = new JTextField();
	    textField_Palyer1.setBounds(140, 400, 140,30);
	    textField_Palyer1.setBackground(Color.LIGHT_GRAY);
	    Border border = BorderFactory.createLineBorder(Color.RED);
	    textField_Palyer1.setFont(new Font ("Times New Roman",Font.BOLD,17));
	    textField_Palyer1.setBorder(border);
	    loginJlabel.add(textField_Palyer1);
	    
	    JLabel labeltextpalyer1 = new JLabel("FirstPlayer:");
	    labeltextpalyer1.setFont(new Font ("Times New Roman",Font.BOLD,17));
        labeltextpalyer1.setForeground(Color.WHITE);
	    labeltextpalyer1.setBounds(40,398,120, 30);
	    
	    loginJlabel.add(labeltextpalyer1);

		loginJlabel.setLayout(null);
		textField_Palyer2 = new JTextField();
	    textField_Palyer2.setBounds(520, 400, 140,30);
	    textField_Palyer2.setBackground(Color.LIGHT_GRAY);
	    textField_Palyer2.setFont(new Font ("Times New Roman",Font.BOLD,17));
	    textField_Palyer2.setBorder(border);
	    loginJlabel.add(textField_Palyer2);
	    
	    JLabel labeltextpalyer2 = new JLabel("SecondPlayer:");
	    labeltextpalyer2.setFont(new Font ("Times New Roman",Font.BOLD,17));
        labeltextpalyer2.setForeground(Color.WHITE);
	    labeltextpalyer2.setBounds(410,398,120, 30);
	    
	    loginJlabel.add(labeltextpalyer2);
	    
	    
	    
	    JButton Enter = new JButton("Play");	   
	    Enter.addActionListener(this);
	    Enter.setBounds(302,430, 150,40);
	    Enter.setFont(new Font ("Times New Roman",Font.BOLD,17));
	    Enter.setForeground(Color.black);
	    Enter.setBackground(new Color(0xccccb3));
	    Enter.setBorder(BorderFactory.createEtchedBorder());
	    Enter.setFocusable(false);
	    
	    loginJlabel.add(Enter);
	    
		validate();
		repaint();
		
	}
   public static void main(String[] args) {
	   new loginWindow ();

   }

public static boolean stringIsMereSpaces(String s) {
	for(int i = 0 ; i < s.length() ; i++)
		if(s.charAt(i) != ' ')
			return false;
	return true;
					
		
}
public void actionPerformed(ActionEvent e)  {
		
	String name1 = textField_Palyer1.getText();
	String name2 = textField_Palyer2.getText();
	
	if(name1.isEmpty() && name2.isEmpty() )
	{
		JOptionPane.showMessageDialog(null, "both players must enter their names !","", JOptionPane.ERROR_MESSAGE);
		return;
	}
	if(name1.isEmpty())
	{
		JOptionPane.showMessageDialog(null, "First Player must enter his name !","", JOptionPane.ERROR_MESSAGE);
		return;
	}
	if(stringIsMereSpaces(name1))
	{
		JOptionPane.showMessageDialog(null, "first player name must contain some characters, not only spaces !","", JOptionPane.ERROR_MESSAGE);
		return;

	}
	if(name2.isEmpty())
	{
		JOptionPane.showMessageDialog(null, "Second Player must enter his name !","", JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	if(stringIsMereSpaces(name2))
	{
		JOptionPane.showMessageDialog(null, "second player name must contain some characters, not only spaces !","", JOptionPane.ERROR_MESSAGE);
		return;

	}
	if(name1.equals(name2))
	{
		JOptionPane.showMessageDialog(null, "players names must be different !","", JOptionPane.ERROR_MESSAGE);
		return;

	}
	
	
	Player player1 = new Player(textField_Palyer1.getText());
	Player player2 = new Player(textField_Palyer2.getText());
	Game game = new Game(player1, player2);
	try 
	{
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");

	} catch (IOException e1) {
		
		System.out.println("error loading champions or abilities");
	}

	dispose();
	
	new chooseChampions(game,player1, player2);
	
	
}


}