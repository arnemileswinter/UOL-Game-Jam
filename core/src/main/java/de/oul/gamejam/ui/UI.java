package de.oul.gamejam.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import de.oul.gamejam.Scoreboard;

public class UI extends Table {
  public UI(HealthBar healthBar, Scoreboard scoreboard) {
    row();
    Table healthTable = new Table();
    healthTable.add(new VisLabel("Health")).left();
    healthTable.row();
    healthTable.add(healthBar);
    healthTable.row();
    healthTable.add(new ScoreLabel(scoreboard)).left();
    add(healthTable).width(Gdx.graphics.getWidth()/3).height(Gdx.graphics.getWidth()/8).grow().left().top();
  }
}
