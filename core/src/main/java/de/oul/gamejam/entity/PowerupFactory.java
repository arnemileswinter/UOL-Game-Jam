package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import de.oul.gamejam.component.PhysicsComponent;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.PowerUpComponent;
import de.oul.gamejam.component.TextureComponent;
import de.oul.gamejam.powerups.PowerUpEffectProvider;

public class PowerupFactory {
  private final PooledEngine          engine;
  private final TextureRegion         texture;
  private final BodyDef               bodyDef;
  private final FixtureDef            fixtureDef;
  private final World                 world;
  private final PowerUpEffectProvider powerUpEffectProvider;

  public PowerupFactory(PooledEngine engine, World world, PowerUpEffectProvider powerUpEffectProvider){
    this.engine = engine;
    this.powerUpEffectProvider = powerUpEffectProvider;
    this.texture = new TextureRegion(new Texture(Gdx.files.internal("Item.png")));

    this.world = world;

    this.bodyDef = new BodyDef();
    this.fixtureDef = new FixtureDef();
  }

  public Entity create(float x, float y){
    Entity powerup = engine.createEntity();

    // give it a texture
    TextureComponent tex = engine.createComponent(TextureComponent.class);
    tex.textureRegion = texture;
    powerup.add(tex);

    // give it a position
    PositionComponent position = engine.createComponent(PositionComponent.class);
    position.vector.set(x, y);
    powerup.add(position);

    // make it collectible
    PhysicsComponent physics = engine.createComponent(PhysicsComponent.class);
    physics.body = createItemPhysics(x, y);
    physics.body.setUserData(powerup);
    powerup.add(physics);

    // make it do something on collecting
    PowerUpComponent powerUpComponent = engine.createComponent(PowerUpComponent.class);
    powerUpComponent.buffStrategy = powerUpEffectProvider.getBuffStrategy();
    powerUpComponent.nerfStrategy = powerUpEffectProvider.getNerfStrategy();
    powerup.add(powerUpComponent);

    engine.addEntity(powerup);

    return powerup;
  }

  private Body createItemPhysics(float x, float y){
    bodyDef.type = BodyDef.BodyType.StaticBody;
    bodyDef.position.set(x, y);

    CircleShape shape = new CircleShape();
    shape.setRadius(0.5f);
    fixtureDef.shape = shape;

    Body body = world.createBody(bodyDef);
    body.createFixture(fixtureDef);
    shape.dispose();

    return body;
  }
}
