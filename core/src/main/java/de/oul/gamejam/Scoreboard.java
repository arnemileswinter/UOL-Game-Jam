package de.oul.gamejam;

public class Scoreboard {
  private int killedEnemies;
  private int levelCount =1;

  public int getKilledEnemies(){
    return killedEnemies;
  }

  public void addKilledEnemies(){
    this.killedEnemies++;
  }

  public void gotGoal(){
    killedEnemies+= 100;
  }

  public int getLevelCount() {
    return levelCount;
  }

  public void addLevel(){
    levelCount++;
  }
}
