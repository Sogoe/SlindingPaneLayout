package com.sogoe.slidingpane;

import android.R.anim;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v4.widget.SlidingPaneLayout.PanelSlideListener;
import android.view.View;

public class MainActivity extends Activity {
	private ObjectAnimator mainAnim;
	private ObjectAnimator backAnim;
	private final int DURATION = 300;
	private View main;
	private View back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SlidingPaneLayout spl = (SlidingPaneLayout) findViewById(R.id.slidingpane);
		main = findViewById(R.id.main_view);
		back = findViewById(R.id.back_view);

		initAnimations();
		
		spl.setSliderFadeColor(Color.TRANSPARENT);
		spl.setCoveredFadeColor(Color.TRANSPARENT);		
		spl.setPanelSlideListener(new PanelSlideListener() {
			
			@Override
			public void onPanelSlide(View view, float offset) {

				mainAnim.setCurrentPlayTime((long) (offset * DURATION));
				backAnim.setCurrentPlayTime((long) (offset * DURATION));
			}
			
			@Override
			public void onPanelOpened(View view) {
				//do some things
			}
			
			@Override
			public void onPanelClosed(View view) {
				//do some things
			}
		});
	}
	
	private void initAnimations() {
		PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.75f);
		PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.75f);
		mainAnim = ObjectAnimator.ofPropertyValuesHolder(main, scaleX, scaleY).setDuration(DURATION);
		
		PropertyValuesHolder fade = PropertyValuesHolder.ofFloat("alpha", 0.2f, 1.f);
		PropertyValuesHolder scaleX_back = PropertyValuesHolder.ofFloat("scaleX", 0.75f, 1.f);
		PropertyValuesHolder scaleY_back = PropertyValuesHolder.ofFloat("scaleY", 0.75f, 1.f);
		PropertyValuesHolder translationX_back = PropertyValuesHolder.ofFloat("translationX", -100.f, 0.f);
		backAnim = ObjectAnimator.ofPropertyValuesHolder(back, scaleX_back, scaleY_back, translationX_back, fade).setDuration(DURATION);
		
	}
}
