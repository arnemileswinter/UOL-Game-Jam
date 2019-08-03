package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import de.oul.gamejam.View;

public class ViewComponent implements Component, Pool.Poolable {
    public View view = View.Up;
    public String assetString;

    @Override
    public void reset() {
        view = View.Up;
    }

    public void changeView(View view){
        this.view = view;
    }
}
