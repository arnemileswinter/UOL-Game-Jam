package de.oul.gamejam.powerups.buffs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.ShootingComponent;
import de.oul.gamejam.powerups.BuffStrategy;

public class FasterShootingBuff implements BuffStrategy {
  private final ComponentMapper<ShootingComponent> shootingM = ComponentMapper.getFor(ShootingComponent.class);

  /**
   * @return The title of the buff to be displayed.
   */
  @Override
  public String name(){
    return "Faster shooting!";
  }

  @Override
  public void buff(Entity player){
    ShootingComponent shootingComponent = shootingM.get(player);
    shootingComponent.shootSpeed -= 0.1 * shootingComponent.shootSpeed;
  }
}
