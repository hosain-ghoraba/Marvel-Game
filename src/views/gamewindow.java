package views;
import model.world.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import engine.Game;
import engine.Player;
import model.world.Champion;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;

public class gamewindow extends JFrame implements ActionListener {
	JTextArea cur_champ_details ;
	JTextArea playersdetails ;
	Player pl1 ;
	Player pl2 ;
	Game game ;
	JPanel gamePanel ;	 
	
public gamewindow(Game game,Player pl1,Player pl2) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Marvel - Ultimate War");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		//check this method functionalitty
		this.game = game;
		this.pl1=pl1;
		this.pl2=pl2 ;
		
		//	this.setResizable(false);
		
		ImageIcon logo = new ImageIcon("logo.png");
		setIconImage(logo.getImage());
		
		playersdetails = new JTextArea();		
        playersdetails.setBounds(this.getWidth()-255,0,255,(this.getHeight()/2)-50 );
		playersdetails.setEditable(false);
		playersdetails.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		add(playersdetails);
        updateplayersdata();
        
        cur_champ_details = new JTextArea();      
		cur_champ_details.setEditable(false);
		cur_champ_details.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));			
		cur_champ_details.setBounds(this.getWidth()-255,(getHeight()/2)-50, 255, (this.getHeight()/2)+50);		
		add(cur_champ_details);
		update_curchamp_datails();
		
		gamePanel =new JPanel();
		gamePanel.setLayout(new GridLayout(5,5,0,0));
		gamePanel.setBounds(0,0,this.getWidth()-255,this.getHeight());
		gamePanel.setVisible(true);
/*
JButton zero_zero = new JButton ("1");
JButton zero_one = new JButton ("2");
JButton zero_two = new JButton ("3");
JButton zero_three = new JButton ("4");
JButton zero_four = new JButton ("5");
JButton one_zero = new JButton ();
JButton one_one = new JButton ();
JButton one_two = new JButton ();
JButton one_three = new JButton ();
JButton one_four = new JButton ();
JButton two_zero = new JButton ();
JButton two_one = new JButton ();
JButton two_two = new JButton ();
JButton two_three = new JButton ();
JButton two_four = new JButton ();
JButton three_zero = new JButton() ;
JButton three_one =new JButton() ;
JButton three_two = new JButton() ;
JButton three_three = new JButton();
JButton three_four = new JButton();		
JButton four_zero = new JButton() ;
JButton four_one =new JButton() ;
JButton four_two = new JButton() ;
JButton four_three = new JButton();
JButton four_four = new JButton();
zero_zero.addActionListener(this);
zero_one.addActionListener(this);
zero_two.addActionListener(this);
zero_three.addActionListener(this);
zero_four.addActionListener(this);
one_zero.addActionListener(this);
one_one.addActionListener(this);one_two.addActionListener(this);
one_three.addActionListener(this);
one_four.addActionListener(this);
two_zero.addActionListener(this);two_one.addActionListener(this);
two_two.addActionListener(this);
two_three.addActionListener(this);
two_four.addActionListener(this);three_zero.addActionListener(this);three_one.addActionListener(this);
three_two.addActionListener(this);
three_three.addActionListener(this);three_four.addActionListener(this);
four_zero.addActionListener(this);
four_one.addActionListener(this);
four_two.addActionListener(this);four_three.addActionListener(this);four_four.addActionListener(this);


gamePanel.add(zero_zero);
gamePanel.add(zero_one);
gamePanel.add(zero_two);
gamePanel.add(zero_three);
gamePanel.add(zero_four);
gamePanel.add(one_zero);
gamePanel.add(one_one);
gamePanel.add(one_two);
gamePanel.add(one_three);
gamePanel.add(one_four);
gamePanel.add(two_zero);
gamePanel.add(two_one);
gamePanel.add(two_two);
gamePanel.add(two_three);
gamePanel.add(two_four);
gamePanel.add(three_zero);
gamePanel.add(three_one);
gamePanel.add(three_two);
gamePanel.add(three_three);
gamePanel.add(three_four);
gamePanel.add(four_zero);
gamePanel.add(four_one);
gamePanel.add(four_two);
gamePanel.add(four_three);
gamePanel.add(four_four);

*/
add(gamePanel);
		revalidate();
		repaint();
	}

public void update_curchamp_datails() {
		Champion c = game.getCurrentChampion();
	//*	Their name.
		//Their type.
		//their currentHp, mana, and actions points.
		//Their list of abilities, as well as information about each ability. (Indicated below).
		//Their list of applied effects, as well as information. (Name and duration)
		//Their attack damage and attack range.
		
			
		String s = "current champion : "+c.getName() +"\n"+ "type: ";
		if(c instanceof AntiHero) {
			s+= "Anti hero" ;
		}
		else if(c instanceof Hero) {
			s+= " hero" ;

		}
		else
			s+= "villain" ;
		s+= "\n"+ "current Health points:  "+c.getCurrentHP() +"\n"+" mana"+ c.getMana() +"\n"+ "actions points :"+c.getCurrentActionPoints()+ "\n"+"abilities :";
		for(Ability x : c.getAbilities()) {
			s+="\n";
			s+=x.getName()+ " ,"  +"type: " ;
			if(x instanceof HealingAbility) {
				s+="HealingAbility"+ " ," +"mana: "+x.getManaCost()+ " ," +"action cost :"+x.getCastRange()+ " ," +"current cooldown: "+x.getCurrentCooldown()+ " ,"+" base cooldown:"+x.getBaseCooldown() + " ," ;
				s+= " heal amount :"+((HealingAbility)x).getHealAmount() ;
			}
			else if(x instanceof DamagingAbility) {
				s+= "DamagingAbility"+ " ," +"mana: "+x.getManaCost()+ " ," +"action cost :"+x.getCastRange()+ " ," +"current cooldown: "+x.getCurrentCooldown()+ " ,"+" base cooldown:"+x.getBaseCooldown() + " ," ;
				s+= " Damageamount:"+((DamagingAbility)x).getDamageAmount() ;
			}
			else {
				s+="CrowdControlAbility"+ " ," +"mana: "+x.getManaCost()+ " ," +"action cost :"+x.getCastRange()+ " ," +"current cooldown: "+x.getCurrentCooldown()+ " ,"+" base cooldown:"+x.getBaseCooldown() + " ," ;
				s+= " effect:"+ ((CrowdControlAbility)x).getEffect().getName() + "  effects's duration: "+((CrowdControlAbility)x).getEffect().getDuration() ;
			}
				//need to add info
			/*
			 * The ability name./*
The ability type.
The ability area of effect.
The ability cast range.
The mana and action costs.
The current and base cooldowns.
The healAmount/ damageAmount/ effect. (Depending on ability type).
			 */
		}
		s+="\n";
		for(Effect x :c.getAppliedEffects()) {
			
			s+= x.getName() + " duration :" + x.getDuration()+ "  ,";
			
		}
		s+="\n"+" attack damage :"+c.getAttackDamage()+"\n"+"attack range:"+ c.getAttackRange();
		
    cur_champ_details.setText(s);
		
	}
public void updateplayersdata(){
		String s = pl1.getName() +" : "+"\n" + "remaining champions :";
		for(Champion x : pl1.getTeam()) {
			if(x.getCondition()!=(model.world.Condition.KNOCKEDOUT)) {
				s+= x.getName() + "  ";
			}
		}
		if(!game.isFirstLeaderAbilityUsed()) {
			s+="\n"+"  LeaderAbility not used";
		}
		else
			s+="\n"+"  LeaderAbility  used";
		String z = pl2.getName() +" : "+"\n" + "remaining champions :";
		for(Champion x : pl2.getTeam()) {
			if(x.getCondition()!=(model.world.Condition.KNOCKEDOUT)) {
				z+= x.getName() + "  ";
			}
		}
		if(!game.isSecondLeaderAbilityUsed()) {
			z+="\n"+"  LeaderAbility not used";
		}
		else
			z+="\n"+"  LeaderAbility  used";
playersdetails.setText(s +"\n"+z);
}
public void add_button(JButton x) {
	gamePanel.add(x);
	
}
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}


}
