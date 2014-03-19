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

package org.zapylaev.game.truetennis.core.net;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nuggeta.NuggetaPlug;
import com.nuggeta.network.Message;
import com.nuggeta.ngdl.nobjects.CreateGameResponse;
import com.nuggeta.ngdl.nobjects.NRawMessage;
import org.zapylaev.game.truetennis.core.domain.Team;
import org.zapylaev.game.truetennis.core.model.IModel;
import org.zapylaev.game.truetennis.core.model.IModelListener;

import java.util.*;

public class ServerModelProxy implements IModel {

    private final IModel mModel;
    private final NuggetaPlug mNuggetaPlug;
    private String mGameId;

    public ServerModelProxy(IModel model, NuggetaPlug nuggetaPlug) {
        mModel = model;
        mNuggetaPlug = nuggetaPlug;

        mModel.addModelListener(new IModelListener() {
            @Override
            public void onModelUpdate(String modelState) {
                NRawMessage rawMessage = new NRawMessage();
                rawMessage.setContent(modelState);
                mNuggetaPlug.sendGameMessage(rawMessage, mGameId);
            }

            @Override
            public void goalEvent() {

            }

            @Override
            public void winEvent() {

            }
        });
    }

    @Override
    public void addModelListener(IModelListener listener) {
        mModel.addModelListener(listener);
    }

    @Override
    public void update() {
        List<Message> messages = mNuggetaPlug.pump();
        for (Message message : messages) {
            if (message instanceof CreateGameResponse) {
                CreateGameResponse createGameResponse = (CreateGameResponse) message;
                mGameId = createGameResponse.getGameId();
                mNuggetaPlug.joinGame(mGameId);
            } else if (message instanceof NRawMessage) {
                String content = ((NRawMessage)message).getContent();
                if (content.equals(Messages.UP)) {
                    mModel.moveUp(Team.RIGHT);
                } else {
                    mModel.moveDown(Team.RIGHT);
                }
            }
        }
        mModel.update();
    }

    @Override
    public void dispose() {
        mModel.dispose();
    }

    @Override
    public void joinGame(String gameId) {
        mModel.joinGame(gameId);
    }

    @Override
    public void moveUp(Team team) {
        mModel.moveUp(team);
    }

    @Override
    public void moveDown(Team team) {
        mModel.moveDown(team);
    }

    @Override
    public void startRound() {
        mModel.startRound();
    }

    @Override
    public void resetRound() {
        mModel.resetRound();
    }

    @Override
    public void debugRender(OrthographicCamera camera) {
        mModel.debugRender(camera);
    }
}