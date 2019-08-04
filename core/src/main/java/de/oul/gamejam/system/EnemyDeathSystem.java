package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.Scoreboard;
import de.oul.gamejam.component.EnemyComponent;
import de.oul.gamejam.component.FocusedComponent;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.component.ToRemoveComponent;

public class EnemyDeathSystem extends IteratingSystem {
  private final Scoreboard scoreboard;

  public EnemyDeathSystem(Scoreboard scoreboard){
    super(Family.all(HealthComponent.class).exclude(ToRemoveComponent.class, FocusedComponent.class).get());
    this.scoreboard = scoreboard;
  }

  @Override
  protected void processEntity(Entity entity, float v){
    HealthComponent healthComponent = entity.getComponent(HealthComponent.class);
    if (healthComponent.current < 1f) {
      scoreboard.addKilledEnemies();
      entity.add(getEngine().createComponent(ToRemoveComponent.class));
    }
  }
}
