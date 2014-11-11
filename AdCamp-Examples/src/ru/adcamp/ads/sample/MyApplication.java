package ru.adcamp.ads.sample;

import ru.adcamp.ads.AdsManager;
import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		AdsManager.getInstance().initialize(this, true, Log.VERBOSE);
	}

}
