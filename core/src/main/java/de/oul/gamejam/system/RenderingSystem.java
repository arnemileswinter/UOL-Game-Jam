package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.TextureComponent;

/**
 * Renders entities with a texture and a position.
 */
public class RenderingSystem extends IteratingSystem {
  private final SpriteBatch spriteBatch;
  private final OrthographicCamera camera;

  /**
   * Instantiates a system that will iterate over the entities described by the Family.
   */
  public RenderingSystem(){
    super(Family.all(PositionComponent.class, TextureComponent.class).get());
    spriteBatch = new SpriteBatch();
    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }

  /**
   * Because a call to spriteBatch.begin() and .end() methods are heavy,
   * we ensure they are called before iteration of entities.
   */
  @Override
  public void update(float delta) {
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
    TextureComponent texture = entity.getComponent(TextureComponent.class);
    PositionComponent position = entity.getComponent(PositionComponent.class);

    spriteBatch.draw(texture.textureRegion, position.vector.x, position.vector.y);
  }
}
