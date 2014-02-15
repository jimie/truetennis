package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.physics.box2d.World;
import com.sun.media.jfxmediaimpl.MediaDisposer;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public interface Controller extends MediaDisposer.Disposable {
    void update();

    World getWorld();
}
