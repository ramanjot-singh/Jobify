package com.example.myproject;

import company.CoNavig;

import js.JsNavig;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	ImageView js, co;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		js = (ImageView) findViewById(R.id.imageView1);
		co = (ImageView) findViewById(R.id.imageView2);
		js.setOnClickListener(this);
		co.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == js.getId()) {
			Intent i = new Intent(MainActivity.this, LoginRegisterDual.class);
			i.putExtra("context", "js");
			startActivity(i);
		} else if (v.getId() == co.getId()) {
			Intent i = new Intent(MainActivity.this, LoginRegisterDual.class);
			i.putExtra("context", "co");
			startActivity(i);
		}

	}
}
