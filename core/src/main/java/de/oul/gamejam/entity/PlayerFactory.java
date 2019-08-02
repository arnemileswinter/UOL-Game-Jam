package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.TextureComponent;

/**
 * Creates a player entity, adds its components and puts it into the engine.
 */
public class PlayerFactory {
  /** The pool to create entities and components with. */
  private final PooledEngine engine;
  /** The texture to apply to the player. */
  private final TextureRegion textureRegion;

  /**
   * @param engine The pool to create components with.
   */
  public PlayerFactory(PooledEngine engine) {
    this.engine = engine;

    textureRegion = new TextureRegion(new Texture(Gdx.files.internal("player.png")));
  }

  /**
   * Creates a player and adds it to the player.
   * @param x The player's x-position.
   * @param y The player's y-position.
   * @return The created player entity.
   */
  public Entity createPlayer(float x, float y) {
    Entity player = engine.createEntity();
    // Give the player a position.
    PositionComponent position = engine.createComponent(PositionComponent.class);
    position.vector.set(x,y);
    player.add(position);
    // Give the player a texture.
    TextureComponent texture = engine.createComponent(TextureComponent.class);
    texture.textureRegion = textureRegion;
    player.add(texture);

    // Add the player to the engine.
    engine.addEntity(player);
    return player;
  };
}
