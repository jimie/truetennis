package org.zapylaev.game.truetennis.core.model;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import org.zapylaev.game.truetennis.core.Constants;

class Box2dWalls {
    private static final float ADDITIONAL_GAP = 0.05f;
    private static final float WIDTH = Constants.SCREEN_WIDTH - Box2dStick.WIDTH * 2 - ADDITIONAL_GAP * 2;
    private static final float HEIGHT = 1f;
    private static final float DENSITY = 1f;
    private static final float FRICTION = 0f;
    private static final float RESTITUTION = 1f;

    static void create(World world) {
        createWall(world, -Constants.SCREEN_HEIGHT / 2 - HEIGHT);
        createWall(world, Constants.SCREEN_HEIGHT / 2);
    }

    private static void createWall(World world, float y) {
        new Box2dRect(world, -Constants.SCREEN_WIDTH / 2 + Box2dStick.WIDTH + ADDITIONAL_GAP, y, WIDTH, HEIGHT,
                BodyDef.BodyType.StaticBody, DENSITY, FRICTION, RESTITUTION);
    }
}
