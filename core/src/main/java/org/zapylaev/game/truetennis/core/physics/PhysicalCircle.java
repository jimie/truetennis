package org.zapylaev.game.truetennis.core.physics;

import com.badlogic.gdx.physics.box2d.*;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class PhysicalCircle {
    private PhysicalCircle(World world,
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

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        circleShape.dispose();
    }

    public static final class BoxBuilder extends Builder<PhysicalCircle> {

        @Override
        public PhysicalCircle build(World world) {
            if (width != height) {
                throw new IllegalArgumentException();
            }
            return new PhysicalCircle(world, x, y,
                    width,
                    type,
                    density,
                    friction,
                    restitution);
        }
    }
}
