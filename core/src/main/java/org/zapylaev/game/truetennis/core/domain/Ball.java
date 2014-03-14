package org.zapylaev.game.truetennis.core.domain;

import com.badlogic.gdx.math.Vector2;

public class Ball {
    private float mRadius;
    private Vector2 mPosition;

    public Ball() {
        mPosition = new Vector2();
    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public void setPosition(Vector2 position) {
        mPosition = position;
    }

    public float getRadius() {
        return mRadius;
    }

    public void setRadius(float radius) {
        mRadius = radius;
    }
}
