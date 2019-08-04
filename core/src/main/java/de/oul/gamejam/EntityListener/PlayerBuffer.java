package de.oul.gamejam.EntityListener;

import com.badlogic.ashley.core.Entity;

import java.util.LinkedList;

public class PlayerBuffer {
    public LinkedList<Entity> list = new LinkedList<>();
    public int counter=0;


    public void addEntity(Entity entity){
        list.add(entity);
    }

    public void removeEntity(Entity entity){
        list.remove(entity);
        int listSize = list.size();
        if(listSize == 0) return;
        counter = (counter) % listSize;
    }

    public Entity getNextPayer(){
        Entity entity = list.get(counter);
        counter = (counter+ 1) % list.size();
        return entity;
    }

}
