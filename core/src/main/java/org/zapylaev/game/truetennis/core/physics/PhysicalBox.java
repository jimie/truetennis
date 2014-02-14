package org.zapylaev.game.truetennis.core.physics;

import com.badlogic.gdx.physics.box2d.*;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class PhysicalBox {
    private PhysicalBox(World world,
                       float x, float y,
                       float width, float height,
                       BodyDef.BodyType type,
                       float density,
                       float friction,
                       float restitution) {
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

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        polygonShape.dispose();
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
