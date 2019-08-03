package de.oul.gamejam.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class SwitchAssetComponent implements Component, Pool.Poolable {
    public int value =0;
    public int max=0;
    public String assetString;

    @Override
    public void reset() {

    }

    public void switchAsset(){
        value = (value+1)%max;
    }
}
