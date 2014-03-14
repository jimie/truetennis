package org.zapylaev.game.truetennis.core.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import org.zapylaev.game.truetennis.core.Constants;
import org.zapylaev.game.truetennis.core.domain.Ball;
import org.zapylaev.game.truetennis.core.domain.Field;
import org.zapylaev.game.truetennis.core.domain.Player;

import java.util.*;

public class PhysicalModel implements IModel {
    private final DebugRenderer mDebugRenderer;
    private final World mWorld;
    private final Box2dBall mBox2dBall;
    private final Box2dStick mBox2dStickLeft;
    private final Box2dStick mBox2dStickRight;
    private final List<IModelListener> mModelListeners;

    private final Json mJson;
    private final Field mField;
    private final Player mLeftPlayer;
    private final Player mRightPlayer;
    private final Ball mBall;

    public PhysicalModel() {
        mWorld = new World(new Vector2(0, 0), true);
        Box2dWalls.create(mWorld);
        mBox2dStickLeft = new Box2dStick(mWorld, -Constants.SCREEN_WIDTH / 2, 0 - Box2dStick.HEIGHT / 2);
        mBox2dStickRight = new Box2dStick(mWorld, Constants.SCREEN_WIDTH / 2 - Box2dStick.WIDTH, 0 - Box2dStick.HEIGHT / 2);

        mBox2dStickLeft.setPrismaticJointEnabled(true);
        mBox2dStickRight.setPrismaticJointEnabled(true);

        mBox2dBall = new Box2dBall(mWorld, 0, 0);
        mModelListeners = new ArrayList<IModelListener>();
        mDebugRenderer = new DebugRenderer();
        mField = new Field();
        mLeftPlayer = new Player();
        mRightPlayer = new Player();
        mBall = new Ball();
        mField.setLeftPlayer(mLeftPlayer);
        mField.setRightPlayer(mRightPlayer);
        mField.setBall(mBall);
        mJson = new Json();
    }

    @Override
    public void addModelListener(IModelListener listener) {
        mModelListeners.add(listener);
    }

    @Override
    public void update() {
        processInput();
        mWorld.step(Gdx.graphics.getDeltaTime(), 8, 3);
        checkGoal();

        for (IModelListener modelListener : mModelListeners) {
            modelListener.onModelUpdate(obtainModelState());
        }
    }

    private String obtainModelState() {
        mLeftPlayer.setPosition(mBox2dStickLeft.getPosition());
        mRightPlayer.setPosition(mBox2dStickRight.getPosition());
        mBall.setPosition(mBox2dBall.getPosition());
        return mJson.toJson(mField);
    }

    private void checkGoal() {
        float ballX = mBox2dBall.getPosition().x;
        if ((ballX > Constants.SCREEN_WIDTH / 2) ||
                (ballX < -Constants.SCREEN_WIDTH / 2)){
            mBox2dBall.reset();
            for (IModelListener modelListener : mModelListeners) {
                modelListener.goalEvent();
            }
        }
    }

    private void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            mBox2dStickLeft.applyForceY(Box2dStick.MAX_FORCE);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            mBox2dStickLeft.applyForceY(-Box2dStick.MAX_FORCE);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mBox2dStickRight.applyForceY(Box2dStick.MAX_FORCE);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mBox2dStickRight.applyForceY(-Box2dStick.MAX_FORCE);
        }
    }

    @Override
    public void dispose() {
        mWorld.dispose();
        mDebugRenderer.dispose();
    }

    @Override
    public void startRound() {
        mBox2dBall.applyImpulse(0.7f, 0);
    }

    @Override
    public void debugRender(OrthographicCamera camera) {
        mDebugRenderer.render(mWorld, camera);
    }
}
