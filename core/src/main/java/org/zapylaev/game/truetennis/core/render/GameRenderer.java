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

package org.zapylaev.game.truetennis.core.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.zapylaev.game.truetennis.core.IRenderer;
import org.zapylaev.game.truetennis.core.domain.Ball;
import org.zapylaev.game.truetennis.core.domain.Field;
import org.zapylaev.game.truetennis.core.domain.Player;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GameRenderer implements IRenderer {
    private final OrthographicCamera mCamera;
    private final SpriteBatch mMainBatch;

    private final HUD mHUD;
    private Field mField;
    private final ITexture<Player> mPlayerTexture;
    private final ITexture<Ball> mBallTexture;

    public GameRenderer(OrthographicCamera camera, OrthographicCamera hudCamera) {
        mCamera = camera;
        mMainBatch = new SpriteBatch();
        mPlayerTexture = new PlayerTexture();
        mBallTexture = new BallTexture();
        mHUD = new HUD(hudCamera);
    }

    @Override
    public void render() {
        if (mField == null) return;
        mMainBatch.setProjectionMatrix(mCamera.combined);
        mMainBatch.begin();
        mPlayerTexture.draw(mMainBatch, mField.getLeftPlayer());
        mPlayerTexture.draw(mMainBatch, mField.getRightPlayer());
        mBallTexture.draw(mMainBatch, mField.getBall());
        mMainBatch.end();

        mHUD.render(mMainBatch, mField);
    }

    @Override
    public void updateModel(Field model) {
        mField = model;
    }

    @Override
    public void dispose() {
        mMainBatch.dispose();
    }
}
