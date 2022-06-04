package views;
import model.world.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ToolTipManager;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

import engine.Game;
import engine.Player;
import model.world.Champion;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;

public class viewDraft extends JFrame implements ActionListener {
	Game game ;
	JPanel battlefield;	
    JPanel infoPanel;
		   
public viewDraft(Game game) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Marvel - Ultimate War");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);		
		ImageIcon logo = new ImageIcon("logo.png");
		setIconImage(logo.getImage());
		
		this.game = game;
		
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	    infoPanel.add(new JLabel("\n")); // space
		       
		     
		       JTextArea pl1_info = new JTextArea();
		       
		       pl1_info.setEditable(false);
		       String info1 = "                          player 1 \n";
		       info1 += "Name : " + game.getFirstPlayer().getName() + "\n";
		       if(game.isFirstLeaderAbilityUsed())
		            info1 += "leader ability used : " + "YES";
		       else 
		    	   info1 += "leader ability used : " + "NO";
		       pl1_info.setText(info1);
		       pl1_info.setFont(new Font("Dialog",ABORT,16));
		       infoPanel.add(pl1_info);		   
		       
		       infoPanel.add(new JLabel("\n")); // space
		       
		       JTextArea rem_champs_label = new JTextArea("     " + game.getFirstPlayer().getName() +  "'s remaining champions : ");
		       rem_champs_label.setBackground(Color.green);
		       rem_champs_label.setFont(new Font ("Dialog",Font.BOLD,15));

		       rem_champs_label.setOpaque(true);
		       infoPanel.add(rem_champs_label);
		       
               for(Champion curr_champ : game.getFirstPlayer().getTeam())
               {
 			    	 JPanel box = new JPanel(new FlowLayout());
 			    	 box.setBackground(Color.white);
 			    	 
			    	 JLabel curr_champ_info = new JLabel(curr_champ.getName() + "  ");
			    	 curr_champ_info.setFont(new Font("Dialog",ABORT,17));
			    	 curr_champ_info.setBackground(Color.cyan);
			    	 curr_champ_info.setOpaque(true);
			    	 String passedToToolPit1 = Game.StringToHTML(game.showChampInfo(curr_champ));
			    	 curr_champ_info.setToolTipText(passedToToolPit1);
			    	 ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
			    	 box.add(curr_champ_info);
			    	 
			    	 JLabel curr_champ_effects = new JLabel("Effects");	
			    	 curr_champ_effects.setFont(new Font("Dialog",ABORT,15));
			    	 curr_champ_effects.setBackground(Color.orange);			    	
			    	 curr_champ_effects.setOpaque(true);
			    	 String effects = "";
			    	 for(Effect e : curr_champ.getAppliedEffects())
			    		 effects += e.toString();
			    	 String passedToToolPit2 = Game.StringToHTML(effects);
			    	 curr_champ_effects.setToolTipText(passedToToolPit2);
			    	 ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
			    	 box.add(curr_champ_effects);
			    	 
			         infoPanel.add(box);
               }
               
               infoPanel.add(new JLabel("\n")); // empty label to create a space
               
               JTextArea pl2_info = new JTextArea();
               pl2_info.setEditable(false);
		       String info2 = "                          player 2 \n";
		       info2 += "Name : " + game.getSecondPlayer().getName() + "\n";
		       if(game.isSecondLeaderAbilityUsed())
		            info2 += "leader ability used : " + "YES";
		       else 
		    	   info2 += "leader ability used : " + "NO";
		       pl2_info.setText(info2);
		       
		       infoPanel.add(new JLabel("\n")); // space
		       pl2_info.setFont(new Font("Dialog",ABORT,16));
		       infoPanel.add(pl2_info);	
		       
		       JTextArea rem_champs_labe2 = new JTextArea("     " + game.getSecondPlayer().getName() +  "'s remaining champions : ");
		      
		       rem_champs_labe2.setBackground(Color.green);
		       rem_champs_labe2.setFont(new Font ("Dialog",Font.BOLD,15));
		       rem_champs_labe2.setOpaque(true);
		       infoPanel.add(new JLabel("\n")); // space
		       infoPanel.add(rem_champs_labe2);

               for(Champion champ : game.getSecondPlayer().getTeam())
               {
 			    	 JPanel box = new JPanel();
 			    	 box.setBackground(Color.white);
 			    	 
			    	 JLabel champ_info = new JLabel(champ.getName() + "      ");
			    	 champ_info.setFont(new Font("Dialog",ABORT,17));
			    	 champ_info.setBackground(Color.pink);
			    	 champ_info.setOpaque(true);
			    	 String passedToToolPit1 = Game.StringToHTML(game.showChampInfo(champ));
			    	 champ_info.setToolTipText(passedToToolPit1);
			    	 ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
			    	 box.add(champ_info);
			    	 
			    	 JLabel champ_effects = new JLabel("Effects");	
			    	 champ_effects.setFont(new Font("Dialog",ABORT,17));
			    	 champ_effects.setBackground(Color.orange);
			    	 champ_effects.setOpaque(true);
			    	 String effects = "";
			    	 for(Effect e : champ.getAppliedEffects())
			    		 effects += e.toString();
			    	 String passedToToolPit2 = Game.StringToHTML(effects);
			    	 champ_effects.setToolTipText(passedToToolPit2);
			    	 ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
			    	 box.add(champ_effects);
			    	 
			         infoPanel.add(box);
               }
               
              infoPanel.add(new JLabel("\n")); // empty label to create a space
              infoPanel.add(new JLabel("\n")); // empty label to create a space
              infoPanel.add(new JLabel("\n")); // empty label to create a space
              
              JTextArea turnsTitle =  new JTextArea("                         turns : ");         
              turnsTitle.setBackground(Color.green);
              turnsTitle.setFont(new Font ("Dialog",Font.BOLD,15));
              turnsTitle.setOpaque(true);              
			  infoPanel.add(turnsTitle);
			  
		      JTextArea orders = new JTextArea();
		      orders.setFont(new Font("Dialog",ABORT,16));
		      orders.setEditable(false);
		      String ordersText = "";
		      for(Object o : game.getTurnOrder().turnorderall())		      
		    	  ordersText += ((Champion)o).getName() + '\n';
		      orders.setText(ordersText);
		      infoPanel.add(orders);
		      
		      infoPanel.add(new JLabel("\n")); // space
		      infoPanel.add(new JLabel("\n")); // space
		      
		      JTextArea cur_champ_label = new JTextArea("               current champion :");		      
		      cur_champ_label.setBackground(Color.green);
		      cur_champ_label.setFont(new Font ("Dialog",Font.BOLD,15));
		      infoPanel.add(cur_champ_label);		      
		      
		      JPanel currChampBox = new JPanel();
		      currChampBox.setBackground(Color.white);
		      
		      JLabel currChampName = new JLabel(game.getCurrentChampion().getName() + "      ");
		      currChampName.setFont(new Font("Dialog",ABORT,17));
		      if(game.getFirstPlayer().getTeam().contains(game.getCurrentChampion()))
		    	  currChampName.setBackground(Color.cyan);
		      else
		    	  currChampName.setBackground(Color.pink);
		      currChampName.setOpaque(true);
		      currChampName.setToolTipText(Game.StringToHTML(game.showChampInfo(game.getCurrentChampion())));
		      ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
		      currChampBox.add(currChampName);
		      
		      JLabel currChampAbilities = new JLabel("abilities  ");
		      currChampAbilities.setFont(new Font("Dialog",ABORT,17));
		      currChampAbilities.setBackground(Color.YELLOW);
		      currChampAbilities.setOpaque(true);
		      String abilities = "";
		      int abilityCounter = 1;
		      for(Ability a : game.getCurrentChampion().getAbilities())
		      {
		    	  abilities += "Ability " + abilityCounter + " : \n";
		    	  abilities += a.toString() + '\n';
		    	  abilityCounter ++;
		      }
		      currChampAbilities.setToolTipText(Game.StringToHTML(abilities));
		      ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
		      currChampBox.add(currChampAbilities);
		      
		      JLabel currChampEffects = new JLabel("Effects");
		      currChampEffects.setFont(new Font("Dialog",ABORT,17));
		      currChampEffects.setBackground(Color.orange);
		      currChampEffects.setOpaque(true);
		      String effects = "";
		      for(Effect e : game.getCurrentChampion().getAppliedEffects())
		    	  effects += e.toString();
		      currChampEffects.setToolTipText(Game.StringToHTML(effects));
		      ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
		      currChampBox.add(currChampEffects);
		    	
		      infoPanel.add(currChampBox);
		      
		      for(int i = 0 ; i < 20 ; i++) // to create a space (20 lines)
		    	  infoPanel.add(new JLabel("\n")); 

		      
		      //add( , BorderLayout.CENTER);
	          add(infoPanel , BorderLayout.EAST);
		      
	          
			  revalidate();
			  repaint();	
}	

public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}



}
