package com.example.testphoto;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends ArrayAdapter<Integer> {
	Context mContext;
	ArrayList<Bitmap>imageBitmap;
	public ImageAdapter(Context context, int resource) {
		super(context, resource);
		mContext = context;
		imageBitmap = new ArrayList<Bitmap>();
	}
	
	public void add(Bitmap i) {
		imageBitmap.add(i);
	}
	public void clearPhoto() {
		imageBitmap.clear();
	}
	public void addPhoto(ArrayList<Drawable> photo) {
		for (Drawable i : photo) {
			if (i != null) {
				imageBitmap.add(((BitmapDrawable) i).getBitmap());
			}
			
		}
	}
	@Override    
 	public View getView(int position, View convertView, ViewGroup parent) {
    	ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} 
		else {
			imageView = (ImageView) convertView;
		}		
		if (position < imageBitmap.size()) {
			imageView.setImageBitmap(imageBitmap.get(position));
		} 
		else { 			
			imageView.setImageBitmap(imageBitmap.get(0));
		}
		
		return imageView;
	}
	public int size() {
		// TODO Auto-generated method stub
		return imageBitmap.size();
	}
		
		

}