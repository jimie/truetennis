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
import com.nuggeta.network.Message;
import com.nuggeta.ngdl.nobjects.NRawMessage;
import org.zapylaev.game.truetennis.core.domain.Team;
import org.zapylaev.game.truetennis.core.model.IModel;
import org.zapylaev.game.truetennis.core.model.IModelListener;
import org.zapylaev.game.truetennis.core.model.PhysicalModel;
import org.zapylaev.game.truetennis.core.net.communicator.INetCommunicator;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerModelProxy implements IModel {

    public static final int SERVER_SEND_FPS = 10;
    private final IModel mModel;
    private final INetCommunicator mNuggetaPlug;
    private final ScheduledExecutorService mExecutor;
    private volatile boolean mReady;

    public ServerModelProxy(INetCommunicator nuggetaPlug) {
        mModel = new PhysicalModel();
        mNuggetaPlug = nuggetaPlug;

        mExecutor = Executors.newSingleThreadScheduledExecutor();
        mExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                mReady = true;
            }
        }, 0, 1000 / SERVER_SEND_FPS, TimeUnit.MILLISECONDS);

        mModel.addModelListener(new IModelListener() {
            @Override
            public void onModelUpdate(String modelState) {
                if (mReady) {
                    mNuggetaPlug.sendGameMessage(modelState);
                    mReady = false;
                }
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
        List<Message> messages = mNuggetaPlug.retrieveLastMessages();
        for (Message message : messages) {
            if (message instanceof NRawMessage) {
                String content = ((NRawMessage)message).getContent();
                if (content.equals(Messages.UP)) {
                    mModel.sendMoveUp(Team.RIGHT);
                } else {
                    mModel.sendMoveDown(Team.RIGHT);
                }
            }
        }
        mModel.update();
    }

    @Override
    public void dispose() {
        mExecutor.shutdown();
        mModel.dispose();
    }

    @Override
    public void sendMoveUp(Team team) {
        mModel.sendMoveUp(team);
    }

    @Override
    public void sendMoveDown(Team team) {
        mModel.sendMoveDown(team);
    }

    @Override
    public void sendStartRound() {
        mModel.sendStartRound();
    }

    @Override
    public void sendResetRound() {
        mModel.sendResetRound();
    }

    @Override
    public void debugRender(OrthographicCamera camera) {
        mModel.debugRender(camera);
    }
}
