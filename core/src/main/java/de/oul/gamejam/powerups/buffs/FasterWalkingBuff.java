package de.oul.gamejam.powerups.buffs;

import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.VelocityComponent;
import de.oul.gamejam.powerups.BuffStrategy;

public class FasterWalkingBuff implements BuffStrategy {
  /**
   * @return The title of the buff to be displayed.
   */
  @Override
  public String name(){
    return "Faster movement!";
  }

  @Override
  public void buff(Entity player){
    VelocityComponent velocityComponent = player.getComponent(VelocityComponent.class);
    velocityComponent.speed += 0.1f * velocityComponent.speed;
  }
}
