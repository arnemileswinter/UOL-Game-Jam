package de.oul.gamejam.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.TextureComponent;


public class MapTileFactory {

    private final PooledEngine engine;
    private final TextureRegion textureRegion;

    public MapTileFactory(PooledEngine engine){
        this.engine = engine;
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("Wall.png")));
    }

    public Entity createWand(float x, float y){
        Entity title = engine.createEntity();
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.vector.set(x,y);
        title.add(position);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.textureRegion = textureRegion;
        title.add(texture);
        engine.addEntity(title);
        return title;
    }
}
