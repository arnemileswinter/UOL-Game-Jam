package de.oul.gamejam.powerups.buffs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.entity.PlayerFactory;
import de.oul.gamejam.powerups.BuffStrategy;

public class HealBuff implements BuffStrategy {
  private final ComponentMapper<HealthComponent> healthM = ComponentMapper.getFor(HealthComponent.class);

  /**
   * @return The title of the buff to be displayed.
   */
  @Override
  public String name(){
    return "Full health";
  }

  @Override
  public void buff(Entity player){
    HealthComponent health = healthM.get(player);
    health.current = health.max;
  }
}
