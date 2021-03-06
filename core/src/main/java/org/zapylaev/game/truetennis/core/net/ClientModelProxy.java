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
import org.zapylaev.game.truetennis.core.net.communicator.INetCommunicator;

import java.util.*;

public class ClientModelProxy implements IModel {

    private INetCommunicator mNuggetaPlug;
    private IModelListener mListener;

    public ClientModelProxy(INetCommunicator nuggetaPlug) {
        mNuggetaPlug = nuggetaPlug;
    }

    @Override
    public void addModelListener(IModelListener listener) {
        mListener = listener;
    }

    @Override
    public void update() {
        List<Message> messages = mNuggetaPlug.retrieveLastMessages();
        for (Message message : messages) {
            if (message instanceof NRawMessage) {
                String content = ((NRawMessage)message).getContent();
                if (mListener != null) {
                    mListener.onModelUpdate(content);
                }
            }
        }
    }

    @Override
    public void dispose() {
    }

    @Override
    public void sendMoveUp(Team team) {
        mNuggetaPlug.sendGameMessage(Messages.UP);
    }

    @Override
    public void sendMoveDown(Team team) {
        mNuggetaPlug.sendGameMessage(Messages.DOWN);
    }

    @Override
    public void sendStartRound() {

    }

    @Override
    public void sendResetRound() {

    }

    @Override
    public void debugRender(OrthographicCamera camera) {

    }

    @Override
    public void sendTouchDown(float x, float y, int pointer) {

    }

    @Override
    public void sendTouchDragged(float x, float y, int pointer) {

    }

    @Override
    public void sendTouchUp(float x, float y, int pointer) {

    }
}
