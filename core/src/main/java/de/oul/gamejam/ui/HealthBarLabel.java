package de.oul.gamejam.ui;

import com.kotcrab.vis.ui.widget.VisLabel;

public class HealthBarLabel extends VisLabel implements HealthBar.HealthChangedListener {
  @Override
  public void healthChanged(float current, float max){
    this.setText(Math.floor(current) + " / " + Math.floor(max));
  }
}
