package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import de.oul.gamejam.component.*;

import java.util.Random;

public class EnemyFactory  implements SpawnInterface {
    /** The pool to create entities and components with. */
    private final PooledEngine  engine;
    /** The box2d physics world. */
    private final World         world;

    private final Random random;

    /**
     * @param engine The pool to create components with.
     */
    public EnemyFactory(PooledEngine engine, World world) {
        this.engine = engine;
        this.world = world;

        random = new Random();
    }

    @Override
    public Entity spawn(int x, int y) {
        Entity enemy = engine.createEntity();

        // Give the enemy a position.
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.vector.set(x,y);
        enemy.add(position);

        // Give the enemy a texture.
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        enemy.add(texture);

        // Give the enemy physics.
        PhysicsComponent physics = engine.createComponent(PhysicsComponent.class);
        physics.body = this.getPlayerBody(x,y);
        physics.body.setUserData(enemy);
        enemy.add(physics);

        // make it the enemy
        EnemyComponent enemyComponent = engine.createComponent(EnemyComponent.class);
        enemy.add(enemyComponent);

        VelocityComponent velocityComponent = engine.createComponent(VelocityComponent.class);
        velocityComponent.speed=0.1f;
        enemy.add(velocityComponent);

        // make it possible to shoot
        ShootingComponent shootingComponent = engine.createComponent(ShootingComponent.class);
        shootingComponent.isShooting=true;
        enemy.add(shootingComponent);

        //give the enemy life
        HealthComponent healthComponent = engine.createComponent(HealthComponent.class);
        enemy.add(healthComponent);

        //add View to Enemy
        ViewComponent viewComponent = engine.createComponent(ViewComponent.class);
        viewComponent.assetString ="Robot";
        enemy.add(viewComponent);

        float r = random.nextFloat();

        if (r < 0.25f) {
            // enemy follows player
            enemy.add(engine.createComponent(FollowPlayerComponent.class));
            viewComponent.assetString = "Shroom";
        } else if(r < 0.5f) {
            enemy.add(engine.createComponent(FollowPlayerComponent.class));
            viewComponent.assetString = "Brain";
            healthComponent.max = 200;
            healthComponent.current = 200;
            shootingComponent.bulletDamage += 30;
        } else {
            enemy.add(engine.createComponent(WalkAroundComponent.class));
        }

        // Add the enemy to the engine.
        engine.addEntity(enemy);
        return enemy;
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

