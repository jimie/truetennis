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

package org.zapylaev.game.truetennis.core.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import org.zapylaev.game.truetennis.core.Constants;
import org.zapylaev.game.truetennis.core.Debug;
import org.zapylaev.game.truetennis.core.GamePrefs;
import org.zapylaev.game.truetennis.core.domain.Team;
import org.zapylaev.game.truetennis.core.domain.Ball;
import org.zapylaev.game.truetennis.core.domain.Field;
import org.zapylaev.game.truetennis.core.domain.Player;

import java.util.*;

public class PhysicalModel implements IModel {
    private DebugRenderer mDebugRenderer;
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
    private int mWinScore;

    public PhysicalModel() {
        mWorld = new World(new Vector2(0, 0), true);
        Box2dWalls.create(mWorld);
        mBox2dStickLeft = new Box2dStick(mWorld, -Constants.SCREEN_WIDTH / 2, 0 - Box2dStick.HEIGHT / 2);
        mBox2dStickRight = new Box2dStick(mWorld, Constants.SCREEN_WIDTH / 2 - Box2dStick.WIDTH, 0 - Box2dStick.HEIGHT / 2);

        mBox2dStickLeft.setPrismaticJointEnabled(true);
        mBox2dStickRight.setPrismaticJointEnabled(true);

        mBox2dBall = new Box2dBall(mWorld, 0, 0);
        mModelListeners = new ArrayList<IModelListener>();
        if (Debug.RENDER_BOX2D) {
            mDebugRenderer = new DebugRenderer();
        }
        mField = new Field();
        mLeftPlayer = new Player();
        mLeftPlayer.setSize(new Vector2(Box2dStick.WIDTH, Box2dStick.HEIGHT));
        mRightPlayer = new Player();
        mRightPlayer.setSize(new Vector2(Box2dStick.WIDTH, Box2dStick.HEIGHT));
        mBall = new Ball();
        mBall.setRadius(Box2dBall.RADIUS);
        mField.setLeftPlayer(mLeftPlayer);
        mField.setRightPlayer(mRightPlayer);
        mField.setBall(mBall);
        mJson = new Json();
        mWinScore = GamePrefs.getInstance().getWinScore();
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
        checkWin();

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
        boolean goal = false;
        if (ballX > Constants.SCREEN_WIDTH / 2) {
            goal = true;
            mField.setLeftPlayerScore(mField.getLeftPlayerScore() + 1);
        } else if (ballX < -Constants.SCREEN_WIDTH / 2) {
            goal = true;
            mField.setRightPlayerScore(mField.getRightPlayerScore() + 1);
        }
        if (goal) {
            mBox2dBall.reset();
            for (IModelListener modelListener : mModelListeners) {
                modelListener.goalEvent();
            }
        }
    }

    private void checkWin() {
        if (mField.getLeftPlayerScore() >= mWinScore) {
            for (IModelListener modelListener : mModelListeners) {
                modelListener.winEvent();
                mField.setLastWinTeam(Team.LEFT);
            }
        } else if (mField.getRightPlayerScore() >= mWinScore) {
            for (IModelListener modelListener : mModelListeners) {
                modelListener.winEvent();
                mField.setLastWinTeam(Team.RIGHT);
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
        if (mDebugRenderer != null) {
            mDebugRenderer.dispose();
        }
    }

    @Override
    public void startRound() {
        mBox2dBall.applyImpulse(0.7f, 0);
    }

    @Override
    public void resetRound() {
        mLeftPlayer.setPosition(new Vector2(0, 0));
        mRightPlayer.setPosition(new Vector2(0, 0));
        mField.setLeftPlayerScore(0);
        mField.setRightPlayerScore(0);
    }

    @Override
    public void debugRender(OrthographicCamera camera) {
        if (mDebugRenderer != null) {
            mDebugRenderer.render(mWorld, camera);
        }
    }
}
