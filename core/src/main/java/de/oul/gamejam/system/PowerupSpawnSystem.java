package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.entity.PowerupFactory;

import java.util.Random;

public class PowerupSpawnSystem extends IteratingSystem {
  private final ComponentMapper<PositionComponent> posm = ComponentMapper.getFor(PositionComponent.class);


  private final PowerupFactory factory;
  private final Random         random;
  private       float          elapsedTime;
  /** Set on a random */
  /** between 0 and 1 */
  private       float          targetTime;

  /**
   * Spawn powerups around the player
   *
   * @param factory
   */
  public PowerupSpawnSystem(PowerupFactory factory){
    super(Family.all(PlayerComponent.class, PositionComponent.class).get());
    this.factory = factory;
    this.random = new Random();
    this.targetTime = random.nextFloat() * random.nextInt(10);
  }

  @Override
  protected void processEntity(Entity entity, float v){
    if (elapsedTime <= targetTime) {
      elapsedTime += v;
      return;
    }

    elapsedTime = 0;
    targetTime = random.nextFloat() * (random.nextInt(10) + 5);

    Vector2 playerPosition = posm.get(entity).vector;

    factory.create(playerPosition.x + random.nextInt(5), playerPosition.y + random.nextInt(5));
  }
}
