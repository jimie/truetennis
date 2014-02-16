package org.zapylaev.game.truetennis.core.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import org.zapylaev.game.truetennis.core.Constants;
import org.zapylaev.game.truetennis.core.physics.PhysicalBox;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class Stick implements Renderable {
    public static final float WIDTH = 0.4f;
    public static final float HEIGHT = 2.8f;
    public static final float DENSITY = 0.2f;
    public static final float FRICTION = 1f;
    public static final float RESTITUTION = 1f;
    public static final float LINEAR_DAMPING = 1f;
    public static final float MAX_FORCE = 10f;

    private final World mWorld;
    private final PhysicalBox mPhysicalBox;
    private PrismaticJoint mPrismaticJoint;

    public Stick(World world, float x, float y) {
        mWorld = world;
        mPhysicalBox = (PhysicalBox) new PhysicalBox.BoxBuilder()
                .setX(x)
                .setY(y)
                .setWidth(WIDTH)
                .setHeight(HEIGHT)
                .setDensity(DENSITY)
                .setFriction(FRICTION)
                .setRestitution(RESTITUTION)
                .setType(BodyDef.BodyType.DynamicBody)
                .build(world);
        mPhysicalBox.getBody().setLinearDamping(LINEAR_DAMPING);
    }

    public void applyForceY(float amount) {
        mPhysicalBox.getBody().applyForceToCenter(0 , amount, true);
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    public void setPrismaticJointEnabled(boolean enabled) {
        if (enabled) {
            PrismaticJointDef prismaticJointDef = new PrismaticJointDef();
            prismaticJointDef.initialize(mWorld.createBody(new BodyDef()), mPhysicalBox.getBody(), new Vector2(0, 0), new Vector2(0 , 1));
            prismaticJointDef.enableLimit = true;
            prismaticJointDef.upperTranslation = Constants.SCREEN_HEIGHT / 2 - Stick.HEIGHT / 2;
            prismaticJointDef.lowerTranslation = -Constants.SCREEN_HEIGHT / 2 + Stick.HEIGHT / 2;

            mPrismaticJoint = (PrismaticJoint) mWorld.createJoint(prismaticJointDef);
        } else {
            if (mPrismaticJoint != null) {
                mWorld.destroyJoint(mPrismaticJoint);
            }
        }
    }
}
