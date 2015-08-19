package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class LoginRegisterDual extends Activity {
	String where;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register_dual);
		where = getIntent().getStringExtra("context");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_register_dual, menu);
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

	public void login(View v) {
		if (where.equals("js")) {
			Intent i = new Intent(LoginRegisterDual.this, EmpLogin.class);
			startActivity(i);
		} else {
			Intent i = new Intent(LoginRegisterDual.this, CompanyLogin.class);
			startActivity(i);
		}
	}

	public void register(View v) {
		if (where.equals("js")) {
			Intent i = new Intent(LoginRegisterDual.this, EmpRegister.class);
			startActivity(i);
		} else {
			Intent i = new Intent(LoginRegisterDual.this, CompanyRegister.class);
			startActivity(i);
		}
	}
}
