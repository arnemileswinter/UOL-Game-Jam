package de.oul.gamejam.powerups.buffs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.entity.PlayerFactory;
import de.oul.gamejam.powerups.BuffStrategy;

public class MoreLifeBuff implements BuffStrategy {
  private final ComponentMapper<HealthComponent> healthM = ComponentMapper.getFor(HealthComponent.class);

  /**
   * @return The title of the buff to be displayed.
   */
  @Override
  public String name(){
    return "More max life!";
  }

  @Override
  public void buff(Entity player){
    HealthComponent health = healthM.get(player);
    float currentRatio = health.current / health.max;

    health.max += health.max * 0.2;
    health.current = currentRatio * health.max;
  }
}
