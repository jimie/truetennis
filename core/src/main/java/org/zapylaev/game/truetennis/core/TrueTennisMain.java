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
import com.nuggeta.NuggetaPlug;
import com.nuggeta.network.Message;
import com.nuggeta.ngdl.nobjects.CreateGameResponse;
import com.nuggeta.ngdl.nobjects.GetGamesResponse;
import com.nuggeta.ngdl.nobjects.GetGamesStatus;
import com.nuggeta.ngdl.nobjects.NGame;
import com.nuggeta.ngdl.nobjects.NRawMessage;
import com.nuggeta.ngdl.nobjects.NuggetaQuery;
import org.zapylaev.game.truetennis.core.domain.Team;
import org.zapylaev.game.truetennis.core.model.IModelListener;
import org.zapylaev.game.truetennis.core.model.PhysicalModel;
import org.zapylaev.game.truetennis.core.net.ClientModelProxy;
import org.zapylaev.game.truetennis.core.screen.GameController;
import org.zapylaev.game.truetennis.core.screen.menu.StartScreen;

import java.util.*;

public class TrueTennisMain extends Game {

    public static final String NUGGETA_TOKEN = "nuggeta://true_pong_e1a7c11d-bbeb-401d-a7cd-0dd5c50a07b5";
    private NuggetaPlug mNuggetaPlug;
    private PhysicalModel mServerModel;
    private Controls mControls;
    private boolean mServer;
    private String mGameId;

    @Override
    public void create() {
        Assets.getInstance().init();
        setScreen(new StartScreen(this));

        mNuggetaPlug = new NuggetaPlug(NUGGETA_TOKEN);
        mNuggetaPlug.start();
        Gdx.app.setLogLevel(Application.LOG_NONE);
    }

    public void createGame() {
        mServerModel = new PhysicalModel();
        mControls = new Controls(mServerModel);
        setScreen(new GameController(mServerModel));
        mNuggetaPlug.createGame();
        mServer = true;
        mServerModel.addModelListener(new IModelListener() {
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

    public void joinGame() {
        ClientModelProxy clientModelProxy = new ClientModelProxy(mNuggetaPlug);
        mControls = new Controls(clientModelProxy);
        setScreen(new GameController(clientModelProxy));
        NuggetaQuery nuggetaQuery = new NuggetaQuery();
        nuggetaQuery.setStart(0);
        nuggetaQuery.setLimit(10);

        mNuggetaPlug.getGames(nuggetaQuery);
        mServer = false;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.62f, 0.82f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        super.render();

        if (mControls != null) {
            mControls.applyControls();
        }

        if (mServer) {
            pumpServerMessages();
        }
    }

    private void pumpServerMessages() {
        List<Message> messages = mNuggetaPlug.pump();
        for (Message message : messages) {
            if (message instanceof CreateGameResponse) {
                CreateGameResponse createGameResponse = (CreateGameResponse) message;
                mGameId = createGameResponse.getGameId();
                mNuggetaPlug.joinGame(mGameId);
            } else if (message instanceof NRawMessage) {
                String content = ((NRawMessage)message).getContent();
                if (content.equals(org.zapylaev.game.truetennis.core.net.Message.UP)) {
                    mServerModel.moveUp(Team.RIGHT);
                } else {
                    mServerModel.moveDown(Team.RIGHT);
                }
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        mNuggetaPlug.stop();
    }
}
