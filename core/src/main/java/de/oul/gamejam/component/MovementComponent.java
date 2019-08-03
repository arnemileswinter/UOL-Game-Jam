package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;


public class MovementComponent implements Component {
    public Vector2 vector;

    public MovementComponent (Vector2 vector) {
        this.vector = vector;
    }
}

