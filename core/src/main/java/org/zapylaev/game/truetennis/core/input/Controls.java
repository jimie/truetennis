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
import org.zapylaev.game.truetennis.core.IRenderer;
import org.zapylaev.game.truetennis.core.domain.Team;
import org.zapylaev.game.truetennis.core.model.IModel;

public class Controls extends InputAdapter {

    private final IModel mModel;
    private final IRenderer mGameRenderer;
    private State mState;

    public Controls(IModel model, IRenderer gameRenderer) {
        mModel = model;
        mGameRenderer = gameRenderer;
    }

    public void process() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mModel.moveUpStart(Team.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mModel.moveDownStart(Team.LEFT);
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            if (mState == State.IDLE) {
                mModel.startRound();
                mState = State.GAME;
                return true;
            } else if (mState == State.END_GAME) {
                mGameRenderer.stopWinEffect();
                mModel.resetRound();
                mModel.startRound();
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
