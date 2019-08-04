package de.oul.gamejam.powerups.nerfs;

import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.VelocityComponent;
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
    VelocityComponent velocityComponent = player.getComponent(VelocityComponent.class);
    velocityComponent.speed -= 0.1f * velocityComponent.speed;
  }
}
