package com.example.testphoto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;

import exceptions.MemoryNotFoundException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MyPhoto extends Activity {
	private String text;
	private String isCashed;
	private LinearLayout imgBox;
	private GridView grid;
	private EditText edit;
	private ImageAdapter imageAdapter;
	private String folderToSave = Environment.getExternalStorageDirectory()
			.toString();
	private Downloader downloader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_photo);
		edit = (EditText) findViewById(R.id.editText1);

		// imgBox = (LinearLayout)findViewById(R.id.img);
		grid = (GridView) findViewById(R.id.gridView1);
		imageAdapter = new ImageAdapter(this,
				android.R.layout.simple_list_item_1);
		text = getIntent().getExtras().getString("words");
		isCashed = getIntent().getExtras().getString("cash");

		// choose between yandex google bing
		downloader = new ImageDownloader();
		if (isCashed.equals("true")) {
			loadFromCash(text);
		} else {
			InternetOperations in = new InternetOperations();
			in.execute();
		}

	}

	public class InternetOperations extends
			AsyncTask<Void, Void, ArrayList<Drawable>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected ArrayList<Drawable> doInBackground(Void... voids) {
			// query();

			try {
				return downloader.loadPics(text);
			} catch (IOException e) {
			} catch (JSONException e) {
			}

			return new ArrayList<Drawable>();
		}

		@Override
		protected void onPostExecute(ArrayList<Drawable> pics) {
			super.onPostExecute(pics);			
			showPicture(pics);
		}
	}

	private void showPicture(ArrayList<Drawable> picture) {
		imageAdapter.clearPhoto();
		imageAdapter.addPhoto(picture);
		grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View p,
					int position, long id) {
				// final Intent intent = new Intent(MainActivity.this,
				// big_photo.class);
				// intent.putExtra("key", position);
				// startActivity(intent);

			}
		});
		imageAdapter.clear();

		for (int i = 0; i < imageAdapter.size(); i++)
			imageAdapter.add(i);
		imageAdapter.notifyDataSetChanged();
		grid.setAdapter(imageAdapter);
		for (Drawable i : picture) {
			try {
				writeImageFile(((BitmapDrawable) i).getBitmap());

			} catch (MemoryNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void writeImageFile(Bitmap bitmap) throws MemoryNotFoundException {
		FileOutputStream fOut = null;
		try {

			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date());
			// checkMemory(bitmap);
			File file = new File(folderToSave, timeStamp + ".jpg");
			fOut = new FileOutputStream(file);
			edit.setText(file.getAbsolutePath());
			bitmap.compress(CompressFormat.JPEG, 85, fOut);
			fOut.flush();
			fOut.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadFromCash(String text) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
