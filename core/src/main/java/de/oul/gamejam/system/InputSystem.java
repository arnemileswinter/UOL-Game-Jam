package de.oul.gamejam.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import de.oul.gamejam.View;
import de.oul.gamejam.component.*;


public class InputSystem extends IteratingSystem implements InputProcessor {

    private final ComponentMapper<ShootingComponent> shootingM = ComponentMapper.getFor(ShootingComponent.class);

    ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    public InputSystem() {
        super(Family.all(PositionComponent.class, PlayerComponent.class).get());
        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        Vector2 position = pm.get(entity).vector;
        Vector2 velocity = vm.get(entity).vector;


        float speed = vm.get(entity).speed;
        float diagonalSpeed = speed;
        if(velocity.x != 0 && velocity.y !=0  ){
            diagonalSpeed = diagonalSpeed /2;
        }
        position.x += velocity.x*diagonalSpeed;
        position.y += velocity.y*diagonalSpeed;

        ShootingComponent shooting = shootingM.get(entity);
        shooting.isShooting = Gdx.input.isKeyPressed(Input.Keys.ENTER);
    }

    @Override
    public boolean keyDown(int keycode) {
        for(Entity player: getEngine().getEntitiesFor(Family.all(PlayerComponent.class, VelocityComponent.class).get())){
            Vector2 velocity = player.getComponent(VelocityComponent.class).vector;
            ViewComponent view = player.getComponent(ViewComponent.class);
            if(Input.Keys.W == keycode){
                velocity.y += 1;
                view.changeView(View.Up);
            }else if(Input.Keys.D == keycode){
                velocity.x += 1;
                view.changeView(View.Right);
            }else if(Input.Keys.S == keycode){
                velocity.y += -1;
                view.changeView(View.Down);
            }else if(Input.Keys.A == keycode){
                velocity.x += -1;
                view.changeView(View.Left);
            }else if(Input.Keys.TAB == keycode){
                Entity entity = null;
                for(Entity Vplayer:getEngine().getEntitiesFor(Family.all(FocusedComponent.class).get())){
                    entity = Vplayer;
                }
                getEngine().getSystem(CameraFocusPlayerSystem.class).changePlayer(entity);

            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for(Entity player: getEngine().getEntitiesFor(Family.all(PlayerComponent.class, VelocityComponent.class).get())){
            Vector2 velocity = player.getComponent(VelocityComponent.class).vector;
            ViewComponent view = player.getComponent(ViewComponent.class);
            if(Input.Keys.W == keycode){
                velocity.y = 0;
                view.changeView(View.Up);
            }else if(Input.Keys.D == keycode){
                velocity.x = 0;
                view.changeView(View.Right);
            }else if(Input.Keys.S == keycode){
                velocity.y = 0;
                view.changeView(View.Down);
            }else if(Input.Keys.A == keycode){
                velocity.x = 0;
                view.changeView(View.Left);
            }
        }
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

}
