package org.zapylaev.game.truetennis.html;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import org.zapylaev.game.truetennis.core.TrueTennisMain;

public class TrueTennisMainHtml extends GwtApplication {
    @Override
    public ApplicationListener getApplicationListener() {
        return new TrueTennisMain();
    }

    @Override
    public GwtApplicationConfiguration getConfig() {
        return new GwtApplicationConfiguration(480, 320);
    }
}
