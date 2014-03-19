package com.nuggeta.sample.libgdx.client;

import com.badlogic.gdx.ApplicationListener;

import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.nuggeta.libgdx.NuggetaLibGdxHtml;
import com.nuggeta.sample.libgdx.truetennis;
import com.nuggeta.libgdx.NuggetaLibGdxHtml;

public class GwtLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig () {
        GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(480, 320);
        return cfg;
    }

    @Override
    public ApplicationListener getApplicationListener () {
        NuggetaLibGdxHtml.register();
        return new truetennis();
    }
}