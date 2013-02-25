package com.example.testproject;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {
	private ViewPager viewPager;
	private MyListView lv_main;
	private LayoutInflater layoutInflater;
	private LinearLayout ll_point;
	private FrameLayout frameLayout;
	private int frameheight;
	private final String msg[] = { "one", "two", "three", "four", "five",
			"six", "seven" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initHeadImage();
	}

	private void adapterInit() {
		PagerAdapter adapter = new FragmentPagerAdapter(
				getSupportFragmentManager()) {
			int[] colours = new int[] { Color.CYAN, Color.GRAY, Color.MAGENTA,
					Color.LTGRAY, Color.GREEN, Color.WHITE, Color.YELLOW };

			@Override
			public int getCount() {
				return colours.length;
			}

			@Override
			public Fragment getItem(int position) {
				Fragment fragment = new ColourFragment();
				Bundle args = new Bundle();
				args.putInt("colour", colours[position]);
				args.putInt("identifier", position);
				fragment.setArguments(args);
				return fragment;
			}
		};

		// wrap pager to provide infinite paging with wrap-around
		PagerAdapter wrappedAdapter = new InfinitePagerAdapter(adapter);

		// actually an InfiniteViewPager
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setAdapter(wrappedAdapter);

	}

	void initHeadImage() {
		layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View headview = layoutInflater.inflate(R.layout.head_image, null);
		viewPager = (ViewPager) headview.findViewById(R.id.viewpager);
		lv_main = (MyListView) findViewById(R.id.lv_main);

		// wrap pager to provide infinite paging with wrap-around
		//

		// ll_point = (LinearLayout) headview.findViewById(R.id.ll_point);
		// frameLayout = (FrameLayout) headview.findViewById(R.id.fl_main);
		// LayoutParams layoutParams = frameLayout.getLayoutParams();
		// layoutParams.height = frameheight;
		// frameLayout.setLayoutParams(layoutParams);

		PagerAdapter adapter = new FragmentPagerAdapter(
				getSupportFragmentManager()) {
			int[] colours = new int[] { Color.CYAN, Color.GRAY, Color.MAGENTA,
					Color.LTGRAY, Color.GREEN, Color.WHITE, Color.YELLOW };

			@Override
			public int getCount() {
				return colours.length;
			}

			@Override
			public Fragment getItem(int position) {
				Fragment fragment = new ColourFragment();
				Bundle args = new Bundle();
				args.putInt("colour", colours[position]);
				args.putInt("identifier", position);
				fragment.setArguments(args);
				return fragment;
			}
		};

		PagerAdapter wrappedAdapter = new InfinitePagerAdapter(adapter);

		// actually an InfiniteViewPager
		viewPager.setAdapter(adapter);
		lv_main.addHeaderView(headview);
		lv_main.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, msg));
//		lv_main.setViewPager(viewPager);

	}

}
