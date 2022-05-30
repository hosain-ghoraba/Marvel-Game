package views;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import engine.Game;
import engine.Player;

public class loginWindow extends JFrame implements ActionListener  {
	JTextField textField_Palyer1 ;
	JTextField textField_Palyer2 ;
//	chooseChampions
	public loginWindow ()
	{   
		//JFrame window
		this.setTitle("Marvel: Ultimate War");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,525);
		this.setIconImage(getIconImage());
		
	 	this.setResizable(false);
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

	    
	  

		this.validate();;
		this.repaint();
	}
   public static void main(String[] args) {
	   new loginWindow ();
}
@Override
public void actionPerformed(ActionEvent e)  {
	
	if(textField_Palyer1.getText().isEmpty() && textField_Palyer2.getText().isEmpty() )
	{
		JOptionPane.showMessageDialog(null, "both players must enter their names !","", JOptionPane.ERROR_MESSAGE);
		return;
	}
	if(textField_Palyer1.getText().isEmpty())
	{
		JOptionPane.showMessageDialog(null, "First Player must enter his name !","", JOptionPane.ERROR_MESSAGE);
		return;
	}
	if(textField_Palyer2.getText().isEmpty())
	{
		JOptionPane.showMessageDialog(null, "Second Player must enter his name !","", JOptionPane.ERROR_MESSAGE);
		return;
	}
	
		
	Player player1 = new Player(textField_Palyer1.getText());
	Player player2 = new Player(textField_Palyer2.getText());
	Game game = new Game(player1, player2);
	try {
		game.loadAbilities("Abilities.csv");
		game.loadChampions("Champions.csv");

	} catch (IOException e1) {
		
		System.out.println("balabizoo 2l l3ba byza");
	}

	this.dispose();
	
	new chooseChampions(game, player1, player2);
	
	
}
}