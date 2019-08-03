package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.TextureComponent;

/**
 * Renders entities with a texture and a position.
 */
public class VisibilitySystem extends IteratingSystem {
  private final ComponentMapper<PositionComponent> pm                = ComponentMapper.getFor(PositionComponent.class);
  private final ComponentMapper<TextureComponent>  tm                = ComponentMapper.getFor(TextureComponent.class);
  private final float                              VISIBILITY_OFFSET = 2;
  private final Camera                             camera;

  public VisibilitySystem(Camera camera){
    super(Family.all(PositionComponent.class, TextureComponent.class).get());
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
    Vector2          position = pm.get(entity).vector;
    TextureComponent t        = tm.get(entity);
    if (!(position.y < (camera.position.y - (0.5f * camera.viewportHeight) - VISIBILITY_OFFSET) || position.y > camera.position.y + (0.5f * camera.viewportHeight) + VISIBILITY_OFFSET)) {
      t.isVisible =
              !(position.x < (camera.position.x - (0.5f * camera.viewportWidth) - VISIBILITY_OFFSET) || position.x > camera.position.x + (0.5f * camera.viewportWidth) + VISIBILITY_OFFSET);
    } else {
      t.isVisible = false;
    }
  }
}
