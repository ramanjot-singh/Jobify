package com.example.myproject;

import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmpRegister extends ActionBarActivity {
	EditText email, name, mobile, password, confirmPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emp_register);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		email = (EditText) findViewById(R.id.editText2);
		name = (EditText) findViewById(R.id.editText1);
		mobile = (EditText) findViewById(R.id.editText3);
		password = (EditText) findViewById(R.id.editText4);
		confirmPass = (EditText) findViewById(R.id.editText5);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emp_register, menu);
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

	public void empRegister(View v) {

		if (name.getText().toString().equals("")
				|| (mobile.getText().toString().equals("")
						|| (email.getText().toString().equals("")) || (password
						.getText().toString().equals("")))
				|| (confirmPass.getText().toString().equals(""))) {
			Toast.makeText(this, "Not filled", 1000).show();
		} else if (!(confirmPass.getText().toString().equals(password.getText()
				.toString()))) {
			Toast.makeText(this, "Enter same password", 1000).show();
		} else {
			try {
				connection con = new connection();
				String path = con.path
						+ "empRegister.php?name="
						+ URLEncoder.encode(name.getText().toString(), "UTF-8")
						+ "&mobile="
						+ URLEncoder.encode(mobile.getText().toString(),
								"UTF-8")
						+ "&email="
						+ URLEncoder
								.encode(email.getText().toString(), "UTF-8")
						+ "&password="
						+ URLEncoder.encode(password.getText().toString(),
								"UTF-8");
				HttpGet hg = new HttpGet(path);

				HttpResponse hr = con.hp.execute(hg);

				int th = hr.getStatusLine().getStatusCode();
				if (th == 200) {
					String ss = EntityUtils.toString(hr.getEntity()).trim();
					if (ss.equals("")) {
						Toast.makeText(this, "added successfully", 100).show();
						Intent i = new Intent(this, js.JsNavig.class);
						i.putExtra("username", email.getText().toString());
						i.putExtra("name", name.getText().toString());
						startActivity(i);
					} else {
						Toast.makeText(this, ss, 1000).show();
					}

				}

				else {
					Toast.makeText(
							getApplicationContext(),
							"check your internet connecttion.Server error:"
									+ th, Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(this, e.toString(), 1000).show();
			}
		}

	}
}
