import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;


public class Visual {

	// setup for varibales
	public JFrame background;
	JPanel playerPanel;
	public JPanel CpuPanel;
	JLabel backgroundPic;
	JLabel plyrPokemon;
	JLabel cpuPokemon;
	
	JPanel PlyrHitmarkerPanel;
	public JPanel CpuHitmarkerPanel;
	JLabel hitmarker1; 
	JLabel hitmarker2;
	JProgressBar healthBar;
	JProgressBar healthBar2;
	JPanel shieldPanel1;
	JPanel shieldPanel2;
	JLabel shield1;
	JLabel shield2;

	JPanel beamPanel1;
	JPanel beamPanel2;
	JLabel beam1;
	JLabel beam2;

	
	public Visual(){
		// initilize the variables

		// set the window and give it a name
		background = new JFrame("Pokemon Battle X");;
		playerPanel = new JPanel();
		CpuPanel = new JPanel();
		// set up the background pic and center it.
		backgroundPic = new JLabel("", new ImageIcon("background2.png"), JLabel.CENTER);;
		
		PlyrHitmarkerPanel = new JPanel();
		CpuHitmarkerPanel = new JPanel();
		// set up the image for the hitmarker.
		hitmarker1 = new JLabel(new ImageIcon("hitmarker.png"));
		hitmarker2 = new JLabel(new ImageIcon("hitmarker.png"));

		healthBar = new JProgressBar();
		healthBar2 = new JProgressBar();

		shieldPanel1 = new JPanel();
		shieldPanel2 = new JPanel();
		shield1 = new JLabel(new ImageIcon("Shield.png"));
		shield2 = new JLabel(new ImageIcon("Shield.png"));

		beamPanel1 = new JPanel();
		beamPanel2 = new JPanel();
		beam1 = new JLabel(new ImageIcon("Beam1.png"));
		beam2 = new JLabel(new ImageIcon("Beam2.png"));

    
		
		
	}

	public void getBackGround(){

		// set layout as null.
		background.setLayout(null);
		// set bounds for the background pic same as the window to cover it all and not extend it.
		backgroundPic.setBounds(0,0,1200,560);

		// sets size
		background.setSize(1200,560);
		// makes it visible
		background.setVisible(true);
		background.setLocationRelativeTo(null);
		// close the window when stop or the close button is pressed
		background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// sets the size for the plane
		playerPanel.setSize(290, 300);
		// sets the plane to invisible because we don't want a white square.
		playerPanel.setOpaque(false);
		// set location of the JPanel within the window.
		playerPanel.setLocation(150,250);
		
		

		CpuPanel.setSize(290, 300);
		CpuPanel.setOpaque(false);
		CpuPanel.setLocation(750,50);
		

		PlyrHitmarkerPanel.setSize(100, 100);
		PlyrHitmarkerPanel.setOpaque(false);
		PlyrHitmarkerPanel.setLocation(300,300);
		
		CpuHitmarkerPanel.setSize(100, 100);
		CpuHitmarkerPanel.setOpaque(false);
		CpuHitmarkerPanel.setLocation(750,100);

		healthBar.setBounds(50, 20, 400, 30);
		healthBar.setStringPainted(true);
		healthBar.setForeground(Color.red);

		healthBar2.setBounds(740, 20, 400, 30);
		healthBar2.setStringPainted(true);
		healthBar2.setForeground(Color.red);


		shieldPanel1.setSize(300,367);
		shieldPanel1.setOpaque(false);
		shieldPanel1.setLocation(150,200);
		

		shieldPanel2.setSize(300,367);
		shieldPanel2.setOpaque(false);
		shieldPanel2.setLocation(750,50);

		beamPanel1.setSize(500,127);
		beamPanel1.setOpaque(false);
		beamPanel1.setLocation(350,300);

		beamPanel2.setSize(500,127);
		beamPanel2.setOpaque(false);
		beamPanel2.setLocation(300,200);
		
		// the follwowing two are always used when addind or removing beacuase it tracks the order and layers of the background so when it is closed or opened, the displays go to the diseigned location rather than not showing.
		background.revalidate(); 
		background.repaint();

		// add the panels and backgrond pic to the window and assign it in which layer it would appear.
		background.add(PlyrHitmarkerPanel,0);
		background.add(CpuHitmarkerPanel,1);
		background.add(shieldPanel1,2);
		background.add(shieldPanel2,3);
		background.add(beamPanel1, 4);
		background.add(beamPanel2, 5);
		background.add(CpuPanel,6);
		background.add(playerPanel,7);
		background.add(healthBar,8);
		background.add(healthBar2,9);
		background.add(backgroundPic,10);
		
		
	}
	// display player's pokemon
	public void displayPlayerImage(String imageName) {
		plyrPokemon = new JLabel(new ImageIcon(imageName));
		playerPanel.add(plyrPokemon);
		// following are used when adding or removing to keep track of layers. if not applied, stuff will stay hidden behind other layers. (the other layers could be invisible)
		playerPanel.revalidate(); 
		playerPanel.repaint();
		
		
	}
	// display ai's pokemon
	public void displayCPUImage(String imageName){
		cpuPokemon = new JLabel(new ImageIcon(imageName));
		CpuPanel.add(cpuPokemon);
		CpuPanel.revalidate(); 
		CpuPanel.repaint();
	}
	// remove player's pokemon
	public void removePlayerImage(){
		playerPanel.remove(plyrPokemon); 
		playerPanel.revalidate(); 
		playerPanel.repaint();

	}
	// remove ai's pokemon
	public void removeCPUImage(){
		CpuPanel.remove(cpuPokemon); 
		CpuPanel.revalidate(); 
		CpuPanel.repaint();

	}
	// display player's hitmarker
	public void displayPlyrHitmarker(){
		PlyrHitmarkerPanel.add(hitmarker1);
		playerPanel.revalidate(); 
		playerPanel.repaint();
	}
	// display ai's hitmarker
	public void displayCPUHitmarker(){
		CpuHitmarkerPanel.add(hitmarker2);
		CpuPanel.revalidate(); 
		CpuPanel.repaint();
	}

	// remove player hitmarker
	public void removePlyrHitmarker(){
		PlyrHitmarkerPanel.remove(hitmarker1);
		playerPanel.revalidate(); 
		playerPanel.repaint();
	}
	// remove ai hitmarker
	public void removeCPUHitmarker(){
		CpuHitmarkerPanel.remove(hitmarker2);
		CpuPanel.revalidate(); 
		CpuPanel.repaint();
	}

	// refresh the player healthbar with new health
	public void plyrTookDamage(double hp){
		healthBar.setValue((int)hp);
	}

	// refresh the ai healthbar with new health
	public void cpuTookDamage(double hp){
		healthBar2.setValue((int)hp);
	}

	// display player shield
	public void displayShield1(){
		shieldPanel1.add(shield1);
		shieldPanel1.revalidate();
		shieldPanel1.repaint();
	}
	// removes player shield
	public void removeShield1(){
		shieldPanel1.remove(shield1);
		shieldPanel1.revalidate();
		shieldPanel1.repaint();
	}
	// display ai shield
	public void displayShield2(){
		shieldPanel2.add(shield2);
		shieldPanel2.revalidate();
		shieldPanel2.repaint();
	}
	// removes ai shield
	public void removeShield2(){
		shieldPanel2.remove(shield2);
		shieldPanel2.revalidate();
		shieldPanel2.repaint();
	}

	public void addBeam1(){
		beamPanel1.add(beam1);
		beamPanel1.revalidate();
		beamPanel1.repaint();
	}
	public void addBeam2(){
		beamPanel2.add(beam2);
		beamPanel2.revalidate();
		beamPanel2.repaint();
	}
	public void removeBeam1(){
		beamPanel1.remove(beam1);
		beamPanel1.revalidate();
		beamPanel1.repaint();
	}
	public void removeBeam2(){
		beamPanel2.remove(beam2);
		beamPanel2.revalidate();
		beamPanel2.repaint();
	}
	
	
	
	

	// majority of these methods could have been one method with two unknowns that would be states when the method is called. However, this idea came too late and there wasn't enough time to implement it   
}