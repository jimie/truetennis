package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import org.zapylaev.game.truetennis.core.screen.GameController;

public class TrueTennisMain extends Game {

    @Override
    public void create() {
        Assets.getInstance().init();
        setScreen(new GameController());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.4f, 0.62f, 0.82f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        super.render();
    }
}
