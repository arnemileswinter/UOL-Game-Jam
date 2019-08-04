package de.oul.gamejam.powerups;


import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Array;
import de.oul.gamejam.entity.PlayerFactory;
import de.oul.gamejam.powerups.buffs.*;
import de.oul.gamejam.powerups.nerfs.LessHealthBuff;

public class RandomPowerUpEffectProvider implements PowerUpEffectProvider{

  private Array<BuffStrategy> buffs;
  private Array<NerfStrategy> nerfs;

  public RandomPowerUpEffectProvider(PooledEngine engine, PlayerFactory playerFactory) {
    this.buffs = new Array<>();
    this.nerfs = new Array<>();

    buffs.addAll(
            new FasterShootingBuff(),
            new HealBuff(),
            new MoreBulletDamageBuff(),
            new MoreLifeBuff(),
            new SecondPlayerBuff(playerFactory, engine)
    );
    nerfs.addAll(
            new LessHealthBuff()
    );
  }

  @Override
  public BuffStrategy getBuffStrategy(){
    return buffs.random();
  }

  @Override
  public NerfStrategy getNerfStrategy(){
    return nerfs.random();
  }
}
