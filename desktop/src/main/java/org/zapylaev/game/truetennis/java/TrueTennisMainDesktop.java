package org.zapylaev.game.truetennis.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.zapylaev.game.truetennis.core.TrueTennisMain;

public class TrueTennisMainDesktop {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.useGL20 = true;
        new LwjglApplication(new TrueTennisMain(), config);
    }
}
