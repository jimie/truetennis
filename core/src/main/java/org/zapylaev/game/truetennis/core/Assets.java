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
    public BitmapFont fontDroidSans17;

    public void init() {
        player = new Texture(Gdx.files.internal("player.png"));
        ball = new Texture(Gdx.files.internal("ball.png"));
        player.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ball.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        fontDroidSans17 = new BitmapFont(Gdx.files.internal("fonts/DroidSans17.fnt"));
        fontDroidSans17.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void dispose() {
        player.dispose();
        ball.dispose();
        fontDroidSans17.dispose();
    }
}
