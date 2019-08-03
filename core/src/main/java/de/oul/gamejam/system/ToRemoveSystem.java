package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import de.oul.gamejam.component.ToRemoveComponent;

public class ToRemoveSystem extends IteratingSystem {
  /**
   * Instantiates a system that will iterate over the entities described by the Family.
   */
  public ToRemoveSystem(){
    super(Family.all(ToRemoveComponent.class).get());
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
    getEngine().removeEntity(entity);
  }
}
