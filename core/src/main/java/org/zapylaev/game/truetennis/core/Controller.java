package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.physics.box2d.World;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public interface Controller {
    void update();

    World getWorld();
}
