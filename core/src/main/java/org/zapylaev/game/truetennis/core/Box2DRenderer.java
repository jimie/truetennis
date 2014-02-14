package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class Box2DRenderer implements Renderer {

    private final OrthographicCamera mCamera;
    private final World mWorld;
    private Box2DDebugRenderer mDebugRenderer;

    public Box2DRenderer(Controller controller, OrthographicCamera camera) {
        mDebugRenderer = new Box2DDebugRenderer();
        mCamera = camera;
        mWorld = controller.getWorld();
    }

    @Override
    public void render() {
        mDebugRenderer.render(mWorld, mCamera.combined);
    }

    @Override
    public void dispose() {
        mDebugRenderer.dispose();
    }
}
