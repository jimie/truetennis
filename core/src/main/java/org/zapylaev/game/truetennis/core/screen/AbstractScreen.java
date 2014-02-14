package org.zapylaev.game.truetennis.core.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class AbstractScreen implements Screen {

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
