package de.oul.gamejam.system;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import de.oul.gamejam.component.EnemyComponent;
import de.oul.gamejam.entity.EnemyFactory;

import java.util.Random;

public class SpawnSystem extends IteratingSystem {

  private final EnemyFactory enemyFactory;

  private final Random random = new Random();

  public SpawnSystem(EnemyFactory enemyFactory){
    super(Family.all(EnemyComponent.class).get());
    this.enemyFactory = enemyFactory;
    placeEnemysInWorld();
  }

  public void placeEnemysInWorld(){
    int enemyCounter = 0;
    while (enemyCounter < 30) {
      int xCoordinate = random.nextInt(100);
      int yCoordinate = random.nextInt(100);
      enemyFactory.createEnemy(xCoordinate, yCoordinate);
      enemyCounter++;
    }

  }

  @Override
  protected void processEntity(Entity entity, float v){

  }
}
