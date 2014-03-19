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

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.nuggeta.NuggetaPlug;
import com.nuggeta.network.Message;
import com.nuggeta.ngdl.nobjects.StartResponse;
import com.nuggeta.ngdl.nobjects.StartStatus;
import org.zapylaev.game.truetennis.core.screen.GameController;

import java.util.*;

public class TrueTennisMain extends Game {

    public static final String NUGGETA_TOKEN = "nuggeta://true_pong_e1a7c11d-bbeb-401d-a7cd-0dd5c50a07b5";
    private NuggetaPlug mNuggetaPlug;

    @Override
    public void create() {
        Assets.getInstance().init();
        setScreen(new GameController());

        mNuggetaPlug = new NuggetaPlug(NUGGETA_TOKEN);
        mNuggetaPlug.start();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.62f, 0.82f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        super.render();

        List<Message> messages = mNuggetaPlug.pump();

        for (Message message : messages) {
            if (message instanceof StartResponse) {
                StartResponse startResponse = (StartResponse) message;
                if (startResponse.getStartStatus() == StartStatus.READY) {
                    System.out.println("CONNECTION READY");

                    // ...
                    // Do something when connection is ready
                    // ...
                } else if (startResponse.getStartStatus() == StartStatus.WARNED) {
                    // ...
                    // This indicate your version of the game is not up to date but working with the new
                    // version
                    // IMPORTANT : read about game versioning management
                } else if (startResponse.getStartStatus() == StartStatus.FAILED) {
                    // ...
                    // Do something when connection to nuggeta fails
                    // ...
                } else if (startResponse.getStartStatus() == StartStatus.REFUSED) {
                    // This indicates the connection has been refused by nuggeta, the player must download the
                    // IMPORTANT : read about game versioning management
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
