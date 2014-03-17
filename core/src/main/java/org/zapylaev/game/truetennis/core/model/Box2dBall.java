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
    public static final float RADIUS = 0.3f;
    private static final float DENSITY = 0.2f;
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
