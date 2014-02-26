package org.zapylaev.game.truetennis.core.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.zapylaev.game.truetennis.core.Controller;
import org.zapylaev.game.truetennis.core.Renderer;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GameRenderer implements Renderer {
    private final OrthographicCamera mCamera;
    private SpriteBatch mMainBatch;

    public GameRenderer(Controller controller, OrthographicCamera camera) {
        mCamera = camera;
        mMainBatch = new SpriteBatch();
    }

    @Override
    public void render() {
        mMainBatch.setProjectionMatrix(mCamera.combined);
        mMainBatch.begin();
        mMainBatch.end();
    }

    @Override
    public void dispose() {
        mMainBatch.dispose();
    }
}
