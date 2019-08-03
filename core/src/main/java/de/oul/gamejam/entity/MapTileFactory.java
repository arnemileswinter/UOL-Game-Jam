package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import de.oul.gamejam.component.PhysicsComponent;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.TextureComponent;


public class MapTileFactory {

    private final PooledEngine engine;
    private final TextureRegion textureRegion;
    private final World world;

    public MapTileFactory(PooledEngine engine, World world){
        this.engine = engine;
        this.world = world;
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("Wall.png")));
    }

    public Entity createWall(float x, float y){
        Entity tile = engine.createEntity();
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.vector.set(x,y);
        tile.add(position);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.textureRegion = textureRegion;
        tile.add(texture);

        PhysicsComponent physicsComponent = engine.createComponent(PhysicsComponent.class);
        physicsComponent.body = createWallPhysics(x, y);
        tile.add(physicsComponent);

        engine.addEntity(tile);
        return tile;
    }

    /**
     * Adds a physics body to the wall tile.
     * @param x The x-coordinate of the wall
     * @param y The y-coordinate of the wall.
     * @return The physics body.
     */
    private Body createWallPhysics(float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x,y);
        Body body = world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f,0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        body.createFixture(fixtureDef);

        polygonShape.dispose();

        return body;
    }
}
