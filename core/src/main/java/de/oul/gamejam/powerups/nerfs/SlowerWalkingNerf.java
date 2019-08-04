package de.oul.gamejam.powerups.nerfs;

import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.DesiredVelocityComponent;
import de.oul.gamejam.powerups.BuffStrategy;
import de.oul.gamejam.powerups.NerfStrategy;

public class SlowerWalkingNerf implements NerfStrategy {
  /**
   * @return The title of the buff to be displayed.
   */
  @Override
  public String name(){
    return "Slower movement!";
  }

  @Override
  public void nerf(Entity player){
    DesiredVelocityComponent desiredVelocityComponent = player.getComponent(DesiredVelocityComponent.class);
    desiredVelocityComponent.speed -= 0.1f * desiredVelocityComponent.speed;
  }
}
