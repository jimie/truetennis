package org.zapylaev.game.truetennis.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Json;
import org.zapylaev.game.truetennis.core.Constants;
import org.zapylaev.game.truetennis.core.IRenderer;
import org.zapylaev.game.truetennis.core.domain.Field;
import org.zapylaev.game.truetennis.core.model.IModel;
import org.zapylaev.game.truetennis.core.model.IModelListener;
import org.zapylaev.game.truetennis.core.model.PhysicalModel;
import org.zapylaev.game.truetennis.core.render.GameRenderer;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GameController extends InputAdapter implements Screen, IModelListener {
    enum State {
        IDLE, GAME
    }
    private IModel mModel;
    private IRenderer mGameRenderer;
    private OrthographicCamera mCamera;
    private State mState;
    private Json mJson;

    @Override
    public void show() {
        mModel = new PhysicalModel();
        mCamera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        mCamera.update();
        mGameRenderer = new GameRenderer(mCamera);
        mModel.addModelListener(this);
        Gdx.input.setInputProcessor(this);
        mState = State.IDLE;
        mJson = new Json();
    }

    @Override
    public void render(float delta) {
        mModel.update();
        mGameRenderer.render();
        mModel.debugRender(mCamera);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (mState == State.IDLE) {
            if (keycode == Input.Keys.SPACE) {
                mModel.startRound();
                mState = State.GAME;
                return true;
            }
        }
        return false;
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / height;
        mCamera.viewportWidth = Constants.SCREEN_HEIGHT * aspectRatio;
        mCamera.update();
    }

    @Override
    public void dispose() {
        mGameRenderer.dispose();
        mModel.dispose();
    }

    @Override
    public void onModelUpdate(String modelState) {
        mGameRenderer.updateModel(mJson.fromJson(Field.class, modelState));
    }

    @Override
    public void goalEvent() {
        mState = State.IDLE;
    }
}
