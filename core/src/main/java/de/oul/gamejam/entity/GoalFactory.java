package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.SwitchAssetComponent;
import de.oul.gamejam.component.TextureComponent;

public class GoalFactory {

    private final PooledEngine engine;
    private final TextureRegion textureRegion;
    private final World         world;

    public GoalFactory(PooledEngine pooledEngine, World world){
        this.engine = pooledEngine;
        this.world = world;
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("GoalEntry1.png")));
    }

    public Entity createGoal(float x, float y){
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

        engine.addEntity(goal);
        return goal;
    }
}
