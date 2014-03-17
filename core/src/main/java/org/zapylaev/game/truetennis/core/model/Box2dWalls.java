/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Zapylaiev Kyrylo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
