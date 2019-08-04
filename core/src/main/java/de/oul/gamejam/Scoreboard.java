package de.oul.gamejam;

public class Scoreboard {
  private int killedEnemies;

  public int getKilledEnemies(){
    return killedEnemies;
  }

  public void addKilledEnemies(){
    this.killedEnemies++;
  }

  public void gotGoal(){
    killedEnemies+= 100;
  }
}
