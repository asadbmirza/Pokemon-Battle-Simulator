package battleLogic;


import java.util.Arrays;
import java.util.Scanner;

import pokemonAttributes.Moves;
import visuals.Visual;

public class Battle extends BattleFunctions {
  private Scanner input; 

  public Battle() {

    //Set up member variables
    input = new Scanner(System.in);
    plyr = new Player();
    ai = new CPU();
		graphics = new Visual();
    playerDeathCount = 0;
    cpuDeathCount = 0;
    //Switch true is used for determining if the user has switched that turn
    switchTrue = false;
    //Used for preventing misinputs
    flags = Arrays.asList("1","2","3","4","s","m","v");
		plyrHpLeft= 100;
		cpuHpLeft = 100;

		
    
    //Begin the game, player selects their three pokemon
    setUp(plyr, ai);
	
    //Begin the Match, user sends out pokemon
    System.out.println("\nYou sent out " + plyr.getFirstPokemon().getName());
    //Set up background and display player's pokemon
		graphics.getBackGround();
		graphics.displayPlayerImage(plyr.getFirstPokemon().getImageName());
    //Display CPU's pokemon
    System.out.println("Opposing trainer sent out " + ai.getFirstPokemon().getName());
		graphics.displayCPUImage(ai.getFirstPokemon().getImageName());

    //While the player has not lost their three pokemon and the cpu has not lost their three pokemon
    while (playerDeathCount < 3 && cpuDeathCount < 3) {
      
      ///CPU UI:
      
      //Set up temporary pokemonname and hpleft for cleaner printing
      String pokemonName = ai.getFirstPokemon().getName();
      int hpLeft = ai.getFirstPokemon().getStats().getHP();
      System.out.println("\n" + pokemonName + " HP: " + hpLeft + "/" + ai.getFirstPokemon().getStats().getTotalHP());

			
      
      ///Players UI:
      pokemonName = plyr.getFirstPokemon().getName();
      hpLeft = plyr.getFirstPokemon().getStats().getHP();
      System.out.println(pokemonName + " HP: " + hpLeft + "/" + plyr.getFirstPokemon().getStats().getTotalHP());

			
			//Used to set up healthbar
			graphics.plyrTookDamage(plyrHpLeft);
			graphics.cpuTookDamage(cpuHpLeft);
      
      //Print Current Pokemon's Moves
      System.out.println("|(1) " + plyr.getFirstPokemon().getMoveset().moveOne().getName() + "|  |(2) "  + plyr.getFirstPokemon().getMoveset().moveTwo().getName() + "|  |(3) "  + plyr.getFirstPokemon().getMoveset().moveThree().getName() + "|  |(4) "  + plyr.getFirstPokemon().getMoveset().moveFour().getName() + "|");
      //Type s to switch, m to view move stats, and v to view your lead pokemon's stats
      System.out.println("("+ flags.get(4) + ") Switch Pokemon? | (m) View Move Stats | (v) View Your Pokemon's Stats");
		
      //Get user's input
      String in = input.next();
      
      //if user input is not 1,2,3,4,m or s(if user loses 2 pokemon they cannot switch, "s" becomes "n" then and is an invalid input)
      while (flags.indexOf(in) == -1 || in.equals("n")) {
        System.out.println("Invalid input!");
        in = input.next();

        if (playerDeathCount == 2 && in.equals("n")) {
          System.out.println("Cannot switch!");
        }
      }

      //Get lead pokemon's moveset
      if (in.equals("m")) {
        System.out.println("\n");
        System.out.println(plyr.getFirstPokemon().getMoveset());
        continue;
      }
			
      //Get lead pokemon's stats
      if (in.equals("v")) {
        System.out.println("\n");
        System.out.println(plyr.getFirstPokemon().getName() + "\nType: " + plyr.getFirstPokemon().getTypeOne().getType() + ", " + plyr.getFirstPokemon().getTypeTwo().getType() + "\n" +plyr.getFirstPokemon().getStats());
        continue;
      }
      
      //Switch Pokemon(counts as a move)
      if (in.equals("s") && playerDeathCount<=1) {
        //Ask user for who they want to switch to
        playerSwitch(false);
        switchTrue = true;
      }    

      //Move selection:
      System.out.println("\n");
      Moves playerAtt;
      if (in.equals("1")) {
        playerAtt = plyr.getFirstPokemon().getMoveset().moveOne();
      }
      else if (in.equals("2")) {
        playerAtt = plyr.getFirstPokemon().getMoveset().moveTwo();
      }
      else if (in.equals("3")) {
        playerAtt = plyr.getFirstPokemon().getMoveset().moveThree();
      }
      else {
        playerAtt = plyr.getFirstPokemon().getMoveset().moveFour();
      }

      //CPU selects an attack
      Moves cpuAtt = ai.chooseMove();
      
      //Choose who attacks first
      int choose = chooseWhoAttacks(plyr.getFirstPokemon(),ai.getFirstPokemon(),playerAtt,cpuAtt,switchTrue);

      //If player moves first and they have not switched this turn, they attack
      if (choose == 1 && switchTrue == false) {
        //attack cpu
attack(plyr.getFirstPokemon(),ai.getFirstPokemon(),playerAtt,plyr);

        //update health bar
        cpuHpLeft = ai.getFirstPokemon().getStats().getHP() *1.0 / ai.getFirstPokemon().getStats().getTotalHP() *100;
        graphics.cpuTookDamage(cpuHpLeft);
        
        //If CPU's pokemon faints(health reaches 0)
        if (ai.getFirstPokemon().getStats().getHP() == 0) {
          System.out.println(ai.getFirstPokemon().getName() + " fainted!");
          cpuDeathCount += 1;
          if (cpuDeathCount == 3) {
            break;
          }
          graphics.removeCPUImage();
          //Switch out their pokemon
          switchPokemon(ai,ai.getSecondPokemon(),"2");
          System.out.println("Opposing trainer sent out " + ai.getFirstPokemon().getName() + "!");
          ai.removeSecondPokemon();
          graphics.displayCPUImage(ai.getFirstPokemon().getImageName());
        }
        //If ai does not faint, he moves
        else {
          //ai attacks player
          attack(ai.getFirstPokemon(),plyr.getFirstPokemon(),cpuAtt,ai);

          //update player's health bar
          plyrHpLeft = plyr.getFirstPokemon().getStats().getHP() *1.0/ plyr.getFirstPokemon().getStats().getTotalHP() *100;
          graphics.plyrTookDamage(plyrHpLeft);
        
        }
      }

      //If Cpu moves first
      else if (choose == 2) {
        //Cpu attacks
        attack(ai.getFirstPokemon(),plyr.getFirstPokemon(),cpuAtt,ai);
        
        //update player's health bar
        plyrHpLeft = plyr.getFirstPokemon().getStats().getHP() *1.0/ plyr.getFirstPokemon().getStats().getTotalHP() *100;
        graphics.plyrTookDamage(plyrHpLeft);
        
        //Check if player's pokemon faints
        if (plyr.getFirstPokemon().getStats().getHP() == 0) {
          System.out.println(plyr.getFirstPokemon().getName() + " fainted!");
          //Player is forced to switch out their pokemon
          playerSwitch(true);
          playerDeathCount += 1;
          //If two pokemon faint, remove ability to switch
          if (playerDeathCount == 2) {
            flags.set(4,"n");
            
          }
        }
        //If player does not faint, they move and if they have not switched
        else if(switchTrue == false) {
          //attack
          attack(plyr.getFirstPokemon(),ai.getFirstPokemon(),playerAtt,plyr);
          graphics.cpuTookDamage(cpuHpLeft);
          cpuHpLeft = ai.getFirstPokemon().getStats().getHP() *1.0 / ai.getFirstPokemon().getStats().getTotalHP() *100;
        }
        
      }
      //Check if cpu pokemon has fainted
      if (ai.getFirstPokemon().getStats().getHP() == 0) {
        
        System.out.println(ai.getFirstPokemon().getName() + " fainted!");
        cpuDeathCount+=1;
        if (cpuDeathCount == 3) {
          break;
        }
        graphics.removeCPUImage();
        switchPokemon(ai,ai.getSecondPokemon(),"2");
        ai.removeSecondPokemon();
        graphics.displayCPUImage(ai.getFirstPokemon().getImageName());
        System.out.println("Opposing trainer sent out " + ai.getFirstPokemon().getName() + "!");
      }
      //Check if player's pokemon has fainted
      if (plyr.getFirstPokemon().getStats().getHP() == 0) {
  
        System.out.println(plyr.getFirstPokemon().getName() + " fainted!");
        playerSwitch(true);
        playerDeathCount+=1;
        
      }
      //If two of player's pokemon faint, remove ability to switch
      if (playerDeathCount == 2) {
        flags.set(4,"n");
      }

      //Reset switch variable and update health bar percents
      switchTrue = false; 
      cpuHpLeft = ai.getFirstPokemon().getStats().getHP() *1.0 / ai.getFirstPokemon().getStats().getTotalHP() *100;
      plyrHpLeft = plyr.getFirstPokemon().getStats().getHP() *1.0 / plyr.getFirstPokemon().getStats().getTotalHP() *100;
			
    }
    //If player loses 3 pokemon they lose 
    if (playerDeathCount == 3) {
      graphics.removePlayerImage();
      graphics.plyrTookDamage(0);
      System.out.println("You lose, you paid $100000 to the winner, you blacked out...");
    }
    //If cpu loses 3 pokemon they lose
    else if (cpuDeathCount == 3) {
      graphics.removeCPUImage();
      graphics.cpuTookDamage(0);
      System.out.println("Congratulations! You win!");
    }
    
  }

  
}