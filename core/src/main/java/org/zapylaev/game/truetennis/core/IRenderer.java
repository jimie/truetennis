package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.utils.Disposable;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public interface IRenderer extends Disposable {
    void render();
    void updateModelState(String modelState);
}
