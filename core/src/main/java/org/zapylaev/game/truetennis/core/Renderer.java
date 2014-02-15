package org.zapylaev.game.truetennis.core;

import com.sun.media.jfxmediaimpl.MediaDisposer;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public interface Renderer extends MediaDisposer.Disposable{
    void render();
}
