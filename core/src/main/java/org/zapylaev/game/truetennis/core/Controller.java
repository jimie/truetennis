package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public interface Controller extends Disposable {
    void update();

    World getWorld();
}
