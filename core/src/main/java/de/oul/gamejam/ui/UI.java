package de.oul.gamejam.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;

public class UI extends Table {
  public UI(HealthBar healthBar) {
    row();
    Table healthTable = new Table();
    healthTable.add(new VisLabel("Health")).left();
    healthTable.row();
    healthTable.add(healthBar);
    add(healthTable).width(Gdx.graphics.getWidth()/3).height(Gdx.graphics.getWidth()/8).grow().left().top();
  }
}
