package ru.adcamp.ads.sample;

import ru.adcamp.ads.BannerAdView;
import android.app.Activity;
import android.os.Bundle;

/** В данном примере показано, каким образом можно добавить
 * баннерную рекламу в свое приложение. */
public class BannerAdActivity extends Activity {
	
	private BannerAdView bannerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Баннеры добавляются в xml файле
		setContentView(R.layout.banner_ad_activity);
		
		bannerView = (BannerAdView) findViewById(R.id.bannerView);
		// Простого вызова showAd буде достаточно, чтобы показать рекламу в текущем размере BannerAdView.
		bannerView.showAd();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		bannerView.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		bannerView.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		bannerView.onDestroy();
	}
	

}
