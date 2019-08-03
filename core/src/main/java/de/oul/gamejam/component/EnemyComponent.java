package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class EnemyComponent implements Component, Pool.Poolable {
    public float directionCheck = 1f;
    public float lastCheck = 0f;

    @Override
    public void reset(){
    }
}
