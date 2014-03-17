package org.zapylaev.game.truetennis.core.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.zapylaev.game.truetennis.core.IRenderer;
import org.zapylaev.game.truetennis.core.domain.Ball;
import org.zapylaev.game.truetennis.core.domain.Field;
import org.zapylaev.game.truetennis.core.domain.Player;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GameRenderer implements IRenderer {
    private final OrthographicCamera mCamera;
    private final SpriteBatch mMainBatch;

    private final HUD mHUD;
    private Field mField;
    private final ITexture<Player> mPlayerTexture;
    private final ITexture<Ball> mBallTexture;

    public GameRenderer(OrthographicCamera camera, OrthographicCamera hudCamera) {
        mCamera = camera;
        mMainBatch = new SpriteBatch();
        mPlayerTexture = new PlayerTexture();
        mBallTexture = new BallTexture();
        mHUD = new HUD(hudCamera);
    }

    @Override
    public void render() {
        if (mField == null) return;
        mMainBatch.setProjectionMatrix(mCamera.combined);
        mMainBatch.begin();
        mPlayerTexture.draw(mMainBatch, mField.getLeftPlayer());
        mPlayerTexture.draw(mMainBatch, mField.getRightPlayer());
        mBallTexture.draw(mMainBatch, mField.getBall());
        mMainBatch.end();

        mHUD.render(mMainBatch, mField);
    }

    @Override
    public void updateModel(Field model) {
        mField = model;
    }

    @Override
    public void dispose() {
        mMainBatch.dispose();
    }
}
