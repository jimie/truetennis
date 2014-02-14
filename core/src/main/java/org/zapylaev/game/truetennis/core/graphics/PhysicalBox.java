package org.zapylaev.game.truetennis.core.graphics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class PhysicalBox {
    public static final float DENSITY = 1f;
    public static final float CONSTITUTION = 1f;
    public static final float SOME = 1f;

    public PhysicalBox(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0 ,0);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(2, 2);

        Body body = world.createBody(bodyDef);
        body.createFixture(polygonShape, 0);
    }
}
