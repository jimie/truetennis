package org.zapylaev.game.truetennis.core.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.zapylaev.game.truetennis.core.Assets;
import org.zapylaev.game.truetennis.core.domain.Ball;

public class BallTexture implements ITexture<Ball> {
    private final Texture mTexture;

    public BallTexture() {
        mTexture = Assets.getInstance().ball;
    }

    @Override
    public void draw(SpriteBatch batch, Ball domainObj) {
        float x = domainObj.getPosition().x;
        float y = domainObj.getPosition().y;
        float size = domainObj.getRadius();
        batch.draw(mTexture, x - size, y - size, size * 2, size * 2);
    }
}
