package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.oul.gamejam.View;
import de.oul.gamejam.component.PlayerComponent;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.TextureComponent;
import de.oul.gamejam.component.ViewComponent;

public class ViewSystem extends IteratingSystem {
    ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);
    ComponentMapper<ViewComponent> vm = ComponentMapper.getFor(ViewComponent.class);

    public ViewSystem() {
        super(Family.all(PositionComponent.class,ViewComponent.class,TextureComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        View view = vm.get(entity).view;
        TextureRegion textureRegion = tm.get(entity).textureRegion;
        String assetString = entity.getComponent(ViewComponent.class).assetString;
        switch (view){
            case Up:
                textureRegion.setTexture(new Texture(Gdx.files.internal(assetString+"Up.png")));
                break;
            case Left:
                textureRegion.setTexture(new Texture(Gdx.files.internal(assetString+"Left.png")));
                break;
            case Down:
                textureRegion.setTexture(new Texture(Gdx.files.internal(assetString+"Down.png")));
                break;
            case Right:
                textureRegion.setTexture(new Texture(Gdx.files.internal(assetString+"Right.png")));
                break;
        }
    }
}
