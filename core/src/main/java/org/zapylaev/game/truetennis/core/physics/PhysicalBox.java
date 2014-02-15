package org.zapylaev.game.truetennis.core.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class PhysicalBox {

    private final Body mBody;
    private final World mWorld;

    private PhysicalBox(World world,
                       float x, float y,
                       float width, float height,
                       BodyDef.BodyType type,
                       float density,
                       float friction,
                       float restitution) {
        mWorld = world;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x + width / 2, y + height / 2);
        bodyDef.type = type;

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;

        mBody = world.createBody(bodyDef);
        mBody.createFixture(fixtureDef);

        polygonShape.dispose();
    }

    public Body getBody() {
        return mBody;
    }

    public static final class BoxBuilder extends Builder<PhysicalBox> {

        @Override
        public PhysicalBox build(World world) {
            return new PhysicalBox(world, x, y,
                    width, height,
                    type,
                    density,
                    friction,
                    restitution);
        }
    }
}
