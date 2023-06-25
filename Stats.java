class Stats {
  private int HP;
  private int totalHP;
  private int ATK;
  private int DEF;
  private int SpAtk;
  private int SpDef;
  private int SPEED;

  public Stats(int H, int A, int D, int spA, int spD, int S) {
    HP = H;
    totalHP = H;
    ATK = A;
    DEF = D;
    SpAtk = spA;
    SpDef = spD;
    SPEED = S;
  }

  public int getHP() {
    return HP;
  }

  public int getTotalHP() {
    return totalHP;
  }

  public int getATK() {
    return ATK;
  }

  public int getDEF() {
    return DEF;
  }

  public int getSpAtk() {
    return SpAtk;
  }

  public int getSpDef() {
    return SpDef;
  }

  public int getSPEED() {
    return SPEED;
  }

  public void setHP(int temp) {
    HP = temp;
  }

  public void setATK(int temp) {
    ATK = temp;
  }

  public void setDEF(int temp) {
    DEF = temp;
  }

  public void setSpAtk(int temp) {
    SpAtk = temp;
  }

  public void setSpDef(int temp) {
    SpDef = temp;
  }

  public void setSPEED(int temp) {
    SPEED = temp;
  }

  public String toString() {
    return "HP: " + HP + "\nATTACK: " + ATK + "\nDEFENSE: " + DEF + "\nSPECIAL ATTACK: " + SpAtk + "\nSPECIAL DEFENSE: " + SpDef + "\nSPEED: " + SPEED;
  }
}