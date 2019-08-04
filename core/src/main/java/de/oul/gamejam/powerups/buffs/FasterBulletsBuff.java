package de.oul.gamejam.powerups.buffs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.ShootingComponent;
import de.oul.gamejam.powerups.BuffStrategy;

public class FasterBulletsBuff implements BuffStrategy {
  private final ComponentMapper<ShootingComponent> shootingM = ComponentMapper.getFor(ShootingComponent.class);

  /**
   * @return The title of the buff to be displayed.
   */
  @Override
  public String name(){
    return "Faster bullets!";
  }

  @Override
  public void buff(Entity player){
    ShootingComponent shootingComponent = shootingM.get(player);
    shootingComponent.bulletSpeed += 0.1 * shootingComponent.bulletSpeed;
  }
}
