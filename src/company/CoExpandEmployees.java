package company;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.connection;

public class CoExpandEmployees extends Activity {
	ListView l1;
	SimpleAdapter s1;
	ArrayList<HashMap<String, String>> a1;

	String s[] = { "FIRST", "SECOND" };
	String id[] = { "name", "exp_salary", "xp", "skills" };
	int cnt[] = { R.id.textView1, R.id.textView2, R.id.textView3,
			R.id.textView4 };
	String aa;

	String user[] = new String[] { "raman.2020@gmail.com",
			"raman.2094@gmail.com", "raman.2094@yahoo.in" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_co_expand_employees);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		aa = getIntent().getStringExtra("array");
		// Toast.makeText(this, "array" + aa, 100).show();
		l1 = (ListView) findViewById(R.id.l_34);
		a1 = new ArrayList<HashMap<String, String>>();

		JSONArray ja;
		try {
			ja = new JSONArray(aa);

			for (int i = 0; i < ja.length(); i++) {
				JSONObject json;
				json = ja.getJSONObject(i);
				this.get(json.getString("js_username"));
				s1 = new SimpleAdapter(this, a1, R.layout.co_search_js_design,
						id, cnt);
				l1.setAdapter(s1);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.co_expand_employees, menu);
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

	public void get(String user) {

		try {

			connection con = new connection();
			String path = con.path
					+ "searchJs_acc_to_expand_employees.php?js_username="
					+ URLEncoder.encode(user, "UTF-8");
			HttpGet hg = new HttpGet(path);
			HttpResponse hr = con.hp.execute(hg);

			int th = hr.getStatusLine().getStatusCode();
			if (th == 200) {
				String s = EntityUtils.toString(hr.getEntity()).trim();
				if (s.equals("")) {
					Toast.makeText(this, "Sorry!No one applied ", 100).show();
				} else {
					Toast.makeText(this, s, 1000).show();
					String rs = "";

					JSONArray ja = new JSONArray(s);
					for (int i = 0; i < ja.length(); i++) {
						JSONObject json = ja.getJSONObject(i);
						rs = rs + json.toString();
						HashMap<String, String> h2 = new HashMap<String, String>();
						h2.put("name", json.getString("name"));
						h2.put("exp_salary", json.getString("exp_salary"));
						h2.put("xp", json.getString("xp"));
						h2.put("skills", json.getString("skills"));
						a1.add(h2);

					}
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
}
