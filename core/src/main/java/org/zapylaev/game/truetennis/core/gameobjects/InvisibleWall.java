package org.zapylaev.game.truetennis.core.gameobjects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import org.zapylaev.game.truetennis.core.Constants;
import org.zapylaev.game.truetennis.core.physics.PhysicalBox;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class InvisibleWall {
    public static final float ADDITIONAL_GAP = 0.05f;
    public static final float WIDTH = Constants.SCREEN_WIDTH - Stick.WIDTH * 2 - ADDITIONAL_GAP * 2;
    public static final float HEIGHT = 1f;
    public static final float DENSITY = 1f;
    public static final float FRICTION = 0f;
    public static final float RESTITUTION = 1f;
    private PhysicalBox mPhysicalBox;

    public InvisibleWall(World world, float y) {
        mPhysicalBox = (PhysicalBox) new PhysicalBox.BoxBuilder()
                .setX(-Constants.SCREEN_WIDTH / 2 + Stick.WIDTH + ADDITIONAL_GAP)
                .setY(y)
                .setWidth(WIDTH)
                .setHeight(HEIGHT)
                .setDensity(DENSITY)
                .setFriction(FRICTION)
                .setRestitution(RESTITUTION)
                .setType(BodyDef.BodyType.StaticBody)
                .build(world);
    }
}
