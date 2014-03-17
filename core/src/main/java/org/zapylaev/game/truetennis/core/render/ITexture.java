package org.zapylaev.game.truetennis.core.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
interface ITexture<T> {
    void draw(SpriteBatch batch, T domainObj);
}
