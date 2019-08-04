package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import de.oul.gamejam.View;
import de.oul.gamejam.component.PositionComponent;
import de.oul.gamejam.component.TextureComponent;
import de.oul.gamejam.component.ViewComponent;

public class ViewSystem extends IteratingSystem {
  private final ObjectMap<String, Texture> textureCache = new ObjectMap<>();
  ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);
  ComponentMapper<ViewComponent>    vm = ComponentMapper.getFor(ViewComponent.class);

  public ViewSystem(){
    super(Family.all(PositionComponent.class, ViewComponent.class, TextureComponent.class).get());
  }

  @Override
  protected void processEntity(Entity entity, float v){
    View          view          = vm.get(entity).view;
    TextureRegion textureRegion = tm.get(entity).textureRegion;
    String        assetString   = entity.getComponent(ViewComponent.class).assetString;
    Texture       cached        = null;
    switch (view) {
      case Up:
        cached = textureCache.get(assetString + "Up");
        if (cached == null) {
          System.out.println(assetString + "Up");
          cached = new Texture(Gdx.files.internal(assetString + "Up.png"));
          textureCache.put(assetString + "Up", cached);
        }
        textureRegion.setTexture(cached);
        textureRegion.setRegionHeight(cached.getHeight());
        textureRegion.setRegionWidth(cached.getWidth());
        break;
      case Left:
        cached = textureCache.get(assetString + "Left");
        if (cached == null) {
          System.out.println(assetString + "Left");
          cached = new Texture(Gdx.files.internal(assetString + "Left.png"));
          textureCache.put(assetString + "Left", cached);
        }
        textureRegion.setTexture(cached);
        textureRegion.setRegionHeight(cached.getHeight());
        textureRegion.setRegionWidth(cached.getWidth());
        break;
      case Down:
        cached = textureCache.get(assetString + "Down");
        if (cached == null) {
          System.out.println(assetString + "Down");
          cached = new Texture(Gdx.files.internal(assetString + "Down.png"));
          textureCache.put(assetString + "Down", cached);
        }
        textureRegion.setTexture(cached);
        textureRegion.setRegionHeight(cached.getHeight());
        textureRegion.setRegionWidth(cached.getWidth());
        break;
      case Right:
        cached = textureCache.get(assetString + "Right");
        if (cached == null) {
          System.out.println(assetString + "Right");
          cached = new Texture(Gdx.files.internal(assetString + "Right.png"));
          textureCache.put(assetString + "Right", cached);
        }
        textureRegion.setTexture(cached);
        textureRegion.setRegionHeight(cached.getHeight());
        textureRegion.setRegionWidth(cached.getWidth());
        break;
    }
  }
}
