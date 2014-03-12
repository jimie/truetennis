package org.zapylaev.game.truetennis.core.model;

public interface IModelListener {
    void onModelUpdate(String modelState);
    void goalEvent();
}
