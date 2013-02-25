package com.example.testproject;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MyListView extends ListView {
	private GestureDetector mGestureDetector;
	View.OnTouchListener mGestureListener;
	private Context context;
	private ViewPager viewPager;

	public void setViewPager(ViewPager viewPager) {
		this.viewPager = viewPager;
	}

	public MyListView(Context context) {
		super(context);
		this.context = context;
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		mGestureDetector = new GestureDetector(new YScrollDetector());
		setFadingEdgeLength(0);

	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		super.onInterceptTouchEvent(ev);
		return mGestureDetector.onTouchEvent(ev);
	}

	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			if (Math.abs(distanceY) >= Math.abs(distanceX)) {
				Log.e("jj", "иооб....");
				return true;
			}
			Log.e("jj", "вСср....");
			return false;
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			Toast.makeText(context, "м╪" + viewPager.getCurrentItem(), 1).show();
			return super.onSingleTapUp(e);
		}

	}
}