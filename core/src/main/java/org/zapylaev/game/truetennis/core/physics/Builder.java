package org.zapylaev.game.truetennis.core.physics;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public abstract class Builder<T> {

    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected BodyDef.BodyType type;
    protected float density;
    protected float friction;
    protected float restitution;

    public Builder setX(float x) {
        this.x = x;
        return this;
    }

    public Builder setY(float y) {
        this.y = y;
        return this;
    }

    public Builder setWidth(float width) {
        this.width = width;
        return this;
    }

    public Builder setHeight(float height) {
        this.height = height;
        return this;
    }

    public Builder setType(BodyDef.BodyType type) {
        this.type = type;
        return this;
    }

    public Builder setDensity(float density) {
        this.density = density;
        return this;
    }

    public Builder setFriction(float friction) {
        this.friction = friction;
        return this;
    }

    public Builder setRestitution(float restitution) {
        this.restitution = restitution;
        return this;
    }
    public abstract T build(World world);
}
