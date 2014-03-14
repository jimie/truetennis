package org.zapylaev.game.truetennis.core.domain;

import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 mSize;
    private Vector2 mPosition;

    public Player() {
        mSize = new Vector2();
        mPosition = new Vector2();
    }

    public Vector2 getSize() {
        return mSize;
    }

    public void setSize(Vector2 size) {
        mSize = size;
    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public void setPosition(Vector2 position) {
        mPosition = position;
    }
}
