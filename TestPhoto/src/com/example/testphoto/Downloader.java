package com.example.testphoto;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.graphics.drawable.Drawable;

public interface Downloader {
	public ArrayList<Drawable> loadPics(String text) throws JSONException, IOException;

}
