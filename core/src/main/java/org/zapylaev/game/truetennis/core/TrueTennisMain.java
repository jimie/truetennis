package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import org.zapylaev.game.truetennis.core.screen.GameScreen;

public class TrueTennisMain extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
