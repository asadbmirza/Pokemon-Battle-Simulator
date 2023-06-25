class Pokemon {

  //member variables
  private String name;
  private Moveset moves;
  private Stats stat;
  private Type typing1;
  private Type typing2;
	private String image;

  //Set up each 6 pokemon based on name
  public Pokemon(String s) {
    name = s;

    //Set up Greninja's moves, stats, and typing
    if (name.equals("Greninja")) {
      moves = new Moveset(new Moves("Ice Beam"), new Moves("Gunk Shot"), new Moves("U-Turn"), new Moves("Dark Pulse"));
      stat = new Stats(132,100,72,108,76,127);
      typing1 = new Type("water");
      typing2 = new Type("dark");
			image = "GRENINJA.png";
      
    }

    //Set up Blaziken's moves, stats, and typing
    else if (name.equals("Blaziken")) {
      moves = new Moveset(new Moves("Flare Blitz"), new Moves("Swords Dance"), new Moves("Close Combat"), new Moves("Stone Edge"));
      stat = new Stats(140,125,75,115,75,85);
      typing1 = new Type("fire");
      typing2 = new Type("fighting");
			image = "blaziken.png";
    }

    //Set up Torterra's moves, stats, and typing
    else if (name.equals("Torterra")) {
      moves = new Moveset(new Moves("Earthquake"), new Moves("Wood Hammer"), new Moves("Protect"), new Moves("Synthesis"));
      stat = new Stats(155,114,110,80,90,61);
      typing1 = new Type("grass");
      typing2 = new Type("ground");
			image = "torterra.png";
    }

    //Set up Machamps's moves, stats, and typing
    else if (name.equals("Machamp")) {
      moves = new Moveset(new Moves("Dynamic Punch"), new Moves("Bullet Punch"), new Moves("Dual Chop"), new Moves("Bulk Up"));
      stat = new Stats(150,135,85,70,90,60);
      typing1 = new Type("fighting");
      typing2 = new Type("none");
			image = "machamp.png";
    }

    //Set up Gardevoir's moves, stats, and typing
    else if (name.equals("Gardevoir")) {
      moves = new Moveset(new Moves("Psychic"), new Moves("Moonblast"), new Moves("Calm Mind"), new Moves("Healing Wish"));
      stat = new Stats(128,70,70,130,120,85);
      typing1 = new Type("psychic");
      typing2 = new Type("fairy");
			image = "Gardevoir.png";
    }

    //Set up Gengar's moves, stats, and typing
    else if (name.equals("Gengar")) {
      moves = new Moveset(new Moves("Shadow Ball"), new Moves("Sludge Wave"), new Moves("Icy Wind"), new Moves("Thunderbolt"));
      stat = new Stats(120,70,65,135,80,115);
      typing1 = new Type("ghost");
      typing2 = new Type("poison");
			image = "Gengar.png";
    }
  }

  public String getName() {
    return name;
  }

  public Moveset getMoveset() {
    return moves;
  }
	public String getImageName(){
		return image;
	}
  
  public Stats getStats() {
    return stat;
  }
 
  public Type getTypeOne() {
    return typing1;
  }

  public Type getTypeTwo() {
    return typing2;
  }

  public Pokemon copyPokemon(Pokemon p) {
    Pokemon copy = new Pokemon(p.getName());
    return copy;
  }  
  
}