package org.zapylaev.game.truetennis.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GamePrefs {

    private static final String PREFS_NAME = "org.zapylaev.game.truetennis";
    private static final String SOME_PREF = "SOME_PREF";

    private static GamePrefs sInstance;
    private Preferences mPreferences;

    private GamePrefs() {}

    public static GamePrefs getInstance() {
        if (sInstance == null) {
            sInstance = new GamePrefs();
            sInstance.mPreferences = Gdx.app.getPreferences(PREFS_NAME);
        }
        return sInstance;
    }

    public String getTemp() {
        return mPreferences.getString(SOME_PREF, "temp");
    }

    public void setTemp(String temp) {
        mPreferences.putString(SOME_PREF, temp);
        mPreferences.flush();
    }
}
