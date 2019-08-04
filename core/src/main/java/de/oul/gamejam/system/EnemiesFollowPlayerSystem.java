package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.*;

import java.util.Random;

public class EnemiesFollowPlayerSystem extends IteratingSystem {
  ComponentMapper<PositionComponent> pm     = ComponentMapper.getFor(PositionComponent.class);
  ComponentMapper<VelocityComponent> vm     = ComponentMapper.getFor(VelocityComponent.class);
  Random                             random = new Random();
  private Entity player;


  public EnemiesFollowPlayerSystem(){
    super(Family
                  .all(EnemyComponent.class,
                       VelocityComponent.class,
                       PositionComponent.class,
                       FollowPlayerComponent.class)
                  .get());
  }

  @Override
  public void update(float delta){
    for (Entity entity : getEngine().getEntitiesFor(Family.all(FocusedComponent.class, PlayerComponent.class).get())) {
      player = entity;
    }
    if (player != null) {
      super.update(delta);
    }
  }

  @Override
  protected void processEntity(Entity enemy, float v){
    PositionComponent enemyPosition  = pm.get(enemy);
    PositionComponent playerPosition = pm.get(player);
    VelocityComponent enemyVelocity  = vm.get(enemy);

    enemyVelocity.vector.set(playerPosition.vector).sub(enemyPosition.vector).setLength(enemyVelocity.speed * 10);
  }
}
