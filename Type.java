class Type {
  private String typing;
  
  public Type(String s) {
    typing = s;
  }

  public String getType() {
    return typing;
  }

  public double superEffectiveMultiply(Type s) {

    //fire super effective/not very effective chart
    if (typing.equals("fire")) {
      if (s.getType().equals("water") || s.getType().equals("rock") || s.getType().equals("ground")) {
        return 2;
      }
      else if (s.getType().equals("fire") || s.getType().equals("grass") || s.getType().equals("ice") || s.getType().equals("bug") || s.getType().equals("steel") || s.getType().equals("fairy")) {
        return 0.5;
      }
      else {
       return 1; 
      }
    }
    
    //fighting super effective/not very effective chart
    if (typing.equals("fighting")) {
      if (s.getType().equals("flying") || s.getType().equals("psychic") || s.getType().equals("fairy")) {
        return 2;
      }
      else if (s.getType().equals("bug") || s.getType().equals("rock") || s.getType().equals("dark")) {
        return 0.5;
      }
      else {
        return 1;
      }
    }

    //water super effective/not very effective chart
    if (typing.equals("water")) {
      if (s.getType().equals("electric") || s.getType().equals("grass")) {
        return 2;
      }
      else if (s.getType().equals("fire") || s.getType().equals("water") || s.getType().equals("ice")) {
        return 0.5;
      }
      else {
        return 1;
      }
    }

    //dark super effective/not very effective chart
    if (typing.equals("dark")) {
      if (s.getType().equals("fighting") || s.getType().equals("bug") || s.getType().equals("fairy")) {
        return 2;
      }
      else if (s.getType().equals("ghost") || s.getType().equals("dark")) {
        return 0.5;
      }
      else if (s.getType().equals("psychic")) {
        return 0;
      }
      else {
        return 1;
      }
    }

    //grass super effective/not very effective chart
    if (typing.equals("grass")) {
      if (s.getType().equals("fire") || s.getType().equals("bug") || s.getType().equals("flying") || s.getType().equals("poison") || s.getType().equals("ice")) {
        return 2;
      }
      else if (s.getType().equals("water") || s.getType().equals("electric") || s.getType().equals("grass") || s.getType().equals("ground")) {
        return 0.5;
      }
      else {
        return 1;
      }
    }

    //ground super effective/not very effective chart
    if (typing.equals("ground")) {
      if (s.getType().equals("water") || s.getType().equals("grass") || s.getType().equals("ice")) {
        return 2;
      }
      else if (s.getType().equals("rock") || s.getType().equals("poison")) {
        return 0.5;
      }
      else if (s.getType().equals("electric")) {
        return 0;
      }
      else {
        return 1;
      }
    }

    //ghost super effective/not very effective chart
    if (typing.equals("ghost")) {
      if (s.getType().equals("ghost") || s.getType().equals("dark")) {
        return 2;
      }
      else if (s.getType().equals("poison") || s.getType().equals("bug")) {
        return 0.5;
      }
      else if (s.getType().equals("normal") || s.getType().equals("fighting")) {
        return 0;
      }
      else {
        return 1;
      }
    }

    //poison super effective/not very effective chart
    if (typing.equals("poison")) {
      if (s.getType().equals("ground") || s.getType().equals("psychic")) {
        return 2;
      }
      else if (s.getType().equals("poison") || s.getType().equals("bug") || s.getType().equals("fighting") || s.getType().equals("fairy") || s.getType().equals("grass")) {
        return 0.5;
      }
      else {
        return 1;
      }
    }

    //psychic super effective/not very effective chart
    if (typing.equals("psychic")) {
      if (s.getType().equals("bug") || s.getType().equals("ghost") || s.getType().equals("dark")) {
        return 2;
      }
      else if (s.getType().equals("psychic") || s.getType().equals("fighting")) {
        return 0.5;
      }
      else {
        return 1;
      }
    }

    //fairy super effective/not very effective chart
    if (typing.equals("fairy")) {
      if (s.getType().equals("steel") || s.getType().equals("poison")) {
        return 2;
      }
      else if (s.getType().equals("fighting") || s.getType().equals("bug") || s.getType().equals("dark")) {
        return 0.5;
      }
      else if (s.getType().equals("dragon")) {
        return 0;
      }
      else {
        return 1;
      }
    }
    return 1;
  }
}