package org.zapylaev.game.truetennis.core.gameobjects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import org.zapylaev.game.truetennis.core.physics.PhysicalBox;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class Stick {
    public static final float WIDTH = 0.2f;
    public static final float HEIGHT = 1.5f;
    public static final float DENSITY = 1f;
    public static final float FRICTION = 1f;
    public static final float RESTITUTION = 1f;

    private PhysicalBox mPhysicalBox;

    public Stick(World world) {
        mPhysicalBox = (PhysicalBox) new PhysicalBox.BoxBuilder()
                .setX(0)
                .setY(0)
                .setWidth(WIDTH)
                .setHeight(HEIGHT)
                .setDensity(DENSITY)
                .setFriction(FRICTION)
                .setRestitution(RESTITUTION)
                .setType(BodyDef.BodyType.DynamicBody)
                .build(world);
    }
}
