package de.oul.gamejam.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import de.oul.gamejam.component.SwitchAssetComponent;
import de.oul.gamejam.component.TextureComponent;


public class SwitchAssetSystem extends IteratingSystem {
    private float v = 0;
    private final ObjectMap<String, Texture> textureCache;

    public SwitchAssetSystem() {
        super(Family.all(SwitchAssetComponent.class).get());
        textureCache = new ObjectMap<>();
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        this.v += v;
        if (this.v > 1) {
            SwitchAssetComponent switchAssetComponent = entity.getComponent(SwitchAssetComponent.class);
            switchAssetComponent.switchAsset();
            TextureRegion textureRegion = entity.getComponent(TextureComponent.class).textureRegion;
            String assertString = switchAssetComponent.assetString;
            Texture texture = textureCache.get(assertString);
            if(texture == null) {
                texture = new Texture(Gdx.files.internal(assertString + switchAssetComponent.value + ".png"));
                textureCache.put(assertString, texture);
            }
            textureRegion.setTexture(texture);
            this.v = 0f;
        }
    }
}
