package com.example.testproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Example Fragment class that shows an identifier inside a TextView.
 * 
 */
public class ColourFragment extends Fragment {

    private int identifier;
    private int colour;
    private ImageView image;
    private TextView textTitle;
    private ImageView imgIndicator1;
    private ImageView imgIndicator2;
    private ImageView imgIndicator3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	Bundle args = getArguments();
	colour = args.getInt("colour");
	identifier = args.getInt("identifier");
    }

    @Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		
	}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.overlay_layout, container, false);
	image= (ImageView) view.findViewById(R.id.imageView1);
	textTitle = (TextView) view.findViewById(R.id.text);
	imgIndicator1= (ImageView) view.findViewById(R.id.pager_indicator1);
	imgIndicator2= (ImageView) view.findViewById(R.id.pager_indicator2);
	imgIndicator3= (ImageView) view.findViewById(R.id.pager_indicator3);
	switch (identifier) {
	case 0:
		image.setImageResource(R.drawable.a);
		imgIndicator1.setImageResource(R.drawable.indicator_focused);
		imgIndicator2.setImageResource(R.drawable.indicator);
		imgIndicator3.setImageResource(R.drawable.indicator);
		textTitle.setText("image1 yesss");
		break;
	case 1:
		
		image.setImageResource(R.drawable.b);
		imgIndicator1.setImageResource(R.drawable.indicator);
		imgIndicator2.setImageResource(R.drawable.indicator_focused);
		imgIndicator3.setImageResource(R.drawable.indicator);
		textTitle.setText("image2 yesss");
		
		break;
	case 2 :
		image.setImageResource(R.drawable.c);
		imgIndicator1.setImageResource(R.drawable.indicator);
		imgIndicator2.setImageResource(R.drawable.indicator);
		imgIndicator3.setImageResource(R.drawable.indicator_focused);
		textTitle.setText("image3 yesss");
		
		break;
	default:
		
		break;
	}
	view.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
Toast.makeText(getActivity(),"yay"+ identifier, Toast.LENGTH_SHORT).show();
			
		}
	});
	return view;
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("dummy", true);
    }
}
