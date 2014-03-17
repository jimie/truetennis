package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.utils.Disposable;
import org.zapylaev.game.truetennis.core.domain.Field;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public interface IRenderer extends Disposable {
    void render();
    void updateModel(Field model);
}
