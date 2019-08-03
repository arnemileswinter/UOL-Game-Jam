package de.oul.gamejam.ui;

import com.kotcrab.vis.ui.widget.VisLabel;
import de.oul.gamejam.Scoreboard;

public class ScoreLabel extends VisLabel {
  private final Scoreboard scoreboard;

  public ScoreLabel(Scoreboard scoreboard) {
    this.scoreboard = scoreboard;
  }

  @Override
  public void act(float delta) {
    this.setText("Score: " + this.scoreboard.getKilledEnemies());
  }
}
