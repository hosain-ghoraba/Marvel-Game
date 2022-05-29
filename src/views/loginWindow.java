package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import engine.Game;
import engine.Player;

public class loginWindow extends JFrame implements ActionListener  {
	JTextField textField_Palyer1 ;
	JTextField textField_Palyer2 ;
//	chooseChampions
	public loginWindow ()
	{
		this.setTitle("Marvel: Ultimate War");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		//this.setResizable(false);
		ImageIcon logo = new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		textField_Palyer1 = new JTextField();
	    textField_Palyer1.setPreferredSize(new Dimension(100,20));
	    textField_Palyer1.setBackground(Color.white);
	    this.getContentPane().add(textField_Palyer1,BorderLayout.EAST);
	    textField_Palyer2 = new JTextField();
	    textField_Palyer2.setPreferredSize(new Dimension(100,20));
	    textField_Palyer2.setBackground(Color.white);
	    this.getContentPane().add(textField_Palyer2,BorderLayout.WEST);
	    JButton submit = new JButton("Enter");
	    submit.addActionListener(this);
	    this.add(submit,BorderLayout.CENTER);
		
		
		this.validate();;
		this.repaint();
	}
   public static void main(String[] args) {
	   new loginWindow ();
}
@Override
public void actionPerformed(ActionEvent e)  {
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
	
	new chooseChampions(game);
	
	
}
}