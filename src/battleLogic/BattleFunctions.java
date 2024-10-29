package battleLogic;


import java.util.List;
import java.util.Scanner;

import pokemonAttributes.Moves;
import pokemonAttributes.Pokemon;
import visuals.Visual;
class BattleFunctions {
  
  // Member Variables for battle class, setup here so it can be used in methods
  protected Player plyr;
  protected CPU ai;
  protected int playerDeathCount;
  protected int cpuDeathCount;
  protected boolean switchTrue;
  protected List<String> flags;
	protected double plyrHpLeft;
	protected double cpuHpLeft;
  protected Visual graphics;
  
  //variable used in the attack class for when an opponent uses protect
  private boolean isProtect;

  //Method used to set up the battle
  public void setUp(Player plyr, CPU ai) {
    Scanner input = new Scanner(System.in);
    int[] in = new int[3];
    int count = 0;

    //Select your pokemon
    for (int i = 0;i<3;i++) {
      System.out.println("Choose a Pokemon (" + (i+1) + "/3) by typing its number!");
      
      System.out.println("1:Greninja");
      System.out.println("2:Blaziken");
      System.out.println("3:Torterra");
      System.out.println("4:Gengar");
      System.out.println("5:Machamp");
      System.out.println("6:Gardevoir");
      in[count] = input.nextInt();

      //If input is less than 1, greater than 6, or has already been picked, keep running the while loop until a different number is chosen
      while (DupeSearch.search(in,count+1) || in[count] < 1 || in[count] > 6) {
        System.out.println("Numbers must be between 1 and 6 and you cannot pick the same number twice");
        in[count] = input.nextInt();
      }
      
      if (in[count] == 1) {
        plyr.addPokemon(new Pokemon("Greninja"));
      }
      else if (in[count] == 2 ) {
        plyr.addPokemon(new Pokemon("Blaziken"));
      }
      else if (in[count] == 3) {
        plyr.addPokemon(new Pokemon("Torterra"));
      }
      else if (in[count] == 4) {
        plyr.addPokemon(new Pokemon("Gengar"));
      }
      else if (in[count] == 5) {
        plyr.addPokemon(new Pokemon("Machamp"));
      }
      else if (in[count] == 6) {
        plyr.addPokemon(new Pokemon("Gardevoir"));
      }
      count++;
    }
    
    //Add Remaining Pokemon to the CPUs team
    for (int i = 1;i<7;i++) {
      if (i != in[0] && i != in[1] && i!=in[2]) {
        if (i == 1) {
          ai.addPokemon(new Pokemon("Greninja"));
        }
        else if (i == 2) {
          ai.addPokemon(new Pokemon("Blaziken"));
        }
        else if (i == 3) {
          ai.addPokemon(new Pokemon("Torterra"));
        }
        else if (i == 4) {
          ai.addPokemon(new Pokemon("Gengar"));
        }
        else if (i == 5) {
          ai.addPokemon(new Pokemon("Machamp"));
        }
        else if (i == 6) {
          ai.addPokemon(new Pokemon("Gardevoir"));
        }
      }
    }
  }

  //Method that returns 1 if the user goes first or 2 if the cpu goes first
  public int chooseWhoAttacks(Pokemon atk, Pokemon opp, Moves one, Moves two, boolean switchTrue) {
    //If the user has switched, the cpu automatically attacks the pokemon being switched in
    if (switchTrue == true) {
      return 2;
    }
    else {
      //If player's move has priority, they go first
      if (one.getPriority() == true && two.getPriority() == false) {
        return 1;
      }
      //If opponent's move has priority, they go first
      else if (one.getPriority() == false && two.getPriority() == true) {
        return 2;
      }
      //If no move has priority or both have priority, whichever pokemon is faster goes first
      else {
        //Protect always goes first, regardless of priority, it is also a priority move itself
        if (one.getName().equals("Protect")) {
          return 1;
        }
        else if (two.getName().equals("Protect")) {
          return 2;
        }

        //If player is faster than opponent
        if (atk.getStats().getSPEED() > opp.getStats().getSPEED()) {
          return 1;
        }
        //If opponent is faster than player
        else if (opp.getStats().getSPEED() > atk.getStats().getSPEED()) {
          return 2;
        }

        //If speeds are equal, leave it to chance
        else {
          return (int)(Math.random()*2) + 1;
        }
      }
    }
    
  }

  //Method is a general pokemon switching method used by both the cpu and player
  public void switchPokemon(Player p, Pokemon newP,String s) {
    Pokemon temp = p.getFirstPokemon();

    //If pokemon being switched is the second pokemon
    if (s.equals("2")) {
      p.setFirstPokemon(newP);
      p.setSecondPokemon(temp);
    }

    //If pokemon being switched is the third pokemon
    else if (s.equals("3")) {
      p.setFirstPokemon(newP);
      p.setThirdPokemon(temp);
    }
    
  } 

  public void attack(Pokemon main, Pokemon opp, Moves attack, Player plyr) {
    //Seperate code for if pokemon uses protect, user protects itself from an attacking move for one turn
    if (attack.getName().equals("Protect")) {
      isProtect = true;
      System.out.println(main.getName() + " used " + attack.getName());
      //If cpu is using move display an animation 
      if (plyr instanceof CPU) {
        graphics.displayShield2();
        try {
          Thread.sleep(1000);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        graphics.removeShield2();
        
      }
      //if player is defending display attack animation
      else {
        graphics.displayShield1();
        try {
          Thread.sleep(1000);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        graphics.removeShield1();
      }
      return;
    }
    //Protect is bad against status moves as it only blocks attacking moves, other pokemon still attacks
    if (isProtect == true && !attack.getCategory().equals("status")) {
      System.out.println(main.getName() + " used " + attack.getName());
      System.out.println(opp.getName() + " protected itself!");
      isProtect = false;
      return;
    }

    //Set up damage and hp variables
    double damage = 0;
    int hp = opp.getStats().getHP();
    
    double STAB = 1;
    //If the move being used is the same type of one of the types of the user, grant a 1.5x attack boost
    if ((main.getTypeOne().getType()).equals((attack.getMoveType().getType()))  ||  (main.getTypeOne().getType()).equals((attack.getMoveType().getType()))) {
      STAB = 1.5;
    }

    //If the move is super effective, grant a 2x attack boost, or not very effective 0.5x attack weaken
    double superEffect = opp.getTypeOne().superEffectiveMultiply(attack.getMoveType());
    
    //If the user's second type is weak against the move, grant a now 4x attack boost, or counteract the the first super effective making it 1x again, or if the move is weak against both pokemon's types make it 0.25x effective.
    superEffect = superEffect*opp.getTypeTwo().superEffectiveMultiply(attack.getMoveType());
    System.out.println(main.getName() + " used " + attack.getName() + "! ");

    //If move is super effective and is an attacking move
    if (superEffect >= 2 && !attack.getCategory().equals("status")) {
      System.out.println("It's super effective!");
    }
    //If move does not effect opponent and is an attacking move
    else if (superEffect == 0 && !attack.getCategory().equals("status")) {
      System.out.println("It doesn't effect " + opp.getName());
    }
    //If move isn't very effective and is an attacking move
    else if (superEffect <= 0.5 && !attack.getCategory().equals("status")) {
      System.out.println("It's not very effective...");
    }
    
    if (attack.getCategory().equals("physical")) {
      //attacking pokemons attack and defending pokemons defense
      double atk = main.getStats().getATK();
      double def = opp.getStats().getDEF();
      
      //Pokemon damage formula based on offical pokemon documentation
      damage = ((((22)*attack.getBattlePower()*(atk/def))/50)+2)*STAB*superEffect;
      opp.getStats().setHP(hp-(int)damage);

      //If cpu is attacking display an animation 
      if (plyr instanceof CPU) {
        graphics.displayPlyrHitmarker();
        try {
          Thread.sleep(1000);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        graphics.removePlyrHitmarker();
        
      }
      //if player is attacking display attack animation
      else {
        graphics.displayCPUHitmarker();
        try {
          Thread.sleep(1000);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        graphics.removeCPUHitmarker();
      }
    }

    //If move is a special attack
    else if (attack.getCategory().equals("special")) {
      //attacking pokemons special attack and defending pokemons special defense
      double spatk = main.getStats().getSpAtk();
      double spdef = opp.getStats().getSpDef();
      
      //Pokemon damage formula based on offical pokemon documentation
      damage = ((((22)*attack.getBattlePower()*(spatk/spdef))/50)+2)*STAB*superEffect;

      opp.getStats().setHP(hp-(int)damage);
      
      //If cpu is attacking display an animation 
      if (plyr instanceof CPU) {
        graphics.addBeam1();
        try {
          Thread.sleep(1000);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        graphics.removeBeam1();
        
      }
      //if player is attacking display attack animation
      else {
        graphics.addBeam2();
        try {
          Thread.sleep(1000);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        graphics.removeBeam2();
      }
    }

    //Prevent hp from going in the negatives
    if (hp - (int)damage < 0) {
      damage = hp;
      opp.getStats().setHP(0);
    }
    
    //If move enables you to switch
    if (attack.getSwitching() == true) {
      //If cpu is using the move and they still have pokemon available, switch to their second pokemon
      if (plyr instanceof CPU && cpuDeathCount < 2) {
        graphics.removeCPUImage();
        switchPokemon(plyr,plyr.getSecondPokemon(),"2");
        graphics.displayCPUImage(plyr.getFirstPokemon().getImageName());
      }
      //if player is using the move
      else if (plyr instanceof Player && playerDeathCount < 2) {
        playerSwitch(false);
      }
    }
  
    //For status moves and secondary effects of attacking moves
    changeStats(main,opp,attack,(int)damage);  
    isProtect = false;
  }

  public void changeStats(Pokemon main, Pokemon opp, Moves attack, int damage) {
    
    //If category is a status move(meaning it does not attack)
    if (attack.getCategory().equals("status")) {
        
      //Set hp equal to edited stat from the status move(if it edit's hp, otherwise will just multiply itself by 1)
      if (attack.getReductionHP() < 1) {
        main.getStats().setHP((int)(main.getStats().getHP()+main.getStats().getTotalHP()*attack.getReductionHP()));
        System.out.println(main.getName() + " healed itself!");

        //Prevents pokemon from healing itself passed total hp
        if (main.getStats().getHP() > main.getStats().getTotalHP()) {
          
          main.getStats().setHP(main.getStats().getTotalHP());
          System.out.println(main.getName() + "'s health is full");
        }
        
      }

      //Set attack equal to edited stat from the status move(if it edit's atk, otherwise will just multiply itself by 1)
        main.getStats().setATK((int)(main.getStats().getATK()*attack.getReductionATK()));
      if (attack.getReductionATK() > 1) {
        System.out.println(main.getName() + " raised it's attack!");
      }

      //Set defense equal to edited stat from the status move(if it edit's def, otherwise will just multiply itself by 1)
        main.getStats().setDEF((int)(main.getStats().getDEF()*attack.getReductionDEF()));
      if (attack.getReductionDEF() > 1) {
        System.out.println(main.getName() + " raised it's defense!");
      }

      //Set special attack equal to edited stat from the status move(if it edit's spatk, otherwise will just multiply itself by 1)
        main.getStats().setSpAtk((int)(main.getStats().getSpAtk()*attack.getReductionSpAtk()));
      if (attack.getReductionSpAtk() > 1) {
        System.out.println(main.getName() + " raised it's special attack!");
      }

      //Set Special Defense equal to edited stat from the status move(if it edit's spdef, otherwise will just multiply itself by 1)
        main.getStats().setSpDef((int)(main.getStats().getSpDef()*attack.getReductionSpDef()));
      if (attack.getReductionSpDef() > 1) {
        System.out.println(main.getName() + " raised it's special defense!");
      }

      //Set Speed equal to edited stat from the status move(if it edit's speed, otherwise will just multiply itself by 1)
        main.getStats().setSPEED((int)(main.getStats().getSPEED()*attack.getReductionSPEED()));
      if (attack.getReductionSPEED() > 1) {
        System.out.println(main.getName() + " raised it's speed!");
      }
      //If cpu is using move display an animation 
      if (plyr instanceof CPU) {
        graphics.displayShield2();
        try {
          Thread.sleep(1000);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        graphics.removeShield2();
        
      }
      //if player is defending display attack animation
      else {
        graphics.displayShield1();
        try {
          Thread.sleep(1000);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        graphics.removeShield1();
      }
    }


    //If category is not a status move rather it lowers opponents stats(only stats being lowered are your hp due to recoil moves, or opponents speed as these are the only moves implemented that have secondary effects)..
    if (!attack.getCategory().equals("status")) {
        
      //Set hp equal to edited stat from the status move(if it edit's hp, otherwise will just multiply itself by 1)
      if (attack.getReductionHP() < 1) {
        main.getStats().setHP((int)(main.getStats().getHP()-damage*0.33));
        System.out.println(main.getName() + " is damaged by recoil!");
        //Prevent HP from decreasing past zero
        if (main.getStats().getHP() < 0) {
          main.getStats().setHP(0);
        }
      }

      //Set Speed equal to edited stat from the status move(if it edit's speed, otherwise will just multiply itself by 1)
      if (attack.getReductionSPEED() < 1) {
        opp.getStats().setSPEED((int)(opp.getStats().getSPEED()*attack.getReductionSPEED()));
        System.out.println(main.getName() + " lowered opponents the opponents speed!");
      }
      
    }
  }

  //Switching method for only the player, death is used for removing pokemon from the team if they faint
  public void playerSwitch(boolean death) {
    //prevent method from running if 2 pokemon have already fainted
    if (playerDeathCount == 2) {
      return;
    }
    Scanner input = new Scanner(System.in);
    graphics.removePlayerImage();
    
    System.out.println("Switch to who?");
    System.out.println("Type 2 for " +plyr.getSecondPokemon().getName());
    if (playerDeathCount == 0) {
      System.out.println("Type 3 for " +plyr.getThirdPokemon().getName());
    }

    String switchP = input.next();
    
    if (playerDeathCount == 1) {
      //If 1 pokemon has fainted, prevent third pokemon from being switched to
      while (!switchP.equals("2") && playerDeathCount == 1) {
        System.out.println("Invalid input, input 2");
        switchP = input.next();
      }
    }
    //Prevent misinputs
    else {
      while (!switchP.equals("2") && !switchP.equals("3")) {
        System.out.println("Invalid input, input 2 or 3");
        switchP = input.next();
      }
    }
    

    //Switch out second pokemon no pokemon has fainted
    if (switchP.equals("2")) {
      System.out.println("Switch out " + plyr.getFirstPokemon().getName());
      System.out.println("Go! " + plyr.getSecondPokemon().getName() + "!");
      switchPokemon(plyr, plyr.getSecondPokemon(),"2");
      
      if (death == true) {
        plyr.removeSecondPokemon();
      }
    }
    //Switch out third pokemon
    else if (switchP.equals("3")) {
      System.out.println("Switch out " + plyr.getFirstPokemon().getName());
      System.out.println("Go! " + plyr.getThirdPokemon().getName() + "!");
      switchPokemon(plyr, plyr.getThirdPokemon(),"3");
      
      if (death == true) {
        plyr.removeThirdPokemon();
      }
    }
    
    graphics.displayPlayerImage(plyr.getFirstPokemon().getImageName());
    
  }

  
}