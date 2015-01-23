package com.example.testphoto;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText field;
	private Button search;
	private final String temp ="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		field = (EditText)findViewById(R.id.field);
		search = (Button)findViewById(R.id.search);
		final Intent intent = new Intent(MainActivity.this, MyPhoto.class);
		
		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = field.getText().toString();
				if (temp.equals(text)) {
					//TOAST we enter null string
				} else {
					if (isCashed(text)) {
						intent.putExtra("cash", "true");
					} else {
						intent.putExtra("cash", "false");
					}
					intent.putExtra("words", field.getText().toString());
					startActivity(intent);
				}
				
			}
		});
		
	}
	
	private boolean isCashed(String text) {
		return false;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
