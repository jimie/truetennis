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

package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import org.zapylaev.game.truetennis.core.model.IModel;
import org.zapylaev.game.truetennis.core.model.PhysicalModel;
import org.zapylaev.game.truetennis.core.net.ClientModelProxy;
import org.zapylaev.game.truetennis.core.net.ServerModelProxy;
import org.zapylaev.game.truetennis.core.net.communicator.AsyncRequest;
import org.zapylaev.game.truetennis.core.net.communicator.INetCommunicator;
import org.zapylaev.game.truetennis.core.net.communicator.NetCommunicatorBuilder;
import org.zapylaev.game.truetennis.core.net.communicator.NetGame;
import org.zapylaev.game.truetennis.core.screen.GameController;
import org.zapylaev.game.truetennis.core.screen.menu.GamesListGame;
import org.zapylaev.game.truetennis.core.screen.menu.StartScreen;

import java.util.*;

public class TrueTennisMain extends Game {

    private INetCommunicator mNetCommunicator;
    private IModel mModel;

    @Override
    public void create() {
        Assets.getInstance().init();
        setScreen(new StartScreen(this));
        Gdx.app.setLogLevel(Application.LOG_ERROR);
        mNetCommunicator = NetCommunicatorBuilder.build(NetCommunicatorBuilder.NUGGETA);
        mNetCommunicator.connect();
    }

    public void createGame() {
        mNetCommunicator.createGameRequest(new AsyncRequest<String>() {
            @Override
            public void answer(String gameId) {
                mNetCommunicator.joinGame(gameId);
                mModel = new ServerModelProxy(mNetCommunicator);
                setScreen(new GameController(mModel));
            }
        });
    }

    public void showGamesList() {
        mNetCommunicator.getGamesRequest(new AsyncRequest<List<NetGame>>() {
            @Override
            public void answer(List<NetGame> games) {
                setScreen(new GamesListGame(TrueTennisMain.this, games));
            }
        });
    }

    public void joinGame(String gameId) {
        mNetCommunicator.joinGame(gameId);
        mModel = new ClientModelProxy(mNetCommunicator);
        setScreen(new GameController(mModel));
    }

    @Override
    public void render() {
        mNetCommunicator.loop();
        Gdx.gl.glClearColor(0.4f, 0.62f, 0.82f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        mNetCommunicator.dispose();
        if (mModel != null) {
            mModel.dispose();
        }
    }
}
