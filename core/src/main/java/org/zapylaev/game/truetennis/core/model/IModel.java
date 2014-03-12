package org.zapylaev.game.truetennis.core.model;

import com.badlogic.gdx.graphics.OrthographicCamera;

public interface IModel {
    void addModelListener(IModelListener listener);
    void update();
    void dispose();
    void startRound();
    void debugRender(OrthographicCamera camera);
}
