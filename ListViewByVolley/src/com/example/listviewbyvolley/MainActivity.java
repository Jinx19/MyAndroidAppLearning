package com.example.listviewbyvolley;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.listviewbyvolley.adapter.CustomListAdapter;
import com.example.listviewbyvolley.app.AppController;
import com.example.listviewbyvolley.model.Movie;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	private static final String url = "http://followada.com/api/json/movies.json";

	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, movieList);
		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading....");
		pDialog.show();

		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#1b1b1b")));
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {

					@Override
					public void onResponse(JSONArray response) {
						// TODO Auto-generated method stub
						Log.d(TAG, response.toString());
						hidePDialog();

						for (int i = 0; i < response.length(); i++) {
							try {
								JSONObject obj = response.getJSONObject(i);
								Movie movie = new Movie();
								movie.setTitle(obj.getString("title"));
								movie.setThumbnailUrl(obj.getString("image"));
								movie.setRating(((Number) obj.get("rating"))
										.doubleValue());
								movie.setYear(obj.getInt("releaseYear"));

								JSONArray genreArry = obj.getJSONArray("genre");
								ArrayList<String> genre = new ArrayList<String>();
								for (int j = 0; j < genreArry.length(); j++) {
									genre.add((String) genreArry.get(j));
								}
								movie.setGenre(genre);

								// adding movie to movies array
								movieList.add(movie);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						VolleyLog.d(TAG, "Error:" + error.getMessage());
						hidePDialog();
					}
				});

		AppController.getInstance().addToRequestQueue(movieReq, "");

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		hidePDialog();
	}

	protected void hidePDialog() {
		// TODO Auto-generated method stub
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}
}
