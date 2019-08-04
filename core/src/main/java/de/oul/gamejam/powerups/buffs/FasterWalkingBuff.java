package de.oul.gamejam.powerups.buffs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.DesiredVelocityComponent;
import de.oul.gamejam.component.ShootingComponent;
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
    DesiredVelocityComponent desiredVelocityComponent = player.getComponent(DesiredVelocityComponent.class);
    desiredVelocityComponent.speed += 0.1f * desiredVelocityComponent.speed;
  }
}
