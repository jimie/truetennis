package org.zapylaev.game.truetennis.core.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import org.zapylaev.game.truetennis.core.physics.PhysicalCircle;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class Ball implements Renderable {
    public static final float RADIUS = 0.5f;
    public static final float DENSITY = 1f;
    public static final float FRICTION = 1f;
    public static final float RESTITUTION = 1f;
    private final PhysicalCircle mPhysicalCircle;

    public Ball(World world, int x, int y) {
        mPhysicalCircle = (PhysicalCircle) new PhysicalCircle.BoxBuilder()
                .setX(x)
                .setY(y)
                .setWidth(RADIUS)
                .setHeight(RADIUS)
                .setDensity(DENSITY)
                .setFriction(FRICTION)
                .setRestitution(RESTITUTION)
                .setType(BodyDef.BodyType.DynamicBody)
                .build(world);
    }

    @Override
    public void render(SpriteBatch batch) {
    }
}
