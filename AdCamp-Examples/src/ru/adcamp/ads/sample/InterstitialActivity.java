package ru.adcamp.ads.sample;

import ru.adcamp.ads.Ad;
import ru.adcamp.ads.ErrorType;
import ru.adcamp.ads.InterstitialBannerActivity;
import ru.adcamp.ads.InterstitialBannerActivity.OnPreloadListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/** В данном примере показано, каким образом можно отобразить полноэкранный
 * баннер, полностью загрузив его до начала показа. */
public class InterstitialActivity extends Activity {
	
	private Button loadAdButton;
	private ProgressBar loadingIndicator;
	private Button showAdButton;

	private Ad prepairedAd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interstitial_activity);
		
		loadAdButton = (Button) findViewById(R.id.loadAdButton);
		loadingIndicator = (ProgressBar) findViewById(R.id.loadingIndicator);
		showAdButton = (Button) findViewById(R.id.showAdButton);
	}
	
	public void loadAd(View v) {
		loadAdButton.setVisibility(View.GONE);
		loadingIndicator.setVisibility(View.VISIBLE);
		
		/* Загружаем рекламу используя наш placementId */
		InterstitialBannerActivity.preload("3", new OnPreloadListener() {
			@Override
			public void onInterstitialAdReady(Ad ad) {
				/* Реклама готова и может быть показана в любой момент */
				prepairedAd = ad;
				loadingIndicator.setVisibility(View.GONE);
				showAdButton.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onInterstitialAdFailed(ErrorType error, String errorDescription) {
				/* Не удалось загрузить рекламу - в этом месте вы можете перейти к показу
				 * рекламы из другой рекламной сети */
				loadingIndicator.setVisibility(View.GONE);
				loadAdButton.setVisibility(View.VISIBLE);
			}
		});
	}
	
	public void showAd(View v) {
		/* Показываем уже полученную и закешированную рекламу. */
		InterstitialBannerActivity.show(this, prepairedAd);
	}

}
