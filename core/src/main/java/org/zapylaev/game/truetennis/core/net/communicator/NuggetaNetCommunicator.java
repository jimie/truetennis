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
import com.nuggeta.ngdl.nobjects.CreateGameResponse;
import com.nuggeta.ngdl.nobjects.CreateGameStatus;
import com.nuggeta.ngdl.nobjects.GetGamesResponse;
import com.nuggeta.ngdl.nobjects.GetGamesStatus;
import com.nuggeta.ngdl.nobjects.NGame;
import com.nuggeta.ngdl.nobjects.NRawMessage;
import com.nuggeta.ngdl.nobjects.NuggetaQuery;

import java.util.*;

class NuggetaNetCommunicator implements INetCommunicator {
    public static final String NUGGETA_TOKEN = "nuggeta://true_pong_e1a7c11d-bbeb-401d-a7cd-0dd5c50a07b5";
    private final NuggetaPlug mNuggetaPlug;
    private final NuggetaQuery mGetGamesQuery;
    private final NRawMessage mRawMessage;
    private final Map<Class<?>, AsyncRequest<?>> mAsyncRequests;
    private String mActiveGameId;

    NuggetaNetCommunicator() {
        mNuggetaPlug = new NuggetaPlug(NUGGETA_TOKEN);
        mGetGamesQuery = new NuggetaQuery();
        mGetGamesQuery.setStart(0);
        mGetGamesQuery.setLimit(10);
        mRawMessage = new NRawMessage();
        mAsyncRequests = new HashMap<Class<?>, AsyncRequest<?>>();
    }

    @Override
    public void connect() {
        mNuggetaPlug.start();
    }

    @Override
    public void loop() {
        List<Message> messages = mNuggetaPlug.pump();
        for (Message message : messages) {
            if (message instanceof GetGamesResponse) {
                GetGamesResponse getGamesResponse = (GetGamesResponse) message;
                if (getGamesResponse.getGetGamesStatus() == GetGamesStatus.SUCCESS) {
                    AsyncRequest<List<NetGame>> request = (AsyncRequest<List<NetGame>>) mAsyncRequests.get(GetGamesResponse.class);
                    if (request != null) {
                        List<NGame> games = getGamesResponse.getGames();
                        List<NetGame> netGames = new ArrayList<NetGame>();
                        for (NGame nGame : games) {
                            netGames.add(new NetGame(nGame));
                        }
                        request.answer(netGames);
                        mAsyncRequests.remove(GetGamesResponse.class);
                    }
                } else {
                    throw new RuntimeException("Can't get games. Status: " + getGamesResponse.getGetGamesStatus());
                }
            } else if (message instanceof CreateGameResponse) {
                CreateGameResponse createGameResponse = (CreateGameResponse) message;
                if (createGameResponse.getCreateGameStatus() == CreateGameStatus.SUCCESS) {
                    AsyncRequest<String> request = (AsyncRequest<String>) mAsyncRequests.get(CreateGameResponse.class);
                    mActiveGameId = createGameResponse.getGameId();
                    request.answer(mActiveGameId);
                    mAsyncRequests.remove(CreateGameResponse.class);
                } else {
                    throw new RuntimeException("Can't create game. Status: " + createGameResponse.getCreateGameStatus());
                }
            }
        }
    }

    @Override
    public void createGameRequest(AsyncRequest<String> answer) {
        mNuggetaPlug.createGame();
        mAsyncRequests.put(CreateGameResponse.class, answer);
    }

    @Override
    public void joinGame(String gameId) {
        mNuggetaPlug.joinGame(gameId);
        mActiveGameId = gameId;
    }

    @Override
    public void getGamesRequest(AsyncRequest<List<NetGame>> answer) {
        mNuggetaPlug.getGames(mGetGamesQuery);
        mAsyncRequests.put(GetGamesResponse.class, answer);
    }

    @Override
    public void sendGameMessage(String message) {
        if (mActiveGameId == null) throw new RuntimeException("Seems game was not created");
        mRawMessage.setContent(message);
        mNuggetaPlug.sendGameMessage(mRawMessage, mActiveGameId);
    }

    @Override
    public List<Message> retrieveLastMessages() {
        return mNuggetaPlug.pump();
    }

    @Override
    public void dispose() {
        if (mActiveGameId != null) {
            mNuggetaPlug.stopGame(mActiveGameId);
        }
        mNuggetaPlug.stop();
    }
}
