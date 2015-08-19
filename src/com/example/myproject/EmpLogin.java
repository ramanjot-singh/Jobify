package com.example.myproject;

import java.net.URLEncoder;
import java.util.HashMap;

import js.JsNavig;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmpLogin extends Activity {
	EditText email, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emp_login);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		email = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emp_login, menu);
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

	public void login_to_table(View v) {
		try {

			connection con = new connection();
			String path = con.path + "js_login.php?email="
					+ URLEncoder.encode(email.getText().toString(), "UTF-8")
					+ "&password="
					+ URLEncoder.encode(password.getText().toString());
			HttpGet hg = new HttpGet(path);
			HttpResponse hr = con.hp.execute(hg);

			int th = hr.getStatusLine().getStatusCode();
			if (th == 200) {
				String s = EntityUtils.toString(hr.getEntity()).trim();

				Toast.makeText(this, s, 1000).show();
				if (s.equals("member Matched")) {
					Toast.makeText(getApplicationContext(), "[][][][]", 1000)
							.show();
					Intent i = new Intent(getApplicationContext(),
							JsNavig.class);
					i.putExtra("js_username", email.getText().toString());
					this.finish();
					startActivity(i);
				} else if (s.equals("Please Register")) {
					Toast.makeText(this, "Please Register", 100).show();
				}

			}

			else {
				Toast.makeText(getApplicationContext(),
						"check your internet connecttion.Server error:" + th,
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), 1000).show();
		}
	}

	public void RegisterNow(View v) {
		Intent i = new Intent(this, EmpRegister.class);
		this.finish();
		startActivity(i);

	}
}
