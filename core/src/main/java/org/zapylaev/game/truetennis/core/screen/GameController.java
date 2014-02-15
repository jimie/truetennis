package org.zapylaev.game.truetennis.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.media.jfxmediaimpl.MediaDisposer;
import org.zapylaev.game.truetennis.core.Constants;
import org.zapylaev.game.truetennis.core.Controller;
import org.zapylaev.game.truetennis.core.gameobjects.Ball;
import org.zapylaev.game.truetennis.core.gameobjects.InvisibleWall;
import org.zapylaev.game.truetennis.core.gameobjects.Stick;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GameController extends InputAdapter implements Controller, MediaDisposer.Disposable {

    private final World mWorld;
    private final OrthographicCamera mCamera;
    private Ball mBall;
    private Stick mStickLeft;
    private Stick mStickRight;

    private boolean mGameStarted;

    public GameController(OrthographicCamera camera) {
        mWorld = new World(new Vector2(0, 0), true);
        mCamera = camera;
        Gdx.input.setInputProcessor(this);
        init();
    }

    private void init() {
        mStickLeft = new Stick(mWorld, -Constants.SCREEN_WIDTH / 2, 0 - Stick.HEIGHT / 2);
        mStickRight = new Stick(mWorld, Constants.SCREEN_WIDTH / 2 - Stick.WIDTH, 0 - Stick.HEIGHT / 2);

        mStickLeft.setPrismaticJointEnabled(true);
        mStickRight.setPrismaticJointEnabled(true);

        new InvisibleWall(mWorld, -Constants.SCREEN_HEIGHT / 2);
        new InvisibleWall(mWorld, Constants.SCREEN_HEIGHT / 2 - InvisibleWall.HEIGHT);

        mBall = new Ball(mWorld, 0, 0);
    }

    @Override
    public void update() {
        mWorld.step(Gdx.graphics.getDeltaTime(), 3, 8);

        float ballX = mBall.getPosition().x;
        if ((ballX > Constants.SCREEN_WIDTH / 2) ||
                (ballX < -Constants.SCREEN_WIDTH / 2)){
            mBall.reset();
            mGameStarted = false;
        }

        processInput();
    }

    private void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            mStickLeft.applyForceY(5);
            mStickRight.applyForceY(5);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            mStickLeft.applyForceY(-5);
            mStickRight.applyForceY(-5);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!mGameStarted) {
            mBall.applyForce(100, 0);
            mGameStarted = true;
        }
        return super.keyDown(keycode);
    }

    public World getWorld() {
        return mWorld;
    }

    @Override
    public void dispose() {
        mWorld.dispose();
    }
}
