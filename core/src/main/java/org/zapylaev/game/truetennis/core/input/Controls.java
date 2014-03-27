/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Zapylaiev Kyrylo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.zapylaev.game.truetennis.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import org.zapylaev.game.truetennis.core.IRenderer;
import org.zapylaev.game.truetennis.core.domain.Team;
import org.zapylaev.game.truetennis.core.model.IModel;

public class Controls extends InputAdapter {

    private final IModel mModel;
    private final IRenderer mGameRenderer;
    private State mState;
    private OrthographicCamera mCamera;

    public Controls(IModel model, IRenderer gameRenderer, OrthographicCamera camera) {
        mModel = model;
        mGameRenderer = gameRenderer;
        mCamera = camera;
    }

    public void process() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            mModel.sendMoveUp(Team.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            mModel.sendMoveDown(Team.LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mModel.sendMoveUp(Team.RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mModel.sendMoveDown(Team.RIGHT);
        }
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, int button) {
        final Vector3 vec = new Vector3(screenX, screenY, 0);
        mCamera.unproject(vec);
        mModel.sendTouchDown(vec.x, vec.y, pointer);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        final Vector3 vec = new Vector3(screenX, screenY, 0);
        mCamera.unproject(vec);
        mModel.sendTouchDragged(vec.x, vec.y, pointer);
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        final Vector3 vec = new Vector3(screenX, screenY, 0);
        mCamera.unproject(vec);
        mModel.sendTouchUp(vec.x, vec.y, pointer);
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            if (mState == State.IDLE) {
                mModel.sendStartRound();
                mState = State.GAME;
                return true;
            } else if (mState == State.END_GAME) {
                mGameRenderer.stopWinEffect();
                mModel.sendResetRound();
                mModel.sendStartRound();
                mState = State.GAME;
                return true;
            }
        }
        return false;
    }

    public void setState(State state) {
        mState = state;
    }
}
