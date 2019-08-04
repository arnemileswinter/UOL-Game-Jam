package de.oul.gamejam.ui;

import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisProgressBar;

public class HealthBar extends VisProgressBar {
  private final Array<HealthChangedListener> listeners;

  public HealthBar(){
    super(0, 100, 0.5f, false);
    this.setAnimateDuration(1f);
    this.listeners = new Array<HealthChangedListener>();
  }

  public void setHealth(float current, float max){
    this.setRange(0, max);
    this.setValue(current);

    for (HealthChangedListener listener : listeners) {
      listener.healthChanged(current, max);
    }
  }

  public void addListener(HealthChangedListener listener){
    listeners.add(listener);
  }

  public interface HealthChangedListener {
    void healthChanged(float current, float max);
  }
}
