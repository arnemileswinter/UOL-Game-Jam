package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import de.oul.gamejam.component.*;

/**
 * Creates a player entity, adds its components and puts it into the engine.
 */
public class PlayerFactory {
  /** The pool to create entities and components with. */
  private final PooledEngine engine;
  /** The texture to apply to the player. */
  private final TextureRegion textureRegion;
  /** The box2d physics world. */
  private final World world;

  /**
   * @param engine The pool to create components with.
   */
  public PlayerFactory(PooledEngine engine, World world) {
    this.engine = engine;
    this.world = world;

    textureRegion = new TextureRegion(new Texture(Gdx.files.internal("Hero1.png")));
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

    // Give the player physics.
    PhysicsComponent physics = engine.createComponent(PhysicsComponent.class);
    physics.body = this.getPlayerBody(x,y);
    player.add(physics);

    // make it the player
    PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
    player.add(playerComponent);

    VelocityComponent velocityComponent = engine.createComponent(VelocityComponent.class);
    player.add(velocityComponent);

    // make it possible to shoot
    ShootingComponent shootingComponent = engine.createComponent(ShootingComponent.class);
    player.add(shootingComponent);

    // Add the player to the engine.
    engine.addEntity(player);
    return player;
  }

  /**
   * Creates a physics body for the player.
   * @param x The x-coordinate that the player's body spawns at.
   * @param y The y-coordinate that the player's body spawns at.
   * @return The physics body for the player.
   */
  private Body getPlayerBody(float x, float y){
    BodyDef bodyDef = new BodyDef();
    bodyDef.position.set(x,y);
    bodyDef.type = BodyDef.BodyType.DynamicBody;

    Body body = world.createBody(bodyDef);

    FixtureDef fixtureDef = new FixtureDef();
    CircleShape circleShape = new CircleShape();
    circleShape.setRadius(0.5f);
    fixtureDef.shape = circleShape;

    body.createFixture(fixtureDef);
    circleShape.dispose();
    return body;
  }

}
