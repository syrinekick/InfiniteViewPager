package com.example.testproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class SampleActivity extends Activity {

	 
	 // we name the left, middle and right page
	 private static final int PAGE_LEFT = 0;
	 private static final int PAGE_MIDDLE = 1;
	 private static final int PAGE_RIGHT = 2; 
	 
	 
	 private LayoutInflater mInflater;
	 private int mSelectedPageIndex = 1;
	// we save each page in a model
	 private PageModel[] mPageModel = new PageModel[3];
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.sample_main);
	  // initializing the model
	  initPageModel();
	  
	  mInflater = getLayoutInflater();
	  MyagerAdaper adapter = new MyagerAdaper();
	    
	  final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
	  viewPager.setAdapter(adapter);
	  // we dont want any smoothscroll. This enables us to switch the page
	  // without the user notifiying this
	  viewPager.setCurrentItem(PAGE_MIDDLE, false);
	  
	  viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
	   
	   @Override
	   public void onPageSelected(int position) {
	    mSelectedPageIndex = position;
	   }
	   
	   @Override
	   public void onPageScrolled(int arg0, float arg1, int arg2) { }
	   
	   @Override
	   public void onPageScrollStateChanged(int state) {
	    if (state == ViewPager.SCROLL_STATE_IDLE) {
	     
	     final PageModel leftPage = mPageModel[PAGE_LEFT];
	     final PageModel middlePage = mPageModel[PAGE_MIDDLE];
	     final PageModel rightPage = mPageModel[PAGE_RIGHT];
	     
	     final int oldLeftIndex = leftPage.getIndex();
	     final int oldMiddleIndex = middlePage.getIndex();
	     final int oldRightIndex = rightPage.getIndex();
	     
	     // user swiped to right direction --> left page
	     if (mSelectedPageIndex == PAGE_LEFT) {
	       
	      // moving each page content one page to the right
	      leftPage.setIndex(oldLeftIndex - 1);
	      middlePage.setIndex(oldLeftIndex);
	      rightPage.setIndex(oldMiddleIndex);
	      
	      setContent(PAGE_RIGHT);
	      setContent(PAGE_MIDDLE);
	      setContent(PAGE_LEFT);
	      
	     // user swiped to left direction --> right page
	     } else if (mSelectedPageIndex == PAGE_RIGHT) {

	      leftPage.setIndex(oldMiddleIndex);      
	      middlePage.setIndex(oldRightIndex);
	      rightPage.setIndex(oldRightIndex + 1);

	      setContent(PAGE_LEFT);
	      setContent(PAGE_MIDDLE);
	      setContent(PAGE_RIGHT);
	     }
	     viewPager.setCurrentItem(PAGE_MIDDLE, false);
	    }
	   }
	  });
	 }
	 
	 private void setContent(int index) {
	  final PageModel model =  mPageModel[index];
	  model.textView.setText(model.getText());
	 }

	 private void initPageModel() {
	  for (int i = 0; i < mPageModel.length; i++) {
	   // initing the pagemodel with indexes of -1, 0 and 1
	   mPageModel[i] = new PageModel(i - 1);
	  }  
	 }

	 private class MyagerAdaper extends PagerAdapter {

	  @Override
	  public int getItemPosition(Object object) {
	   return POSITION_NONE;
	  }

	  @Override
	  public void destroyItem(ViewGroup container, int position, Object object) {
	   container.removeView((View) object);
	  }

	  @Override
	  public int getCount() {
	   // we only need three pages
	   return 3;
	  }

	  @Override
	  public Object instantiateItem(ViewGroup container, int position) {
	   TextView textView = (TextView)mInflater.inflate(R.layout.sample_text, null);
	   PageModel currentPage = mPageModel[position];
	   currentPage.textView = textView;
	   textView.setText(currentPage.getText());
	   container.addView(textView);
	   return textView;
	  }

	  @Override
	  public boolean isViewFromObject(View view, Object obj) {
	   return view == obj;
	  }
	 }
}
