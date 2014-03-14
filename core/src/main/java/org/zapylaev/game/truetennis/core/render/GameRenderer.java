package org.zapylaev.game.truetennis.core.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.zapylaev.game.truetennis.core.IRenderer;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GameRenderer implements IRenderer {
    private final OrthographicCamera mCamera;
    private final SpriteBatch mMainBatch;

    public GameRenderer(OrthographicCamera camera) {
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
    public void updateModelState(String modelState) {
        System.out.println("upd state: " + modelState);
    }

    @Override
    public void dispose() {
        mMainBatch.dispose();
    }
}
