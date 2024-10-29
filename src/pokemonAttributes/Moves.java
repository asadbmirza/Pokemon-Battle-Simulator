package pokemonAttributes;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Moves {
    private String name;
    private Type moveType;
    private int BP;
    private boolean priority;
    private String category;
    private boolean switching;
    private double reductionHP, reductionATK, reductionDEF, reductionSpAtk, reductionSpDef, reductionSPEED;

    public Moves(String s) {
        name = s;
        try {
            InputStream inputStream = getClass().getResourceAsStream("Pokemon Moves.txt");
            if (inputStream == null) {
                throw new FileNotFoundException("File 'Pokemon Moves.txt' not found in the package.");
            }
            
            Scanner in = new Scanner(new InputStreamReader(inputStream));
            String line = "";
            
            while (in.hasNextLine()) {
                line = in.nextLine();
                if (line.equals(name)) {
                    break;
                }
            }
            // Proceed to the next line for attributes after finding the name
            if (in.hasNextLine()) {
                line = in.nextLine();
                String[] attribute = line.split(",");
                moveType = new Type(attribute[0]);
                BP = Integer.parseInt(attribute[1]); 
                priority = Boolean.parseBoolean(attribute[2]);
                category = attribute[3];
                switching = Boolean.parseBoolean(attribute[4]);
                reductionHP = Double.parseDouble(attribute[5]); 
                reductionATK = Double.parseDouble(attribute[6]);
                reductionDEF = Double.parseDouble(attribute[7]); 
                reductionSpAtk = Double.parseDouble(attribute[8]);
                reductionSpDef = Double.parseDouble(attribute[9]); 
                reductionSPEED = Double.parseDouble(attribute[10]);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

  public String getName() {
    return name;
  }

  public Type getMoveType() {
    return moveType;
  }

  public int getBattlePower() {
    return BP;
  }
  
  public boolean getPriority() {
    return priority;
  }
  
  public String getCategory() {
    return category;
  }
  
  public boolean getSwitching() {
    return switching;
  }
  
  public double getReductionHP() {
    return reductionHP;
  }
  
  public double getReductionATK() {
    return reductionATK;
  }

  public double getReductionDEF() {
    return reductionDEF;
  }
  
  public double getReductionSpAtk() {
    return reductionSpAtk;
  }

  public double getReductionSpDef() {
    return reductionSpDef;
  }
  
  public double getReductionSPEED() {
    return reductionSPEED;
  }

  
}

/**
  GRENINJA:
  Type: Water, Dark
  Abilities: Torrent, Protean
  Move 1: Earthquake
  Move 2: Ice beam (type ice; category special; Power 90BP; Accuracy 100%)
  Move 3: Gunk Shot (type poison; category physical; power 120BP; Accuracy 80%)
  Move 4: U-Turn (type bug; category physical; power 70BP; accuracy 100%)
 
  STATS:
    HP: 132
    ATK: 100
    DEF: 72
    Sp Atk: 108
    Sp Def: 76
    SPEED: 127

  BLAZIKEN:
  Type: Fire, Fighting
  Abilities: Blaze, Speed Boost
  Move 1: Bulk Up (fighting, 1.5x attack, 1.5x defense)
  Move 2: Flare Blitz (fire, 120 BP, deals 33 percent recoil damage)
  Move 3: Close Combat (fighting, 120 BP, lowers Def and Sp Def, makes them 0.66)
  Move 4: Stone Edge (Earth, Earthquake, 100 BP)
  Stats:
    HP: 140
    ATK: 125
    DEF: 75
    Sp Atk: 115
    Sp Def: 75
    SPEED: 85
**/

/**
    MACHAMP:
    Type: Fighting
    Abilities: No Guard
    Move 1: Dynamic Punch (100 BP, 100 acc(no guard), fightng)
    Move 2: Swords Dance (Doubles attack until switch)
    Move 3: Dual Chop (80 BP, 100 acc, Dragon)
    Move 4: Bullet Punch (40 BP, 100 acc, priority move, Steel)
    
    Stats: 
      HP: 150
      ATK: 135
      DEF: 85
      Sp Atk: 70
      Sp Def: 90
      SPEED: 60
  **/

/**
    Gardevoir:
    Type: Psychic, Fairy
    Abilities: Trace
    Move 1: Psychic (90 BP, 100 acc, special, Psychic)
    Move 2: Moonblast (95 BP, 100 acc, Special, Fairy)
    Move 3: Calm Mind (1.5x Sp Def, 1.5x Sp. Attack)
    Move 4: Healing Wish (Heals 50% of health after one turn)
    Stats: 
      HP: 128
      ATK: 70
      DEF: 70
      Sp Atk: 130
      Sp Def: 120
      SPEED: 85
  **/


/**
	torterra:
 		ability - overgrow
	 
	 		Move: - protect
						- wood hammer
						- earthquake
						- synthesis. 

      Stats:
  			Hp - 155
  	 		Attack - 114
  			Defense - 110
        Sp Atk - 80
        Sp Def - 90
        Speed - 61
  
	
 Gengar:
	 	ability - Cursed body (if hit by an attack, 30% chance to dsiable that attack)

			Move: - Shadow ball (20% chance to lower opp speed.)
	 							type: ghost
				 				power: 80
				 				acc: 100%

		 				- Sludge wave (10% chance to posion adjacent pokemon)
			 					type: poison
				 				power: 95
				 				acc: 100%

			 			- Icy wind (100% chance to lower opp speed by 1)
								type: ice
								power: 55
								acc: 95%

						- Thunderbolt (30% chance to paralyze)
                type: electric
                power: 90
                acc: 100
      Stats:
        HP: 120
	      ATK: 70
        DEF: 65
        Sp Atk: 135
        Sp Def: 80
        SPEED: 115 
	**/