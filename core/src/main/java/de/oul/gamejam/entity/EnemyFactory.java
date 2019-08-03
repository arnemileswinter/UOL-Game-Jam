package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import de.oul.gamejam.component.*;

public class EnemyFactory {
    /** The pool to create entities and components with. */
    private final PooledEngine engine;
    /** The texture to apply to the player. */
    private final TextureRegion textureRegion;
    /** The box2d physics world. */
    private final World world;

    /**
     * @param engine The pool to create components with.
     */
    public EnemyFactory(PooledEngine engine, World world) {
        this.engine = engine;
        this.world = world;

        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("Hero1.png")));
    }
    public Entity createEnemy(float x, float y) {
        Entity enemy = engine.createEntity();

        // Give the enemy a position.
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.vector.set(x,y);
        enemy.add(position);

        // Give the enemy a texture.
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.textureRegion = textureRegion;
        enemy.add(texture);

        // Give the enemy physics.
        PhysicsComponent physics = engine.createComponent(PhysicsComponent.class);
        physics.body = this.getPlayerBody(x,y);
        enemy.add(physics);

        // make it the enemy
        EnemyComponent enemyComponent = engine.createComponent(EnemyComponent.class);
        enemy.add(enemyComponent);

        VelocityComponent velocityComponent = engine.createComponent(VelocityComponent.class);
        enemy.add(velocityComponent);

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

