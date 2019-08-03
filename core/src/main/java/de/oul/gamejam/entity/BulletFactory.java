package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import de.oul.gamejam.component.*;

public class BulletFactory {
  private final PooledEngine  pooledEngine;
  private final TextureRegion textureRegion;
  private final World         world;
  private final BodyDef bodyDef;
  private final FixtureDef fixtureDef;
  private final CircleShape circleShape;

  public BulletFactory(PooledEngine pooledEngine, World world){
    this.pooledEngine = pooledEngine;
    this.world = world;
    this.textureRegion = new TextureRegion(new Texture(Gdx.files.internal("bullet.png")));
    bodyDef = new BodyDef();
    fixtureDef  = new FixtureDef();
    circleShape = new CircleShape();
  }

  public Entity shootBullet(Entity owner, float damage, Vector2 position, float bulletSpeed, float angle){
    Entity bullet = pooledEngine.createEntity();

    BulletComponent bulletComponent = pooledEngine.createComponent(BulletComponent.class);
    bulletComponent.owner = owner;
    bullet.add(bulletComponent);

    PositionComponent positionComponent = pooledEngine.createComponent(PositionComponent.class);
    positionComponent.vector.set(position.x, position.y);
    bullet.add(positionComponent);

    VelocityComponent velocity = pooledEngine.createComponent(VelocityComponent.class);
    velocity.vector.set(bulletSpeed, 0).setAngle(angle);
    bullet.add(velocity);

    TextureComponent texture = pooledEngine.createComponent(TextureComponent.class);
    texture.textureRegion = textureRegion;
    bullet.add(texture);

    PhysicsComponent physicsComponent = pooledEngine.createComponent(PhysicsComponent.class);
    physicsComponent.body = createBulletBody(position.x, position.y);
    physicsComponent.body.setUserData(bullet);
    bullet.add(physicsComponent);

    pooledEngine.addEntity(bullet);
    return bullet;
  }

  private Body createBulletBody(float x, float y){
    bodyDef.position.set(x, y);
    bodyDef.type = BodyDef.BodyType.DynamicBody;

    Body body = world.createBody(bodyDef);

    circleShape.setRadius(0.25f);
    fixtureDef.shape = circleShape;

    body.createFixture(fixtureDef);

    return body;
  }
}
