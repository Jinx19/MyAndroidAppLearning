package com.example.androidsplashscreennetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView txtPlayCount, txtEarned;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		txtPlayCount = (TextView) findViewById(R.id.txtNowPlaying);
		txtEarned = (TextView) findViewById(R.id.txtEarned);

		Intent i = getIntent();
		String now = i.getStringExtra("now_playing");
		String e = i.getStringExtra("earned");

		txtPlayCount.setText(now);
		txtEarned.setText(e);
	}

}
