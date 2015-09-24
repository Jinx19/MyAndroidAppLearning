package com.example.androidsplashscreennetwork;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashScreen extends Activity {
	private static int SPLASH_TIME_OUT = 3000;
	private static String URL = "http://api.androidhive.info/game/game_stats.json";
	private String now_playing, earned;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		new PrefetchData().execute();
	}

	private class PrefetchData extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected Void doInBackground(Void... params) {
			JsonParse jsonParse = new JsonParse();
			String json = jsonParse.getJSONFromUrl(URL);
			if (json != null) {
				try {
					JSONObject jObj = new JSONObject(json)
							.getJSONObject("game_stat");
					now_playing = jObj.getString("now_playing");
					earned = jObj.getString("earned");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Intent i = new Intent(SplashScreen.this, MainActivity.class);
			i.putExtra("now_playing", now_playing);
			i.putExtra("earned", earned);
			startActivity(i);

			// close this activity
			finish();
		}

	}
}
