package js;

import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.connection;

public class JsExpandJob extends Activity {
	TextView title;
	String js_username, id;
	TextView salary, d_added, xp, description, skills, location, company;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_js_expand_job);
		title = (TextView) findViewById(R.id.title);
		title.setText("opening for java Developer for MNC Company in Mumbai");
		id = getIntent().getStringExtra("id");
		Toast.makeText(this, "" + id, 1000).show();
		js_username = getIntent().getStringExtra("js_username");
		salary = (TextView) findViewById(R.id.salary);
		d_added = (TextView) findViewById(R.id.date);
		xp = (TextView) findViewById(R.id.xp);
		description = (TextView) findViewById(R.id.textView7);
		skills = (TextView) findViewById(R.id.skills);
		location = (TextView) findViewById(R.id.location);
		company = (TextView) findViewById(R.id.industry);

		try {
			connection con = new connection();
			String path = con.path + "job_details_on_base_of_id.php?id="
					+ URLEncoder.encode(id, "UTF-8");

			HttpGet hg = new HttpGet(path);

			HttpResponse hr = con.hp.execute(hg);

			int th = hr.getStatusLine().getStatusCode();
			if (th == 200) {
				String ss = EntityUtils.toString(hr.getEntity()).trim();
				Toast.makeText(this, ss, 1000).show();
				String rs = "";

				JSONArray ja = new JSONArray(ss);
				for (int i = 0; i < ja.length(); i++) {
					JSONObject json = ja.getJSONObject(i);
					rs = rs + json.toString();
					Toast.makeText(this, rs, 1000).show();
					rs = "";
					title.setText(json.getString("title"));
					company.setText(json.getString("company"));
					salary.setText(json.getString("salary"));
					description.setText(json.getString("description"));
					location.setText(json.getString("city"));
					xp.setText(json.getString("xp"));
					d_added.setText(json.getString("d_added"));

				}
			}

			else {
				Toast.makeText(this,
						"check your internet connecttion.Server error:" + th,
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), 1000).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.js_expand_job, menu);
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

	public void add_to_table(View v) {
		try {
			connection con = new connection();
			String path = con.path + "js_id_username_into_table.php?id="
					+ URLEncoder.encode(id, "UTF-8") + "&js_username="
					+ URLEncoder.encode(js_username, "UTF-8");
			HttpGet hg = new HttpGet(path);

			HttpResponse hr = con.hp.execute(hg);

			int th = hr.getStatusLine().getStatusCode();
			if (th == 200) {
				String ss = EntityUtils.toString(hr.getEntity()).trim();
				if (ss.equals("")) {
					Toast.makeText(getApplicationContext(), "Applied", 100)
							.show();
				} else {
					Toast.makeText(getApplicationContext(), ss, 1000).show();
				}
			}

			else {
				Toast.makeText(getApplicationContext(),
						"check your internet connecttion.Server error:" + th,
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.toString(), 1000).show();
		}

	}

	public void my(View v) {
		Toast.makeText(this, "[]", 1000).show();
	}
}
