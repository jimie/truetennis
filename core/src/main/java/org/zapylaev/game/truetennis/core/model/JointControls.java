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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import org.zapylaev.game.truetennis.core.Constants;

import java.util.*;

class JointControls implements ITouchControls {
    private final World mWorld;
    private Map<Integer, MouseJoint> mMouseJoints;

    JointControls(World world) {
        mWorld = world;
        mMouseJoints = new HashMap<Integer, MouseJoint>();
    }

    @Override
    public void sendTouchDown(final float x, final float y, final int pointer) {
        mWorld.QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                MouseJointDef jointDef = new MouseJointDef();
                jointDef.collideConnected = true;
                jointDef.maxForce = Constants.MAX_MOUSE_JOINT_FORCE;
                jointDef.bodyA = mWorld.createBody(new BodyDef());
                jointDef.bodyB = fixture.getBody();
                jointDef.target.set(x, y);
                mMouseJoints.put(pointer, (MouseJoint) mWorld.createJoint(jointDef));
                return false;
            }
        }, x, y, x, y);
    }

    @Override
    public void sendTouchDragged(float x, float y, int pointer) {
        MouseJoint joint = mMouseJoints.get(pointer);
        if (joint != null) {
            joint.setTarget(new Vector2(x, y));
        }
    }

    @Override
    public void sendTouchUp(float x, float y, int pointer) {
        MouseJoint joint = mMouseJoints.get(pointer);
        if (joint != null) {
            mWorld.destroyJoint(joint);
            mMouseJoints.remove(pointer);
        }
    }
}
