package ru.adcamp.ads.sample;

import ru.adcamp.ads.BannerAdView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * В данном примере показано, каким образом можно добавить баннерную рекламу в
 * любой список, т.е. в любой {@link AdapterView}.
 */
public class BannerInListViewActivity extends Activity {
	
	/** Для добавления баннера мы создадим свой собственный {@link BaseAdapter},
	 * работающий как обертка над уже существующим {@link BaseAdapter}.
	 * Нам потребуется вызывать методы onResume, onPause и onDestroy этого адаптера */
	public static class AdCampAdapter extends BaseAdapter {

		private final Context context;
	    private final BaseAdapter adapter;
	    private final String placementId;
	    private final int adPosition;
	   
	    private BannerAdView bannerView;

	    /**
	     * Создает новый адаптер, использующий данные из существующего адаптера, но дополнительно
	     * вставляющий один рекламный баннер до указанной позиции.
	     * @param context контекст, в котором будет отображаться этот адаптер
	     * @param adapter адаптер, из которого будут браться данные
	     * @param placementId идентификатор рекламного места
	     * @param adPosition в каком месте нужно вставить баннер
	     */
	    public AdCampAdapter(Context context, BaseAdapter adapter, String placementId, int adPosition) {
	    	super();
	    	this.context = context;
	        this.adapter = adapter;
	        this.placementId = placementId;
	        this.adPosition = adPosition;
	    }
	    
	    public void onResume() {
	    	if (bannerView != null) {
	    		bannerView.onResume();
	    	}
	    }
	    
	    public void onPause() {
	    	if (bannerView != null) {
	    		bannerView.onPause();
	    	}
	    }
	    
	    public void onDestroy() {
	    	if (bannerView != null) {
	    		bannerView.onDestroy();
	    	}
	    }
	    
	    @Override
	    public int getCount() {
	        int count = adapter.getCount();
	        if (count >= adPosition) {
	            return count + 1;
	        }
	        return count;
	    }

	    @Override
	    public Object getItem(int position) {
	        return adapter.getItem(getOffsetPosition(position));
	    }

	    @Override
	    public long getItemId(int position) {
	        return adapter.getItemId(getOffsetPosition(position));
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        if (position == adPosition) {
	            if (convertView instanceof BannerAdView) {
	                return convertView;
	            } else {
	            	if (bannerView == null) {
	                	bannerView = new BannerAdView(context, null);
	                	bannerView.setPlacementId(placementId);
	                	bannerView.showAd();
	            	}
	            	return bannerView;
	            }
	        } else {
	            return adapter.getView(getOffsetPosition(position), convertView, parent);
	        }
	    }

	    @Override
	    public int getViewTypeCount() {
	        return adapter.getViewTypeCount() + 1;
	    }

	    @Override
	    public int getItemViewType(int position) {
	        if (position == adPosition) {
	            return adapter.getViewTypeCount();
	        } else {
	            return adapter.getItemViewType(getOffsetPosition(position));
	        }
	    }

	    @Override
	    public boolean areAllItemsEnabled() {
	        return false;
	    }

	    @Override
	    public boolean isEnabled(int position) {
	        return (position == adPosition) || adapter.isEnabled(getOffsetPosition(position));
	    }

	    private int getOffsetPosition(int position) {
	        if (position >= adPosition) {
	            return position - 1;
	        }
	        return position;
	    }
		
	}
	
	private AdCampAdapter adCampAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListView listView = new ListView(this);
		// Нашим основноым контентом, в который встраивается баннер, будет набор слов
		// Но вообще это может быть абсолютно любой адаптер
		String[] items = {"one", "two", "three", "four", "five", "six", "seven"};
		BaseAdapter yourAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		// Создаем AdCampAdapter вокруг нашего адаптера с контентом
		adCampAdapter = new AdCampAdapter(this, yourAdapter, "2", 3);
		// Используем в качестве адаптера AdCampAdapter
		listView.setAdapter(adCampAdapter);
		setContentView(listView);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		adCampAdapter.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		adCampAdapter.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		adCampAdapter.onDestroy();
	}

}
