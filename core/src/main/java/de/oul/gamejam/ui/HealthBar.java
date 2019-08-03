package de.oul.gamejam.ui;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisProgressBar;

public class HealthBar extends VisProgressBar {

  public HealthBar(){
    super(0,100,0.5f,false);
    this.setAnimateDuration(1f);
  }

  public void setHealth(float current, float max){
    this.setRange(0, max);
    this.setValue(current);
  }
}
