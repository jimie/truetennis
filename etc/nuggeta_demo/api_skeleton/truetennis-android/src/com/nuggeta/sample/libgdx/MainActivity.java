package com.nuggeta.sample.libgdx;

import android.os.Bundle;


import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.nuggeta.libgdx.NuggetaLibGdxAndroid;

public class MainActivity extends AndroidApplication {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = false;

		//register Nuggeta
		NuggetaLibGdxAndroid.register();
		
		initialize(new truetennis(), cfg);
	}

}