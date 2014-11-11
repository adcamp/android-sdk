package ru.adcamp.ads.sample.optional;

import java.util.ArrayList;
import java.util.List;

import ru.adcamp.ads.sample.BannerAdActivity;
import ru.adcamp.ads.sample.BannerInListViewActivity;
import ru.adcamp.ads.sample.InterstitialActivity;
import ru.adcamp.ads.sample.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/** Данный класс не имеет отношения к работе SDK.
 * Экран, содержащий категории примеров. */
public class ExamplesActivity extends ListActivity {

	static enum Example {
		BANNER_ADS			(R.string.example_title_banner_ads, R.string.example_desc_banner_ads, BannerAdActivity.class),
		BANNER_IN_LIST		(R.string.example_title_banner_in_list, R.string.example_desc_banner_in_list, BannerInListViewActivity.class),
		INTERSTITIAL_AD		(R.string.example_title_interstitial_ad, R.string.example_desc_interstitial_ad, InterstitialActivity.class);

		final int titleRes;
		final int descriptionRes;
		final Class<? extends Activity> activity;

		private Example(int titleRes, int descriptionRes, Class<? extends Activity> activity) {
			this.titleRes = titleRes;
			this.descriptionRes = descriptionRes;
			this.activity = activity;
		}
	}

	static enum Category {
		BANNER_REGULAR(R.string.category_title_banner_regular,
				Example.BANNER_ADS,
				Example.BANNER_IN_LIST),
		BANNER_INTRESTITIAL(R.string.category_title_banner_intrestitial,
				Example.INTERSTITIAL_AD);

		final int titleRes;
		final Example[] examples;

		private Category(int titleRes, Example... examples) {
			this.titleRes = titleRes;
			this.examples = examples;
		}
	}

	private static List<Object> createItems() {
		ArrayList<Object> items = new ArrayList<Object>();
		for (Category category : Category.values()) {
			items.add(category);
			for (Example example : category.examples) {
				items.add(example);
			}
		}
		return items;
	}

	private class ExamplesAdapter extends ArrayAdapter<Object> {

		private static final int VIEW_TYPE_HEADER = 0;
		private static final int VIEW_TYPE_CELL = 1;

		public ExamplesAdapter() {
			super(ExamplesActivity.this, 0, createItems());
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Object item = getItem(position);
			if (item instanceof Example) {
				View view = convertView;
				if (view == null) {
					view = LayoutInflater.from(getContext()).inflate(android.R.layout.two_line_list_item, parent, false);
					int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
					view.setPadding(padding, padding, padding, padding);
				}

				TextView title = (TextView) view.findViewById(android.R.id.text1);
				TextView description = (TextView) view.findViewById(android.R.id.text2);
				Example example = (Example) item;
				title.setText(example.titleRes);
				description.setText(example.descriptionRes);

				return view;
			} else if (item instanceof Category) {
				View view = convertView;
				if (view == null) {
					view = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
					int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
					view.setPadding(padding, padding, padding, padding);
				}

				TextView title = (TextView) view.findViewById(android.R.id.text1);
				title.setText(((Category)item).titleRes);

				return title;
			} else {
				throw new RuntimeException("Unknown item: " + item);
			}
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(int position) {
			Object item = getItem(position);
			if (item instanceof Example) {
				return VIEW_TYPE_CELL;
			} else if (item instanceof Category) {
				return VIEW_TYPE_HEADER;
			} else {
				throw new RuntimeException("Unknown item: " + item);
			}
		}

		@Override
		public boolean isEnabled(int position) {
			return getItemViewType(position) == VIEW_TYPE_CELL;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ExamplesAdapter());
		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Example example = (Example) getListAdapter().getItem(position);
				Intent intent = new Intent(ExamplesActivity.this, example.activity);
				startActivity(intent);
			}
		});
	}

}
