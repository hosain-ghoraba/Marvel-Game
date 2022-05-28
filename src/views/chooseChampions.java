package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Game;
import model.world.Champion;

public class chooseChampions extends JFrame {
	private ArrayList<JButton> btnsProduct;

	public chooseChampions (Game game)
	{
		
		this.setTitle("Marvel: Ultimate War");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setResizable(false);
		ImageIcon logo = new ImageIcon("logo.png");
		this.setIconImage(logo.getImage());
		JPanel champs = new JPanel();
		// set it to use the GridLayout with 3 columns in width
		champs.setSize(new Dimension(400,400));
		champs.setLayout(new GridLayout(0, 4));
		// add it in the center of the JFrame
		add(champs, BorderLayout.CENTER);
		for ( Champion champ : game.getAvailableChampions()) {
			// create a JButton for each product in the supermarket
			JButton btnProduct = new JButton();
			// set its text to the product's info
			btnProduct.setText(champ.getName());
			// add the controller as its ActionListener
		//	btnProduct.addActionListener(this);
			// add it to the products buy buttons panel
		//	supermarketView.addProduct(btnProduct);

			// and also add it to the ArrayList for later use
			btnsProduct.add(btnProduct);
		}

		
		
		
		
		
		
		
		
		
		btnsProduct = new ArrayList<>();

		
		
		
		
	}

}
