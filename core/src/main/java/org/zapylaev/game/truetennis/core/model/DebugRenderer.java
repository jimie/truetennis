package org.zapylaev.game.truetennis.core.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
class DebugRenderer {

    private Box2DDebugRenderer mDebugRenderer;

    DebugRenderer() {
        mDebugRenderer = new Box2DDebugRenderer();
    }

    void render(World world, OrthographicCamera camera) {
        mDebugRenderer.render(world, camera.combined);
    }

    void dispose() {
        mDebugRenderer.dispose();
    }
}
