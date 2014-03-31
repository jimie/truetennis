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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
class Box2dBall {
    public static final float RADIUS = 0.22f;
    private static final float DENSITY = 0.4f;
    private static final float FRICTION = 1f;
    private static final float RESTITUTION = 1f;
    private final Body mBody;

    Box2dBall(World world,
              float x, float y,
              float radius,
              BodyDef.BodyType type,
              float density,
              float friction,
              float restitution) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = type;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;

        mBody = world.createBody(bodyDef);
        mBody.createFixture(fixtureDef);

        circleShape.dispose();
    }

    Box2dBall(World world, float x, float y) {
        this(world, x, y, RADIUS, BodyDef.BodyType.DynamicBody, DENSITY, FRICTION, RESTITUTION);
    }

    void applyImpulse(float x, float y) {
        Vector2 center = mBody.getWorldCenter();
        mBody.applyLinearImpulse(x, y, center.x, center.y, true);
    }

    Vector2 getPosition() {
        return mBody.getPosition();
    }

    void reset() {
        mBody.setTransform(0, 0, 0);
        mBody.setLinearVelocity(0, 0);
        mBody.setAngularVelocity(0);
    }
}
