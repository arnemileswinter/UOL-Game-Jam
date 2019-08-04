package de.oul.gamejam.powerups.nerfs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.powerups.NerfStrategy;

public class LessHealthNerf implements NerfStrategy {
  ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);

  /**
   * @return The title of the nerf to be displayed.
   */
  @Override
  public String name(){
    return "Less max health!";
  }

  @Override
  public void nerf(Entity player){
    HealthComponent health = healthMapper.get(player);
    health.max -= health.max * 0.1f;
    if(health.current > health.max) {
      health.current = health.max;
    }
  }
}
