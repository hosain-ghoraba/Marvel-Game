package views;
import model.world.*;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ToolTipManager;

import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import model.world.Champion;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;

public class gamewindow extends JFrame implements ActionListener {

	JPanel boardPanel ;
    JPanel infoPanel;
    JPanel actionsPanel;

    Game game ;
    

		   
public gamewindow(Game game) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Marvel - Ultimate War");
		setExtendedState(JFrame.MAXIMIZED_BOTH);	
		ImageIcon logo = new ImageIcon("logo.png");
		setIconImage(logo.getImage());
		
		this.game = game;
		
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(5,5,0,0));
		boardPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0,20 )); 
		boardPanel.setVisible(true);
        infoPanel=new JPanel();
        infoPanel = give_updated_infoPanel(game);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        infoPanel.setVisible(true);
        
        
        actionsPanel = new JPanel(new GridLayout(0,1));
        actionsPanel.add(new JButton("move up"));
        actionsPanel.add(new JButton("move down"));
        actionsPanel.add(new JButton("move right"));
        actionsPanel.add(new JButton("move left"));
        actionsPanel.add(new JButton("attack"));
        actionsPanel.add(new JButton("cast ability"));
        actionsPanel.add(new JButton("use leader ability"));
        actionsPanel.add(new JButton("end turn"));
        actionsPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        actionsPanel.setVisible(true);



		add(boardPanel,BorderLayout.CENTER);
		add(actionsPanel,BorderLayout.WEST);
		add(infoPanel,BorderLayout.EAST);
        
		revalidate();
		repaint();
	}


public JPanel give_updated_infoPanel (Game game) {
	
	       infoPanel.removeAll();
	    ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE); // prevents all toolpits from disappearing after a while of appearing


	    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	    infoPanel.add(new JLabel("\n")); // space
	       
	       /////////////////// first player info ( text area )
	       JTextArea pl1_info = new JTextArea();		       
	       String info1 = "                          player 1 \n";
	       info1 += "Name : " + game.getFirstPlayer().getName() + "\n";
	       if(game.isFirstLeaderAbilityUsed())
	            info1 += "leader ability used : " + "YES";
	       else 
	    	   info1 += "leader ability used : " + "NO";
	       pl1_info.setText(info1);
	       pl1_info.setEditable(false);
	       pl1_info.setFont(new Font("Dialog",ABORT,16));
	       infoPanel.add(pl1_info);		   
	       
	       infoPanel.add(new JLabel("\n")); // space
	       
	       ////////////// firstPlayer remaining champion title (first green bar)
	       JTextArea firstPlayer_rem_champs_title = new JTextArea("     " + game.getFirstPlayer().getName() +  "'s remaining champions : ");
	       firstPlayer_rem_champs_title.setBackground(Color.green);
	       firstPlayer_rem_champs_title.setFont(new Font ("Dialog",Font.BOLD,15));
	       firstPlayer_rem_champs_title.setOpaque(true);
	       infoPanel.add(firstPlayer_rem_champs_title);
	       
	       ///////////////////////////// firstPlayer remaining champions : making a panel for each remaining champion, then adding it to infoPanel ( the very big panel)
           for(Champion champ : game.getFirstPlayer().getTeam())
           {
			    	 JPanel box = new JPanel(new FlowLayout());
			    	 
		    	 JLabel champ_name = new JLabel(champ.getName() + "  "); // the blue label
		    	 String champ_name_OnHover = Game.StringToHTML(game.showChampInfo(champ));
		    	 champ_name.setToolTipText(champ_name_OnHover);
		    	 champ_name.setBackground(Color.cyan);
		    	 champ_name.setFont(new Font("Dialog",ABORT,17));
		    	 champ_name.setOpaque(true);
		    	 box.setBackground(Color.white);		    	 
		    	 box.add(champ_name);
		    	 
		    	 
		    	 JLabel champ_effects = new JLabel("Effects");	
		    	 String all_effects = "";
		    	 for(Effect e : champ.getAppliedEffects())
		    		 all_effects += e.toString();
		    	 String chmap_effects_OnHover = Game.StringToHTML(all_effects);
		    	 champ_effects.setToolTipText(chmap_effects_OnHover);			    	 
		    	 champ_effects.setBackground(Color.orange);			    	
		    	 champ_effects.setFont(new Font("Dialog",ABORT,15));
		    	 champ_effects.setOpaque(true);			    	 
		    	 box.add(champ_effects);		
		    	 
		         infoPanel.add(box);
           }
           
           infoPanel.add(new JLabel("\n")); // space
           infoPanel.add(new JLabel("\n")); // space
           
           // secondPlayer info (text area)
           JTextArea pl2_info = new JTextArea();
	       String info2 = "                          player 2 \n";
	       info2 += "Name : " + game.getSecondPlayer().getName() + "\n";
	       if(game.isSecondLeaderAbilityUsed())
	            info2 += "leader ability used : " + "YES";
	       else 
	    	   info2 += "leader ability used : " + "NO";
	       pl2_info.setText(info2);
	       
	       pl2_info.setEditable(false);
	       pl2_info.setFont(new Font("Dialog",ABORT,16));
	      
	       infoPanel.add(pl2_info);	
	       infoPanel.add(new JLabel("\n")); // space
	       
           ////////////// secondPlayer remaining champion title (second green bar)
	       JTextArea secondPlayer_rem_champs_title = new JTextArea("     " + game.getSecondPlayer().getName() +  "'s remaining champions : ");		      
	       secondPlayer_rem_champs_title.setBackground(Color.green);
	       secondPlayer_rem_champs_title.setFont(new Font ("Dialog",Font.BOLD,15));
	       secondPlayer_rem_champs_title.setOpaque(true);		       
	       infoPanel.add(secondPlayer_rem_champs_title);

           for(Champion champ : game.getSecondPlayer().getTeam())   ///////////////////////////// secondPlayer remaining champions : making a panel for each remaining champion, then adding it to infoPanel ( the very big panel)
           {
			    	 JPanel box = new JPanel();
			    	 
		    	 JLabel champ_name = new JLabel(champ.getName() + "      ");
		    	 String champ_name_OnHover = Game.StringToHTML(game.showChampInfo(champ));
		    	 champ_name.setToolTipText(champ_name_OnHover);
		    	 champ_name.setBackground(Color.pink);
		    	 champ_name.setFont(new Font("Dialog",ABORT,17));
		    	 champ_name.setOpaque(true);
		    	 box.add(champ_name);
		    	 
		    	 JLabel champ_effects = new JLabel("Effects");	
		    	 String all_effects = "";
		    	 for(Effect e : champ.getAppliedEffects())
		    		 all_effects += e.toString();
		    	 String champ_effects_OnHover = Game.StringToHTML(all_effects);
		    	 champ_effects.setToolTipText(champ_effects_OnHover);
		    	 champ_effects.setBackground(Color.orange);
		    	 champ_effects.setFont(new Font("Dialog",ABORT,17));
		    	 champ_effects.setOpaque(true);
		    	 box.add(champ_effects);
		    	 
		    	 box.setBackground(Color.white);			    	 
		         infoPanel.add(box);
           }
           
          infoPanel.add(new JLabel("\n")); // space
          infoPanel.add(new JLabel("\n")); // space
          infoPanel.add(new JLabel("\n")); // space
          
          /////////////////////////////////////////////////////// turnOrder details
          JTextArea turnsTitle =  new JTextArea("                         turns : "); // third green bar        
          turnsTitle.setBackground(Color.green);
          turnsTitle.setFont(new Font ("Dialog",Font.BOLD,15));
          turnsTitle.setOpaque(true);              
		  infoPanel.add(turnsTitle);
		  
	      JTextArea turnsArea = new JTextArea();
	      String ordersText = "";
	     // for(Object o : game.getTurnOrder().turnorderall())		      
	    //	  ordersText += ((Champion)o).getName() + '\n';
	    //  System.out.println(ordersText);
	     
	      int x = game.getTurnOrder().size();
	      PriorityQueue temp = new PriorityQueue(x);
	      while (!game.getTurnOrder().isEmpty())
	      {
	    	  ordersText += ((Champion)(game.getTurnOrder().peekMin())).getName()+ '\n';
	    	  temp.insert(game.getTurnOrder().remove());
	      }
	      while (!temp.isEmpty())
	      {
	    	  game.getTurnOrder().insert(temp.remove());
	      }
	      
	      turnsArea.setText(ordersText);
	      turnsArea.setFont(new Font("Dialog",ABORT,16));
	      turnsArea.setEditable(false);
	      infoPanel.add(turnsArea);
	      infoPanel.revalidate();
	      infoPanel.repaint();
	      turnsArea.revalidate();
	      turnsArea.repaint();
	      this.revalidate();
	      this.repaint();
	      infoPanel.add(new JLabel("\n")); // space
	    //  infoPanel.add(new JLabel("\n")); // space
	      
	      ///////////////////////////////////////////////////// current Champion details
	      JTextArea currChampTitle = new JTextArea("               current champion :"); // fourth green bar	      
	      currChampTitle.setBackground(Color.green);
	      currChampTitle.setFont(new Font ("Dialog",Font.BOLD,15));
	      infoPanel.add(currChampTitle);		      
	      
	      JPanel currChampBox = new JPanel();
	      
	      // curr champ name
	      JLabel currChampName = new JLabel(game.getCurrentChampion().getName() + "      ");
	      if(game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) // color depends on holder player ( blur if first player, pink if second player)
	    	  currChampName.setBackground(Color.cyan);
	      else
	    	  currChampName.setBackground(Color.pink);
	      currChampBox.setBackground(Color.white);
	      currChampName.setFont(new Font("Dialog",ABORT,17));
	      currChampName.setOpaque(true);
	      String currChampName_OnHover = Game.StringToHTML(game.showChampInfo(game.getCurrentChampion()));
	      currChampName.setToolTipText(currChampName_OnHover);
	      currChampBox.add(currChampName);
	      
	      // curr champ abilities
	      JLabel currChampAbilities = new JLabel("abilities  ");
	      String abilities = "";
	      int abilityCounter = 1;
	      for(Ability a : game.getCurrentChampion().getAbilities())
	      {
	    	  abilities += "Ability " + abilityCounter + " : \n";
	    	  abilities += a.toString() + '\n';
	    	  abilityCounter ++;
	      }
	      String currChampAbilities_OnHover = Game.StringToHTML(abilities); 
	      currChampAbilities.setToolTipText(currChampAbilities_OnHover);
	      currChampAbilities.setBackground(Color.YELLOW);
	      currChampAbilities.setFont(new Font("Dialog",ABORT,17));
	      currChampAbilities.setOpaque(true);
	      currChampBox.add(currChampAbilities);
	      
	      // curr champ effects
	      JLabel currChampEffects = new JLabel("Effects");
	      
	      String all_effects = "";
	      for(Effect e : game.getCurrentChampion().getAppliedEffects())
	    	  all_effects += e.toString();
	      String all_effects_OnHover = Game.StringToHTML(all_effects);
	      currChampEffects.setToolTipText(all_effects_OnHover);	      
	      currChampEffects.setBackground(Color.orange);
	      currChampEffects.setFont(new Font("Dialog",ABORT,17));
	      currChampEffects.setOpaque(true);	
	      currChampBox.add(currChampEffects);
	      infoPanel.add(currChampBox);
	      
	      for(int i = 0 ; i < 20 ; i++) // to create a space (20 lines)
	    	  infoPanel.add(new JLabel("\n")); 
                    
	      return (infoPanel);

}	
public void add_button(JButton x) {
	boardPanel.add(x);
	
}
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}


}
