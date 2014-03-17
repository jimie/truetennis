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

package org.zapylaev.game.truetennis.core.render.effects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.zapylaev.game.truetennis.core.Assets;
import org.zapylaev.game.truetennis.core.Constants;
import org.zapylaev.game.truetennis.core.domain.Team;

public class WinEffect implements IEffect<Team> {
    private static final float TOP_MARGIN = 100;
    private final BitmapFont mBitmapFont;
    private final OrthographicCamera mHudCamera;

    private boolean mPlaying;

    public WinEffect(OrthographicCamera hudCamera) {
        mHudCamera = hudCamera;
        mBitmapFont = Assets.getInstance().fontDroidSans17;
    }

    @Override
    public void draw(SpriteBatch batch, Team team) {
        if (mPlaying) {
            batch.setProjectionMatrix(mHudCamera.combined);
            batch.begin();
            if (team == Team.LEFT) {
                String str = "Left player WIN!";
                float length = mBitmapFont.getBounds(str).width;
                mBitmapFont.draw(batch, str, 0 - length / 2, Constants.HUD_SCREEN_HEIGHT / 2 - TOP_MARGIN);
            } else {
                String str = "Right player WIN!";
                float length = mBitmapFont.getBounds(str).width;
                mBitmapFont.draw(batch, str, 0 - length / 2, Constants.HUD_SCREEN_HEIGHT / 2 - TOP_MARGIN);
            }
            batch.end();
        }
    }

    @Override
    public void play() {
        mPlaying = true;
    }

    @Override
    public void stop() {
        mPlaying = false;
    }
}
