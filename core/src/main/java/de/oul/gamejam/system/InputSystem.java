package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import de.oul.gamejam.component.PhysicsComponent;
import de.oul.gamejam.component.PlayerComponment;
import de.oul.gamejam.component.PositionComponent;


public class InputSystem extends IteratingSystem implements InputProcessor {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

    boolean move = false;

    public InputSystem() {
        super(Family.all(PositionComponent.class, PlayerComponment.class).get());
        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void processEntity(Entity entity, float v) {

    }

    @Override
    public boolean keyDown(int keycode) {
        Entity player;
        for(Entity entity: getEngine().getEntitiesFor(Family.all(PlayerComponment.class).get())){
            player = entity;
        }
        //player.getComponent(PositionComponent.class)
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void move(Entity entity, Vector2 velocity){
        while(move){

        }
    }
}
