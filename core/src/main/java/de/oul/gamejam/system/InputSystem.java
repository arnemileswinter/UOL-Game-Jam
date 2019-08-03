package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import de.oul.gamejam.View;
import de.oul.gamejam.component.*;


public class InputSystem extends IteratingSystem implements InputProcessor {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

    boolean move = false;

    public InputSystem() {
        super(Family.all(PositionComponent.class, PlayerComponent.class).get());
        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        Vector2 position = entity.getComponent(PositionComponent.class).vector;
        Vector2 velocity = entity.getComponent(VelocityComponent.class).vector;


        float speed = entity.getComponent(VelocityComponent.class).speed;
        position.x += velocity.x*speed;
        position.y += velocity.y*speed;



    }

    @Override
    public boolean keyDown(int keycode) {
        Entity player = null;
        for(Entity entity: getEngine().getEntitiesFor(Family.all(PlayerComponent.class, VelocityComponent.class).get())){
            player = entity;
        }
        Vector2 velocity = player.getComponent(VelocityComponent.class).vector;
        ViewComponent view = player.getComponent(ViewComponent.class);
        if(Input.Keys.W == keycode){
            velocity.y = 1;
            velocity.x = 0;
            view.changeView(View.Up);
        }else if(Input.Keys.D == keycode){
            velocity.y = 0;
            velocity.x = 1;
            view.changeView(View.Right);
        }else if(Input.Keys.S == keycode){
            velocity.y = -1;
            velocity.x = 0;
            view.changeView(View.Down);
        }else if(Input.Keys.A == keycode){
            velocity.y = 0;
            velocity.x = -1;
            view.changeView(View.Left);
        }

        move = true;
        move(player);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Entity player = null;
        for(Entity entity: getEngine().getEntitiesFor(Family.all(PlayerComponent.class, VelocityComponent.class).get())){
            player = entity;
        }
        Vector2 velocity = player.getComponent(VelocityComponent.class).vector;
        velocity.x =0;
        velocity.y =0;
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

    private void move(Entity entity){

    }
}
