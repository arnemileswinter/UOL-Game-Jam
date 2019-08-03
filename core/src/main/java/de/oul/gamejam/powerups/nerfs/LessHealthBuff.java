package de.oul.gamejam.powerups.nerfs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.powerups.NerfStrategy;

public class LessHealthBuff implements NerfStrategy {
  ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);

  @Override
  public void nerf(Entity player){
    HealthComponent health = healthMapper.get(player);
    health.max -= health.max * 0.1f;
    if(health.current > health.max) {
      health.current = health.max;
    }
  }
}
