package de.oul.gamejam;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.VisUI;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class JamGame extends Game {
  /** How many pixels make up a meter */
  public static final float PIXELS_PER_METER = 32;

  @Override
  public void create(){
    setUpUI();
    setScreen(new SplashScreen(this));
  }

  private void setUpUI(){
    VisUI.load();

    TextureRegionDrawable defaultSkin = (TextureRegionDrawable) VisUI.getSkin().getDrawable("progressbar-filled");


    Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
    pixmap.setColor(0xFF0000FF);
    pixmap.fill();
    defaultSkin.setRegion(new TextureRegion(new Texture(pixmap)));

    pixmap.dispose();
  }
}
