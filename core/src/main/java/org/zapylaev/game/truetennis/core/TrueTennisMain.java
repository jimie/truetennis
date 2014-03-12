package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import org.zapylaev.game.truetennis.core.screen.GameController;

public class TrueTennisMain extends Game {

    @Override
    public void create() {
        setScreen(new GameController());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        super.render();
    }
}
