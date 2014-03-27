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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class Assets implements Disposable {
    private static Assets instance;

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    private Assets() {}

    public Texture player;
    public Texture ball;
    public Texture splash;
    public BitmapFont fontDroidSans17;

    public void init() {
        player = new Texture(Gdx.files.internal("img/player.png"));
        ball = new Texture(Gdx.files.internal("img/ball.png"));
        splash = new Texture(Gdx.files.internal("img/splash.png"));
        player.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ball.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        splash.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        fontDroidSans17 = new BitmapFont(Gdx.files.internal("fonts/DroidSans17.fnt"));
        fontDroidSans17.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void dispose() {
        player.dispose();
        ball.dispose();
        splash.dispose();
        fontDroidSans17.dispose();
    }
}
