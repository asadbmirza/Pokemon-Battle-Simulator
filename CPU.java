import java.lang.Math;

class CPU extends Player {

  //Chooses a move for the cpu at random
  public Moves chooseMove()   {
    
    int moveSelect = (int)(Math.random()*4) + 1;

    if (moveSelect == 1) {
      return getFirstPokemon().getMoveset().moveOne();     
    }
    else if (moveSelect == 2) {
      return getFirstPokemon().getMoveset().moveTwo();     
    }
    else if (moveSelect == 3) {
      return getFirstPokemon().getMoveset().moveThree();     
    }
    else {
      return getFirstPokemon().getMoveset().moveFour();     
    }

  }
}