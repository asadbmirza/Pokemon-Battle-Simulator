class Moveset {
  private Moves one;
  private Moves two;
  private Moves three;
  private Moves four;
  
  public Moveset(Moves a, Moves b, Moves c, Moves d){
    this.one = a;
    this.two = b;
    this.three = c;
    this.four = d;
  }

  public Moves moveOne() {
    return one;
  }

  public Moves moveTwo() {
    return two;
  }

  public Moves moveThree() {
    return three;
  }

  public Moves moveFour() {
    return four;
  }

  public String toString() {
    String I = one.getName() + "|Type: " + one.getMoveType() + "|Power: " + one.getBattlePower() + "|Priority: " + one.getPriority() + "|Category: " + one.getCategory() + "|Switches Pokemon: " + one.getSwitching();
    
    String II = two.getName() + "|Type: " + two.getMoveType() + "|Power: " + two.getBattlePower() + "|Priority: " + two.getPriority() + "|Category: " + two.getCategory() + "|Switches Pokemon: " + two.getSwitching();
    
    String III = three.getName() + "|Type: " + three.getMoveType() + "|Power: " + three.getBattlePower() + "|Priority: " + three.getPriority() + "|Category: " + three.getCategory() + "|Switches Pokemon: " + three.getSwitching();

    String IV = four.getName() + "|Type: " + four.getMoveType() + "|Power: " + four.getBattlePower() + "|Priority: " + four.getPriority() + "|Category: " + four.getCategory() + "|Switches Pokemon: " + four.getSwitching();
    
    return I + "\n \n" + II + "\n \n" + III + "\n \n" + IV;
  }
}