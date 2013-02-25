package com.example.testproject;

import java.util.Timer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class TestActivity extends FragmentActivity {
	private ViewPager viewPager;
	private MyListView lv_main;
	private final String msg[] = { "one", "two", "three", "four", "five",
			"six", "seven" };
	private FrameLayout frameLayout;
	private int frameheight;
	private int window_width;
	int i;
	private Timer timer;
	private LayoutInflater layoutInflater;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		window_width = (int) getResources().getDimension(R.dimen.window_width);
		initView();
		
//		Log.i("SYN", "childcount "+viewPager.getChildCount());
//		Log.i("SYN", "getCurrentItem "+viewPager.getCurrentItem());
		
		
//
//		timer = new Timer(true);
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						int index = viewPager.getCurrentItem();
//						if (index == arrayList.size() - 1)
//							index = 0;
//						else
//							index++;
//						viewPager.setCurrentItem(index);
//
//					}
//				});
//			}
//		}, 5000, 5000);

		/***
		 * viewpager
		 * 
		 * PageChangeListener
		 */
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
//				draw_Point(arg0);
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
			
		});
	}
	
	
	
	
	void initHeadImage() {
		layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View headview = layoutInflater.inflate(R.layout.head_image, null);
		viewPager = (ViewPager) headview.findViewById(R.id.viewpager);
		frameLayout = (FrameLayout) headview.findViewById(R.id.fl_main);
		initPagerChild();
		LayoutParams layoutParams = frameLayout.getLayoutParams();
		layoutParams.height = frameheight;
		frameLayout.setLayoutParams(layoutParams);
		PagerAdapter adapter = new FragmentPagerAdapter(
				getSupportFragmentManager()) {
			int[] colours = new int[] { Color.CYAN, Color.GRAY, Color.MAGENTA,
					Color.MAGENTA,
					};

			@Override
			public int getCount() {
				return colours.length;
			}
			

			@Override
			public Fragment getItem(int position) {
				Log.e("SYN", "fragment position "+position);
				Fragment fragment = new ColourFragment();
				Bundle args = new Bundle();
				args.putInt("colour", colours[position]);
				args.putInt("identifier", position);
				fragment.setArguments(args);
				return fragment;
			}
		};
		
		
		//PagerAdapter wrappedAdapter = new InfinitePagerAdapter(adapter);
		viewPager.setAdapter(adapter);
//		draw_Point(0);// 
		lv_main.addHeaderView(headview);// 
		lv_main.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, msg));
		lv_main.setViewPager(viewPager);

	}
	
	

	void initPagerChild() {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.a);
			Bitmap bitmap2 = getBitmap(bitmap, window_width);
			frameheight = bitmap2.getHeight();// 
//		initPoint();
	}
	Bitmap getBitmap(Bitmap bitmap, int width) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scale = (float) width / w;
		matrix.postScale(scale, scale);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}
	
	
	void initView() {
		setContentView(R.layout.main);
		lv_main = (MyListView) findViewById(R.id.lv_main);
		lv_main.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});
		initHeadImage();

	}
	

}
