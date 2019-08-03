package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.ui.HealthBar;

public class HealthBarSystem extends IteratingSystem {
  private final HealthBar healthBar;

  public HealthBarSystem(HealthBar playerHealthBar){
    super(Family.all(HealthComponent.class).get());
    this.healthBar = playerHealthBar;
  }

  /**
   * This method is called on every entity on every update call of the EntitySystem. Override this to implement your
   * system's specific processing.
   *
   * @param entity    The current Entity being processed
   * @param deltaTime The delta time between the last and current frame
   */
  @Override
  protected void processEntity(Entity entity, float deltaTime){
    HealthComponent healthComponent = entity.getComponent(HealthComponent.class);

    // align UI with player health
    if(entity.getComponent(PlayerComponent.class) != null) {
      healthBar.setHealth(healthComponent.current, healthComponent.max);
      return;
    }

    // align all other entity health bars.
    healthComponent.healthBar.setRange(0, healthComponent.max);
    healthComponent.healthBar.setValue(healthComponent.current);
  }
}
