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

package org.zapylaev.game.truetennis.core.net.communicator;

import com.nuggeta.NuggetaPlug;
import com.nuggeta.network.Message;
import com.nuggeta.ngdl.nobjects.NRawMessage;
import com.nuggeta.ngdl.nobjects.NuggetaQuery;

import java.util.*;

class NuggetaNetCommunicator implements INetCommunicator {
    public static final String NUGGETA_TOKEN = "nuggeta://true_pong_e1a7c11d-bbeb-401d-a7cd-0dd5c50a07b5";
    private final NuggetaPlug mNuggetaPlug;
    private final NuggetaQuery mGetGamesQuery;
    private final NRawMessage mRawMessage;

    NuggetaNetCommunicator() {
        mNuggetaPlug = new NuggetaPlug(NUGGETA_TOKEN);
        mGetGamesQuery = new NuggetaQuery();
        mGetGamesQuery.setStart(0);
        mGetGamesQuery.setLimit(10);
        mRawMessage = new NRawMessage();
        mNuggetaPlug.start();
    }

    @Override
    public void createGame() {
        mNuggetaPlug.createGame();
    }

    @Override
    public void joinGame(String gameId) {
        mNuggetaPlug.joinGame(gameId);
    }

    @Override
    public void getGames() {
        mNuggetaPlug.getGames(mGetGamesQuery);
    }

    @Override
    public void sendGameMessage(String message, String gameId) {
        mRawMessage.setContent(message);
        mNuggetaPlug.sendGameMessage(mRawMessage, gameId);
    }

    @Override
    public List<Message> retrieveLastMessages() {
        return mNuggetaPlug.pump();
    }

    @Override
    public void dispose() {
        mNuggetaPlug.stop();
    }
}
