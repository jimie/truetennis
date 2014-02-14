package org.zapylaev.game.truetennis.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.zapylaev.game.truetennis.core.Constants;
import org.zapylaev.game.truetennis.core.Controller;
import org.zapylaev.game.truetennis.core.gameobjects.Ball;
import org.zapylaev.game.truetennis.core.gameobjects.InvisibleWall;
import org.zapylaev.game.truetennis.core.gameobjects.Stick;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GameController extends InputAdapter implements Controller {

    private final World mWorld;
    private final OrthographicCamera mCamera;
    private Ball mBall;

    public GameController(OrthographicCamera camera) {
        mWorld = new World(new Vector2(0, 0), true);
        mCamera = camera;
        Gdx.input.setInputProcessor(this);
        init();
    }

    private void init() {
        new Stick(mWorld, -Constants.SCREEN_WIDTH / 2, 0 - Stick.HEIGHT / 2);
        new Stick(mWorld, Constants.SCREEN_WIDTH / 2 - Stick.WIDTH, 0 - Stick.HEIGHT / 2);
        new InvisibleWall(mWorld, -Constants.SCREEN_HEIGHT / 2);
        new InvisibleWall(mWorld, Constants.SCREEN_HEIGHT / 2 - InvisibleWall.HEIGHT);
        mBall = new Ball(mWorld, 0, 0);
    }

    @Override
    public void update() {
        mWorld.step(Gdx.graphics.getDeltaTime(), 3, 8);
    }

    @Override
    public boolean keyDown(int keycode) {
        mBall.applyForce(10, 0);
        return super.keyDown(keycode);
    }

    public World getWorld() {
        return mWorld;
    }
}
