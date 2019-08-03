package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.TextureComponent;

import static de.oul.gamejam.JamGame.PIXELS_PER_METER;

/**
 * Renders entities with a texture and a position.
 */
public class RenderingSystem extends IteratingSystem {
  private final SpriteBatch spriteBatch;
  private final Camera      camera;

  /**
   * Instantiates a system that will iterate over the entities described by the Family.
   */
  public RenderingSystem(Camera camera){
    super(Family.all(PositionComponent.class, TextureComponent.class).get());
    this.camera = camera;
    spriteBatch = new SpriteBatch();

  }

  /**
   * Because a call to spriteBatch.begin() and .end() methods are heavy, we ensure they are called before iteration of
   * entities.
   */
  @Override
  public void update(float delta){
    camera.viewportHeight = Gdx.graphics.getHeight() / 2f / PIXELS_PER_METER;
    camera.viewportWidth = Gdx.graphics.getWidth() / 2f / PIXELS_PER_METER;
    camera.update();
    spriteBatch.begin();
    spriteBatch.setProjectionMatrix(camera.combined);
    super.update(delta);
    spriteBatch.end();
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
    TextureComponent  texture  = entity.getComponent(TextureComponent.class);
    PositionComponent position = entity.getComponent(PositionComponent.class);

    float width  = texture.textureRegion.getRegionWidth();
    float height = texture.textureRegion.getRegionHeight();

    float originX = width / 2f;
    float originY = height / 2f;

    // Draw the sprite with it's origin centered.
    spriteBatch.draw(texture.textureRegion,
               position.vector.x - originX,
               position.vector.y - originY,
               originX,
               originY,
               width,
               height,
               this.getMetersFromPixels(1),
               this.getMetersFromPixels(1), 0);
  }

  /**
   * Helper method to convert physical meters to pixels.
   * @param pixelValue The value to convert to meters.
   * @return The converted value
   */
  private float getMetersFromPixels(float pixelValue) {
    return pixelValue * 1.0f / PIXELS_PER_METER;
  }
}
