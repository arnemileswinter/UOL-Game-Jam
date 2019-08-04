package de.oul.gamejam.system;


import com.badlogic.ashley.core.EntitySystem;
import de.oul.gamejam.entity.SpawnInterface;
import de.oul.gamejam.world.LevelFactory;

import java.util.Random;

public class SpawnSystem extends EntitySystem {

  private final Random         random = new Random();
  private final LevelFactory   levelFactory;
  private final SpawnInterface enemyFactory;
  private final int levelCount;

  public SpawnSystem(LevelFactory levelFactory, SpawnInterface enemyFactory, int levelCount){
    this.levelFactory = levelFactory;
    this.enemyFactory = enemyFactory;
    this.levelCount = levelCount;
    placeEnemysInWorld();
  }

  public void placeEnemysInWorld(){
    int enemyCounter = 0;
    while (enemyCounter < 30 * levelCount) {
      int xRoom = random.nextInt(10);
      int yRoom = random.nextInt(10);
      levelFactory.rooms[xRoom][yRoom].createEntity(enemyFactory, 0, 0);
      enemyCounter++;
    }

  }
}
