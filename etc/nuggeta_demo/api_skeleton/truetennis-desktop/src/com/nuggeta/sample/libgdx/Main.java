
package com.nuggeta.sample.libgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nuggeta.libgdx.NuggetaLibGdxDesktop;

public class Main {

    public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "truetennis";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;
		
		NuggetaLibGdxDesktop.register();
		
		new LwjglApplication(new truetennis(), cfg);
	}
}
