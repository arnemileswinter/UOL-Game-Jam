package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.HealthComponent;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.ui.HealthBar;

public class HealthBarSystem extends IteratingSystem {
  private final HealthBar healthBar;

  public HealthBarSystem(HealthBar healthBar){
    super(Family.all(PlayerComponent.class, HealthComponent.class).get());
    this.healthBar = healthBar;
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
    healthBar.setHealth(healthComponent.current, healthComponent.max);
  }
}
