package org.zapylaev.game.truetennis.core.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import org.zapylaev.game.truetennis.core.Box2DRenderer;
import org.zapylaev.game.truetennis.core.Constants;
import org.zapylaev.game.truetennis.core.Controller;
import org.zapylaev.game.truetennis.core.Renderer;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GameScreen extends AbstractScreen {
    private Controller mGameController;
    private Renderer mGameRenderer;
    private Renderer mDebugRenderer;
    private OrthographicCamera mCamera;

    @Override
    public void show() {
        super.show();

        mCamera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        mCamera.update();
        mGameController = new GameController(mCamera);
        mGameRenderer = new GameRenderer(mGameController, mCamera);
        mDebugRenderer = new Box2DRenderer(mGameController, mCamera);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        mGameController.update();
        mGameRenderer.render();

        if (Constants.DEBUG) {
            mDebugRenderer.render();
        }
    }
}
