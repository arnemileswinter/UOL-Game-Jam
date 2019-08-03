package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.kotcrab.vis.ui.widget.VisProgressBar;

public class HealthComponent implements Component, Pool.Poolable {
  public VisProgressBar healthBar = new VisProgressBar(0, 100, 0.5f, false);

  public float current = 100;
  public float max = 100;

  /** Resets the object for reuse. Object references should be nulled and fields may be set to default values. */
  @Override
  public void reset(){
    current = 100;
    max = 100;

    healthBar.setValue(100);
    healthBar.setRange(0, 100);
    healthBar.setStepSize(0.5f);
  }
}
