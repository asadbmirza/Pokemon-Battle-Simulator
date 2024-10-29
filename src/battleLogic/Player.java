package battleLogic;


import java.util.ArrayList;
import pokemonAttributes.Pokemon;

class Player {

  //Create arraylist of pokemon, at most three will be added
  protected ArrayList<Pokemon> Pokemons;

  public Player() {
    Pokemons = new ArrayList<Pokemon>();
  }

  public void addPokemon(Pokemon temp) {
    Pokemons.add(temp);
  }

  public String getPokemon() {
    return (Pokemons.get(0).getName() + ", " + Pokemons.get(0).getName() + ", " + Pokemons.get(0).getName());
  }

  public Pokemon getFirstPokemon()  {
    return Pokemons.get(0);
  }

  public Pokemon getSecondPokemon() {
    return Pokemons.get(1);
  }

  public Pokemon getThirdPokemon() {
    return Pokemons.get(2);
  }

  public Pokemon removeFirstPokemon()  {
    return Pokemons.remove(0);
  }

  public Pokemon removeSecondPokemon() {
    return Pokemons.remove(1);
  }

  public Pokemon removeThirdPokemon() {
    return Pokemons.remove(2);
  }

  public void setFirstPokemon(Pokemon replace) {
    Pokemons.set(0,replace);
  }

  public void setSecondPokemon(Pokemon replace) {
    Pokemons.set(1,replace);
  }

  public void setThirdPokemon(Pokemon replace) {
    Pokemons.set(2,replace);
  }

  public int getLength() {
    return Pokemons.size();
  }
  
}