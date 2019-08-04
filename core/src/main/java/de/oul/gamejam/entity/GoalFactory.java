package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.*;
import de.oul.gamejam.component.*;

public class GoalFactory implements SpawnInterface{

    private final PooledEngine engine;
    private final TextureRegion textureRegion;
    private final World         world;

    public GoalFactory(PooledEngine pooledEngine, World world){
        this.engine = pooledEngine;
        this.world = world;
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("GoalEntry1.png")));
    }

    @Override
    public Entity spawn(int x, int y){
        Entity goal = engine.createEntity();

        // Give the player a position.
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.vector.set(x,y);
        goal.add(position);

        // Give the player a texture.
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.textureRegion = textureRegion;
        goal.add(texture);

        SwitchAssetComponent switchAssetComponent = engine.createComponent(SwitchAssetComponent.class);
        switchAssetComponent.assetString="GoalEntry";
        switchAssetComponent.max=2;
        goal.add(switchAssetComponent);

        GoalComponent goalComponent = engine.createComponent(GoalComponent.class);
        goal.add(goalComponent);

        PhysicsComponent physicsComponent = engine.createComponent(PhysicsComponent.class);
        physicsComponent.body = createGoalBody(x,y);
        physicsComponent.body.setUserData(goal);
        goal.add(physicsComponent);

        engine.addEntity(goal);
        return goal;
    }

    private Body createGoalBody(int x, int y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x,y);

        Body body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }
}
