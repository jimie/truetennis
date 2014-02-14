package org.zapylaev.game.truetennis.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.zapylaev.game.truetennis.core.Controller;
import org.zapylaev.game.truetennis.core.gameobjects.Stick;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GameController implements Controller {

    private final World mWorld;
    private final OrthographicCamera mCamera;

    public GameController(OrthographicCamera camera) {
        mWorld = new World(new Vector2(0, 0), true);
        mCamera = camera;
        init();
    }

    private void init() {
        new Stick(mWorld);
    }

    @Override
    public void update() {
        mWorld.step(Gdx.graphics.getDeltaTime(), 3, 8);
    }

    public World getWorld() {
        return mWorld;
    }
}
