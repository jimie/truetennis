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
