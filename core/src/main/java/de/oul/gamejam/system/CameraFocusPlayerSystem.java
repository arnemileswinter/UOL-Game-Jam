package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.component.PositionComponent;

/**
 * Focuses a camera on the player.
 */
public class CameraFocusPlayerSystem extends IteratingSystem {
  private final Camera camera;

  /**
   * Instantiates a system that will iterate over the entities described by the Family.
   */
  public CameraFocusPlayerSystem(Camera camera){
    super(Family.all(PlayerComponent.class, PositionComponent.class).get());
    this.camera = camera;
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
    Vector2 playerPosition = entity.getComponent(PositionComponent.class).vector;
    camera.position.set(playerPosition.x, playerPosition.y, 0);
  }
}
