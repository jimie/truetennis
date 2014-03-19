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
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import org.zapylaev.game.truetennis.core.Constants;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
class Box2dStick extends Box2dRect {
    public static final float WIDTH = 0.35f;
    public static final float HEIGHT = 2.8f;
    public static final float DENSITY = 0.3f;
    public static final float FRICTION = 1f;
    public static final float RESTITUTION = 1f;
    public static final float LINEAR_DAMPING = 2f;
    public static final float MAX_FORCE = 6f;

    private final World mWorld;
    private PrismaticJoint mPrismaticJoint;

    Box2dStick(World world, float x, float y) {
        super(world, x, y, WIDTH, HEIGHT,
                BodyDef.BodyType.DynamicBody, DENSITY, FRICTION, RESTITUTION);
        mWorld = world;
        mBody.setLinearDamping(LINEAR_DAMPING);
    }

    void applyForceY(float amount) {
        mBody.applyForceToCenter(0, amount, true);
    }

    void setPrismaticJointEnabled(boolean enabled) {
        if (enabled) {
            PrismaticJointDef prismaticJointDef = new PrismaticJointDef();
            prismaticJointDef.initialize(mWorld.createBody(new BodyDef()), mBody, new Vector2(0, 0), new Vector2(0 , 1));
            prismaticJointDef.enableLimit = true;
            prismaticJointDef.upperTranslation = Constants.SCREEN_HEIGHT / 2 - Box2dStick.HEIGHT / 2;
            prismaticJointDef.lowerTranslation = -Constants.SCREEN_HEIGHT / 2 + Box2dStick.HEIGHT / 2;

            mPrismaticJoint = (PrismaticJoint) mWorld.createJoint(prismaticJointDef);
        } else {
            if (mPrismaticJoint != null) {
                mWorld.destroyJoint(mPrismaticJoint);
            }
        }
    }
}
