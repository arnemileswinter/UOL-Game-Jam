package de.oul.gamejam.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.ashley.core.Component;

/**
 * Identifies an entity of having a texture.
 */
public class TextureComponent implements Component, Pool.Poolable {
  public TextureRegion textureRegion = null;
  public boolean isVisible;


  /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
  @Override
  public void reset(){
    textureRegion = null;
    isVisible = false;
  }
}
