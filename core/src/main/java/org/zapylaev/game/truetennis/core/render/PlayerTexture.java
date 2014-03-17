package org.zapylaev.game.truetennis.core.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.zapylaev.game.truetennis.core.Assets;
import org.zapylaev.game.truetennis.core.domain.Player;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
class PlayerTexture implements ITexture<Player> {

    private Texture mTexture;

    public PlayerTexture() {
        mTexture = Assets.getInstance().player;
    }

    @Override
    public void draw(SpriteBatch batch, Player domainObj) {
        float x = domainObj.getPosition().x;
        float y = domainObj.getPosition().y;
        float width = domainObj.getSize().x;
        float height = domainObj.getSize().y;
        batch.draw(mTexture, x - width / 2, y - height / 2, width, height);
    }
}
